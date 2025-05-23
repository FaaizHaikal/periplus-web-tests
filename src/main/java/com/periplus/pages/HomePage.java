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
    this.setPageEndpoint("");
  }

  public String addRandomProductToCart() {
    driver.get(pageUrl);

    cartTotalCount = cartTotal.getText().trim();
    
    WebElement selectedBook = selectRandomBook();

    String bookId = Utils.getBookId(getBookUrl(selectedBook));
    
    new Actions(driver).moveToElement(selectedBook).perform();

    addToCart(selectedBook);

    waitCartTotalChanges();
  
    return bookId;
  }

  private void addToCart(WebElement bookItem) {
    WebElement addToCartButton = bookItem.findElement(By.cssSelector("a.addtocart"));

    driverWait.until(ExpectedConditions.visibilityOf(addToCartButton));
    driverWait.until(ExpectedConditions.elementToBeClickable(addToCartButton));

    addToCartButton.click();
  }

  public WebElement selectRandomBook() {
    Random random = new Random();
    
    return visibleBooks.get(random.nextInt(visibleBooks.size()));
  }
  
  private String getBookUrl(WebElement bookItem) {
    return bookItem.findElement(By.cssSelector("h3 a")).getDomAttribute("href");
  }

  public String selectRandomBookUrl() {
    driver.get(pageUrl);

    return getBookUrl(selectRandomBook());
  }

  private void waitCartTotalChanges() {
    driverWait.until(d -> {
      String count = cartTotal.getText().trim();
      return !count.equals(cartTotalCount);
    });
  }
}
