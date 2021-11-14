package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPanelMenuHelper extends PageBase{
    @FindBy (xpath = "//*[@href='/marinaqatestttest/activity']")
    WebElement activityMenu;
    @FindBy (xpath = "//span[contains(text(),'@')]")
    WebElement emailOnAccountPanel;

    public  AccountPanelMenuHelper (WebDriver driver){
        this.driver = driver;
    }

    public AccountPanelMenuHelper waitUntilPageIsOpen() {
        waitUntilElementIsClickable(activityMenu,20);
        waitUntilElementIsVisible(emailOnAccountPanel,10);
        return this;
    }

    public String getAccountEMail() {
        return emailOnAccountPanel.getText();
    }

    public AccountPanelMenuHelper openActivityMenuPage() {
        activityMenu.click();
        return this;
    }
}
