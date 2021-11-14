package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BoardsPageHelper extends PageBase{
    @FindBy (xpath = "//span[contains(text(),'Boards')]")
    WebElement boardsButton;
    @FindBy (css = ".board-tile-fade")
    List<WebElement> boardsList;

    public BoardsPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public BoardsPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(boardsButton, 30);
        //waitUntilAllElementsAreVisible(boardsList,15);
        waitUntiAllElementAreClickable(boardsList,15);
        return this;
    }

    public String getBordsButtonName() {
        //WebElement boardsButton = driver.findElement(By.xpath("//span[contains(text(),'Boards')]"));
        return boardsButton.getText();
    }

    public BoardsPageHelper openCurrentBoard(String bordsTitle) {
        String qaHaifa10BoardButtonLocator =
                "//li[@class = 'boards-page-board-section-list-item'][.//div[@title='" +bordsTitle+"']]";
        waitUntilElementIsClickable(By.xpath(qaHaifa10BoardButtonLocator),10);
        // ---- Press on 'QA Haifa10' board -----
        WebElement qaHaifa10BoardButton = driver.findElement(By.xpath(qaHaifa10BoardButtonLocator));
        qaHaifa10BoardButton.click();
        return this;
    }
}
