package basePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Base {
		
	// Declare here so that all the child class can use the common variable
	public WebDriver driver;
	public Properties prop;
	public ExtentReports rep;
	public ExtentTest test;
	 
	
// To take the screenshot
	public void takeScreenshot1() throws IOException
	{
		String path;
		
		// For unique file name create a date object
		Date d= new Date();
		
		// Replace the : and space with _
		String Scfilename=d.toString().replace(":", "_").replace(" ", "_");
		System.out.println(d.toString());
		
		// Use the TakesScreenshot interface do the casting
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		// store the screenshot in a a file fs
		File fs=ts.getScreenshotAs(OutputType.FILE);
		
		path="C:\\Workspace3\\BestbuyDDFramework\\SC\\"+Scfilename+".png";
		
		// move the file to physical location in your commputer
		FileUtils.copyFile(fs, new File(path));
		
		// log the screenshot in the report
		test.log(LogStatus.INFO, test.addScreenCapture(path));
		
	}
	
		
	
	
	public void init() throws IOException
	{
		
		// Mention where is the file located and assign that to the reference fs
		FileInputStream fs= new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
				
		// Create a object for Property class
		prop= new Properties ();
				
		// Load the file to Property object reference
		prop.load(fs);
		
		String Btype=prop.getProperty("browser");
		
		if (Btype.equals("Chrome")){
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe" );
		driver= new ChromeDriver();
		}
		
		else if (Btype.equals("Mozilla"))
		{
			
			
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\geckodriver.exe");
			driver= new FirefoxDriver();
		}
			
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
}
	
	
	// To  initialize the property file and navigate to the URL
	public void naviagate() throws IOException
	{
		
		
		// Open the URL		
		driver.get(prop.getProperty("url"));
		
		try{
		
		// Click on the click Here link
		driver.findElement(By.xpath(prop.getProperty("clickHere"))).click();
		}
		
		catch(Exception e)
		{
			System.out.println("Click here did not appear");
		}
		
		try{
		// Click on the Accept button 
		driver.findElement(By.name(prop.getProperty("acceptButton"))).click();
		}

		
		catch (Exception e)
		{
			System.out.println(" Accept button is not appeared");
			
		}
		
		
		// Open the URL		
	//	driver.get(prop.getProperty("url"));
		
	}
	
	
	// To get the element from the page
	
	public WebElement getElement(String locator) throws IOException
	{
		
		// Initialize the WebElement to null first
		WebElement e=null;
		
		
		// Find the object by xpath
		if ( locator.endsWith("_xpath"))
		{
			e=driver.findElement(By.xpath(prop.getProperty(locator)));
		}
		
		// Find the object by name
		else if (locator.endsWith("_name"))
		{
			e=driver.findElement(By.name(prop.getProperty(locator)));
		}
	
		// Find the object by id
		else if (locator.endsWith("_id"))
		{
			e=driver.findElement(By.id(prop.getProperty(locator)));
		}
		
		// If the locator is wrong control will come here
		else
		{
			test.log(LogStatus.FAIL, "The locator is wrong "+locator);
			takeScreenshot1();
			Assert.fail();
		}
			
		
		return e;
		
	}
		
	
	// To Select the value from the list
	public void select(String locator,String value)
	{
		Select s=null;
			
	
		try{
		
			// Find the object by xpath
		if ( locator.endsWith("_xpath"))
		{
			// Store the all element in variable s
			s= new Select(driver.findElement(By.xpath(prop.getProperty(locator))));
			
		}
		else if (locator.endsWith("_name"))
		{
			s= new Select(driver.findElement(By.name(prop.getProperty(locator))));
		}
	
		else if (locator.endsWith("_id"))
		{
			s= new Select(driver.findElement(By.id(prop.getProperty(locator))));
		}
		
		else
		{
			
			test.log(LogStatus.FAIL, "The locator---  "+locator+" ---  is not correct");
			Assert.fail();
			
		}
		// select the desired value from s
		s.selectByVisibleText(prop.getProperty(value));
		}
		
		catch (Exception e)
		{
			// In case of any issue call the reportFail() method
			reportFail(e);
		}
		
	}
	
	// To click on the element
	public void click(String locator)
	{
		
		try{
			// Call the getElement() method to get the element . Once returned, click on the element
		getElement(locator).click();
		}
		
		catch(Exception e)
		{
			reportFail(e);
		}
	}
	
	
	
	// To type the data in the input field
	public void type(String locator, String text)
	{
		
		System.out.println(locator);
		
		if ( locator.endsWith("_xpath"))
		{
			driver.findElement(By.xpath(prop.getProperty(locator))).clear();
			driver.findElement(By.xpath(prop.getProperty(locator))).sendKeys(text);
		}
		else if (locator.endsWith("_name"))
		{
			driver.findElement(By.name(prop.getProperty(locator))).clear();
			driver.findElement(By.name(prop.getProperty(locator))).sendKeys(text);
		}
		
		else if (locator.endsWith("_id"))
		{
			driver.findElement(By.id(prop.getProperty(locator))).clear();
			driver.findElement(By.id(prop.getProperty(locator))).sendKeys(text);
		}
		
		else
		{
			System.out.println(" Locator is wrong --  :    "+ locator);
			Assert.fail();
		}
		
		
		
	
	}
	
	
	public void verfyText(String locator, String text) throws IOException
	{
		String s=getElement(locator).getText();
		if (s.equals(text))
		{
			test.log(LogStatus.INFO, "The expected text is** "+ text +" ** and the actual is "+s );
			
		}
		else 
		{
			test.log(LogStatus.FAIL,  "The expected text is ** "+ text +" ** and the actual is "+s );
		}
		
	}
	
	
	public void reportFail(Exception e)
	{
			
		test.log(LogStatus.FAIL, e.getMessage());
		Assert.fail();
	}
	


}
