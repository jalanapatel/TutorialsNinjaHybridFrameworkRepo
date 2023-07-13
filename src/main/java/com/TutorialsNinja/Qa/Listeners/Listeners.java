package com.TutorialsNinja.Qa.Listeners;

import java.awt.Desktop;
import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

import com.TutorialsNinja.Qa.Utils.Extent_Reporter;
import com.TutorialsNinja.Qa.Utils.Utility_Class;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/*
	ITestListner belong to TestNG, when testCase is failed, with help of ITestListner we can take screenshot of Failed Test.
	
	when execute test like start, skip or stop, pass, fail ,will fire events and this events are listen by ITestListner, and appropriate method from ITestListener is invoked, 
	and handle events.
	
	main use of ITestListener is takes screenshot, in case of test failure. so when test is failed, appropriate events is fire, and particular method from ITestListener is invoked and takes Screenshot.
	
	ITestLister is Per-define Interface in TestNG.
	
	ITestLister is extend to ITestNGListener__Interface   --->   interface ITestListener extends ITestNGListener
	
	Right-Click ---> Source --> Override / implements Methods.
	
	ITestResult --> This class describes the result of a test.
	
	ITestContext ---> This class defines a test context which contains all the information for a given test run.
	 							An instance of this context is passed to the test listeners so they can query information about their environment.
	 							
	 To Run/execute/implement ITestListener_Class,  with testng.xml file   --> here, ITestListner_testng.xml  ---> Add listener_Tag  ---> listener_Tag is inform testNG, that invoke listner_Class_Method, when events are fire by Methods in Class.
	   <listeners>
				<listener class-name = "com.TutorialsNinja.Qa.Listeners.Listeners" />                                  <!-- Path of Listener_Clas from Package -->
		</listeners>
		
   (  Listener_Tag make  connection between event in class and method of Listner_Class, 
	   
	Without listener_Tag in testNG.xml file, no connection between event in class and Method of listener_Class, So not invoke method of Listener_Class, when event fired by class_Method  --- like Pass, Fail, Skipped, Start, End...
	
	ExtentTest is Class ---> Defines a test. You can add logs, snapshots, assign author and categories to a test and its children. 
	
	To Take ScreenShot, TypeCast driver,  we can retrieve/ get driver from Method, like onFailureMethod() is invoked, by event of failed testCase, and that failed testCase_Method has to Pass driver, 
	driver is save into result_object of ITestResult_Class, so we can retrieve driver from result_object ---> result.getTestClass().realClass().getDeclaredField("driver").get(result.getInstance() )
	
	very Imp: make WebDrive as public in each_Class so driver from testMethod is retrieve/pass in to Listener_Class (otherwise driver will not retrieve/pass to Listener_Class )
	
	.flush() Method --  Very Important  --  Writes test information from the started reporters to their output view , ExtentSparkReporter: flush output to HTML file

	.flush() method: Convert test_Result_Report into HTML format. if we didnot use flush , then all details of test_Result is not added to, extent_Report. 
	
	
*/



public class Listeners implements ITestListener
{
	ExtentReports extentReport;                                                     // Global Declaration of ExtentReports_Class
	
	ExtentTest extent_Test ; 
	
	String testName; 
	
	@Override
	public void onStart(ITestContext context)                                            // Execution of Project Start    ----> Invoked before running all the test methods belonging to the classes inside the <test>tag and calling all their Configuration methods.
	{
			//	System.out.println("Execution of Project Tests Started...");
				
			 extentReport = Extent_Reporter.generateExtentReport();                                  //  Create extent_Report by invoke Method from Extent_Reporter.
	}
	
	
	
	@Override
	public void onTestStart(ITestResult result)                                    // for each and every Test method, 
			{
				
				testName = result.getName();                                                                               //  .getName() Method is    returned  Name of Test_Method                                                     .
		
				extent_Test  = extentReport.createTest(testName);                  // .createTest() Method is returned, object of ExtentTest_Class  ---> Defines a test. You can add logs
				
				extent_Test.log(Status.INFO,  testName + " Started Executing.....");
				
	
				
			}

	
	
	@Override
	public void onTestSuccess(ITestResult result)                                             // Invoked each time a test Pass successfully...
			{
					testName = result.getName();                                  // here, returned Name of Pass Tests
					
					extent_Test.log(Status.PASS, testName +  " Pass  --> Successfully executed....");
					
					
			
			}

	
	
	@Override
	public void onTestFailure(ITestResult result)                                                  //   Invoked each time a test fails. and Takes ScreenShot of Failed TestCase
			{
				  testName = result.getName();                                                  //  here, returned Name of Failed Tests
					 
				 																											// Capture  ScreenShot of Failed TestCase
				 WebDriver driver = null;                                                      
				 				
						 try
						 {
							driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance()) ;                       // Retrieve ''driver" from result_Object if ITestReault_Class, ( from failed_TestCase ) -- TypeCast Driver
						 } 
						 
						 catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
						 {
							 				e.printStackTrace();
						 }                 

						 String destination_ScreenShot_Path = Utility_Class.captureScreenShot(driver, testName);                          // Capture ScreenShot of Failed_TestCase, invoke method from Utility_Class
			 
				 extent_Test.addScreenCaptureFromPath(destination_ScreenShot_Path);                  					                         // Attach ScreenShot to extentReport from destinaton_Folder
				 
				 extent_Test.log(Status.INFO, result.getThrowable()) ;                                   		                       // Getting Log Information for Test_Fail and Exception Fail.
				 																																		// Print Exception, detailed...   find reason of Failed... TestCase...  ---> Failure Detailed Print
	
				 extent_Test.log(Status.FAIL,   testName  + "  ====>>>   Failed" ) ;				
				

				 
			}
				 
	public void onTestSkipped(ITestResult result)                                         // Invoked each time, a test is skipped.
			{                 
				testName = result.getName();
				
				extent_Test.log(Status.INFO,    result.getThrowable()) ;                                     //  Print Exception detailed, reason of Skipped TestCase... ---> Skipped Detailed Print
				
				extent_Test.log(Status.SKIP,  testName + "  ===>>>   Skipeed"); 
			                        
			}

	

	@Override
	public void onFinish(ITestContext context)                                  //  Invoked after all the test methods belonging to the classes inside the <test> tag have run and all their Configuration methods have been called.
			{
		
					extentReport.flush();                                                                 // .flush() method  --   Writes test information from the started reporters to their output view
		
																																		// if we did not use flush() method, --> All details of test / test_Log / test_Info,  will not added to reports
	
		
					String path_Of_Extent_Report = System.getProperty("user.dir")+ "\\test-output\\ExtentReports\\extentReport.html";
					
					File extentReport_File = new File(path_Of_Extent_Report);
					
					
					try
					{
									Desktop.getDesktop().browse(extentReport_File.toURI());																		// AutoLaunching of Extent_Report
					}
			catch (Throwable e)
					{
								e.printStackTrace();
								System.out.println(e);
					}
			}

			
			
	
	
	
	
	
	
	
	
}
