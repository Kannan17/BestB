package reports;

import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentR {
	// Declare the Extent Report object reference
	static ExtentReports extent;
	
	// Make it static so that the method can be called by it's class name
	public static ExtentReports  getInstance()
	{
		// check whether the extent object reference is null , if null go in side the condition
		if (extent==null){
			
			// For unique file name create a date object and Replace the : and space with _
			Date d= new Date();
			String filename=d.toString().replace(":", "_").replace(" ", "_");
			System.out.println(d.toString());
			
			// create a extent Report object
extent=new ExtentReports(System.getProperty("user.dir")+"\\Report\\Report_"+filename+".html",true,DisplayOrder.NEWEST_FIRST);
		}
		// Return the newly created/ old extent report object
		return extent;
		
	}
	
	
	
	
	

}
