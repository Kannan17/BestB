package testcasePackage;

import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import reports.ExtentR;
import utilPakage.ExcelData;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import basePackage.Base;

public class Testcase2 extends Base {
	
// mention the data provider method name 
//  As per the column the no of parameter will be decided
	@Test(dataProvider="getData")
	public void tc2(Hashtable<String,String> data) throws IOException
	{
	
		System.out.println(data.get("Col1")+"5555555555555555555555555555555");

		// Call the getInstance() method to get the Extent Report object
		rep=ExtentR.getInstance();
		// Start the test and assign that to Extent test reference variable "test"
		test=rep.startTest("Testcase2");
		// log the desired info
		test.log(LogStatus.INFO, "Testcase2 test started");
		System.out.println("tc2() start");
		
	
		
		
		//initiating the browser
		init();
		
		//get the URL
		naviagate();
		
		// take the screenshot
		takeScreenshot1();
		
		// Select the language
		select("languageSelection_name","language");
		
		//Select Go button and Store 
		click("goButton_xpath");
		
		takeScreenshot1();
		
		//search the text computer		
		type("searchBox_id",data.get("Col1"));
		
		//click on search button
		click("searchClick_xpath");
		
		takeScreenshot1();
		
		System.out.println("tc2 - end");
	
		test.log(LogStatus.INFO, "Testcase2 test end");
		
		System.out.println("end");
		}

		
		@AfterTest
		public void end1()
		{
			// To generate the report write the below two codes 
		rep.endTest(test);
		rep.flush();
		
		// close the browser
	//	driver.quit();
		}
		
	
		
		
		
		
		
	
	@DataProvider
		public Object[][] getData() throws Exception
		{
		
		String Testcase="Testcase2";
		
			// Declare a two dimension array
			Object[][] data;
			
			// Call the getExcelData() method and assign the data into two dimension array data
		data= ExcelData.getExcelData(Testcase);
			
		// written the data to calling method
		return data;
			
			
		}


}
