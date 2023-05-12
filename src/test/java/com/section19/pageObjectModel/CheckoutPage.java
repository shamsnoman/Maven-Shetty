package com.section19.pageObjectModel;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.section19.abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	
	public WebDriver driver;
	
	@FindBy
	(css = "[placeholder='Select Country']") WebElement country;
	
	@FindBy
	//(xpath = "//div[@class='form-group']/child::input[@class='input txt text-validated']") WebElement selectCountry;
	
	(xpath = "(//button[contains(@class,'ta-item')])[2]") WebElement selectCountry;
	
	
	@FindBy
	(css=".action__submit")WebElement submit;
	
	@FindBy
	(css = ".ta-results")WebElement results;
	
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void selectCountry(String countryName) {
		Actions action = new Actions(driver);
		action.click(country).sendKeys("ban").build().perform();
		
		//action.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}
	

}
