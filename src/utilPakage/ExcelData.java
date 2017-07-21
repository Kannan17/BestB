package utilPakage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;


public class ExcelData {
	
	@Test
	public static Object[][] getExcelData(String testCname) throws EncryptedDocumentException, InvalidFormatException, IOException
	
	{
		
		// Get the file to the reference variable fs
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\data.xlsx");
		//Create a workbook in POI with fs data
	    Workbook wb =  WorkbookFactory.create(fs);
	    // identify the sheet from which the data needs to be taken
	    Sheet sh = wb.getSheet("sheet1");
	    
	    // create a reference variable for Row
	    Row row;
	    
	    // mention the test case name ( this can be parameterized 
	    String testCaseName=testCname;
	    
	    // Create a variable for staring the excel data and initialize to null
	    String cellData = null;
	    
	    // Initially consider the TestCase name found in 0th row
	    int testcaseTitleRow=0;
	    
	    // Execute the below loop until the TestCase name not equal to the particular cell data
	    while (!(testCaseName.equals(cellData)))
	    {	    // get the first row of the excel sheet when the loop execute at first time	    	
	    	row=sh.getRow(testcaseTitleRow);
	    	// Check if the row is not null and the corresponding cell is not null
	    	if (!(row==null)&&(!(row.getCell(0)==null))){
	    		// when the cell has value convert to string and store in cellData variable
	    		cellData=row.getCell(0).toString();
	    	}
	    	System.out.println(testcaseTitleRow);
	    	// increase the value of testcaseTitleRow by 1 to move to next row
	    	testcaseTitleRow++;   	
	    }
	       // TestCase name found row is printed
	    System.out.println("The test case found in row number "+testcaseTitleRow);
	    
	    System.out.println();
	    
	    //To find the number of rows
	    
	    //Test case title and column rows are subtracted by 1 for array usage
	    int testStartingRow=testcaseTitleRow-1;
		int testColumnNameRow=testcaseTitleRow;
		
		// initially consider the number of rows are zero
		int noOfRows=0;
		/*Start counting the row from column name row ( we can start the counting from data starting row also like below 
		row=sh.getRow(testColumnNameRow+1); it is up to us how we are calculating, if the do like this then we don't need to do 
		 noOfRows-1 at the end of the loop*/
		row=sh.getRow(testColumnNameRow);
			
		// check if the row and the corresponding cell is not null
		while (!(row==null)&&(!(row.getCell(0)==null)))
				{
			System.out.println(noOfRows);
			// increase the count by 1 for noOfRows to know the no of rows
			noOfRows++;
			// increment the value of testColumnNameRow by 1 to move the to next row each time
			testColumnNameRow++;
			// Get the next row data to check if the it is null or not
			row=sh.getRow(testColumnNameRow);
				}
		
		// Print the no of rows
		System.out.println("Total number of rows "+(noOfRows-1));
	 
	    
		
		//to find the number of column
		// since the value of testColumnNameRow was changed while finding no of rows. Initialize it with original value
		testColumnNameRow=testcaseTitleRow;
		
		// Get the first row of the test case
		row=sh.getRow(testColumnNameRow);
		
		// Initialize the no of columns to zero
		int noofColumns=0;
		// check if the column of the row is null. proceed if not null
		while ( !(row.getCell(noofColumns)==null))
		{
			// increment the "noofColumns" to move to the next column 
			noofColumns++;
		}
		
		//Print the no of columns
		System.out.println("No of column in the test case is "+ noofColumns);
	    
	    	 System.out.println(noofColumns +"************");
	    	 
	    	 //declare the tow dimension array
		Object[][] data=new Object [noOfRows-1][1];
		
		System.out.println(testColumnNameRow+1);
		// Initialize k as zero for increment the row value
		int k=0;
		
		 Hashtable<String,String> table=null;
		// get the data from first row of the test case and till the no of row available
		for (int i=testColumnNameRow+1;i<noOfRows+testColumnNameRow;i++)
		{
			// get the row 
				row=sh.getRow(i);	
				table = new  Hashtable<String,String> () ;
				
				// get the column of the row
			for(int j=0;j<noofColumns;j++)
			{
				
				
				 Row CellGetContent2 = sh.getRow(testColumnNameRow); 
				  String key= CellGetContent2.getCell(j).toString();
				  System.out.println(key);
				  // Getting the actual value
				  Row CellGetContent = sh.getRow(i); 
				String value= CellGetContent.getCell(j).toString();
				System.out.println(value);
				
				table.put(key, value);
				
				// store the data in corresponding row and coloumn
				
				//data[k][j]=row.getCell(j).toString();
			//	System.out.print(data[k][j]+"  ");
			}
			data[k][0]=table;
			
			// increment k by 1 to move to next row
			k++;
	//	System.out.println();
		}
	//		System.out.println();
		
		// rerun the data to calling method
		
			return data;
		
		
		
		
	    
	}
	
	

}
