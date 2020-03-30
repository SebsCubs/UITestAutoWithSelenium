package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageFather {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    public PageFather(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void insertText(String text, WebElement field) {
        field.click();
        field.clear();
        field.sendKeys(text);
    }


}
