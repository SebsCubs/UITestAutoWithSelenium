package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.Hooks;

public class ProductPage extends PageFather {

    private By goToCartPopUpButton = By.xpath("//div[@class='doc-click-overlay']/descendant::a");
    private By addToCartButton = By.id("buttonForCustomers");
    private By confirmationLabel = By.xpath("//div[@id='__next']/descendant::div[@class='doc-click-overlay'][5]/descendant::span[3]");

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    public ProductPage(WebDriver webDriver){
        super(webDriver);
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, Long.parseLong("10"));
    }

    public ProductPage addItemToCart() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        webDriver.findElement(addToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(goToCartPopUpButton));
        return this;
    }

    public CartPage goToCartFromPopUp() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(confirmationLabel));
        webDriver.findElement(goToCartPopUpButton).click();
        return new CartPage(webDriver);
    }

    public String getConfirmationText() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(confirmationLabel));
        return webDriver.findElement(confirmationLabel).getText();
    }
}
