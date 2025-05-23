package com.periplus.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
  protected WebDriver driver;
  protected WebDriverWait driverWait;
  protected String pageUrl;
  static final String PERIPLUS_URL = "https://www.periplus.com/";

  public BasePage(WebDriver driver) {
    this.driver = driver;
    this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    PageFactory.initElements(driver, this);
  }

  public void setPageEndpoint(String endpoint) {
    this.pageUrl = PERIPLUS_URL + endpoint;
  }

  public String getPageUrl() {
    return this.pageUrl;
  }
}
