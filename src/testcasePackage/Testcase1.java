package testcasePackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import reports.ExtentR;
import utilPakage.ExcelData;
import basePackage.Base;

public class Testcase1 extends Base {


	@Test(dataProvider="getData")
	public void tc1(String s,String s1) throws IOException
	{
	//System.out.println(s.equals("computer")+" "+s1.equals("test1"));
	

		System.out.println(s+"new change from workspace8"+s1);
	
		//System.out.println(s.toString());
		
		rep=ExtentR.getInstance();
		test= rep.startTest("tc1Test");
		test.log(LogStatus.INFO, "Test case exection statrted");
		
		
		//initiating the browser
		init();
		//get the URL
		
		naviagate();
		
		takeScreenshot1();
		// Select the language
		select("languageSelection_name","language");
		takeScreenshot1();
		
		//Select Go button and Store 
		click("goButton_xpath");
		
		takeScreenshot1();
		
		type("searchBox_id",s);
		
		takeScreenshot1();
		
		
		//Select the store locator
		click("storeLocator_xpath");
		takeScreenshot1();
		
	//	System.out.println("tc1");
		
		
	
		
	}
	
	/*@AfterTest
	
	public void end()
	{
		rep.endTest(test);
		rep.flush();
		driver.quit();
	}*/
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, InvalidFormatException, IOException
	{
	String testcase="Testcase1";
		System.out.println(System.getProperty("user.dir"));
		Object[][] data;
		
		data= ExcelData.getExcelData(testcase);
		
		return data;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*String s[][]= new String[3][2];
		FileInputStream fs = new FileInputStream("C:\\Workspace3\\BestbuyDDFramework\\data.xlsx");
	    Workbook wb =  WorkbookFactory.create(fs);
	    Sheet sh = wb.getSheet("sheet1");
	    
	   for ( int i=0;i<3;i++){
	    	Row row=sh.getRow(i);
	    	for (int j=0;j<2;j++)
	    	{
	    s[i][j] =row.getCell(j).toString();
	    s[i][j] = row.getCell(j).toString();
	    	}
	    }
	   
	
		
		return s;*/
		
		
	}
	
	
	
	

}
