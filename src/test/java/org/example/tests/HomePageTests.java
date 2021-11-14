package org.example.tests;



import org.example.pages.HomePageHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase{
    HomePageHelper homePage;

    @BeforeMethod
    public void initTests(){
        //homePage = new HomePageHelper(driver);
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        homePage.waitUntilPageIsLoaded();
    }

    @Test
    public void trelloApplStart(){
        Assert.assertEquals("Manage Your Team’s Projects From Anywhere | Trello",homePage.getApplTitle(),
                "The title of the application is not corresponds to expected title");
    }

    @Test
    public void checkLogInButton(){
        Assert.assertEquals("Log in",homePage.getLoginIconName(),
                "The name of the button is not 'Log in'");
    }

    @Test
    public void checkSignUpButton(){
        Assert.assertEquals("Sign up", homePage.getSignUpIconName(),
                "Name of the button is not 'Sign Up'");
    }

    @Test
    public void checkPageHeader(){
        Assert.assertEquals("Trello helps teams move work forward.",homePage.getHeader(),
                "The text of the header is not correct");
    }



}
