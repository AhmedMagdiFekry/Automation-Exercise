package com.automationexercices.validations;

import com.automationexercices.utils.WaitManager;
import com.automationexercices.utils.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseAssertion {
    protected  WebDriver driver;
    protected  WaitManager waitManager;
    protected ElementActions elementActions;
    protected BaseAssertion() {

    }
    public BaseAssertion(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
        this.elementActions = new ElementActions(driver);
    }

    public abstract void assertTrue(boolean condition, String message);

    public abstract void assertFalse(boolean condition, String message);

    public abstract void assertEquals(String actual, String expected, String message);

    public void Equals(String actual, String expected, String message) {
        assertEquals(actual, expected, message);
    }

    //common validations
    //isDisplayed
    public void isElementDisplayed(By Locator) {
        boolean flag = waitManager.fluentWait().until(d -> {
            try {
                d.findElement(Locator).isDisplayed();
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        assertTrue(flag, "Element is not displayed: " + Locator);
    }

    // getcurrenturl
    public void validateCurrentUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "Current URL does not match the expected URL");
    }

    // getCurrentTitle
    public void validateCurrentTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle, "Current Title does not match the expected Title");
    }
}