package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ActivityMenuPageHelper extends PageBase{
    @FindBy (css = ".phenom-desc")
    List<WebElement> descriptionsActivityList;

    public ActivityMenuPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public ActivityMenuPageHelper waitUntilPageIsOpened(){
        waitUntiAllElementArePresent(By.cssSelector(".phenom-desc"), 20);
        sleep(3000);
        return this;
    }

    public String getFirstRecordDescription() {
        return descriptionsActivityList.get(0).getText();
    }
}
