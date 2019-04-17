package com.amazon.search.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    private static Logger LOG = Logger.getLogger(HomePage.class);
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchField;

    @Step
    public SearchResultsPage performSearch(String searchText) {
        LOG.info(String.format("Performing search with keywords %s", searchText));
        searchField.sendKeys(searchText);
        searchField.sendKeys(Keys.ENTER);

        return PageFactory.initElements(driver, SearchResultsPage.class);
    }
}

