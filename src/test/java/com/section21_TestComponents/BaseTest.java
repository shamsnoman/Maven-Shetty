package com.section21_TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	
	
public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException {
		
		File file = new File(filePath);
		String jsonContent = FileUtils.readFileToString(file,StandardCharsets.UTF_8);
		
//		FileInputStream input = new FileInputStream(file);
//		String jsonContent = input.toString();
//		
		// for String to hashMap we need to add Jackson dataBind dependency in pom.xml 
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = (List<HashMap<String, String>>) mapper.readValue(jsonContent, 
				new TypeReference<List<HashMap<String,String>>>(){
			
		});
		
		return data;
		
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






















