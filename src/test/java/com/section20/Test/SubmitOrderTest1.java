package com.section20.Test;

import java.io.IOException;
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
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.section20.pageObject.CartPage;
import com.section20.pageObject.CheckoutPage;
import com.section20.pageObject.ConfirmationPage;
import com.section20.pageObject.LandingPage;
import com.section20.pageObject.OrderPage;
import com.section20.pageObject.ProductCatalogue;
import com.section20_TestComponents.BaseTest;

public class SubmitOrderTest1 extends BaseTest {
	//WebDriver driver ;
	
	String productName = "ZARA COAT 3";
	SoftAssert softAssert = new SoftAssert();
	
	@Test
	
	public void submitOrder() throws IOException {
		
		
		
		
		
//		WebDriver driver = new ChromeDriver();
//		
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().window().maximize();
		
		
		
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		
//		LandingPage landingPage = new LandingPage(driver);
//		landingPage.goTo();
		
		//LandingPage landingPage = launchApplication();
		
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
	
	
	@Test(dependsOnMethods="submitOrder")
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("piit@gmail.com", "Maven_001");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		softAssert.assertTrue(orderPage.verifyOrderDisplay(productName));
		softAssert.assertAll();
	}
	
	
}














