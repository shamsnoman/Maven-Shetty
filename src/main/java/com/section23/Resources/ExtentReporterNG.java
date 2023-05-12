package com.section23.Resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {
	
	
	
	public static ExtentReports getReportObject() {
		
		File file = new File(System.getProperty("user.dir") + "\\ExtentReport\\index.html");
		ExtentSparkReporter reporter = new ExtentSparkReporter(file);
		reporter.config().setReportName("Maven Framework Test Execution Result");
		reporter.config().setDocumentTitle("Test Report");
		reporter.config().setTheme(Theme.DARK);
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Java version", " 17.0.7 2023-04-18 LTS");
		extent.setSystemInfo("Maven version", " Apache Maven 3.9.0");
		extent.setSystemInfo("Selenium version"," 4.9.0");
		extent.setSystemInfo("TestNG version", " 7.7.1");
		extent.setSystemInfo("Browser version", " Chrome:113.0.5672.93");
		extent.setSystemInfo("QA Engineer", " Shams");
		
		return extent;
	}

}
