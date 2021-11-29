package org.example.tests;

import java.io.IOException;
import java.net.URL;

import org.example.SuiteConfiguration;
import org.example.util.LogLog4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
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
    }
  }

  @BeforeSuite
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod
  public void initWebDriver() {
    driver = new EventFiringWebDriver(WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities));
    driver.register(new MyListener());
    driver.get(baseUrl);
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    WebDriverPool.DEFAULT.dismissAll();
  }
}
