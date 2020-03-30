package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import pageObjects.ProductPage;

public class ProductPageSteps {
    private WebDriver webDriver;
    private ProductPage productPageInstance;
    public ProductPageSteps(Hooks hooks){
        webDriver = hooks.getWebDriver();
        productPageInstance = new ProductPage(webDriver);
    }


    @When("the item is added to the cart")
    public void theItemIsAddedToTheCart() {
        productPageInstance.addItemToCart();
    }

    @Then("a pop-up is displayed with the confirmation")
    public void aPopUpIsDisplayedWithTheConfirmation() {
        MatcherAssert.assertThat("Error: the item didn't get added",
                productPageInstance.getConfirmationText().contains("Producto(s)"), CoreMatchers.equalTo(true));
    }

    @And("the cart page is open")
    public void theCartPageIsOpen() {
        productPageInstance.goToCartFromPopUp();
    }
}
