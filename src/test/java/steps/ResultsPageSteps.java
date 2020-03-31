package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import pageObjects.ResultsPage;

public class ResultsPageSteps {
    private ResultsPage resultsPage;
    public ResultsPageSteps(Hooks hooks){
        WebDriver webDriver = hooks.getWebDriver();
        resultsPage = new ResultsPage(webDriver);
    }
    @When("a price filter between {string} and {string}")
    public void aPriceFilterBetweenAnd(String lowPrice, String highPrice) {
        resultsPage.filterByPrice(lowPrice,highPrice);
    }

    @Then("the results show only items within the price range")
    public void theResultsShowOnlyItemsWithinThePriceRange() {
        MatcherAssert.assertThat("Error: price filtering couldn't be validated",
                resultsPage.getFirstFilterText().isEmpty(), CoreMatchers.equalTo(false));
    }
}
