package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends PageFather {
    private By deleteButton = By.xpath("//main[@id='root']/descendant::div[@class='fb-prod-actionButton'][1]/a");
    private By emptyCartLabel = By.className("fb-order-status__empty-basket");

    public CartPage(WebDriver webDriver) {
        super(webDriver);
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
