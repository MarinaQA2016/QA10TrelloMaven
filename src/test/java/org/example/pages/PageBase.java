package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageBase {
    WebDriver driver;

    public String getApplTitle() {
        return driver.getTitle();
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }
    public void sleep(int timeMS){
        try {
            Thread.sleep(timeMS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void fillInTextField(WebElement field, String value) {
        field.clear();
        field.click();
        field.sendKeys(value);
    }

    public void waitUntilElementIsVisible(By locator, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(WebElement element, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsClickable(By locator, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsClickable(WebElement element, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntiAllElementAreClickable(List<WebElement> elementsList, int time) {
        try {
            for (WebElement element : elementsList){
            new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(element));}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntiAllElementArePresent(By locator, int time) {
        try {
                new WebDriverWait(driver,time).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsAreVisible(By locator, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilTextToBePresentInElement(WebElement element, String text, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.textToBePresentInElement(element,text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsAreVisible(List<WebElement> elementsList, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOfAllElements(elementsList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsInVisible(WebElement element, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void waitUntilAllElementsAreInVisible(List<WebElement> list, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.invisibilityOfAllElements(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
