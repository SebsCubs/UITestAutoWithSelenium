package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage extends PageFather {
    @FindBy(id = "testId-Accordion-Precio")
    private WebElement precioDrop;
    @FindBy(id = "testId-range-from")
    private WebElement lowerPriceField;
    @FindBy(id = "testId-range-to")
    private WebElement higherPriceField;
    @FindBy(xpath = "//button[@class='jsx-3084763500']")
    private WebElement priceRangeButton;
    @FindBy(xpath = "//button[@class='jsx-693919786 chip']")
    private WebElement selectedFilter;
    @FindBy(xpath = "//img[@id='testId-pod-image-3624126']")
    private WebElement firstResult;


    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    public ResultsPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public ResultsPage filterByPrice(String lower, String higher) {
        webDriverWait = new WebDriverWait(webDriver, Long.parseLong("10"));

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("testId-Accordion-Precio")));
        precioDrop.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("jsx-3084763500")));
        insertText(lower, lowerPriceField);
        insertText(higher, higherPriceField);
        priceRangeButton.click();
        return this;
    }

    public ProductPage getFirstResult() {
        webDriverWait = new WebDriverWait(webDriver, Long.parseLong("10"));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//img[@id='testId-pod-image-3624126']")));
        firstResult.click();
        return new ProductPage(webDriver);
    }

    public String validateFiltering() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@class='jsx-693919786 chip']")));
        return selectedFilter.getText();
    }


}
