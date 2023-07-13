package com.TutorialsNinja.Qa.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility_Class
{
	
	// Create final variable, value of final variable cannot change
	
						public static final int implicit_Wait_Time = 15;
						
						public static final int page_Load_Time = 10;

						private static XSSFRow row;
						
/*                *************************************************************************************************************   */						
	

	public static String generateEmailWithTimeStamp()
				{
							Date date = new Date();
					
							String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
					
							return "jalanaPatel0301" + timeStamp + "@gmail.com";
					
							/*
							               System.out.println(date);                                   // Sun Jul 02 14:25:36 EDT 2023 Current Date and   Time
							  
							              System.out.println(date.toString().replace(" ", "_").replace(":","_"));                                  //            Sun_Jul_02_14_27_48_EDT_2023
							 */
				}
	
/*       ********************************************************************************************************************************   */
	
	
	//         Create method, Read Data from Excel_Sheet,  and return data as Object of two dimensional Array,    take parameter as sheetName 
	//         System.getProperty("user.dir")        --->> Project_Path
	//         \\src\\main\\java\\com\\TutorialsNinja\\Qa\\TestData\\TutorialsNinjaTestData.xlsx"       ----->>  Excel_File_Path
	
		public static Object [ ] [ ] getTestDataFromExcel (String sheetName)
	      	{
			
								File excel_File = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\TutorialsNinja\\Qa\\TestData\\TutorialsNinjaTestData.xlsx" );
							
								XSSFWorkbook  excelWorkBook = null ;                                                          // Create object of XSSFWorkbook, to excess data from WorkBook, ( Outside try_Block, and initialize_variable here, )
								
								
								try
											{
											FileInputStream fis_Excel  = new FileInputStream( excel_File );
											
											excelWorkBook    = new XSSFWorkbook (fis_Excel);     
											}
								catch ( Throwable e)
												{
													e.printStackTrace();
													System.out.println(e);
												}
															                                                                     
								XSSFSheet excel_Sheet = excelWorkBook.getSheet("Login_Test");                                                                        // get excel_Sheet from workBook
								
								int rows = excel_Sheet.getLastRowNum();                                                                                                     // Get No of Rows in Excel_Sheet
								int cols  = excel_Sheet.getRow(0).getLastCellNum();                                                                                     // GEt No Of Cols in Excel_Sheet
								
								
								Object [ ] [ ]  data =  new Object [rows ] [ cols ];		                                                 // Create Object of two dimensional Array
								                                                                                                                                // For Loop is to read rows
								for ( int i = 0; i<rows; i++)                                                                                     // For Loop, extracting row based on Index value of i --> Index Start from 0, and first row = Index Of 1 = Heading of Column (Not Data Here,  )
							     	{
												XSSFRow row = excel_Sheet.getRow(i + 1);
												
												for (int j=0; j<cols; j++)                                                                                    // For Loop, is to read Columns
															{
																XSSFCell  cell = row.getCell( j );                                                                   // for Loop, extracting cell_Value, based on Index_Value of j
																
																CellType cellType = cell.getCellType();                                                        // find type of cell, means find Data-Types of Cell_Value
																
																switch (cellType)
																			{
																						case STRING :                                                                                       // if cellType is String, then,  Returns:the value of the cell as a string 
																								data[ i ][ j ] =  cell.getStringCellValue();
																								break;
																								
																						case NUMERIC:
																								data [ i ] [ j ] =  Integer.toString ( ( int )cell.getNumericCellValue() ) ;                               //   if cell has Numeric data,means if cellType is Numeric,  Returns:the value of the cell as a number
																								break;						                                                                                                    //   Numeric value is return with decimal, which is incorrect, as password like pwd = 12345, Numeric_Value return = 12345.00
																															                                                                                                    //   here, we need to cast into 'int' so we get integer value, ( as whole value, no decimal_Value  , like  '12345')  
																															                                                                                                   // Here, Convert 'int_value' into 'String_Value'    --> Integer.toString()
																								
																						case BOOLEAN:
																								data [ i ] [ j ]  = cell.getBooleanCellValue();
																								break;
																			}
															}
							                   	}
								
				return data;				
	
		}
	
	
	/* **************************************************************************************************************************************************** */
		
		
	public static String  captureScreenShot (WebDriver driver, String testName)
	{
		
		File src_ScreenShot_File = ((TakesScreenshot )driver).getScreenshotAs(OutputType.FILE) ;                                                          //  Take ScreenShot of Failed TestCase...
		
 		String destination_ScreenShot_Path =  System.getProperty("user.dir")+"\\ScreenShots\\" + testName +".png" ;                        // Target ScreenShot_ Folder

		try
		{			
			File trg_ScreenShot_File = new File(destination_ScreenShot_Path);
								
	    	FileHandler.copy(src_ScreenShot_File, trg_ScreenShot_File);                                              // Copy ScreenShot from Source_Location to Destination...
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
		
		
		return destination_ScreenShot_Path;
		
	}
	
	
	
	
	
	
	
	
	

}
