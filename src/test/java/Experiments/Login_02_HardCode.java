package Experiments;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login_02_HardCode
		{
			
		WebDriver driver;                                                      // Declare driver at global level,  So access at anywhere in class
		  
		
		@BeforeMethod                                                     // Create setUp Method, with @BeforeMethod Annotation, So, common code execute before each Method, and not required to repeat code in each Method....
		public void setUp()
		{
			
			String  browserName = "chrome";
			
			if ( browserName.equals("chrome"))
			{
				driver = new ChromeDriver();
			}
			else if ( browserName.equals("fireFox"))
			{
				driver = new FirefoxDriver();
			}
			else if ( browserName.equals("edge"))
			{
				driver = new EdgeDriver();
			}
			
			else if ( browserName.equals("safari"))
			{
				driver = new SafariDriver();
			}
			
			
			
			// driver.get("https://www.google.com/");
			
			driver.manage().window().maximize();                                                                                  // Maximize window
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));                               // Add waits
			
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));                        
			
			driver.get("https://tutorialsninja.com/demo/");                                                      // Open URL 
			
			                                                                                                                               //span[text() = 'My Account']   --.> My Account_XPath
			
			driver.findElement(By.xpath("//span[text() = 'My Account']")).click();

		
			driver.findElement(By.linkText("Login_04")).click();
			
		}
		
		
		
		
		@AfterMethod
		public void tearDown()                                           // Create Method to close driver, with @AfterMethod Annotation, so driver.quit() is not required in each Method
		{                                                                                // close browser/driver is closed evenThough method is failed....
			driver.quit();
		}
	
	
	
					//  Create Method  
	
	// TestCase: 01 Login_04 with Valid Credentials....
	
	
					@Test (priority = 1)
					public void verifyLoginWithValidCredentials()
					{
						
						
						driver.findElement(By.id("input-email")).sendKeys("jalanapatel0301@gmail.com");
						
						driver.findElement(By.id("input-password")).sendKeys("123456789");
						
						driver.findElement(By.xpath("//input[@type='submit']")).click();
						
																																			// Use Assertion to verify that account page is displayed or not 
						
						Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
						
		//				Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), "Edit your account information option is not displayed..");
											
						
	
					}
/*  **************************************************************************************************************************************************************   */							

// TestCase: 02 Login_04 with InValid Credentials.... (get warning message  )  same Credentials...
					
					
					@Test (priority = 3)
					public void verifyLoginwithInvalidCredentials ()
					{
						
			//  Invalid Credentials			
						driver.findElement(By.id("input-email")).sendKeys("jaaalaanaaPaatel0301@gmail.com");
						
						driver.findElement(By.id("input-password")).sendKeys("123456789");
						
						driver.findElement(By.xpath("//input[@type='submit']")).click();
						
						
// Capture/Retrieve  Warning for invalid Credentials     -- (//div[@class='alert alert-danger alert-dismissible'])   ( //div[contains(@class,'alert-dismissible')] )
					
						String actual_Warning_Msg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
						
// Use Assertion, to verify Correct Warning message is Captured or not
						
						String Expected_Warning_Msg = "Warning: No match for E-Mail Address and/or Password.";
						
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
						driver.findElement(By.id("input-email")).sendKeys("jaaalaanaaPaatel0301" + generateTimeStamp() + "@gmail.com"  );
						
						driver.findElement(By.id("input-password")).sendKeys("123456789");
						
						driver.findElement(By.xpath("//input[@type='submit']")).click();
						
						
// Capture/Retrieve  Warning for invalid Credentials     -- (//div[@class='alert alert-danger alert-dismissible'])   ( //div[contains(@class,'alert-dismissible')] )
					
						String actual_Warning_Msg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
						
// Use Assertion, to verify Correct Warning message is Captured or not
						
						String Expected_Warning_Msg = "Warning: No match for E-Mail Address and/or Password.";
						
						Assert.assertTrue(actual_Warning_Msg.contains(Expected_Warning_Msg), "Expected Warning Message is not Displayed....");
						
					
	
		}
					
					/*    **********************************************************************************************************   */			

/*TestCase: 03  Login_04 with InValid Credentials.... (get warning message  )  Different Credentials...  Email Address different everyTime ....... */
										
										
										@Test  (priority = 4)
										public void verifyLoginwithValidEmailInvalidPassword ()
										{
											
	//  Invalid Credentials											
											driver.findElement(By.id("input-email")).sendKeys("jalanapatel0301@gmail.com"  );
											
											driver.findElement(By.id("input-password")).sendKeys("12125654789333");
											
											driver.findElement(By.xpath("//input[@type='submit']")).click();
											
											
					// Capture/Retrieve  Warning for invalid Credentials     -- (//div[@class='alert alert-danger alert-dismissible'])   ( //div[contains(@class,'alert-dismissible')] )
										
											String actual_Warning_Msg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
											
					// Use Assertion, to verify Correct Warning message is Captured or not
											
											String Expected_Warning_Msg = "Warning: No match for E-Mail Address and/or Password.";
											
											Assert.assertTrue(actual_Warning_Msg.contains(Expected_Warning_Msg), "Expected Warning Message is not Displayed....");
											
										
						
							}
										
/*  ***************************************************************************************************************************************   */
						@Test		(priority = 5)
						public void verifyLoginWithoutProvidingCredentials ()
						{
					
							
							driver.findElement(By.id("input-email")).sendKeys("");
							
							driver.findElement(By.id("input-password")).sendKeys("");
							
							driver.findElement(By.xpath("//input[@type='submit']")).click();
					
							String actual_Warning_Message = driver.findElement(By.xpath("//div[contains(@class,'alert alert-danger alert-dismissible')]")).getText();
							
							System.out.println(actual_Warning_Message);
							
							//  Warning: No match for E-Mail Address and/or Password.
							
					    	Assert.assertTrue(actual_Warning_Message.contains("Warning: No match for E-Mail Address and/or Password."),"NO Message Displayed");
		
						}
}