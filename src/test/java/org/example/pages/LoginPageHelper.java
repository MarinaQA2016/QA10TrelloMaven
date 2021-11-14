package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase {
    @FindBy (id="login")
    WebElement loginButton;
    @FindBy (id ="user")
    WebElement emailField;
    @FindBy (id = "password")
    WebElement passwordNotAttlField;
    @FindBy (css = "#error>p")
    WebElement errorMessage;
    @FindBy (xpath = "//input[@value='Log in with Atlassian']")
    WebElement loginAtlButton;
    @FindBy (id = "password")
    WebElement passwordField;
    @FindBy (id = "login-submit")
    WebElement submitAtlButton;
    @FindBy (id = "login-error")
    WebElement errorMessageAttl;

    public LoginPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(loginButton,10);
        waitUntilElementIsClickable(passwordNotAttlField,10);
        return this;
    }

    public LoginPageHelper fillEmailField(String email) {
        fillInTextField(emailField,email);
        return this;
    }



    public LoginPageHelper fillPasswordFieldNotAttl(String password) {
        waitUntilElementIsClickable(passwordNotAttlField,5);
        fillInTextField(passwordNotAttlField,password);
        return this;
    }

    public LoginPageHelper clickLoginButtonNotAttl() {
        waitUntilElementIsClickable(loginButton,10);
        loginButton.click();
        return this;
    }

    public String getErrorMessageNotAttl() {
        waitUntilElementIsVisible(errorMessage,10);
        return errorMessage.getText();
    }

    public LoginPageHelper loginNotAttl(String email, String password) {
        this.fillEmailField(email);
        this.fillPasswordFieldNotAttl(password);
        this.clickLoginButtonNotAttl();
        return  this;

    }

    public LoginPageHelper clickLoginAsAttl() {
        waitUntilElementIsClickable(loginAtlButton,10);
        loginAtlButton.click();
        return this;
    }

    public LoginPageHelper fillPasswordAttl(String password) {
        waitUntilElementIsClickable(passwordField,5);
        fillInTextField(passwordField,password);
        return this;
    }

    public LoginPageHelper submitLoginAttl() {
        waitUntilElementIsClickable(submitAtlButton,5);
        submitAtlButton.click();
        return this;
    }

    public String getErrorMessageAttl() {
        waitUntilElementIsVisible(errorMessageAttl,10);
        return errorMessageAttl.getText();
    }

    public LoginPageHelper loginAttl(String email, String password) {
        this.fillEmailField(email);
        this.clickLoginAsAttl();
        this.fillPasswordAttl(password);
        this.submitLoginAttl();
        return  this;
    }
}
