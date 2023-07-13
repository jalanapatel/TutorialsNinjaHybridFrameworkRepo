package Experiments;

import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import javax.swing.text.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.TutorialsNinja.Qa.Base.Base_Class;
import com.TutorialsNinja.Qa.PageObjects.AccountPage;
import com.TutorialsNinja.Qa.PageObjects.HomePage;
import com.TutorialsNinja.Qa.PageObjects.LoginPage;
import com.TutorialsNinja.Qa.Utils.Utility_Class;


/*       There are two ways call Property_File, 
						 1) Create_Method --> loadPropertiesFile() Method, in Base_Class       and 
						 2) Create Constructor() with Public access , in Base_Class    
						 
						 
			More Optimeze Code
 */



public class Login_07 extends Base_Class
{
				  LoginPage loginPage;
				        
				
				public Login_07 ()                                                                    // Create Login_Constructor, with and declare super() Keyword --> means super class constructor means Base_Class (extends) is super class of Login_Class 
							{                                                                                         // So, Base_Class Constructor is called, and Property file is Loaded.
								super();
							}

	
	WebDriver driver;                                                             // Declare WevDriver and Properties for Class
   
 

	@BeforeMethod
	public void setUp()
							{
						
							// 	loadPropertiesFile();                                                                                           // Calling method from Base_Class, to load Property_File  -- second way to Load Property_File
						
							    driver =  initializeBrowserAndOpenApplicationURL (proprty.getProperty("browserName"));
								
							    HomePage homePage = new HomePage(driver);                                                             // Create Object of Homeapge_Object
							    
							    homePage.clickOnMyAccountDropMenu();
							    
							    loginPage = homePage.selectLoginOption(); 
						
								
							}

		@AfterMethod
		public void tearDown()
							{
								driver.quit();
							}
						
					//  Create Method  
	
	// TestCase: 01 Login_04 with Valid Credentials....
	
	
					@Test (priority = 1, dataProvider = "credentialsSupplier")
					public void verifyLoginWithValidCredentials(String email, String password)
					{
						
						
						
						loginPage.enterEmailAddress(email);              // Get Email from config.Properties
						
						loginPage.enterPassword(password);             //  Get Password from config.Properties
						
						AccountPage accountPage = loginPage.clickOnLoginButton();
	
																																			// Use Assertion to verify that account page is displayed or not 
		

						
						Assert.assertTrue( accountPage.getDisplayStatusofedtitYourAccountInformationOption() );
						
		//				Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
						
		//				Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), "Edit your account information option is not displayed..");

					}
					
					
					
					
					
					
					@DataProvider  (name = "credentialsSupplier")
					public Object [ ]  [ ]  supplyTestData()                                                                                								 // Create DataProvider Method, return data_Object_Array,  which provides data for test_Method, it is hard_Code data
								{                                                                                                                                     						    // provides email and password, 
								
									Object [ ] [ ] data = {                                                                                                                           // Two dimensional Object_Array ---> Method is run for 3 times , depends on No_Of_Data, in Method.
																			{ "jalanapatel0301@gmail.com" , "123456789"} 
																 
																	};
									
									return data;                                                                                                                                      // Return data_Object 
									
								}
					
/*   ************************************************************************************************************************************************************************  */
	/*
					
					//			@Test  (priority = 6, dataProvider = "supplyTestData")   
					
					@Test  (priority = 6, dataProvider = "credentialsSupplier")                                                                             // Establish connection between, dataProvider Method to provide  data to the testMethod, 
					public void verifyLoginWithCredentials__dataProviderTestData( String email, String password )                                         // Get Test_Data from DataProvider Method,    Create Method with two input Parameter -- Email, Password
								{
						
																								
									driver.findElement(By.id("input-email")).sendKeys(email);
									
									driver.findElement(By.id("input-password")).sendKeys(password);
									
									driver.findElement(By.xpath("//input[@type='submit']")).click();
									
									
									// Capture/Retrieve  Warning for invalid Credentials     -- (//div[@class='alert alert-danger alert-dismissible'])   ( //div[contains(@class,'alert-dismissible')] )
														
															String actual_Warning_Msg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
															
															System.out.println(actual_Warning_Msg);         //  Warning: No match for E-Mail Address and/or Password.
															
									// Use Assertion, to verify Correct Warning message is Captured or not
															
															String  Expected_Warning_Msg = dataProprty.getProperty("email_Password_No_Match_Warning_Msg");                           //  Getting Warning Message from test_Data_Property_File, Configure in Base_Class
															
															Assert.assertTrue(actual_Warning_Msg.contains(Expected_Warning_Msg), "Expected Warning Message is not Displayed....");

			//   Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.   ===> if same email/pwd used morethen 5 time ==> actual_Warning_Message
															
															
								}
		
					
	//				@DataProvider  (name = "credentialsSupplier")
					public Object [ ]  [ ]  supplyTestData()                                                                                								 // Create DataProvider Method, return data_Object_Array,  which provides data for test_Method, it is hard_Code data
								{                                                                                                                                     						    // provides email and password, 
								
									Object [ ] [ ] data = {                                                                                                                           // Two dimensional Object_Array ---> Method is run for 3 times , depends on No_Of_Data, in Method.
																			{ "jammyPatel1245@gmail.com" , "1235689"} 
																		,	{ "jammyjammyPate89567@gmail.com", "8945235751" }
																		,	{ "jammyPtelpatel515759@gmail.com", "7531594862" }  
																	};
									
									return data;                                                                                                                                      // Return data_Object 
									
								}
					
		*/		
					
/*  **************************************************************************************************************************************************************   */							
/*
					// Get Test__Data From ExcelSheet  ----> DataDriven FrameWork
					
			
					//  @Test  (priority = 7, dataProvider = "credentialsSupplier_Excel")                                                                             // Establish connection between, dataProvider Method to provide  data to the testMethod, 
					
					
					@Test  (priority = 7, dataProvider = "credentialsSupplier_Excel")                                                                             // Establish connection between, dataProvider Method to provide  data to the testMethod, 
					public void verifyLoginWithCredentials__ExeclTestData( String email, String password )                                         // Get Test_Data from DataProvider Method,    Create Method with two input Parameter -- Email, Password
								{
									driver.findElement(By.id("input-email")).sendKeys(email);
									
									driver.findElement(By.id("input-password")).sendKeys(password);
									
									driver.findElement(By.xpath("//input[@type='submit']")).click();
									
									
									// Capture/Retrieve  Warning for invalid Credentials     -- (//div[@class='alert alert-danger alert-dismissible'])   ( //div[contains(@class,'alert-dismissible')] )
														
															String actual_Warning_Msg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
															
									// Use Assertion, to verify Correct Warning message is Captured or not
															
															String  Expected_Warning_Msg = dataProprty.getProperty("email_Password_No_Match_Warning_Msg");                           //  Getting Warning Message from test_Data_Property_File, Configure in Base_Class
															
															Assert.assertTrue(actual_Warning_Msg.contains(Expected_Warning_Msg), "Expected Warning Message is not Displayed....");

								}
		
					
					@DataProvider  (name = "credentialsSupplier_Excel")
					public Object [ ]  [ ]  supply_Excel_TestData()                                   								 // Create DataProvider Method, return data_Object_Array,  which provides data from ExcelSheet 
								{                                                                                                          					    // provides email and password, 
								
									Object [ ] [ ] data =   Utility_Class.getTestDataFromExcel("Login_Test");                                               // Two dimensional Object_Array ---> Method is run for 3 times , depends on No_Of_Data, in excel_Sheet.
	
									return data;                                                                                                                                      // Return data_Object 
									
								}
					
				
	*/				
/*  **************************************************************************************************************************************************************   */							

					
					
// TestCase: 02 Login_04 with InValid Credentials.... (get warning message  )  same Credentials...
					
					
					@Test (priority = 3)
					public void verifyLoginwithInvalidCredentials ()
					{
						
			//  Invalid Credentials	
						
				
						
						loginPage.enterEmailAddress( Utility_Class.generateEmailWithTimeStamp() );
					
						loginPage.enterPassword(dataProprty.getProperty("invalid_Password"));
						
						loginPage.clickOnLoginButton();
						
						
						
// Capture/Retrieve  Warning for invalid Credentials     -- (//div[@class='alert alert-danger alert-dismissible'])   ( //div[contains(@class,'alert-dismissible')] )
					
						String actual_Warning_Msg = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
			
// Use Assertion, to verify Correct Warning message is Captured or not
						
						String  Expected_Warning_Msg = dataProprty.getProperty("email_Password_No_Match_Warning_Msg");                           //  Getting Warning Message from test_Data_Property_File, Configure in Base_Class
						
						Assert.assertTrue(actual_Warning_Msg.contains(Expected_Warning_Msg), "Expected Warning Message is not Displayed....");
						
						


/*  Note: After running TestCase 5 times, invalid credentials, on 6th Time TestCase is failed, and  account is Locked  as security feature and warning message is changed as " Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.  "   */ 
					/*     Same invalid credential not allowed more than 5 times.  ---> Locked Account  
					        So to avoid this provide new email address every time,(Email address dynamically changes EveryTime, by append dataTime Stamp     */	
						
					}
/*    **********************************************************************************************************   */			
					
					public String  generateTimeStamp()
					{
						Date date = new Date();
						
						return   date.toString().replace(" ", "_").replace(":", "_");						
			
					}
					
		//			String str_Email = "jaaalaanaaPaatel0301" + generateTimeStamp() + "@gmail.com";
					
/*    **********************************************************************************************************   */			

/*TestCase: 03  Login_04 with InValid Credentials.... (get warning message  )  Different Credentials...  Email Address different everyTime ....... */
					
					
					@Test  (priority = 2)
					public void verifyLoginwithInvalidEmailValidPasswordCredentialsDynamic ()
					{
						
	//  Invalid Credentials 
						
					
						
						loginPage.enterEmailAddress( Utility_Class.generateEmailWithTimeStamp() );                    // Invalid_Email from Utility_Class
				
						loginPage.enterPassword(  proprty.getProperty( "valid_Password" )  );                                             // Get Valid_Password from Property_File ----> testdata.properties File
						
						loginPage.clickOnLoginButton();
						
																		
						
// Capture/Retrieve  Warning for invalid Credentials     -- (//div[@class='alert alert-danger alert-dismissible'])   ( //div[contains(@class,'alert-dismissible')] )
					
			
						String actual_Warning_Msg = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();                                     //    Warning: No match for E-Mail Address and/or Password.
			
						System.out.println(actual_Warning_Msg);                                                                                                                               // "Warning: No match for E-Mail Address and/or Password.";
						
// Use Assertion, to verify Correct Warning message is Captured or not
						
						String  Expected_Warning_Msg = dataProprty.getProperty("email_Password_No_Match_Warning_Msg");                           //  Getting Warning Message from test_Data_Property_File, Configure in Base_Class
						
						Assert.assertTrue(actual_Warning_Msg.contains(Expected_Warning_Msg), "Expected Warning Message is not Displayed....");

						
		}
					
					
/*    *******************************************************************************************************************************   */			

/*TestCase: 03  Login_04 with InValid Credentials.... (get warning message  )  Different Credentials...  Email Address different everyTime ....... */
										
										
										@Test  (priority = 4)
										public void verifyLoginwithValidEmailInvalidPassword ()
														{
																		
								//  Invalid Credentials			
			
											
											loginPage.enterEmailAddress(proprty.getProperty("valid_Email"));                                     //     Get Valid_Email from  Property_File   ----> Config.Properties__File
											
											loginPage.enterPassword(dataProprty.getProperty("invalid_Password"));                         //   Get Invalid_Password from testData.Properties__File
											
											loginPage.clickOnLoginButton() ; 
											
									
												// Capture/Retrieve  Warning for invalid Credentials     -- (//div[@class='alert alert-danger alert-dismissible'])   ( //div[contains(@class,'alert-dismissible')] )
																	
				String actual_Warning_Msg = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
											
																	
												// Use Assertion, to verify Correct Warning message is Captured or not
																		
																		String Expected_Warning_Msg = (dataProprty.getProperty("email_Password_No_Match_Warning_Msg"));                                                    // Get Valid_Password from Property_File.....
																		
																		Assert.assertTrue(actual_Warning_Msg.contains(Expected_Warning_Msg), "Expected Warning Message is not Displayed....");

														}
										
/*  ***************************************************************************************************************************************   */
						@Test		(priority = 5)
						public void verifyLoginWithoutProvidingCredentials ()                               // Directly click on Login_Test Button -- No mandatory Fields are provided.....
						{
					
							
	/*						driver.findElement(By.id("input-email")).sendKeys("");
							
							driver.findElement(By.id("input-password")).sendKeys("");       */
										
							loginPage.clickOnLoginButton();
							
						    String actual_Warning_Message = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
											
							System.out.println(actual_Warning_Message);                     //  Warning: No match for E-Mail Address and/or Password.
							
				
							Assert.assertTrue(actual_Warning_Message.contains("Warning: No match for E-Mail Address and/or Password."),"NO Message Displayed");
		
						}
}