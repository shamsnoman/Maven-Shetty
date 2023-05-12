package com.section19.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.section19.abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
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

}
