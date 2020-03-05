package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageFather {
    private WebDriver webDriver;
    public PageFather(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public void insertText(String text, WebElement field){
        field.click();
        field.clear();
        field.sendKeys(text);
    }
}
