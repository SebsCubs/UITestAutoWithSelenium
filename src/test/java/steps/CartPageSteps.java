package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import pageObjects.CartPage;

public class CartPageSteps {
    private WebDriver webDriver;
    private CartPage cartPage;
    public CartPageSteps(Hooks hooks){
        webDriver = hooks.getWebDriver();
        cartPage = new CartPage(webDriver);
    }
    @When("the first item is deleted from the cart")
    public void theFirstItemIsDeletedFromTheCart() {
        cartPage.deleteFirstItem();
    }

    @Then("the cart has one less item")
    public void theCartHasOneLessItem() {
        MatcherAssert.assertThat("Error: price filtering couldn't be validated",
                cartPage.getEmptyCartLabelText().contains("Tu Bolsa de Compras está vacía."), CoreMatchers.equalTo(true));
    }
}
