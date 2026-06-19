package com.automationexercices.pages;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupLoginPage {
    private final String signUpLoginEndpoint = "/login";
    private GUIDriver driver;
    public SignupLoginPage(GUIDriver driver) {
    this.driver=driver;
    }

    // locators
    private final  By emailLogin= By.cssSelector("[data-qa='login-email']");
    private final  By passwordLogin= By.cssSelector("[data-qa='login-password']");
    private final  By signUpName= By.cssSelector("[data-qa='signup-name']");
    private final  By signUpEmail= By.cssSelector("[data-qa='signup-email']");
    private final  By loginButton= By.cssSelector("[data-qa='login-button']");
    private final  By signUpButton= By.cssSelector("[data-qa='signup-button']");
    private final  By signupLabel= By.cssSelector("[data-qa='signup-form'] h2");
    private final By loginErrorMessage= By.cssSelector("[action='/login'] p");
    private final By signUpErrorMessage= By.cssSelector("[action='/signup'] p");


    //actions
    @Step("Navigate to Signup/Login page")
    public SignupLoginPage navigateToSignupLoginPage(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseURLWeb")+signUpLoginEndpoint);
        return this;
    }

    @Step("Enter login email: {email} in login email field")
    public SignupLoginPage enterLoginEmail(String email){
        driver.element().type(emailLogin,email);
        return this;
    }
    @Step("Enter login password: {password} in login password field")
    public SignupLoginPage enterLoginPassword(String password){
        driver.element().type(passwordLogin,password);
        return this;
    }
    @Step("Enter sign up name: {name} in sign up name field")
    public SignupLoginPage enterSignUpName(String name){
        driver.element().type(signUpName,name);
        return this;
    }
    @Step("Enter sign up email: {email} in sign up email field")
    public SignupLoginPage enterSignUpEmail(String email){
        driver.element().type(signUpEmail,email);
        return this;
    }
    @Step("Click on login button")
    public SignupLoginPage clickLoginButton(){
        driver.element().click(loginButton);
        return this;
    }
    @Step("Click on sign up button")
    public SignupPage clickSignUpButton(){
        driver.element().click(signUpButton);
        return new SignupPage(driver);
    }
    //validations
    @Step("Validate that 'New User Signup!' label is visible")
    public SignupLoginPage validateSignupLabelIsVisible(){
        driver.verification().isElementDisplayed(signupLabel);
        return this;
    }
    @Step("Verify that login error message is visible with text: {errorMessage} ")
    public SignupLoginPage verifyLoginErrorMessage(String expectedErrorMessage){
        String actualErrorMessage= driver.element().getText(loginErrorMessage);
        driver.verification().Equals(actualErrorMessage,expectedErrorMessage,"Login error message is not correct");
        return this;
    }
    @Step("Verify that sign up error message is visible with text: {errorMessage} ")
    public SignupLoginPage verifySignUpErrorMessage(String expectedErrorMessage){
        String actualErrorMessage= driver.element().getText(signUpErrorMessage);
        driver.verification().Equals(actualErrorMessage,expectedErrorMessage,"Sign up error message is not correct");
        return this;
    }

}
