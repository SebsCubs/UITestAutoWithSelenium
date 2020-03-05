package helpers;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private static WebDriver webDriver;

    @Before
    public void setup() {
        DriverFactory driverFactory = new DriverFactory();
        webDriver = driverFactory.setWebDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.falabella.com.co/falabella-co/");
    }

    @After
    public void teardown() {
        webDriver.quit();
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }
}
