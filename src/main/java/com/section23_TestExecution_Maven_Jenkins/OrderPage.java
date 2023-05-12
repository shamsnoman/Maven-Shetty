package com.section23_TestExecution_Maven_Jenkins;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.section23.abstractComponents.AbstractComponent1;


public class OrderPage extends AbstractComponent1{

	WebDriver driver;
	
	@FindBy
	(css = "tr td:nth-child(3)") List<WebElement> productNames;
	
	@FindBy
	(css = ".totalRow button") WebElement checkoutEle;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = productNames.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
//namecolumnZaraCoat3  xpath= //table[@class='table table-bordered table-hover ng-star-inserted']/tbody/tr/td[2]
//
//namecolumnZaraCoat3  css = tr td:nth-child(3)



}
