package kannanPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import reports.ExtentR;
import utilPakage.ExcelData;
import basePackage.Base;

import com.relevantcodes.extentreports.LogStatus;

public class CheckboxSelection extends Base {
	
	
	@Test(dataProvider="getData")
	public void checkBoxVerification(String s) throws IOException, InterruptedException
	{
		

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
	type("searchBox_id",s);
	Thread.sleep(1000);
	
	//click on search button
	//click("searchClick_xpath");
	click("firstSearchSuggestion_xpath");
	
	takeScreenshot1();
	
	
	boolean isChecked;
	
	isChecked=driver.findElement(By.id("brand_facet-0")).isSelected();
	System.out.println(isChecked);
	
	driver.findElement(By.id("brand_facet-0")).click();
	
	isChecked=driver.findElement(By.id("brand_facet-0")).isSelected();
	System.out.println(isChecked);
	
	
	
	List list= new ArrayList(driver.findElements(By.xpath("//*[@id='brand_facet']/div[2]/ul/li")));
	
	Iterator itr= list.iterator();
	
	int count=0;
	while (itr.hasNext())
	{
		count++;
		System.out.println(count);
		System.out.println(itr.next());
	}
	
	
	
	
	
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
	
		
		String testCaseName="checkBoxVerification";
	
	
		// Declare a two dimension array
		Object[][] data;
		
		// Call the getExcelData() method and assign the data into two dimension array data
	data= ExcelData.getExcelData(testCaseName);
		
	// written the data to calling method
	return data;
		
		
	}

	
	

}
