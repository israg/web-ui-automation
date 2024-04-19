package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;

public class CommonStep {

    CommonPage page;

    @Before
    public void setUp() { page = new CommonPage(); }

    @After
    public void tearDown() {
        page.tearDown();
    }

    @Given("Check page url contains {string}")
    public void checkPageUrlContains(String url) {
        page.verifyUrl(url);
    }

    @When("Fill {string} field with {string}")
    public void fillFieldWith(String key, String text) {
        page.fill(key, text);
    }

    @And("Wait for given seconds {int}")
    public void waitForGivenSeconds(int seconds) {
        page.waitFor(seconds);
    }

    @And("Click {string}")
    public void click(String key) {
        page.click(key);
    }

    @And("Scroll to {string} element")
    public void scrollToElement(String key) {
        page.scrollToElement(key);
    }

    @And("Scroll page to down on {int} tab")
    public void scrollPageToDown(Integer index) {
        page.scrollPageToDown(index);
    }

    @Then("Check {string} text existence on page")
    public void checkTextExistenceOnPage(String key) { page.checkWithText(key); }
}
