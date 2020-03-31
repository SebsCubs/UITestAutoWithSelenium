package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import pageObjects.RegisterPage;

public class RegisterPageSteps {
    private String faultConversion;
    private RegisterPage registerPage;

    public RegisterPageSteps(Hooks hooks) {
        WebDriver webDriver = hooks.getWebDriver();
        registerPage = new RegisterPage(webDriver);
    }

    @When("the registration is attempted with a {string} error")
    public void theRegistrationIsAttemptedWithAError(String fault_type) {
        String credentials = System.getenv("UITestFields");
        String fields[] = credentials.split(",");
        switch (fault_type) {
            case "'Celular' is not filled":
                faultConversion = "1";
                break;
            case "The passwords don't match":
                fields[5] = "FalaTest125";
                faultConversion = "2";
                break;
            case "Invalid mail format":
                fields[3] = "fallatest";
                faultConversion = "3";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fault_type);
        }
        registerPage.faultyRegister(faultConversion, fields);
    }

    @Then("the page reloads with an error is prompted")
    public void thePageReloadsWithAnErrorIsPrompted() {
        switch (faultConversion) {
            case "1":
                MatcherAssert.assertThat("Error: The fault register didn't go well",
                        registerPage.getFaultLabelText("1").contains("Debes ingresar un celular"),
                        CoreMatchers.equalTo(true));
                break;
            case "2":
                MatcherAssert.assertThat("Error: The fault register didn't go well",
                        registerPage.getFaultLabelText("2").contains("La clave ingresada no coincide"),
                        CoreMatchers.equalTo(true));
                break;
            case "3":
                MatcherAssert.assertThat("Error: The fault register didn't go well",
                        registerPage.getFaultLabelText("3").contains("Formato de email inválido"),
                        CoreMatchers.equalTo(true));
                break;

        }

    }


}
