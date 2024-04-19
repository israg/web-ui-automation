package utils.helper;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.driver.WebDrivers;
import utils.helper.elementHelper.ElementMap;
import utils.helper.elementHelper.ElementResponse;
import utils.helper.elementHelper.Element;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Helper {

    public WebDriver webDriver = null;
    WebDrivers webDrivers = new WebDrivers();

    public Helper() {
        if (webDriver == null) {
            webDriver = webDrivers.createAndGetDriver();
            webDriver.manage().window().maximize();
            webDriver.manage().deleteAllCookies();
            webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        }
    }

    // This method waits until element is visible
    public WebElement waitUntilElementIsVisible(WebElement element, long startTime) {
        webDriver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        WebDriverWait subWait = new WebDriverWait(webDriver, Duration.ofSeconds(1));
        if ((System.currentTimeMillis() - startTime) > 10000) {
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return null;
        }
        try {
            subWait.until(ExpectedConditions.visibilityOf(element));
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return element;
        } catch (StaleElementReferenceException | TimeoutException e) {
            return waitUntilElementIsVisible(element, startTime);
        }
    }

    // This method waits until element is clickable
    public WebElement waitUntilElementIsClickable(WebElement element, long startTime) {
        WebDriver subDriver = webDriver;
        subDriver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        WebDriverWait subWait = new WebDriverWait(subDriver, Duration.ofSeconds(1));
        if ((System.currentTimeMillis() - startTime) > 15000) {
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return null;
        }
        try {
            subWait.until(ExpectedConditions.elementToBeClickable(element));
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return element;
        } catch (StaleElementReferenceException | ElementClickInterceptedException | TimeoutException e) {
            System.out.println(e.getMessage());
            return waitUntilElementIsClickable(element, startTime);
        }
    }

    //This method get element with given key
    public By getBy(String key) {
        ElementResponse elementInfo = ElementMap.INSTANCE.findElementInfoByKey(key);
        return Element.getElementInfoToBy(elementInfo);
    }

    // This method returns element with given text
    public WebElement getElementWithText(String text) {
        return webDriver.findElement(By.xpath("//*[text()='" + text + "']"));
    }

    // This method centers element with JavaScript
    public WebElement centerElement(WebElement element) {
        String scrollScript = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) webDriver).executeScript(scrollScript, element);
        waitForGivenTime(1);
        return element;
    }

    // This method waits for given time
    public void waitForGivenTime(double duration) {
        try {
            Thread.sleep((long) (duration * 1000L));
        } catch (InterruptedException exception) {
            Assert.fail(exception.getLocalizedMessage());
        }
    }

    // This method scrolls to the element with JavaScript
    public void scrollWithJS(WebElement webElement) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", webElement);
    }

    // This method scrolls to page with JavaScript
    public void scrollToPageDownWithJS(Integer index) {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(index));
        ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

    }
}
