package com.section19.pageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.section19.abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	
	@FindBy
	(css = ".itemNumber+h3") List<WebElement> cartProducts;
	
	@FindBy
	(css = ".totalRow button") WebElement checkoutEle;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage  goToCheckout() {
		checkoutEle.click();
		return new CheckoutPage(driver);
		
	}



}
