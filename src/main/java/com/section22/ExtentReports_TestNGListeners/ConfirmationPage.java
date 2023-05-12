package com.section22.ExtentReports_TestNGListeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.section22.abstractComponents.AbstractComponent1;


public class ConfirmationPage extends AbstractComponent1 {

	WebDriver driver;
	
	@FindBy
	(css = ".hero-primary")WebElement confirmationMessage;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	
	public String getConfirmationMessage() {
		return confirmationMessage.getText();
	}
	
	
	
}
