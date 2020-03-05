package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    private WebDriver webDriver;

    public WebDriver setWebDriver(){
        webDriver=new ChromeDriver();
        return webDriver;
    }
}
