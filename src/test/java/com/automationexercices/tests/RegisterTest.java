package com.automationexercices.tests;

import com.automationexercices.apis.UserManagementAPI;
import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.SignupLoginPage;
import com.automationexercices.pages.SignupPage;
import com.automationexercices.pages.components.NavigationBarComponent;
import com.automationexercices.utils.TimeManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest{
    String timeStamp=TimeManager.getSimpleUniqueTimestamp();
    // Tests
    @Test
    public void validSignUpTC(){
        new SignupLoginPage(driver)
                .navigateToSignupLoginPage()
                .enterSignUpName(testData.getJsonReader("name"))
                .enterSignUpEmail(testData.getJsonReader("email")+ timeStamp +"@gmail.com")
                .clickSignUpButton();
        new SignupPage(driver)
                .fillRegisterationForm(testData.getJsonReader("titleMale"),
                        testData.getJsonReader("password"),
                        testData.getJsonReader("day"),
                        testData.getJsonReader("month"),
                        testData.getJsonReader("year"),
                        testData.getJsonReader("firstName"),
                        testData.getJsonReader("lastName"),
                        testData.getJsonReader("company"),
                        testData.getJsonReader("address1"),
                        testData.getJsonReader("address2"),
                        testData.getJsonReader("country"),
                        testData.getJsonReader("state"),
                        testData.getJsonReader("city"),
                        testData.getJsonReader("zipcode"),
                        testData.getJsonReader("mobileNumber"))
                .clickOnCreateAccountButton()
                .verifyAccountCreatedSuccessfully();
        }
        @Test
        public void verifyErrorMessageWhenAccountCreatedBefore(){

        new UserManagementAPI().createUserAccount(
                testData.getJsonReader("name"),
                        testData.getJsonReader("email")+ timeStamp +"@gmail.com",
                testData.getJsonReader("password"),
                testData.getJsonReader("titleMale"),
                testData.getJsonReader("day"),
                testData.getJsonReader("month"),
                testData.getJsonReader("year"),
                testData.getJsonReader("firstName"),
                testData.getJsonReader("lastName"),
                testData.getJsonReader("company"),
                testData.getJsonReader("address1"),
                testData.getJsonReader("address2"),
                testData.getJsonReader("country"),
                testData.getJsonReader("state"),
                testData.getJsonReader("city"),
                testData.getJsonReader("zipcode"),
                testData.getJsonReader("mobileNumber")
        )
                .verifyUserCreationSuccess();
        }


    //Configurations
    @BeforeMethod
    public void setup(){
        driver=new GUIDriver();
        new NavigationBarComponent(driver).navigate();



    }

    @AfterMethod
    public void tearDown(){
    //driver.quitDriver();
    }

}
