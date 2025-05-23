package com.periplus;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.periplus.pages.LoginPage;
import com.periplus.pages.HomePage;
import com.periplus.pages.CheckoutPage;
import com.periplus.utils.ConfigReader;

public class CartTest 
{
  private WebDriver driver;
  private LoginPage loginPage;
  private HomePage homePage;
  private CheckoutPage checkoutPage;

  @BeforeMethod
  public void setup() {
    driver = new ChromeDriver();

		loginPage = new LoginPage(driver);
    homePage = new HomePage(driver);
    checkoutPage = new CheckoutPage(driver);

    loginPage.login(ConfigReader.getProperty("periplus.email"), ConfigReader.getProperty("periplus.password"));
  }

  @Test
  public void addToCartTest()
  {
    String bookId = homePage.addRandomProductToCart();

    assertTrue(checkoutPage.bookInCart(bookId));
  }

  @AfterMethod
  public void tearDown() {
    if (driver != null) {
      // driver.quit();
    }
  }
}
