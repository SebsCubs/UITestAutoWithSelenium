package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends PageFather {
    @FindBy(xpath = "//button[@class='jsx-1816208196 button button-primary button-primary-xtra-large']")
    private WebElement addToCartButton;
    @FindBy(xpath = "//span[@class='jsx-3049166186 label']")
    private WebElement confirmationLabel;
    @FindBy(xpath = "//a[@class='jsx-3840845563 button button-primary button- ']")
    private WebElement goToCartPopUpButton;


    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    public ProductPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public ProductPage addItemToCart() {
        webDriverWait = new WebDriverWait(webDriver, Long.parseLong("10"));

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@class='jsx-1816208196 button button-primary button-primary-xtra-large']")));
        addToCartButton.click();

        return this;
    }

    public CartPage goToCartFromPopUp() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@class='jsx-3840845563 button button-primary button- ']")));
        goToCartPopUpButton.click();
        return new CartPage(webDriver);
    }

    public String getConfirmationText() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='jsx-3049166186 label']")));
        return confirmationLabel.getText();
    }


}
