package searchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class SearchPage extends Base {
	
// mention the data provider method name 
//  As per the column the no of parameter will be decided
	@Test(dataProvider="getData",priority=1,enabled = false)
	public void searchPageTest(Hashtable<String,String> data) throws IOException
	{
	
		if (rep==null)
		{
		// Call the getInstance() method to get the Extent Report object
		rep=ExtentR.getInstance();
		
		//initiating the browser
		init();
		
		//get the URL
		naviagate();
		
		// Select the language
		select("languageSelection_name","language");
				
		//Select Go button and Store 
		click("goButton_xpath");
		}
		
		//get the URL
		//naviagate();
			
		// Start the test and assign that to Extent test reference variable "test"
		test=rep.startTest("searchPageTest");
		// log the desired info
		test.log(LogStatus.INFO, "searchPageTest test started");
		
		
		
		// take the screenshot
		takeScreenshot1();
		
		
		//search the text computer		
		type("searchBox_id",data.get("ProductName"));
		
		//click on search button
		click("firstSearchSuggestion_xpath");
		
		takeScreenshot1();
		
		//Verify the text presence
		verfyText("priceLalel_xpath", data.get("item1"));
		verfyText("brandLabel_xpath", data.get("item2"));
		verfyText("conditionLabel_xpath", data.get("item3"));
		verfyText("cReviewLabel_xpath", data.get("item4"));
		verfyText("collectionLabel_xpath", data.get("item5"));
		
		
		
		System.out.println("searchPageTest - end");
	
		test.log(LogStatus.INFO, "searchPageTest test end");
		
		System.out.println("end");
		}
	
	
	@Test(dataProvider="getData1",priority=0,enabled = false)
	public void titleVerify(Hashtable<String,String> data) throws IOException
	{
		
		if (rep==null)
		{
		// Call the getInstance() method to get the Extent Report object
		rep=ExtentR.getInstance();
		
		//initiating the browser
		init();
		
		//get the URL
		naviagate();
				
		
		// Select the language
		select("languageSelection_name","language");
				
		//Select Go button and Store 
		click("goButton_xpath");
		}
			
		//get the URL
	//	naviagate();
		
		// Start the test and assign that to Extent test reference variable "test"
		test=rep.startTest("titleVerify");
		// log the desired info
		test.log(LogStatus.INFO, "titleVerify test started");
		System.out.println("titleVerify start");
		
		
		
		//search the text computer		
		type("searchBox_id",data.get("item"));
		takeScreenshot1();
		
		
		//click on search button
		click("secondSearchSuggestion_xpath");
		
		takeScreenshot1();
		
		test.log(LogStatus.INFO, "Second option is selected");
		
		String titleText=driver.findElement(By.xpath(prop.getProperty("searchCriteria_xpath"))).getText().trim();
		System.out.println("*"+titleText+"*");
		System.out.println("*"+data.get("item")+"*");
		test.log(LogStatus.INFO, "text is extracted");
		System.out.println(titleText);
		if (titleText.contains(data.get("item")) )
		{
			System.out.println("Title is displayed as per the search criteria");
			test.log(LogStatus.PASS, "Expected Result  : "+data.get("item")+ "  Actual Result  : "+ titleText);
		}
		
		else
		{
			System.out.println("Title is not displayed as per the search criteria");
			test.log(LogStatus.FAIL, "Expected Result  : "+data.get("item")+ "Actual Result  : "+ titleText);
			
		}
		
		
		
	}
	
	
	@Test(dataProvider="getData2",priority=2,enabled = false)
	public void sortBy(Hashtable<String,String> data) throws IOException, InterruptedException
	{
		
		if (rep==null)
		{
		// Call the getInstance() method to get the Extent Report object
		rep=ExtentR.getInstance();
		
		//initiating the browser
		init();
		
		//get the URL
		naviagate();
				
		
		// Select the language
		select("languageSelection_name","language");
				
		//Select Go button and Store 
		click("goButton_xpath");
		}
			
		//get the URL
	//	naviagate();
		
		// Start the test and assign that to Extent test reference variable "test"
		test=rep.startTest("sortBy");
		// log the desired info
		test.log(LogStatus.INFO, "sortBy test started");
		System.out.println("sortBy start");
		
		
		
		//search the text computer		
		type("searchBox_id",prop.getProperty("searchCritera"));
		takeScreenshot1();
		
		
		//click on search button
		click("secondSearchSuggestion_xpath");
		
		takeScreenshot1();
		driver.findElement(By.id(prop.getProperty("sortby_id"))).click();
		Thread.sleep(2000);
		takeScreenshot1();
		Select s=new Select( driver.findElement(By.id(prop.getProperty("sortby_id"))));
		
		List<WebElement> options = s.getOptions();
		options.size();
		
		int j;
		
		for (int i=0;i<options.size();i++)
		{
			j=i+1;
			System.out.println(options.get(i).getText());
			if (data.get("option"+j).equals(options.get(i).getText().trim()))
			{
				test.log(LogStatus.PASS, "Expected "+data.get("option"+j)+ " Actual option"+j+" ="+options.get(i).getText().trim());
			}
			else
			{
				test.log(LogStatus.FAIL, "option"+j+" ="+options.get(i).getText().trim());
			}
			
		}
	
		
	}
	
	
	
	@Test(priority=3,enabled = false)
	public void elementCount() throws IOException
	{
		
		if (rep==null)
		{
		// Call the getInstance() method to get the Extent Report object
		rep=ExtentR.getInstance();
		
		//initiating the browser
		init();
		
		//get the URL
		naviagate();
				
		
		// Select the language
		select("languageSelection_name","language");
				
		//Select Go button and Store 
		click("goButton_xpath");
		}
			
		//get the URL
	//	naviagate();
		
		// Start the test and assign that to Extent test reference variable "test"
		test=rep.startTest("elementCount");
		// log the desired info
		test.log(LogStatus.INFO, "elementCount test started");
		System.out.println("elementCount start");
		
		
		
		//search the text computer		
		type("searchBox_id",prop.getProperty("searchCritera"));
		takeScreenshot1();
		
		
		//click on search button
		click("secondSearchSuggestion_xpath");
		
		takeScreenshot1();
		
		
		
		
		List<WebElement> list= driver.findElements(By.xpath("//div[@class='col-xs-6 list-item-postcard-column']"));
		int count;
		System.out.println(count=list.size());
		
	
		
		test.log(LogStatus.INFO, "number of webelement listed are "+count);
		
		if ( count==24)
		{
			
			test.log(LogStatus.PASS, "Expeted count 24 "+"Actual count "+ list.size());
		}
		
		
	}
		
		
		@Test(priority=4,enabled = false)
		public void addtocartbutton() throws IOException
		{
			
			if (rep==null)
			{
			// Call the getInstance() method to get the Extent Report object
			rep=ExtentR.getInstance();
			
			//initiating the browser
			init();
			
			//get the URL
			naviagate();
					
			
			// Select the language
			select("languageSelection_name","language");
					
			//Select Go button and Store 
			click("goButton_xpath");
			}
				
			//get the URL
		//	naviagate();
			
			// Start the test and assign that to Extent test reference variable "test"
			test=rep.startTest("addtocartbutton");
			// log the desired info
			test.log(LogStatus.INFO, "addtocartbutton test started");
			System.out.println("addtocartbutton start");
			
			
			
			//search the text computer		
			type("searchBox_id",prop.getProperty("searchCritera"));
			takeScreenshot1();
			
			
			//click on search button
			click("secondSearchSuggestion_xpath");
						
			takeScreenshot1();
			
			
			String bcolor=driver.findElement(By.xpath("//*[@id='main-results']/div[3]/div[1]/div/div[3]/div/div[1]/div[2]/div[4]/div[2]/div[1]/a")).getCssValue("background-color");
			String tcolor=driver.findElement(By.xpath("//*[@id='main-results']/div[3]/div[1]/div/div[3]/div/div[1]/div[2]/div[4]/div[2]/div[1]/a")).getCssValue("color");
			System.out.println(bcolor);
			System.out.println(tcolor);
			
			
			//Need to check whether it is yello or not
			test.log(LogStatus.INFO, bcolor);
			test.log(LogStatus.INFO, tcolor);
			
			
			List<WebElement> list= driver.findElements(By.xpath("//span[contains(text(),'Add to Cart')]"));
			int count;
			System.out.println(count=list.size());
			
		JavascriptExecutor js= ((JavascriptExecutor)driver);
		//js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		js.executeScript("window.scrollBy(0,500)", "");
		takeScreenshot1();
		
			
			test.log(LogStatus.INFO, "number of webelement listed are "+count);
			
			if ( count==24)
			{
				
				test.log(LogStatus.PASS, "Expeted add to cart count 24 "+"Actual add to cart count "+ list.size());
			}
			
			
	}
		
		
		
		
		@Test(priority=5,enabled = false)
		public void priceLabelCheck() throws IOException
		{
			
			if (rep==null)
			{
			// Call the getInstance() method to get the Extent Report object
			rep=ExtentR.getInstance();
			
			//initiating the browser
			init();
			
			//get the URL
			naviagate();
					
			
			// Select the language
			select("languageSelection_name","language");
					
			//Select Go button and Store 
			click("goButton_xpath");
			}
				
			//get the URL
		//	naviagate();
			
			// Start the test and assign that to Extent test reference variable "test"
			test=rep.startTest("priceLabelCheck");
			// log the desired info
			test.log(LogStatus.INFO, "priceLabelCheck test started");
			System.out.println("priceLabelCheck start");
			
			
			
			//search the text computer		
			type("searchBox_id",prop.getProperty("searchCritera"));
			takeScreenshot1();
			
			
			//click on search button
			click("secondSearchSuggestion_xpath");
						
			takeScreenshot1();
			
			
			//List<WebElement> list= driver.findElements(By.xpath("//div[@class='pb-hero-price pb-purchase-price']/span[starts-with(@aria-label,'Your price for this item is')]"));
			List<WebElement> list= driver.findElements(By.xpath("//div[@class='price-view-pb']/div/div/span[starts-with(@aria-label,'Your price for this item is')]"));
			int count;
			System.out.println(count=list.size());
			
		JavascriptExecutor js= ((JavascriptExecutor)driver);
		//js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		js.executeScript("window.scrollBy(0,2000)", "");
		takeScreenshot1();
		
			
			test.log(LogStatus.INFO, "number of webelement listed are "+count);
			
			if ( count==24)
			{
				
				test.log(LogStatus.PASS, "Expeted price lable count 24 "+"Actual price lable count "+ list.size());
			}
			else
			{
				test.log(LogStatus.FAIL, "Expeted price lable count 24 "+"Actual price lable count "+ list.size());
			}
			
			
	}		
		
		
		@Test(priority=5,enabled = true)
		public void filterCheck() throws IOException, InterruptedException
		{
			
			if (rep==null)
			{
			// Call the getInstance() method to get the Extent Report object
			rep=ExtentR.getInstance();
			
			//initiating the browser
			init();
			
			//get the URL
			naviagate();
					
			
			// Select the language
			select("languageSelection_name","language");
					
			//Select Go button and Store 
			click("goButton_xpath");
			}
				
			//get the URL
		//	naviagate();
			
			// Start the test and assign that to Extent test reference variable "test"
			test=rep.startTest("filterCheck");
			// log the desired info
			test.log(LogStatus.INFO, "filterCheck test started");
			System.out.println("filterCheck start");
			
			
			
			//search the text computer		
			type("searchBox_id",prop.getProperty("searchCritera"));
			takeScreenshot1();
			
			
			//click on search button
			click("secondSearchSuggestion_xpath");
						
			takeScreenshot1();
			
			
			//List<WebElement> list= driver.findElements(By.xpath("//div[@class='pb-hero-price pb-purchase-price']/span[starts-with(@aria-label,'Your price for this item is')]"));
			List<WebElement> list= driver.findElements(By.xpath("//li[starts-with(@data-query-path,'brand_facet=Brand~')]"));
			int count;
			System.out.println(list.get(0).getAttribute("data-value"));
			System.out.println(count=list.size());
			list.get(0).click();
		
			
			Thread.sleep(5000);
			System.out.println(list.get(0).getAttribute("data-value"));
			
			System.out.println(list.get(0).getTagName());
						
			System.out.println(list.get(0).getText());
			
			System.out.println(list.get(0).getCssValue("class"));
		
			
			
		JavascriptExecutor js= ((JavascriptExecutor)driver);
		//js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		js.executeScript("window.scrollBy(0,2000)", "");
		takeScreenshot1();
		
		
			
			test.log(LogStatus.INFO, "number of webelement listed are "+count);
			
			if ( count==24)
			{
				
				test.log(LogStatus.PASS, "Expeted price lable count 24 "+"Actual price lable count "+ list.size());
			}
			else
			{
				test.log(LogStatus.FAIL, "Expeted price lable count 24 "+"Actual price lable count "+ list.size());
			}
			
			
	}		
		
		
	
	@DataProvider
		public Object[][] getData() throws Exception
		{
		
		String Testcase="searchPageTest";
		
			// Declare a two dimension array
			Object[][] data;
			
			// Call the getExcelData() method and assign the data into two dimension array data
		data= ExcelData.getExcelData(Testcase);
			
		// written the data to calling method
		return data;
						
		}
	
	
	
	

	@DataProvider
		public Object[][] getData1() throws Exception
		{
		
		String Testcase="titleVerify";
		
			// Declare a two dimension array
			Object[][] data;
			
			// Call the getExcelData() method and assign the data into two dimension array data
		data= ExcelData.getExcelData(Testcase);
			
		// written the data to calling method
		return data;
						
		}
	

	@DataProvider
		public Object[][] getData2() throws Exception
		{
		
		String Testcase="sortBy";
		
			// Declare a two dimension array
			Object[][] data;
			
			// Call the getExcelData() method and assign the data into two dimension array data
		data= ExcelData.getExcelData(Testcase);
			
		// written the data to calling method
		return data;
						
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
	

	
	


}
