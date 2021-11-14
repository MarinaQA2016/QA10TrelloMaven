package org.example.tests;


import org.example.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActivityPageTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qa10HaifaCurrentBoard;
    AccountPanelMenuHelper accountPanelMenu;
    ActivityMenuPageHelper activityMenuPage;

    @BeforeMethod
    public void initTests(){
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver,BoardsPageHelper.class);
        qa10HaifaCurrentBoard = new CurrentBoardPageHelper(driver,"QA Haifa10");
        accountPanelMenu = PageFactory.initElements(driver, AccountPanelMenuHelper.class);
        activityMenuPage = PageFactory.initElements(driver,ActivityMenuPageHelper.class);

        homePage.waitUntilPageIsLoaded()
                .openLoginPage();
        loginPage.waitUntilPageIsLoaded()
                .loginAttl(EMAIL,PASSWORD);
        boardsPage.waitUntilPageIsLoaded()
                .openCurrentBoard("QA Haifa10");
        qa10HaifaCurrentBoard.waitUntilPageIsLoaded();

    }
    @Test
    public void newListCreatingInActivityTest(){
        String nameNewList = "QA10New";
        qa10HaifaCurrentBoard.createNewList(nameNewList)
                .openAccountPanelPage();
        accountPanelMenu.waitUntilPageIsOpen()
                .openActivityMenuPage();
        activityMenuPage.waitUntilPageIsOpened();
        Assert.assertTrue(activityMenuPage.getFirstRecordDescription().contains(nameNewList));
    }

}
