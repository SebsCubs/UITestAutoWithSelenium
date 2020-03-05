package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends PageFather {
    @FindBy(xpath = "//div[@class='fb-prod-actionButton']/a[text()='Eliminar']")
    private WebElement deleteButton;
    @FindBy(xpath = "//p[@class='fb-order-status__empty-basket']")
    private WebElement emptyCartLabel;


    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    public CartPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public CartPage deleteFirstItem() {
        webDriverWait = new WebDriverWait(webDriver, Long.parseLong("10"));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='fb-prod-actionButton']/a[text()='Eliminar']")));
        deleteButton.click();
        return this;
    }

    public String getEmptyCartLabelText() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[@class='fb-order-status__empty-basket']")));
        return emptyCartLabel.getText();
    }
}
