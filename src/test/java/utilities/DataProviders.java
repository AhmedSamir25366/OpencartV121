package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

// This class will contain all Data Provider Methods

public class DataProviders {
	// Data Provider Method 1
	
	@DataProvider(name = "LoginData") // this name should be different from one data provider method to another data provider method
	
	public String [][] getData() throws IOException // we will get the data from the excel file and store it in two dimensional array
	{
		String path = ".\\testData\\testdata.xlsx"; // taking excel file from testData folder (location of excel file)
		
		ExcelUtility xlutil = new ExcelUtility(path); // creating an object for XLUtility class because it contains all the utilities methods
		
		int totalrows = xlutil.getRowCount("Sheet1"); // sheetName --> it will get the rows number in our excel sheet 
		int totalcols = xlutil.getCellCount("Sheet1", 1); // sheetName + row number  ---> it will get the column number in our excel sheet 
		
		String logindata[][] = new String [totalrows][totalcols]; // created for 2 dimensional array which can store 
		
		// this loop will read the data from the excel sheet and copy the data in the two dimensional array
		// i is row and row starts from index 0 but we started from index 1 to ignore the first row (Titles) and outer for loop represents the rows
		for(int i=1; i<=totalrows; i++)// 
		{
			for(int j=0; i<=totalcols; j++) // j is column and j starts from index 0 and inner for loop represents the columns
			{
				logindata[i -1] [j] = xlutil.getCellData("Sheet1", i, j); // we wrote [i -1] to store the first row value in index 0 not 1 in 2 d array
			}
		}
		return logindata; // returning two dimensional array
	}
	
	
	// Data Provider Method 2
	
	// Data Provider Method 3
	
	// Data Provider Method 4
	
	

}
