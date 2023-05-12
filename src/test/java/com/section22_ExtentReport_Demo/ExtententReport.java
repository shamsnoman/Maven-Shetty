package com.section22_ExtentReport_Demo;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class ExtententReport {
	
	ExtentSparkReporter reporter ;
	public ExtentReports extent;
	public WebDriver driver;
	
	
	@BeforeTest
	public void config() {
		File file = new File(System.getProperty("user.dir") + "\\ExtentReport\\index.html");

		reporter = new ExtentSparkReporter(file);
		reporter.config().setReportName(" Test Report");
		reporter.config().setDocumentTitle("Automation Test Result");
		reporter.config().setTheme(Theme.STANDARD);
		//reporter.config().setTimeStampFormat("mm-dd-yyyy : hh-mm-ss");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("QA Engineer", "Shams");

	}
	
	
	@Test	
	public void initialDemo() {
		
		ExtentTest test = extent.createTest("initialDemo");
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/");
		String title = driver.getTitle();
		System.out.println(title);
		
		//test.fail("To see how failed TC work");
	
		
		extent.flush();
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
	

}
