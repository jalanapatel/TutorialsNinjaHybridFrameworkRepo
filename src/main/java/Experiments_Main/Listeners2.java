package Experiments_Main;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

import com.TutorialsNinja.Qa.Utils.Extent_Reporter;
import com.aventstack.extentreports.ExtentReports;

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
	
*/
public class Listeners2 implements ITestListener
{
	@Override
	public void onStart(ITestContext context)                                            // Execution of Project Start    ----> Invoked before running all the test methods belonging to the classes inside the <test>tag and calling all their Configuration methods.
	{
				System.out.println("Execution of Project Tests Started...");
				
				
	}
	
	@Override
	public void onTestStart(ITestResult result)                                    // for each and every Test method, 
			{
				
				String testName = result.getName();                                                                               //  .getName() Method is    returned  Name of Test_Method                                                     .
				System.out.println( testName + " Started Executing.....");
				
			}

	@Override
	public void onTestSuccess(ITestResult result)                                             // Invoked each time a test Pass successfully...
			{
					String testName = result.getName();                                  // here, returned Name of Pass Tests
					
					System.out.println( testName +  " Pass  --> Successfully executed....");

			}

	@Override
	public void onTestFailure(ITestResult result)                                                  //   Invoked each time a test fails.
			{
				 String testName = result.getName();                                                               //  here, returned Name of Failed Tests
				 
				 System.out.println( testName + "  Failed...");
				 
				 System.out.println( result.getThrowable());                                      // Print Exception, detailed...   find reason of Failed... TestCase...  ---> Failure Detailed Print
				 
				 System.out.println(" ScreenShot taken" );
				 
			}
				 
	public void onTestSkipped(ITestResult result)                                         // Invoked each time, a test is skipped.
			{
				String testName = result.getName();
				
				System.out.println( testName + "  Skipped");
				
				System.out.println( result.getThrowable());                                 //  Print Exception detailed, reason of Skipped TestCase... ---> Skipped Detailed Print
			}

	

	@Override
	public void onFinish(ITestContext context)                                  //  Invoked after all the test methods belonging to the classes inside the <test> tag have run and all their Configuration methods have been called.
			{
				System.out.println(" Finished executing Project Tests.....");
			}

			
			
	
	
	
	
	
	
	
	
}
