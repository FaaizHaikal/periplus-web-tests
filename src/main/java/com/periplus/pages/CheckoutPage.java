package com.periplus.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.periplus.utils.Utils;


public class CheckoutPage extends BasePage {
  @FindBy(css = ".product-name")
  private List<WebElement> productsName;

  public CheckoutPage(WebDriver driver) {
    super(driver);
    this.setPageEndpoint("checkout/cart");
  }

  public Boolean bookInCart(String checkBookId) {
    driver.get(pageUrl);
    
    for (WebElement product : productsName) {
      String bookId = Utils.getBookId(product.findElement(By.cssSelector("a")).getDomAttribute("href"));

      if (bookId.equals(checkBookId)) {
        return true;
      }
    }

    return false;
  }
}
