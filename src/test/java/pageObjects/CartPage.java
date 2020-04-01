package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends PageFather {
    private By deleteButton = By.xpath("//main[@id='root']/descendant::div[@class='fb-prod-actionButton'][1]/a");
    private By emptyCartLabel = By.className("fb-order-status__empty-basket");


    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    public CartPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, Long.parseLong("10"));
    }

    public CartPage deleteFirstItem() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(deleteButton));
        webDriver.findElement(deleteButton).click();
        return this;
    }

    public String getEmptyCartLabelText() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartLabel));
        return webDriver.findElement(emptyCartLabel).getText();
    }
}
