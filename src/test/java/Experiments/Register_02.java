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

import com.TutorialsNinja.Qa.Base.Base_Class;
import com.TutorialsNinja.Qa.PageObjects.AccountSuccessPage;
import com.TutorialsNinja.Qa.PageObjects.HomePage;
import com.TutorialsNinja.Qa.PageObjects.RegisterPage;
import com.TutorialsNinja.Qa.Utils.Utility_Class;

/*
      dataproprty ===>>>    testData.properties_File
      proprty  ====>>>   config.properties_File
 
 */
public class Register_02 extends Base_Class
{

	WebDriver driver;
	
	public Register_02()                             // Create Register_Constructor, with super() Keyword ---> means super_Class constructor means Base_Class (extends) is super class of Register_Class
	{
			super();                                  // So, Base_Class Constructor is called, and Property file is loaded..
	}
	


	@BeforeMethod
	public void setUp()
	{
		
	    driver =  initializeBrowserAndOpenApplicationURL (proprty.getProperty("browserName"));
	    
	    HomePage homePage = new HomePage(driver);
	    
	    homePage.clickOnMyAccountDropMenu();
	    
	    homePage.selectRegisterOption();
	    
	
	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

//  TestCase : 01   ---   Verify Registering AnAccount With Mandatory_Fields

	@Test(priority = 1)
	public void verifyRegisteringAnAccountwithMandatoryFields()
	{

		RegisterPage registerPage = new RegisterPage(driver);
		
		registerPage.enterFirstName(dataProprty.getProperty("first_Name"));
		
		registerPage.enterLastName(dataProprty.getProperty("last_Name" ));
		
		registerPage.enterEmailAddress(Utility_Class.generateEmailWithTimeStamp());    		 // Utility_Class
																										// -- //
																										// jalanaPatel0301Sun_Jul_02_14_50_51_EDT_2023@gmail.com

		registerPage.enterTelephoneNumber(dataProprty.getProperty("telephone_No"));
		
		registerPage.enterPassword(proprty.getProperty("valid_Password"));
		
		registerPage.enterconfirmPassword(proprty.getProperty("valid_Password"));
		
		registerPage.selectPrivactPolicy();
		
		registerPage.clickOnContinueButton();
		

		

		AccountSuccessPage accountSuccessPage = new AccountSuccessPage (driver);
		
		String actual_Success_Heading = accountSuccessPage.retrieveAccountSuccessPageHeading();

		

		// System.out.println(actual_Success_Heading);                // Your Account Has Been Created!

		String Expected_Account_Success_Heading = dataProprty.getProperty("account_Success_Created_Heading");

		// Verify Account is Created Successfully, Using Assertion

		// Assert.assertTrue(driver.findElement(By.xpath("//div[@id='content']/h1")).isDisplayed(),
		// "Account is not Created.....!!!"); // 1st Way

		Assert.assertEquals(actual_Success_Heading, Expected_Account_Success_Heading, 	"Account Sucees page is not Displayed, Account is not Created.......");

	}

//  TestCase : 02   ---   Verify Registering AnAccount With All_Fields					

	@Test(priority = 2)
	public void verifyRegisteringAnAccountwithAllFields() 
	{
		
		RegisterPage registerPage = new RegisterPage (driver);
		
		registerPage.enterFirstName(dataProprty.getProperty("first_Name"));                      // Getting first_Name from testData.properties Files, Configure in Base_Class

		registerPage.enterLastName(dataProprty.getProperty("last_Name"));                      // Getting last_Name from testData.properties Files, Configure in Base_Class

		registerPage.enterEmailAddress(Utility_Class.generateEmailWithTimeStamp()); // Utility_Class
																										// -- //
																										// jalanaPatel0301Sun_Jul_02_14_50_51_EDT_2023@gmail.com

		registerPage.enterTelephoneNumber(dataProprty.getProperty("telephone_No"));                   // Getting telephone_No from testData.properties Files, Configure in Base_Class

		registerPage.enterPassword(proprty.getProperty("valid_Password"));
		
		registerPage.enterconfirmPassword(proprty.getProperty("valid_Password"));
		
		
		// Include NewsLetter Checkbox
		
		registerPage.selectYesNewsLetterOption();                              // Select 'Yes' CheckBox

																		// driver.findElement(By.xpath("//input[@name='newsletter'][@value=0]")).click();
																		// // Select 'No' CheckBox
		 registerPage.selectPrivactPolicy();
		
		registerPage.clickOnContinueButton();
		
		
		
		
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage (driver);
		
		String actual_Success_Heading = accountSuccessPage.retrieveAccountSuccessPageHeading();

																			 // System.out.println(actual_Heading); // Your Account Has Been Created!

		String Expected_Heading = dataProprty.getProperty("account_Success_Created_Heading") ;            // Getting account_Success_Created_Heading  from testData.properties Files, Configure in Base_Class


		// Verify Account is Created Successfully, Using Assertion

		// Assert.assertTrue(driver.findElement(By.xpath("//div[@id='content']/h1")).isDisplayed(),
		// "Account is not Created.....!!!"); // 1st Way

		Assert.assertEquals(actual_Success_Heading, Expected_Heading,
				"Account Sucees page is not Displayed, Account is not Created.......");

	}

//  TestCase : 03   ---   Verify Registering AnAccount With Mandatory_Fields					

	@Test(priority = 3)
	public void verifyRegisteringAnAccountwithExistingEmailAddress()
	{
	
		RegisterPage registerPage = new RegisterPage(driver);
		
		registerPage.enterFirstName(dataProprty.getProperty("first_Name"));
		
		registerPage.enterLastName(dataProprty.getProperty("last_Name"));
		
		registerPage.enterEmailAddress(proprty.getProperty("valid_Email"));

		

																																							// Utility_Class -- //
																						// jalanaPatel0301Sun_Jul_02_14_50_51_EDT_2023@gmail.com

		registerPage.enterTelephoneNumber(dataProprty.getProperty("telephone_No"));
		
		registerPage.enterPassword(proprty.getProperty("valid_Password"));
		
		registerPage.enterconfirmPassword(proprty.getProperty("valid_Password"));

		// Include NewsLetter Checkbox

		registerPage.selectYesNewsLetterOption();                                         // Select 'Yes' CheckBox

		// driver.findElement(By.xpath("//input[@name='newsletter'][@value=0]")).click();
		// // Select 'No' CheckBox

		
		
		registerPage.selectPrivactPolicy();
		
		registerPage.clickOnContinueButton();
		
		String actual_Warning_Message = registerPage.retrieveDuplicateEmailAddressWarning();

												// System.out.println(actual_Warning_Message);                               // Warning: E-Mail Address is  already registered!

		String Expected_duplicate_Email_Warning_Msg =  dataProprty.getProperty("duplicate_Email_Warning_Msg") ;            // Getting account_Success_Created_Heading  from testData.properties Files, Configure in Base_Class

		
		
		Assert.assertTrue(actual_Warning_Message.contains(Expected_duplicate_Email_Warning_Msg),
				"Warning message regarding Duplicate Email-Address is not Displayed...Register Accout with New_Email Address...");

	}

//  TestCase : 04   ---   Verify Registering AnAccount WithOut Filling Any Detail_Fields					

	@Test(priority = 4)
	public void verifyRegisteringAnAccountWithOutFillingAnyDetails()
	{
		
	//	driver.findElement(By.id("input-firstname")).sendKeys("");        

		// driver.findElement(By.xpath("//input[@type='submit']")).click(); // Click
		// Continue
		
		
		RegisterPage registerPage = new RegisterPage(driver);
		
		registerPage.clickOnContinueButton();                                   // Directly Click Continue

		
// Privacy_Policy Warning Message is Verify,  Using Assertion.....

		String actualPrivacyPolicyWarning = registerPage.retrievePrivacyPolicyWarning();
	
		System.out.println(actualPrivacyPolicyWarning); // Warning: You must agree to the Privacy Policy!
		
		String Expected_privacy_Warning_Msg =  dataProprty.getProperty("privacy_Policy_Warning") ;            // Getting account_Success_Created_Heading  from testData.properties Files, Configure in Base_Class

		Assert.assertTrue(actualPrivacyPolicyWarning.contains(Expected_privacy_Warning_Msg),		"Privacy_Policy Message is not Displayed....");

		
//  First_Name Warning Message verify, Using Assertion.....
		
		
		String actualFirstNameWarning = registerPage.retrieveFirstNameWarning();

		System.out.println(actualFirstNameWarning); // First Name must be between 1 and 32 characters!
		
		String Expected_First_Name_Warning_Msg =  dataProprty.getProperty("first_Name_Warning") ;            // Getting account_Success_Created_Heading  from testData.properties Files, Configure in Base_Class

		Assert.assertEquals(actualFirstNameWarning,   Expected_First_Name_Warning_Msg ,	"First_Name_Warning Message is not Displayed.....");

		
//  Last_Name Warning Message verify, Using Assertion.....
		
		 String actualLastNameWarning = registerPage.retrieveLastNameWarning() ;

		System.out.println(actualLastNameWarning); // Last Name must be between 1 and 32 characters!
		
		String Expected_Last_Name_Warning_Msg =  dataProprty.getProperty("last_Name_Warning") ;            // Getting account_Success_Created_Heading  from testData.properties Files, Configure in Base_Class

		Assert.assertEquals(actualLastNameWarning ,  Expected_Last_Name_Warning_Msg,  	"Last_Name_Warning Message is not Displayed.....");

		
//  Email Warning Message verify, Using Assertion.....
		
		 String actual_emailWarning = registerPage.retrieveEmailWarning() ;

		System.out.println(actual_emailWarning); // E-Mail Address does not appear to be valid!
		
		String Expected_Email_Warning_Msg =  dataProprty.getProperty("email_Warning") ;            // Getting account_Success_Created_Heading  from testData.properties Files, Configure in Base_Class

		Assert.assertEquals(actual_emailWarning,  Expected_Email_Warning_Msg ,		"Email_Warning Message is not Displayed.....");

		
//  Telephone Warning Message verify, Using Assertion.....
		
		 String actualTelaphoneWaring = registerPage.retrieveTelephoneWarning() ;

		System.out.println(actualTelaphoneWaring); // Telephone must be between 3 and 32 characters!
		
		String Expected_telephone_Warning_Msg =  dataProprty.getProperty("telephone_Warning") ;            // Getting account_Success_Created_Heading  from testData.properties Files, Configure in Base_Class

		Assert.assertEquals(actualTelaphoneWaring,  Expected_telephone_Warning_Msg , 	"Telephone_Warning Message is not Displayed.....");

		
//  Password Warning Message verify, Using Assertion.....
		
		 String actualPasswordWarning = registerPage.retrievePasswordWarning() ;

		System.out.println(actualPasswordWarning); // Password must be between 4 and 20 characters!
		
		String Expected_Password_Warning_Msg =  dataProprty.getProperty("password_Warning") ;            // Getting account_Success_Created_Heading  from testData.properties Files, Configure in Base_Class

		Assert.assertEquals(actualPasswordWarning,  Expected_Password_Warning_Msg, 	"Password_Warning Message is not Displayed.....");

	}

}
