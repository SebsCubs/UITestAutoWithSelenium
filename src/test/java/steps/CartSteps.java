package steps;

import helpers.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.ResultsPage;

public class CartSteps {

    private HomePage homePage = new HomePage(Hooks.getWebDriver());
    private ResultsPage resultsPage;
    private HomePage validationPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @Given("An user is logged in")
    public void anUserIsLoggedIn() {
        homePage = homePage.openLoginDialog();
        validationPage = homePage.login("fallatest@yandex.com", "FallabellaTest123");

        MatcherAssert.assertThat("Error: The login was unsuccessful",
                validationPage.getUserWelcome().contains("Bienvenid@,"), CoreMatchers.equalTo(true));
    }

    @And("An item page is open")
    public void anItemPageIsOpen() {
        resultsPage = homePage.search("libros");
        productPage = resultsPage.getFirstResult();
    }

    @When("The item is added to the cart")
    public void theItemIsAddedToTheCart() {
        productPage = productPage.addItemToCart();
    }

    @Then("A pop-up is displayed with the confirmation")
    public void aPopUpIsDisplayedWithTheConfirmation() {
        MatcherAssert.assertThat("Error: The item wasn't added",
                productPage.getConfirmationText().contains("Producto(s) agregado(s) a la bolsa de compras"),
                CoreMatchers.equalTo(true));
    }

    @And("At least one item is in the cart")
    public void atLeastOneItemIsInTheCart() {
        resultsPage = homePage.search("libros");
        productPage = resultsPage.getFirstResult();
        productPage = productPage.addItemToCart();
        MatcherAssert.assertThat("Error: The item wasn't added",
                productPage.getConfirmationText().contains("Producto(s) agregado(s) a la bolsa de compras"),
                CoreMatchers.equalTo(true));
    }

    @And("The cart page is open")
    public void theCartPageIsOpen() {
        cartPage = productPage.goToCartFromPopUp();
    }

    @When("The first item is deleted from the cart")
    public void theFirstItemIsDeletedFromTheCart() {
        cartPage = cartPage.deleteFirstItem();
    }

    @Then("The cart has one less item")
    public void theCartHasOneLessItem() {
        MatcherAssert.assertThat("Error: The item didn't get deleted",
                cartPage.getEmptyCartLabelText().contains("Tu Bolsa de Compras está vacía."),
                CoreMatchers.equalTo(true));
    }
}
