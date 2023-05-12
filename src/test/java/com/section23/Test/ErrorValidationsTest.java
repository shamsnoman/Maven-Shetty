package com.section23.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.section23_TestExecution_Maven_Jenkins.CartPage;
import com.section23_TestExecution_Maven_Jenkins.ProductCatalogue;
import com.section23_TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest { 
	SoftAssert softAssert = new SoftAssert();
	
	@Test(groups = {"ErrorHandling"})
	
	public void logInErrorValidation() throws IOException {
				
		landingPage.loginApplication("wrongemail@gmail.com", "wrongPassword");
		String actualMessage = landingPage.getErrorMessage();
		softAssert.assertEquals(actualMessage, "Incorrect email or password.");	
		softAssert.assertAll();
		 
	}
	
	
	@Test()
	public void productErrorValidation() {
	
		String productName = "ZARA COAT 3";
		

		
		com.section22.ExtentReports_TestNGListeners.ProductCatalogue productCatalogue = landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
		
		
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	     com.section22.ExtentReports_TestNGListeners.CartPage cartPage =  productCatalogue.goToCartPage();	
		
		
		Boolean match = cartPage.verifyProductDisplay(productName);
		softAssert.assertTrue(match);
		Assert.fail();
	
		softAssert.assertAll();
	}
	
}














