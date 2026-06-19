package com.automationexercices.apis;

import com.automationexercices.utils.dataReader.PropertyReader;
import com.automationexercices.utils.logs.LogsManager;
import com.automationexercices.validations.Verification;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class UserManagementAPI {
    RequestSpecification requestSpecification;
    Response response;
    Verification verification;

    public UserManagementAPI(){
        requestSpecification = RestAssured.given();

        verification= new Verification();
    }

    // endpoints
    public static final String createAccountEndpoint = "/createAccount";
    public static final String deleteAccountEndpoint = "/deleteAccount";

    // api methods
    @Step("Create user account with full details")
    public UserManagementAPI createUserAccount(String name, String email, String password, String title,
                                        String birth_date, String birth_month, String birth_year,
                                        String firstname, String lastname, String company,
                                        String address1, String address2, String country,
                                        String zipcode, String state, String city,
                                        String mobile_number) {
        Map <String, String> formParams = new HashMap<>();
        formParams.put("name", name);
        formParams.put("email", email);
        formParams.put("password", password);
        formParams.put("title", title);
        formParams.put("birth_date", birth_date);
        formParams.put("birth_month", birth_month);
        formParams.put("birth_year", birth_year);
        formParams.put("firstname", firstname);
        formParams.put("lastname", lastname);
        formParams.put("company", company);
        formParams.put("address1", address1);
        formParams.put("address2", address2);
        formParams.put("country", country);
        formParams.put("zipcode", zipcode);
        formParams.put("state", state);
        formParams.put("city", city);
        formParams.put("mobile_number", mobile_number);
        response =  requestSpecification.spec(Builder.getUserManagementSpec(formParams))
                .post(createAccountEndpoint);
        LogsManager.info(response.asPrettyString());
        return this;

    }

    @Step("Delete user account with email")
    public UserManagementAPI deleteUserAccount(String email, String password) {
        Map <String,String> formParameters= new HashMap<>();
        formParameters.put("email", email);
        formParameters.put("password", password);
        response= requestSpecification.spec(Builder.getUserManagementSpec(formParameters))
                .delete(deleteAccountEndpoint);
        LogsManager.info(response.asPrettyString());
        return this;

    }

    // validations
    @Step("Verify that the user creation was successful")
    public UserManagementAPI verifyUserCreationSuccess(){

        verification.Equals(response.jsonPath().get("message"), "User created!",
                "User is not created successfully");
        return this;
    }
    @Step("Verify that the user deletion was successful")
    public UserManagementAPI verifyUserDeletionSuccess(){
        verification.Equals(response.jsonPath().get("message"), "Account deleted!",
                "User is not deleted successfully");
        return this;

}}
