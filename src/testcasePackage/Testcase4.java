package testcasePackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import reports.ExtentR;

import com.relevantcodes.extentreports.LogStatus;

import basePackage.Base;

public class Testcase4 extends Base {
	
	@Test
	public void tc1() throws IOException
	{
		
		
		// Call the getInstance() method to get the Extent Report object
		rep=ExtentR.getInstance();
		// Start the test and assign that to Extent test reference variable "test"
		test=rep.startTest("Testcase4");
		// log the desired info
		test.log(LogStatus.INFO, "Testcase4 test started");
		System.out.println("tc4() start");
		
		
		//initiating the browser
		init();
		//get the URL
		
		naviagate();
		// Select the language
		select("languageSelection_name","language");
	
		//Select Go button and Store 
		click("goButton_xpath");
		
		//Select the store locator
		click("storeLocator_xpath");
		
		
	//	clickid(prop.getProperty("storeLocator"));prop.getProperty(locator)
		
		
		System.out.println("tc1");
		
		
		
	}
	
	
	@AfterTest
	public void end1()
	{
		// To generate the report write the below two codes 
	rep.endTest(test);
	rep.flush();
	
	// close the browser
	driver.quit();
	}
	

}
