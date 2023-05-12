package com.section23_TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.section22.ExtentReports_TestNGListeners.LandingPage;

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
		
		//this will check maven code 
		//when we put "mvn test -Dbrowser=firefox" in maven it will run using Firefox and if no browser info is provided in maven like
		//if we put "mvn test" then it will read the browser info from properties file.
		
//		if(System.getProperty("browser")!=null){
//			System.getProperty("browser");
//		}else {
//			prop.getProperty("browser");
//		}
//	
		
		 String browserName=  System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {			
	 driver = new ChromeDriver();
				
		}else if(browserName.equals("firefox")) {
			
			driver = new FirefoxDriver();
					
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
	

public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
	TakesScreenshot ts =  (TakesScreenshot)driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	File destFile = new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
	FileUtils.copyFile(source, destFile);
	return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
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






















