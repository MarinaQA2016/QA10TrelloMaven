package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageHelper extends PageBase{

    @FindBy (xpath = "//a[@href='/signup']")
    WebElement signUpButton;
    @FindBy (xpath = "//a[@href='/login']")
    WebElement loginIcon;

    public HomePageHelper(WebDriver driver) {
        this.driver=driver;
    }

    public HomePageHelper waitUntilPageIsLoaded() {
        log4j.startMethod("HomePageHelper : waitUntilPageIsLoaded");
        waitUntilElementIsClickable(loginIcon,10);
        waitUntilElementIsClickable(signUpButton,10);
        log4j.endMethod("HomePageHelper : waitUntilPageIsLoaded");
        return this;
    }

    public String getLoginIconName() {
        return loginIcon.getText();
    }

    public String getSignUpIconName() {
        return signUpButton.getText();
    }

    public String getHeader() {
        WebElement header = driver.findElement(By.xpath("//h1"));
        return header.getText();
    }


    public HomePageHelper openLoginPage() {
        log4j.startMethod("HomePageHelper : openLoginPage");
        loginIcon.click();
        log4j.endMethod("HomePageHelper : openLoginPage");
        return this;
    }
}
