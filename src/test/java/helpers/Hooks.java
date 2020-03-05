package helpers;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

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
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
                String testName = scenario.getName();
                scenario.embed(screenshot, "image/png", "photo");
                scenario.write(testName);
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        }
        webDriver.quit();
    }





    public static WebDriver getWebDriver() {
        return webDriver;
    }
}
