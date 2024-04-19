package utils.helper;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Action extends Helper {

    Actions actions;
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

    public Action() {
        actions = new Actions(webDriver);
    }

    //This method finds element with given key
    public WebElement findElement(String key) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 20000) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(getBy(key)));
            } catch (StaleElementReferenceException e) {
                System.out.println(e);
            }
        }
        return null;
    }

    // This method clicks on element with given element
    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    // This method teardown the driver
    public void tearDown() {
        webDriver.quit();
    }

    // This method clear and fill text field with given text
    public void clearAndFillInput(String key, String text) {
        centerElement(waitUntilElementIsVisible(findElement(key), System.currentTimeMillis())).clear();
        centerElement(waitUntilElementIsVisible(findElement(key), System.currentTimeMillis())).sendKeys(text);
    }

    //This method finds WebElementLists that is created by key
    public List<WebElement> findElements(String key) {
        return webDriver.findElements(getBy(key));
    }

    // This method check on List element with given key and index
    public void checkElementOnList(String key, int index, String expectedText) {
        List<WebElement> elements = findElements(key);
        if (index >= 1 && index <= elements.size()) {
            String actualText = elements.get(index - 1).getText();
            Assert.assertEquals("Element text does not match the expected text", expectedText, actualText);
        } else {
            Assert.fail("Index is out of bounds");
        }
    }

    //This method verifies current url with given url
    public void verifyUrl(String url) {
        Assert.assertTrue(webDriver.getCurrentUrl().contains(url));
    }

    //This method makes the thread wait for a certain while
    public void waitFor(double duration) {
        waitForGivenTime(duration);
    }

    // This method scrolls to the given element
    public void scrollToElement(WebElement element) {
        scrollWithJS(element);
    }

    // This method scrolls to page down
    public void scrollPageToDown(Integer index) {
        scrollToPageDownWithJS(index);
    }

    // This method checks element with text is displayed on page
    public void checkElementWithText(String key) {
        try {
            Assert.assertTrue(centerElement(waitUntilElementIsVisible(getElementWithText(key), System.currentTimeMillis())).isDisplayed());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
