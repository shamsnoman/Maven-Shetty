package com.section21.parameterization_dataProvider;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.section20.abstractComponents.AbstractComponent1;


public class CartPage extends AbstractComponent1{

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
