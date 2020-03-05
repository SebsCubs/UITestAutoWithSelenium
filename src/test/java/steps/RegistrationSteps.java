package steps;

import helpers.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import pageObjects.HomePage;
import pageObjects.RegisterPage;

public class RegistrationSteps {
    RegisterPage registerPage;
    HomePage homePage = new HomePage(Hooks.getWebDriver());
    HomePage validationPage;
    String fault_type;

    @Given("The registration form is open")
    public void theRegistrationFormIsOpen() {
        registerPage = homePage.openRegisterForm();

    }

    @When("The registration is attempted with a {string}")
    public void theRegistrationIsAttemptedWithA(String fault_type) {
        String[] fields = {"Sebastian", "Cubitos", "Poe", "fallatest@yandex.com", "FalaTest123", "FalaTest123",
                "10205894632", "12", "Jul", "2000", "3111234567"};
        switch (fault_type) {
            case "2":
                fields[5] = "FalaTest125";
                break;
            case "3":
                fields[3] = "fallatest";
                break;
        }

        this.fault_type = fault_type;
        registerPage = registerPage.faultyRegister(fault_type, fields);
    }


    @Then("The page reloads with an error is prompted")
    public void thePageReloadsWithAnErrorIsPrompted() {
        switch (fault_type) {
            case "1":
                MatcherAssert.assertThat("Error: The fault register didn't go well",
                        registerPage.validateFaultRegistration("1").contains("Debes ingresar un celular"),
                        CoreMatchers.equalTo(true));
                break;
            case "2":
                MatcherAssert.assertThat("Error: The fault register didn't go well",
                        registerPage.validateFaultRegistration("2").contains("La clave ingresada no coincide"),
                        CoreMatchers.equalTo(true));
                break;
            case "3":
                MatcherAssert.assertThat("Error: The fault register didn't go well",
                        registerPage.validateFaultRegistration("3").contains("Formato de email inválido"),
                        CoreMatchers.equalTo(true));
                break;

        }

    }
}
