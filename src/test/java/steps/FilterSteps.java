package steps;

import helpers.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import pageObjects.HomePage;
import pageObjects.ResultsPage;

public class FilterSteps {

    HomePage homePage = new HomePage(Hooks.getWebDriver());
    ResultsPage resultsPage;

    @Given("A search for {string} is made")
    public void aSearchForIsMade(String searchQuery) {
        resultsPage = homePage.search(searchQuery);
    }

    @When("A price filter between {string} and {string}")
    public void aPriceFilterBetweenAnd(String lower, String higher) {
        resultsPage = resultsPage.filterByPrice(lower, higher);
    }

    @Then("The results show only items within the price range")
    public void theResultsShowOnlyItemsWithinThePriceRange() {
        MatcherAssert.assertThat("Error: The filtering failed",
                resultsPage.validateFiltering().contains("$"), CoreMatchers.equalTo(true));
    }
}
