package utils;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager extends DriverManager{

    @Override
    public void createDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("test-type");
        options.addArguments("--headless");
        options.addArguments("start-maximized");
        options.addArguments("--no-sandbox");
        driver = new FirefoxDriver(options);
    }
}
