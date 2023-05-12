package com.section22.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.section22.ExtentReports_TestNGListeners.CartPage;
import com.section22.ExtentReports_TestNGListeners.OrderPage;


public class AbstractComponent1 {
	
	public WebDriver driver;

	public AbstractComponent1(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
 
	@FindBy
	(css = "[routerlink*='cart']")WebElement cartHeader;
	
	@FindBy
	(css="[routerlink*='myorders']")WebElement orderHeader;
	
	
	
	public void waitForElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	
	public CartPage goToCartPage() {
		cartHeader.click();
		com.section22.ExtentReports_TestNGListeners.CartPage cartPage = new com.section22.ExtentReports_TestNGListeners.CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
		
	}
	
}




