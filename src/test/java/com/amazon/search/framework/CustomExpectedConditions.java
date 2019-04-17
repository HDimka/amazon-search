package com.amazon.search.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomExpectedConditions {


    public static class ElementPresent implements ExpectedCondition<WebElement>
    {
        private final By locator;

        /**
         * Creates a new ElementPresent instance.
         *
         * @param locator
         *        the locator
         */
        public ElementPresent(By locator)
        {
            this.locator = locator;

        }

        @Override
        public WebElement apply(WebDriver driver)
        {

            return driver.findElement(locator);
        }
    }
}
