package com.section21.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.section20.pageObject.CartPage;
import com.section20.pageObject.CheckoutPage;
import com.section20.pageObject.ConfirmationPage;
import com.section20.pageObject.ProductCatalogue;
import com.section20_TestComponents.BaseTest;
import com.section22_TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {
	SoftAssert softAssert = new SoftAssert();
	
	@Test(groups = {"ErrorHandling"})
	
	public void logInErrorValidation() throws IOException {
				
		landingPage.loginApplication("wrongemail@gmail.com", "wrongPassword");
		String actualMessage = landingPage.getErrorMessage();
		softAssert.assertEquals(actualMessage, "Incorrect email or password.");	
		softAssert.assertAll();
		 
	}
	
	
	@Test(retryAnalyzer=Retry.class)
	public void productErrorValidation() {
	
		String productName = "ZARA COAT 3";
		

		
		ProductCatalogue productCatalogue = landingPage.loginApplication("rahulshetty@gmail.com", "Iamkng@000");
		
		
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	     CartPage cartPage =  productCatalogue.goToCartPage();	
		
		
		Boolean match = cartPage.verifyProductDisplay(productName);
		softAssert.assertTrue(match);
		Assert.fail();
		
		
	
		//softAssert.assertAll();
	}
	
}














