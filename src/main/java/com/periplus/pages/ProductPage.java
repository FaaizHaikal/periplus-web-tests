package com.periplus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.periplus.utils.Utils;

public class ProductPage extends BasePage {

  @FindBy(id = "cart_total")
  private WebElement cartTotal;

  String cartTotalCount;

  public ProductPage(WebDriver driver) {
    super(driver);

    this.pageUrl = "";
  }

  public void openPage(String url) {
    this.pageUrl = url;
    driver.get(pageUrl);
  }

  public String addToCart() {
    if (this.pageUrl.equals("")) {
      throw new RuntimeException("Product page is not opened! Try calling openPage first");
    }

    waitPreloader();

    cartTotalCount = cartTotal.getText().trim();

    String bookId = Utils.getBookId(pageUrl);

    WebElement addToCartButton = driver.findElement(By.className("btn-add-to-cart"));

    driverWait.until(ExpectedConditions.visibilityOf(addToCartButton));
    driverWait.until(ExpectedConditions.elementToBeClickable(addToCartButton));

    addToCartButton.click();

    waitCartTotalChanges();

    return bookId;
  }

  private void waitPreloader() {
    driverWait.until(ExpectedConditions.invisibilityOfElementLocated(
      By.cssSelector("div.preloader")
    ));
  }

  private void waitCartTotalChanges() {
    driverWait.until(d -> {
      String count = cartTotal.getText().trim();
      return !count.equals(cartTotalCount);
    });
  }
}
