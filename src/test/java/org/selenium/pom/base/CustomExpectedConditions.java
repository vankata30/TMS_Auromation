package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomExpectedConditions {
    public static ExpectedCondition<Boolean> elementToBeClickable(By locator) {
        return driver -> {
            try {
                WebElement element = driver.findElement(locator);
                return element.isEnabled() && element.isDisplayed();
            } catch (ElementClickInterceptedException e) {
                return false;
            }
        };
    }
}
