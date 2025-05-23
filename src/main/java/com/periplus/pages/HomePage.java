package com.periplus.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.periplus.utils.Utils;

public class HomePage extends BasePage {
  @FindBy(css = ".owl-item.active .single-product")
  private List<WebElement> visibleBooks;

  @FindBy(id = "cart_total")
  private WebElement cartTotal;

  String cartTotalCount;

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public String addRandomProductToCart() {
    cartTotalCount = cartTotal.getText().trim();

    Random random = new Random();
    WebElement selectedBook = visibleBooks.get(random.nextInt(visibleBooks.size()));
    
    String bookId = Utils.getBookId(selectedBook.findElement(By.cssSelector("h3 a")).getDomAttribute("href"));
    
    new Actions(driver).moveToElement(selectedBook).perform();

    WebElement addToCartButton = selectedBook.findElement(By.cssSelector("a.addtocart"));

    driverWait.until(ExpectedConditions.visibilityOf(addToCartButton));
    driverWait.until(ExpectedConditions.elementToBeClickable(addToCartButton));

    addToCartButton.click();

    waitCartTotalChanges();

    return bookId;
  }

  private void waitCartTotalChanges() {
    driverWait.until(d -> {
      String count = cartTotal.getText().trim();
      return !count.equals(cartTotalCount);
    });
  }
}
