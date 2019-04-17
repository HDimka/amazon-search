package com.amazon.search.tests;

import com.amazon.search.pages.HomePage;
import com.amazon.search.pages.SearchResultsPage;
import org.hamcrest.Matchers;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;


public class AmazonSearchTests extends BaseTest {

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private String searchString = "baseball gloves for kids";
    private String brandToFilter = "Wilson";

    @BeforeClass
    public void amazonSearchTestBeforeClass() {

        homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
    }


    @Test
    public void amazonSearchFilteringTest() {

        searchResultsPage = homePage.performSearch(searchString);
        searchResultsPage.waitUntilSearchResultsAppeared();
        searchResultsPage.selectBrand(brandToFilter);
        int actualItemsSize = searchResultsPage.getSearchResultsItemsSize();
//        Verify that search results contains more then 0 values
        assertThat("Search does not return any items", actualItemsSize, Matchers.greaterThan(0));
    }
}
