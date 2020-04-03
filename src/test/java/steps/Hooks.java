package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import utils.DriverManager;
import utils.DriverManagerFactory;
import utils.DriverType;

public class Hooks {
    private  WebDriver webDriver;
    private DriverManager driverManager;
    private DriverManagerFactory driverManagerFactory = new DriverManagerFactory();
    @Before
    public void setup() {
        driverManager = driverManagerFactory.getManager(DriverType.FIREFOX);
        webDriver = driverManager.getDriver();

        webDriver.manage().window().setPosition(new Point(0,0));
        webDriver.manage().window().setSize(new Dimension(1920,1080));
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
        driverManager.quitDriver();
    }





    public WebDriver getWebDriver() {
        return webDriver;
    }
}
