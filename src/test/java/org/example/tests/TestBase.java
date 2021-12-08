package org.example.tests;


import java.io.File;
import java.io.IOException;
import java.net.URL;


import com.google.common.io.Files;
import org.example.SuiteConfiguration;
import org.example.util.LogLog4j;
import org.openqa.selenium.*;


import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected static Capabilities capabilities;
  public static final String EMAIL = "marinaqatest2019@gmail.com";
  public static final String PASSWORD = "marinaqa";
  //protected WebDriver driver;
  protected EventFiringWebDriver driver;
  public static LogLog4j log4j = new LogLog4j();

  public static class MyListener extends AbstractWebDriverEventListener {
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
      log4j.info("Try find the element by locator: " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
      log4j.info("Was found the element by locator: " + by);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
      log4j.error("Error " + throwable);
      //takePicture((TakesScreenshot) driver);
    }
  }

  public static void takePicture(TakesScreenshot driver) {
    File tmp = driver.getScreenshotAs(OutputType.FILE);
    File screen = new File("screen-"+System.currentTimeMillis() +".png");
    try {
      Files.copy(tmp,screen);
    } catch (IOException e) {
      e.printStackTrace();
    }
    log4j.info("see screen " + screen);
  }


  @BeforeSuite(alwaysRun = true)
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod(alwaysRun = true)
  public void initWebDriver() {
    ChromeOptions options = new ChromeOptions();
    options.setHeadless(true);
    options.addArguments("--disable-gpu");
    driver = new EventFiringWebDriver(WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities));
    //driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, options);
    driver.register(new MyListener());
    driver.get(baseUrl);
  }
  @AfterMethod(alwaysRun = true)
  public void finishTest(ITestResult result){
    if (result.getStatus()==ITestResult.FAILURE) {
      log4j.error (" Test was failure ");
      takePicture((TakesScreenshot) driver);
    }

  }
  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    WebDriverPool.DEFAULT.dismissAll();
  }
}
