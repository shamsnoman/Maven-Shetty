package com.section19.pageObjectModel;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class SubmitOrderTest {
	
	public static void main(String[]args) {
		
		SoftAssert softAssert = new SoftAssert();
		String productName = "ZARA COAT 3";
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginApplication("piit@gmail.com", "Maven_001");
		
		
		//ProductCatalogue productCatalogue = new  ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	     CartPage cartPage =  productCatalogue.goToCartPage();	
		
		
		//CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyProductDisplay(productName);
		softAssert.assertTrue(match);
		
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("bangladesh");
		
		
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
				
	     
		softAssert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		softAssert.assertAll();
		
		
		 
	}
	
	
}














