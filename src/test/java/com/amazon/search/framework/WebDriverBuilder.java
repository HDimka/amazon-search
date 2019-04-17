package com.amazon.search.framework;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import io.github.bonigarcia.wdm.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;


public final class WebDriverBuilder {

    private static Logger LOG = Logger.getLogger(WebDriverBuilder.class);

    private WebDriverBuilder() {
    }


    public static WebDriver getDriver(BrowserName browser) {
        WebDriver driver;

        switch (browser) {
            case FIREFOX:
                driver = getFirefoxDriver();
                break;
            case CHROME:
                driver = getChromeDriver();
                break;

            case EDGE:
                driver = getEdgeDriver();
                break;

            default:
                driver = getFirefoxDriver();
        }

        return driver;
    }

    private static FirefoxProfile newFireFoxProfile() {
        FirefoxProfile profile = new FirefoxProfile();

        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/octet-stream;text/plain;application/xml;text/xml;application/x-java-jnlp-file");
        profile.setPreference("browser.download.importedFromSqlite", true);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.startup.homepage_override.mstone", "ignore");
        profile.setPreference("startup.homepage_welcome_url", "about:blank");
        profile.setPreference("startup.homepage_welcome_url.additional", "about:blank");
        profile.setPreference("browser.startup.homepage", "about:blank");

        return profile;
    }

    private static WebDriver getFirefoxDriver() {
        FirefoxProfile profile = newFireFoxProfile();
        profile.setPreference("browser.tabs.remote.autostart", false);
        profile.setPreference("browser.tabs.remote.autostart.1", false);
        profile.setPreference("browser.tabs.remote.autostart.2", false);
        FirefoxOptions options = new FirefoxOptions();

        WebDriverManager firefoxDriverManager = FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX);
        firefoxDriverManager.setup();

        System.setProperty("webdriver.gecko.driver", firefoxDriverManager.getBinaryPath());
        options.setBinary(new FirefoxBinary());
        options.setProfile(profile);

        LOG.info("Initializing FireFox Driver");
        return new FirefoxDriver(options);
    }

    private static ChromeDriverService getChromeDriverService() throws IOException {
        WebDriverManager chromeManager = ChromeDriverManager.getInstance(DriverManagerType.CHROME);
        chromeManager.setup();
        System.setProperty("webdriver.chrome.driver", chromeManager.getBinaryPath());
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(chromeManager.getBinaryPath()))
                .usingAnyFreePort()
                .build();
        service.start();

        return service;
    }

    private static WebDriver getChromeDriver() {
        ChromeDriverService service = null;
        try {
            service = getChromeDriverService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ChromeOptions options = new ChromeOptions();
        options.setCapability("chrome.verbose", true);
        options.setCapability("chrome.switches", Arrays.asList("--disable-translate"));
        try {
            LOG.info("Initializing Chrome Driver");
            return new ChromeDriver(service, options);
        } catch (WebDriverException e) {
            service.stop();
            throw e;
        }
    }

    private static WebDriver getEdgeDriver() {
        EdgeDriverManager.getInstance(DriverManagerType.EDGE).setup();
        EdgeOptions options = new EdgeOptions();

        WebDriver edge = new EdgeDriver(options);
        edge.manage().deleteAllCookies();
        LOG.info("Initializing Edge Driver");
        return edge;
    }

}
