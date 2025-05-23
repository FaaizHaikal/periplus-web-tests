package com.periplus;

import static org.junit.Assert.assertFalse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.periplus.pages.LoginPage;
import com.periplus.utils.ConfigReader;

public class CartTest 
{
  private WebDriver driver;
  private LoginPage loginPage;

  @BeforeMethod
  public void setup() {
    driver = new ChromeDriver();
		driver.manage().window().maximize();

		loginPage = new LoginPage(driver);
  }

  @Test
  public void loginTest()
  {
    loginPage.login(ConfigReader.getProperty("periplus.email"), ConfigReader.getProperty("periplus.password"));

    String currentUrl = driver.getCurrentUrl();
    assertFalse(currentUrl == loginPage.getPageUrl());
  }
}
