package utils.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.helper.PropertyManager;

import java.io.File;
import java.io.IOException;

public class ChromeWebDriver {

    //PropertyManager propertyManager = new PropertyManager();
    WebDriver driver;
    //String url = propertyManager.getProperty("config.properties", "url");
    String url = "https://www.hepsiburada.com/";

    public WebDriver initializeAndGetChromeDriver() {
        try {
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, (new File(System.getProperty("user.dir") + "/src/test/resources/driver/chromedriver").getCanonicalPath()));
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--test-type");
            options.addArguments("--disable-extensions");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--allow-running-insecure-content");
            options.addArguments("--disable-translate");
            options.addArguments("--always-authorize-plugins");
            options.addArguments("--disable-infobars");
            options.addArguments("--enable-automation");

            System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driver;
    }
}