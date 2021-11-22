package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CurrentBoardPageHelper extends  PageBase{
    @FindBy (xpath = "//h1")
    WebElement boardHeader;
    @FindBy (css = ".list")
    List<WebElement>   toDoList;
    @FindBy (css = ".list-card-details")
    List<WebElement> cardsList;
    @FindBy (css = ".placeholder")
    WebElement addListButton;
    @FindBy (css = ".list-name-input")
    WebElement listNameField;
    @FindBy (css = ".js-save-edit")
    WebElement saveListButton;
    @FindBy (css = ".js-cancel-edit")
    WebElement cancelEditListButton;
    @FindBy (css = ".js-add-a-card")
    WebElement addCard;
    @FindBy (css = ".js-card-title")
    WebElement cardDetailsField;
    @FindBy (css = ".js-add-card")
    WebElement submitAddCardButton;
    @FindBy (css = ".js-cancel")
    WebElement cancelAddCard;
    @FindBy (css = ".js-open-header-member-menu")
    WebElement acoountPanelMenuButton;
    @FindBy (css = ".js-open-list-menu")
    List<WebElement>  listMenuIconsList;
    //@FindBy (css = ".js-close-list")
    @FindBy (xpath = "//*[@class= 'js-close-list']/..")
    WebElement archiveListMenu;


    String boardTitle;

    public CurrentBoardPageHelper(WebDriver driver, String boardTitle){
        this.driver=driver;
        this.boardTitle = boardTitle;
        PageFactory.initElements(driver,this);
    }

    public CurrentBoardPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(boardHeader,20);
        waitUntilAllElementsAreVisible(toDoList,20);
        waitUntilAllElementsAreVisible(cardsList, 20);
        return this;
    }

    public int getNumberOfLists() {
        return toDoList.size();
    }

    public CurrentBoardPageHelper createNewList(String title) {
        this.pressAddListButton();
        this.fillInListTitle(title);
        this.saveNewList();
        this.cancelCreateNewList();
        return this;
    }

    public CurrentBoardPageHelper pressAddListButton() {
        waitUntilElementIsClickable(addListButton,10);
        addListButton.click();
        return this;
    }

    public CurrentBoardPageHelper fillInListTitle(String title) {
        waitUntilElementIsClickable(listNameField,5);
        listNameField.sendKeys(title);
        return this;
    }

    public CurrentBoardPageHelper saveNewList() {
        waitUntilElementIsClickable(saveListButton,10);
        saveListButton.click();
        sleep(2000);
        return this;
    }

    public CurrentBoardPageHelper cancelCreateNewList() {
        waitUntilElementIsClickable(cancelEditListButton,5);
        cancelEditListButton.click();
        waitUntilElementIsClickable(By.cssSelector(".placeholder"),10);
        return this;
    }

    public int getNumberOfCards() {
        return cardsList.size();
    }

    public void pressAddCardButton() {
        addCard.click();
    }

    public CurrentBoardPageHelper fillInNewCardTitle(String title) {
        waitUntilElementIsClickable(cardDetailsField, 5);
        cardDetailsField.click();
        cardDetailsField.sendKeys(title);
        return this;
    }

    public CurrentBoardPageHelper submitCreatingCard() {
        waitUntilElementIsClickable(submitAddCardButton,10);
        submitAddCardButton.click();
        return this;
    }

    public CurrentBoardPageHelper cancelCreatingNewCard() {
        waitUntilElementIsClickable(cancelAddCard,10);
        cancelAddCard.click();
        waitUntilElementIsClickable(By.cssSelector(".js-add-a-card"),5);
        return  this;
    }

    public CurrentBoardPageHelper addNewCard(String title) {
        this.pressAddCardButton();
        this.fillInNewCardTitle(title);
        this.submitCreatingCard();
        this.cancelCreatingNewCard();
        return this;
    }

    public CurrentBoardPageHelper openAccountPanelPage() {
        waitUntilElementIsClickable(acoountPanelMenuButton,10);
        acoountPanelMenuButton.click();
        return this;
    }

    public void deleteLastList() {
        waitUntiAllElementAreClickable(listMenuIconsList,15);
        int size = listMenuIconsList.size();
        scrollingTillElement(addListButton);
        waitUntilElementIsClickable(listMenuIconsList.get(size-1),15);
        deleteListByNumber(size);
    }



    public void deleteListByNumber(int num) {
        listMenuIconsList.get(num-1).click();
        waitUntilElementIsClickable(submitAddCardButton,15);
        scrollingTillElement(archiveListMenu);
        sleep(500);
        waitUntilElementIsClickable(archiveListMenu,20);
        archiveListMenu.click();
        waitUntilAllElementsAreInVisible(driver.findElements(By.cssSelector(".js-close-list")),20);

    }
    public String getHeaderText(){
        return boardHeader.getText();
    }
}
