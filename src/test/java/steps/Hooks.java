package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {
    private static WebDriver webDriver;

    @Before
    public void setup() {
        // Set up driver factory
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--no-sandbox");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.navigate().to("https://www.falabella.com.co/falabella-co/");
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





    public WebDriver getWebDriver() {
        return webDriver;
    }
}
