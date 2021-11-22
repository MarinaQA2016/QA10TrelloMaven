package org.example.tests;

import org.example.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HelpPageTests extends TestBase{
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qa10HaifaCurrentBoard;
    AccountPanelMenuHelper accountPanelMenu;
    HelpPageHelper helpPage;

    @BeforeMethod
    public void initTests()  {
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver,BoardsPageHelper.class);
        qa10HaifaCurrentBoard = new CurrentBoardPageHelper(driver,"QA Haifa10");
        accountPanelMenu = PageFactory.initElements(driver, AccountPanelMenuHelper.class);
        helpPage = PageFactory.initElements(driver, HelpPageHelper.class);

        homePage.waitUntilPageIsLoaded()
                .openLoginPage();
        loginPage.waitUntilPageIsLoaded()
                .loginAttl(EMAIL,PASSWORD);
        boardsPage.waitUntilPageIsLoaded()
                .openCurrentBoard("QA Haifa10");
        qa10HaifaCurrentBoard.waitUntilPageIsLoaded()
                .openAccountPanelPage();
        accountPanelMenu.waitUntilPageIsOpen()
                .openHelpMenuPage();
        helpPage.waitUntilPageIsLoaded();
    }
    @Test
    public void helpPageCheck(){
        Assert.assertEquals(helpPage.getHeader(),"Get help with Trello");
    }

   @Test
    public void closeHelpPageCheck(){
        helpPage.closeNotLastWindow();
        qa10HaifaCurrentBoard.waitUntilPageIsLoaded();
        Assert.assertEquals(qa10HaifaCurrentBoard.getHeaderText(),"QA Haifa10");
    }
}
