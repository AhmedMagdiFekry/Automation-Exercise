package com.automationexercices.validations;

import com.automationexercices.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

// soft assertion
public class Validation extends BaseAssertion{
    private static SoftAssert softAssert = new SoftAssert();
    private static boolean usedSoftAssert = false;
    public Validation(){
    super();
    }
    public Validation(WebDriver driver) {
        super(driver);
    }

    @Override
    public void assertTrue(boolean condition, String message) {
        usedSoftAssert = true;
    softAssert.assertTrue(condition,message);
    }

    @Override
    public void assertFalse(boolean condition, String message) {
        usedSoftAssert = true;
    softAssert.assertFalse(condition,message);
    }

    @Override
    public void assertEquals(String actual, String expected, String message) {
   usedSoftAssert = true;
    softAssert.assertEquals(actual,expected,message);
    }
    public static void assertAll(){
        if (!usedSoftAssert) return;
        try{
            softAssert.assertAll();
        }catch (Exception e){
            LogsManager.error("Assertion failed: " , e.getMessage());
        }
        finally {
            softAssert = new SoftAssert(); // Reset the SoftAssert instance
        }
    }
}
