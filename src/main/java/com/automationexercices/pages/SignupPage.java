package com.automationexercices.pages;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.components.NavigationBarComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupPage {
    private GUIDriver driver;
    public SignupPage(GUIDriver driver) {
        this.driver=driver;
    }

    // locators

    private final By passwordField=By.cssSelector("[data-qa=\"password\"]");
    private final By daysDropdown=By.id("days");
    private final By monthsDropdown=By.id("months");
    private final By yearsDropdown=By.id("years");
    private final By newsletterCheckbox=By.id("newsletter");
    private final By offersCheckbox=By.id("optin");
    private final By firstNameField=By.id("first_name");
    private final By lastNameField=By.id("last_name");
    private final By companyField=By.id("company");
    private final By addressField=By.id("address1");
    private final By address2Field=By.id("address2");
    private final By countryDropdown=By.id("country");
    private final By stateField=By.id("state");
    private final By cityField=By.id("city");
    private final By zipCodeField=By.id("zipcode");
    private final By mobileNumberField=By.id("mobile_number");
    private final By createAccountButton=By.cssSelector("[data-qa='create-account']");
    private final By accountCreatedLabel=By.cssSelector("h2 > b");
    private final By continueButton=By.cssSelector("[data-qa='continue-button']");
    // actions
    @Step("Choose title: {title}") // Mr or Mrs
    private SignupPage chooseTitle(String title){
        By titleLocator=By.xpath("//input[@value='"+title+"']");
        //click on the title radio button
        driver.element().click(titleLocator);
        return this;
    }
    @Step("Fill Registeration Form")
    public SignupPage fillRegisterationForm(  String title,  String password, String day, String month, String year,
                                            String firstName, String lastName, String company, String address1,
                                            String address2, String country, String state, String city,
                                            String zipcode, String mobileNumber){
   chooseTitle(title);

    driver.element().type(passwordField,password);
    driver.element().selectFromDropDown(daysDropdown,day);
    driver.element().selectFromDropDown(monthsDropdown,month);
    driver.element().selectFromDropDown(yearsDropdown,year);
    driver.element().click(newsletterCheckbox);
    driver.element().click(offersCheckbox);
    driver.element().type(firstNameField,firstName);
    driver.element().type(lastNameField,lastName);
    driver.element().type(companyField,company);
    driver.element().type(addressField,address1);
    driver.element().type(address2Field,address2);
    driver.element().selectFromDropDown(countryDropdown,country);
    driver.element().type(stateField,state);
    driver.element().type(cityField,city);
    driver.element().type(zipCodeField,zipcode);
    driver.element().type(mobileNumberField,mobileNumber);
    return this;
    }
    @Step("Click on Create Account button")
    public SignupPage clickOnCreateAccountButton(){
        driver.element().click(createAccountButton);
        return new SignupPage(driver);

}
    // validations
    @Step("Verify that account is created successfully")
    public SignupPage verifyAccountCreatedSuccessfully(){
        driver.verification().isElementDisplayed(accountCreatedLabel);
        return this;
    }
    @Step("Click on Continue button" )
    public NavigationBarComponent clickOnContinueButton(){
        driver.element().click(continueButton);
        return new NavigationBarComponent(driver);
    }
}
