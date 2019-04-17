package com.amazon.search.tests;

import com.amazon.search.framework.BrowserName;
import com.amazon.search.framework.WebDriverBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    private WebDriver driver;
    private static final String BASE_URL = "http://amazon.com";

    @BeforeClass
    public void beforeClassBaseTest() {
        openBrowser();
        driver.get(BASE_URL);
    }

    private void openBrowser() {
        BrowserName browser = BrowserName.fromString(System.getProperty("browser"));
        if (browser == null) {
            browser = BrowserName.CHROME;
        }
        driver = WebDriverBuilder.getDriver(browser);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    private void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getWebDriver() {
        return driver;
    }


    @AfterClass
    public void baseTestAfterClass() {
        closeBrowser();
    }
}
