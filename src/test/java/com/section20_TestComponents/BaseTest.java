package com.section20_TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.section20.pageObject.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	

	
	public BaseTest() {
		// TODO Auto-generated constructor stub
	}

	

	public WebDriver initializeDriver() throws IOException {
		
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\section20\\Resources\\GlobalData.properties");
		FileInputStream input = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(input);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {			
	 driver = new ChromeDriver();
				
		}else if(browserName.equals("firefox")) {
			
			WebDriver driver = new FirefoxDriver();
					
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
	}
	
	@BeforeMethod (alwaysRun=true)
	public LandingPage launchApplication() throws IOException {

		driver = initializeDriver();

		landingPage = new LandingPage(driver);
		landingPage.goTo();

		return landingPage;
	}
	
	@AfterMethod (alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	
}






















