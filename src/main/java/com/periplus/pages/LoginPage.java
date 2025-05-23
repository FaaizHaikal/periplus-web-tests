package com.periplus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
  @FindBy(name = "email")
  private WebElement emailField;

  @FindBy(id = "ps")
  private WebElement passwordField;

  @FindBy(id = "button-login")
  private WebElement loginButton;
  
  public LoginPage(WebDriver driver) {
    super(driver);

    this.pageUrl = PERIPLUS_URL + "/account/Login";
  }

  public void login(String email, String password) {
    driver.get(pageUrl);

    emailField.clear();
    emailField.sendKeys(email);

    passwordField.clear();
    passwordField.sendKeys(password);

    loginButton.click();
  }
}
