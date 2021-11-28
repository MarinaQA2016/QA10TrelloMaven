package org.example.tests;


import org.example.pages.*;
import org.example.util.DataProviders;
import org.example.util.LogLog4j;
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
        log4j.info("=== Start test case 'loginNegativeNoEmail==='");
        log4j.info("Enter email= '', password = '12345678'");
        loginPage.loginNotAttl("","12345678");
        log4j.info("Check that appears an error message 'Missing emai'");

        Assert.assertEquals("Missing email", loginPage.getErrorMessageNotAttl(),
                "The final error-message is not 'Missing email'");
        log4j.info("=== End the test case ====");
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

    @Test (dataProviderClass = DataProviders.class, dataProvider = "loginNegativeIncorrectEmailParametric")
    public void loginNegativeIncorrectEmailParametric(String login, String password, String message){
        log4j.info("");
        log4j.info("==== Start testCase 'loginNegativeIncorrectEmailParametric', login=" + login + ", password=" + password+
                ", message=" + message +"=====");
        log4j.info("Enter login=" + login + ", password=" +  password);
        loginPage.loginNotAttl(login,password);
        log4j.info("Check that the message on the screen is '" + message + "'");
        Assert.assertEquals(message, loginPage.getErrorMessageNotAttl(),
                "The error-message is not" + message);
        log4j.info("=== End Tests ====");
        log4j.info("");
        log4j.info("");
    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "loginNotEmailParametric")
    public void loginNotEmailParametric(String login, String password){
        loginPage.loginNotAttl(login,password);
        Assert.assertEquals("There isn't an account for this username", loginPage.getErrorMessageNotAttl(),
                "The error-message is not 'There isn't an account for this username'" );
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
