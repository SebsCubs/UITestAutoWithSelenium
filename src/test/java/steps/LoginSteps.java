package steps;

import helpers.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import pageObjects.HomePage;

public class LoginSteps {
    HomePage homePage = new HomePage(Hooks.getWebDriver());
    HomePage validationPage;

    @Given("The login dialog is open")
    public void theLoginDialogIsOpen() {
        homePage = homePage.openLoginDialog();
    }

    @When("The username and password are entered")
    public void theUsernameAndPasswordAreEntered() {
        validationPage = homePage.login("fallatest@yandex.com", "FallabellaTest123");
    }

    @Then("The user is signed in and the name appears in the top right corner")
    public void theUserIsSignedInAndTheNameAppearsInTheTopRightCorner() {
        MatcherAssert.assertThat("Error: The login was unsuccessful",
                validationPage.getUserWelcome().contains("Bienvenid@,"), CoreMatchers.equalTo(true));

    }

    @When("The {string} and {string} is entered")
    public void theAndIsEntered(String username, String password) {
        validationPage = homePage.login(username, password);
    }

    @Then("An error prompts stating the credentials are invalid")
    public void anErrorPromptsStatingTheCredentialsAreInvalid() {
        MatcherAssert.assertThat("The login error message is not available",
                validationPage.getLoginErrorText().contains("E-mail o clave incorrecta."), CoreMatchers.equalTo(true));
    }

    @Given("An user account is signed in")
    public void anUserAccountIsSignedIn() {
        homePage = homePage.openLoginDialog();
        homePage = homePage.login("fallatest@yandex.com", "FallabellaTest123");
        MatcherAssert.assertThat("Error: The login was unsuccessful",
                homePage.getUserWelcome().contains("Bienvenid@,"), CoreMatchers.equalTo(true));

    }

    @When("The account is signed out")
    public void theAccountIsSignedOut() {
        validationPage = homePage.logOut();
    }

    @Then("The user is not signed any more")
    public void theUserIsNotSignedAnyMore() {
        MatcherAssert.assertThat("Error: couldn't sign out",
                validationPage.getMiCuentaButtonText().contains("Inicia sesión"), CoreMatchers.equalTo(true));
    }
}
