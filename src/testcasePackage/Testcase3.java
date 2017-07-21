package testcasePackage;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import reports.ExtentR;
import basePackage.Base;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Testcase3 extends Base{
	
	
	
	
	@Test
	public void tc3() throws IOException
	{
		System.out.println("tc3()-start");

		rep=ExtentR.getInstance();
		test=rep.startTest("Testcase3");
		test.log(LogStatus.INFO, "Testcase3 test started");
	
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe" );
		driver= new ChromeDriver();
		test.log(LogStatus.INFO, "ChromeDriver opened");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Window Maximized");
		driver.get("http://www.bestbuy.com/");
		takeScreenshot1();
		driver.findElement(By.xpath("//html/body/div/div/table[2]/tbody/tr/td/table[1]/tbody/tr[2]/td/a")).click();
		takeScreenshot1();
		driver.findElement(By.name("button")).click();
		
		driver.get("http://www.bestbuy.com/");
		
				
		
		takeScreenshot1();
						
		Select s= new  Select(driver.findElement(By.name("select_locale")));
		s.selectByVisibleText("United States - English");
		driver.findElement(By.xpath("//div[@class='go_button']")).click();		
	
		test.log(LogStatus.INFO, "Go button is clicked");
		
		takeScreenshot1();
		
		test.log(LogStatus.INFO, "Testcase3 test end");
	}

	
	@AfterTest
	public void end1()
	{
	rep.endTest(test);
	rep.flush();
	driver.quit();
	}

	
	

}
