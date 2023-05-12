package com.section22_TestComponents;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v111.media.Media;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.section22.Resources.ExtentReporterNG;


public class Listeners extends BaseTest implements ITestListener{
	
	ExtentReports  extent = ExtentReporterNG.getReportObject();
	ExtentTest test;

	
	ThreadLocal<ExtentTest> threadExtent = new ThreadLocal();

	@Override
	public void onTestStart(ITestResult result) {
		 test = extent.createTest(result.getMethod().getMethodName());
		 threadExtent.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "The test is passed");
	}

	@Override
	public void onTestFailure(ITestResult result)  {
		//test.fail(result.getTestName());
		 threadExtent.get().fail(result.getThrowable());
		//test.log(Status.FAIL, "The test is failed");
		//test.log(Status.INFO, "data");
		
		// take screenshot and then attach it to report
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Calling getScreenShot() from BaseTest class
		
        String path = null;
		try {
		path = getScreenShot(result.getMethod().getMethodName(),driver);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
			test.addScreenCaptureFromPath(path, result.getMethod().getMethodName());
		
		
		
	
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "The test is skipped");
		test.log(Status.INFO, "data");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
