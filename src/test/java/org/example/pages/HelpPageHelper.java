package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HelpPageHelper extends PageBase{
    @FindBy (xpath = "//h1")
    WebElement helpHeader;

    public HelpPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public  HelpPageHelper waitUntilPageIsLoaded(){
        waitUntilNumberOfWindowsIs(2,40);
        switchToAnotherWindow();
        waitUntilElementIsVisible(helpHeader,10);
        return  this;
    }

    public String getHeader(){
        return helpHeader.getText();
    }


}
