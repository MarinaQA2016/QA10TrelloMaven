package org.example.tests;


import org.example.pages.*;
import org.example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTests extends TestBase{


    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod
    public void initTests()  {
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        homePage.waitUntilPageIsLoaded()
                .openLoginPage();
        loginPage.waitUntilPageIsLoaded();
    }

    @Test
    public void loginNegativeNoEmail()  {
        loginPage.loginNotAttl("","12345678");

        Assert.assertEquals("Missing email", loginPage.getErrorMessageNotAttl(),
                "The final error-message is not 'Missing email'");
    }

    @Test
    public void loginNegativeLoginNotEmail()  {
        loginPage.loginNotAttl("gjgywggjwhdg","12345678");

        Assert.assertEquals("There isn't an account for this username", loginPage.getErrorMessageNotAttl(),
                "The error-message is not 'There isn't an account for this username'");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderThird")
    public void loginEmailIncorrectNegative(String login, String password)  {
        //loginPage.loginNotAttl("gjgywggjwhdg","12345678");
        loginPage.loginNotAttl(login,"password");
        Assert.assertEquals("There isn't an account for this email", loginPage.getErrorMessageNotAttl(),
                "The error-message is not 'There isn't an account for this email'");
    }

    @Test
    public void loginNegativePasswordIncorrect()  {
        loginPage.loginAttl(EMAIL,"incorrect");

        Assert.assertTrue(loginPage.getErrorMessageAttl().contains("Incorrect email address and"),
                "The error message doesn't contain 'Incorrect email address and'");
    }


    @Test (dataProviderClass = DataProviders.class, dataProvider = "loginPositive")
    public void loginPositive(String login, String password)  {
        //loginPage.loginAttl(EMAIL,PASSWORD);
        loginPage.loginAttl(login,password);
        boardsPage.waitUntilPageIsLoaded();

        Assert.assertTrue(boardsPage.getBordsButtonName().equals("Boards"),
                "The text of the checked button is not 'Boards'");
    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "dataProviderSecond")
    public void loginPositiveSecondDataProvider(String login, String password)  {
        //loginPage.loginAttl(EMAIL,PASSWORD);
        loginPage.loginAttl(login,password);
        boardsPage.waitUntilPageIsLoaded();

        Assert.assertTrue(boardsPage.getBordsButtonName().equals("Boards"),
                "The text of the checked button is not 'Boards'");
    }
}
