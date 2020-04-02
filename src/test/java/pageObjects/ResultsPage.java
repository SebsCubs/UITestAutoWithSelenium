package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResultsPage extends PageFather {
    @FindBy(id = "testId-Accordion-Precio")
    private WebElement precioDrop;
    @FindBy(id = "testId-range-from")
    private WebElement lowerPriceField;
    @FindBy(id = "testId-range-to")
    private WebElement higherPriceField;

    private By priceRangeButton = By.xpath("//button[@id='testId-Accordion-Precio']/following-sibling::div/descendant::button");
    private By selectedFilter = By.xpath("//div[@id='testId-SelectedFilters-container']/descendant::button[2]");
    private By firstResult = By.xpath("//div[@id='testId-searchResults-products']/child::div[1]");

    public ResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ResultsPage filterByPrice(String lower, String higher) {
        webDriverWait.until(ExpectedConditions.visibilityOf(precioDrop));
        precioDrop.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(priceRangeButton));
        insertText(lower, lowerPriceField);
        insertText(higher, higherPriceField);
        webDriver.findElement(priceRangeButton).click();
        return this;
    }

    public ProductPage  getFirstResult() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstResult));
        webDriver.findElement(firstResult).click();
        return new ProductPage(webDriver);
    }

    public String getFirstFilterText() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(selectedFilter));
        return webDriver.findElement(selectedFilter).getText();
    }
}
