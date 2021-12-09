package org.example.tests;



import org.example.pages.HomePageHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase{
    HomePageHelper homePage;

    @BeforeMethod(alwaysRun = true)
    public void initTests(){
        //homePage = new HomePageHelper(driver);
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        homePage.waitUntilPageIsLoaded();
    }

    @Test(groups = {"sanity","system"})
    public void trelloApplStart(){
        log4j.info("");
        log4j.info("=== Start test case 'trelloApplStart' ===");
        log4j.info("Check that the title is 'Trello'");
        Assert.assertEquals(homePage.getApplTitle(), "Trello",
                "The title of the application is not corresponds to expected title");
        log4j.info("=== End Tests ====");
        log4j.info("");
        log4j.info("");
    }

    @Test
    public void checkLogInButton(){
        Assert.assertEquals("Log in",homePage.getLoginIconName(),
                "The name of the button is not 'Log in'");
    }


    @Test(groups = {"sanity","regression"})
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
