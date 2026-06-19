package com.automationexercices.pages.components;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.*;
import com.automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationBarComponent {
    private final GUIDriver driver;

    public NavigationBarComponent(GUIDriver driver) {
        this.driver = driver;
    }

    // locators
    private final By homeButton = By.xpath("//*[contains(text(),'Home')]");//*[text()=' Home']
    private final By productsButton = By.cssSelector("a[href='/products']]");
    private final By cartButton = By.xpath("//a[.=' Cart']");
    private final By testcasesButton = By.xpath("//*[text()=' Test Cases']");
    private final By signupLoginButton = By.xpath("//*[contains(text(),'Signup / Login')]");
    private final By deleteAccountButton = By.xpath("//*[text()=' Delete Account']]");
    private final By apiTestingButton = By.xpath("//a[.=' API Testing']");
    private final By contactUsButton = By.xpath("//*[contains(text(),' Contact us')]");
    private final By videoTutorialsButton = By.xpath("//*[text()=' Video Tutorials']");
    private final By logoutButton = By.xpath("//*[contains(text(),' Logout')]");
    private final By homePageLabel=By.cssSelector("h1 > span");
    private final By userLabel=By.tagName("b");

    // actions
    @Step("Navigate to Home Page")
    public NavigationBarComponent navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseURLWeb"));
        return this;
    }

    @Step("Click on Home button")
    public NavigationBarComponent clickOnHomeButton() {
        driver.element().click(homeButton);
        return this;
    }

    @Step("Click on Products button")
    public ProductsPage clickOnProductsButton() {
        driver.element().click(productsButton);
        return new ProductsPage(driver);
    }

    @Step("Click on Cart button")
    public CartPage clickOnCartButton() {
        driver.element().click(cartButton);
        return new CartPage(driver);
    }

    @Step("Click on Signup/Login button")
    public SignupLoginPage clickOnSignupLoginButton() {
        driver.element().click(signupLoginButton);
        return new SignupLoginPage(driver);
    }
    @Step("Click on Delete Account button")
    public DeleteAccountPage clickOnDeleteAccountButton() {
        driver.element().click(deleteAccountButton);
        return new DeleteAccountPage(driver);
    }

    @Step("Click on Logout button")
    public LogoutPage clickOnLogoutButton() {
        driver.element().click(logoutButton);
        return new LogoutPage(driver);
    }

    @Step("Click on Test Cases button")
    public TestCasesPage clickOnTestCasesButton() {
        driver.element().click(testcasesButton);
        return new TestCasesPage(driver);
    }
    @Step("Click on Contact US button")
    public ContactUsPage clickOnContactUsButton() {
        driver.element().click(contactUsButton);
        return new ContactUsPage(driver);
    }

    public VideoTutorialsPage clickOnVideoTutorialsButton() {
        driver.element().click(videoTutorialsButton);
        return new VideoTutorialsPage(driver);}

        // validations
        @Step("Validate Home Page Label")
        public NavigationBarComponent validateHomePageLabel() {
            driver.verification().isElementDisplayed(homePageLabel);
            return this;
        }
        public NavigationBarComponent validateUserLabel(String expectedName) {
            String actualName=driver.element().getText(userLabel);
            driver.verification().
                    Equals(actualName,expectedName,"User name does not match, Expected: "+ expectedName +", Actual: "+ actualName);

            return this;
        }

    }
