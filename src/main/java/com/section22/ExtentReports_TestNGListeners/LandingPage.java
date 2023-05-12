package com.section22.ExtentReports_TestNGListeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.section22.abstractComponents.AbstractComponent1;


public class LandingPage extends AbstractComponent1{
	
	public WebDriver driver;
	

	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy
	(id="userEmail") WebElement userEmail;
			
	@FindBy
	(id="userPassword") WebElement passwordEle;
	
	@FindBy
	(id="login") WebElement submit;
	
	@FindBy
	(css="[class*='flyInOut']") WebElement errrorMessage;
	
	public ProductCatalogue loginApplication(String email,String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new  ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(errrorMessage);
		 String msg = errrorMessage.getText();
		 return msg;
	}

}
