package com.section18.mavenproject;

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

public class StandAloneTest {
	
	public static void main(String[]args) {
		
		SoftAssert softAssert = new SoftAssert();
		String productName = "ZARA COAT 3";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.findElement(By.id("userEmail")).sendKeys("piit@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Maven_001");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".mb-3"))));
		
		List<WebElement> products  = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b"))
				.getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
//		products.stream().filter((WebElement product)->{
//			return product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ZARA COAT 3");
//		});
		
		prod.findElement(By.cssSelector(".card-body>button:nth-of-type(2)")).click();
		
	
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		//driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart'][class='btn btn-custom']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".itemNumber+h3"));
	Boolean match =  cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		
	     softAssert.assertTrue(match);
	     //softAssert.assertFalse(match);
		
		
		//driver.findElement(By.xpath("//li[@class='totalRow']/child::button[@class='btn btn-primary']")).click();
		 driver.findElement(By.cssSelector(".totalRow button")).click();
		 
		 Actions a = new Actions(driver);
		 a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		 
		 
//		WebElement country =  driver.findElement(By.xpath("//div[@class='form-group'] /child::input[@class='input txt text-validated']"));
//		 
//		 
//		 Actions action = new Actions(driver);
//		 action.click(country).sendKeys("ban").build().perform();
		 
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
	
	
		

		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		softAssert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		softAssert.assertAll();
		
		
		 
	}
	
	
}














