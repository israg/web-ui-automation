package utils.driver;

import org.openqa.selenium.WebDriver;
import utils.helper.PropertyManager;

public class WebDrivers {
    //PropertyManager propertyManager = new PropertyManager();
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    ChromeWebDriver chromeWebDriver = new ChromeWebDriver();

    public WebDriver createAndGetDriver() {

        if (driver.get() != null) {
            driver.get().quit();
        }

        //String browserName = propertyManager.getProperty("config.properties", "browser");
        String browserName = "Chrome";

        if (browserName.equalsIgnoreCase("Chrome")) {
            driver.set(chromeWebDriver.initializeAndGetChromeDriver());
        }
        return driver.get();
    }
}
