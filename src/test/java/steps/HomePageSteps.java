package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import pageObjects.HomePage;

public class HomePageSteps {

    private WebDriver webDriver;
    private HomePage homePageInstance;
    public HomePageSteps(Hooks hooks){
        webDriver = hooks.getWebDriver();
        homePageInstance = new HomePage(webDriver);
    }


    @Given("a search for {string} is made")
    public void aSearchForIsMade(String searchQuery) {
        homePageInstance.search(searchQuery);
    }

    @And("an item page is opened")
    public void anItemPageIsOpened() {
        homePageInstance.goToFirstResult("libros");
    }

    @And("at least one item is in the cart")
    public void atLeastOneItemIsInTheCart() {
        homePageInstance.addFirstResultToCart("libros");
    }

    @Given("the registration form is open")
    public void theRegistrationFormIsOpen() {
        homePageInstance.openRegisterForm();
    }

    @Given("the login dialog is open")
    public void theLoginDialogIsOpen() {
        homePageInstance.openLoginDialog();
    }

    @When("the username and password are entered")
    public void theUsernameAndPasswordAreEntered() {
        String fields[] = getCredentialsFromEnv();
        homePageInstance = homePageInstance.login(fields[3],fields[4]);
    }

    @Then("the user is signed in and the name appears in the top right corner")
    public void theUserIsSignedInAndTheNameAppearsInTheTopRightCorner() {
        String welcomeValidation = homePageInstance.getUserWelcome();
        MatcherAssert.assertThat("Error: The login was unsuccessful",
                welcomeValidation.contains("Bienvenid@,"), CoreMatchers.equalTo(true));
    }

    @When("the {string} and {string} are entered")
    public void theAndAreEntered(String username, String password) {
        homePageInstance = homePageInstance.login(username,password);
    }

    @Then("an error prompts stating the credentials are invalid")
    public void anErrorPromptsStatingTheCredentialsAreInvalid() {
        MatcherAssert.assertThat("The login error message is not available",
                homePageInstance.getLoginErrorText().contains("E-mail o clave incorrecta."), CoreMatchers.equalTo(true));
    }

    @Given("an user account is signed in")
    public void anUserAccountIsSignedIn() {
        String fields[] = getCredentialsFromEnv();
        homePageInstance.openLoginDialog();
        homePageInstance.login(fields[3],fields[4]);
    }

    @When("the account is signed out")
    public void theAccountIsSignedOut() {
            homePageInstance.logOut();
    }

    @Then("the user is not signed any more")
    public void theUserIsNotSignedAnyMore() {
        MatcherAssert.assertThat("Error: couldn't sign out",
                homePageInstance.getMiCuentaButtonText().contains("Inicia sesi"), CoreMatchers.equalTo(true));
    }

    private String[] getCredentialsFromEnv(){
        String credentials = System.getenv("UITestFields");
        String fields[] = credentials.split(",");
        return fields;
    }
}
