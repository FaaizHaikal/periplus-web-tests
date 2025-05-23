package com.periplus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
  @FindBy(css = ".single-product:first-child")
  private WebElement firstProductItem;

  @FindBy(css = ".single-product:first-child h3")
  private WebElement firstProductTitle;

  @FindBy(css = ".single-product:first-child a.addtocart")
  private WebElement firstAddToCartButton;

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public void disableCarouselScroll() {
    ((JavascriptExecutor)driver).executeScript(
        "document.querySelector('.owl-stage').style.transform = 'translateX(0)';"
    );
  }

  public String addFirstProductToCart() {
    disableCarouselScroll();

    new Actions(driver).moveToElement(firstProductItem).perform();

    driverWait.until(ExpectedConditions.visibilityOf(firstAddToCartButton));
    driverWait.until(ExpectedConditions.elementToBeClickable(firstAddToCartButton));

    firstAddToCartButton.click();

    return firstProductTitle.getText().trim();
  }
}
