package com.periplus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
  @FindBy(css = ".owl-item:first-child")
  private WebElement firstProductItem;

  @FindBy(css = ".owl-item:first-child .addtocart")
  private WebElement firstAddToCartButton;

  @FindBy(css = ".owl-item:first-child h3")
  private WebElement firstProductTitle;

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public String addFirstProductToCart() {
    firstAddToCartButton.click();

    return firstProductTitle.getText().trim();
  }
}
