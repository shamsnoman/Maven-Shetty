package com.section21.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.section20.pageObject.CartPage;
import com.section20.pageObject.CheckoutPage;
import com.section20.pageObject.ConfirmationPage;
import com.section20.pageObject.LandingPage;
import com.section20.pageObject.OrderPage;
import com.section20.pageObject.ProductCatalogue;
import com.section21_TestComponents.BaseTest;

public class SubmitOrderTest1 extends BaseTest {

	String productName = "ZARA COAT 3";
	SoftAssert softAssert = new SoftAssert();
	
	@Test(dataProvider = "getData",groups = "Purchase" )
	
	public void submitOrder(HashMap<String,String> input) throws IOException {
		
			
	ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
	     CartPage cartPage =  productCatalogue.goToCartPage();	
		
		
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
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
	
	
	
	

	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data =	getJsonDataToMap(System.getProperty("user.dir")+
				"\\src\\test\\java\\com\\section21\\data\\PurchaseOrder.json");
	
		
		
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};
		
//		HashMap<String,String> map1 = new HashMap();
//		map1.put("email","piit@gmail.com");
//		map1.put("password", "Maven_001");
//		map1.put("productName", "ZARA COAT 3");
//		
//		
//		HashMap<Object,Object> map2 = new HashMap<Object, Object>();
//		map2.put("email","rahulshetty@gmail.com");
//		map2.put("password", "Iamking@000");
//		map2.put("productName", "ZARA COAT 3");
//		
//		HashMap<Object,Object> map3 = new HashMap<Object, Object>();
//		map3.put("email","shetty@gmail.com");
//		map3.put("password", "Iamking@000");
//		map3.put("productName", "ADIDAS ORIGINAL");
		
		
		
//		Object[][] data = { {"rahulshetty@gmail.com", "Iamking@000","ZARA COAT 3"},
//				            {"piit@gmail.com", "Maven_001","ADIDAS ORIGINAL"},
//				            {"shetty@gmail.com", "Iamking@000","ZARA COAT 3"}};
		

		

	}


	
}














