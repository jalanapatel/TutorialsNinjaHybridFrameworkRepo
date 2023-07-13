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
import com.TutorialsNinja.Qa.Utils.Utility_Class;


public class Register extends Base_Class
{

	WebDriver driver;

	@BeforeMethod
	public void setUp()
	{
		
	    driver =  initializeBrowserAndOpenApplicationURL ("Firefox");
		
		driver.findElement(By.xpath("//span[text() = 'My Account']")).click();

		driver.findElement(By.linkText("Register_Test")).click();
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

		driver.findElement(By.id("input-firstname")).sendKeys("Jammy");

		driver.findElement(By.id("input-lastname")).sendKeys("Patelss");

		driver.findElement(By.id("input-email")).sendKeys(Utility_Class.generateEmailWithTimeStamp()); // Utility_Class
																										// -- //
																										// jalanaPatel0301Sun_Jul_02_14_50_51_EDT_2023@gmail.com

		driver.findElement(By.id("input-telephone")).sendKeys("2356845623");

		driver.findElement(By.id("input-password")).sendKeys("56897453256");

		driver.findElement(By.id("input-confirm")).sendKeys("56897453256");

		driver.findElement(By.xpath("//input[@type='checkbox']")).click();

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		String actual_Success_Heading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();

		// System.out.println(actual_Heading); // Your Account Has Been Created!

		String Expected_Heading = "Your Account Has Been Created!";

		// Verify Account is Created Successfully, Using Assertion

		// Assert.assertTrue(driver.findElement(By.xpath("//div[@id='content']/h1")).isDisplayed(),
		// "Account is not Created.....!!!"); // 1st Way

		Assert.assertEquals(actual_Success_Heading, Expected_Heading,
				"Account Sucees page is not Displayed, Account is not Created.......");

	}

//  TestCase : 02   ---   Verify Registering AnAccount With All_Fields					

	@Test(priority = 2)
	public void verifyRegisteringAnAccountwithAllFields() 
	{
		driver.findElement(By.id("input-firstname")).sendKeys("Jammy");

		driver.findElement(By.id("input-lastname")).sendKeys("Patelss");

		driver.findElement(By.id("input-email")).sendKeys(Utility_Class.generateEmailWithTimeStamp()); // Utility_Class
																										// -- //
																										// jalanaPatel0301Sun_Jul_02_14_50_51_EDT_2023@gmail.com

		driver.findElement(By.id("input-telephone")).sendKeys("2356845623");

		driver.findElement(By.id("input-password")).sendKeys("56897453256");

		driver.findElement(By.id("input-confirm")).sendKeys("56897453256");

		// Include NewsLetter Checkbox

		driver.findElement(By.xpath("//input[@name='newsletter'][@value=1]")).click(); // Select 'Yes' CheckBox

		// driver.findElement(By.xpath("//input[@name='newsletter'][@value=0]")).click();
		// // Select 'No' CheckBox

		driver.findElement(By.xpath("//input[@type='checkbox']")).click();

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		String actual_Success_Heading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();

		// System.out.println(actual_Heading); // Your Account Has Been Created!

		String Expected_Heading = "Your Account Has Been Created!";

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
		
		driver.findElement(By.id("input-firstname")).sendKeys("Jammy");

		driver.findElement(By.id("input-lastname")).sendKeys("Patelss");

		driver.findElement(By.id("input-email")).sendKeys("jalanapatel0301@gmail.com"); // Utility_Class -- //
																						// jalanaPatel0301Sun_Jul_02_14_50_51_EDT_2023@gmail.com

		driver.findElement(By.id("input-telephone")).sendKeys("2356845623");

		driver.findElement(By.id("input-password")).sendKeys("56897453256");

		driver.findElement(By.id("input-confirm")).sendKeys("56897453256");

		// Include NewsLetter Checkbox

		driver.findElement(By.xpath("//input[@name='newsletter'][@value=1]")).click(); // Select 'Yes' CheckBox

		// driver.findElement(By.xpath("//input[@name='newsletter'][@value=0]")).click();
		// // Select 'No' CheckBox

		driver.findElement(By.xpath("//input[@type='checkbox']")).click();

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		String actual_Warning_Message = driver
				.findElement(By.xpath("//div[contains(@class, 'alert alert-danger alert-dismissible')]")).getText();

		// System.out.println(actual_Warning_Message); // Warning: E-Mail Address is
		// already registered!

		Assert.assertTrue(actual_Warning_Message.contains("Warning: E-Mail Address is already registered!"),
				"Warning message regarding Duplicate Email-Address is not Displayed...Register Accout with New_Email Address...");

	}

//  TestCase : 04   ---   Verify Registering AnAccount WithOut Filling Any Detail_Fields					

	@Test(priority = 4)
	public void verifyRegisteringAnAccountWithOutFillingAnyDetails()
	{
		driver.findElement(By.id("input-firstname")).sendKeys("");

		// driver.findElement(By.xpath("//input[@type='submit']")).click(); // Click
		// Continue

		driver.findElement(By.xpath("//input[@value='Continue']")).click(); // Click Continue

// Privacy_Policy Warning Message is Verify,  Using Assertion.....

		String actualPrivacyPolicyWarning = driver
				.findElement(By.xpath("//div[contains(@class, 'alert alert-danger alert-dismissible')]")).getText();

		System.out.println(actualPrivacyPolicyWarning); // Warning: You must agree to the Privacy Policy!

		Assert.assertTrue(actualPrivacyPolicyWarning.contains("Warning: You must agree to the Privacy Policy!"),
				"Privacy_Policy Message is not Displayed....");

//  First_Name Waring Message verify, Using Assertion.....

		String actualFirstNameWarning = driver
				.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();

		System.out.println(actualFirstNameWarning); // First Name must be between 1 and 32 characters!

		Assert.assertEquals(actualFirstNameWarning, "First Name must be between 1 and 32 characters!",
				"First_Name_Warning Message is not Displayed.....");

//  Last_Name Waring Message verify, Using Assertion.....

		String actualLastNameWarning = driver
				.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();

		System.out.println(actualLastNameWarning); // Last Name must be between 1 and 32 characters!

		Assert.assertEquals(actualLastNameWarning, "Last Name must be between 1 and 32 characters!",
				"Last_Name_Warning Message is not Displayed.....");

//  Email Waring Message verify, Using Assertion.....

		String emailWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div"))
				.getText();

		System.out.println(emailWarning); // E-Mail Address does not appear to be valid!

		Assert.assertEquals(emailWarning, "E-Mail Address does not appear to be valid!",
				"Email_Warning Message is not Displayed.....");

//  Telephone Waring Message verify, Using Assertion.....

		String telephoneWarning = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div"))
				.getText();

		System.out.println(telephoneWarning); // Telephone must be between 3 and 32 characters!

		Assert.assertEquals(telephoneWarning, "Telephone must be between 3 and 32 characters!",
				"Telephone_Warning Message is not Displayed.....");

//  Password Waring Message verify, Using Assertion.....

		String passWordWarning = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div"))
				.getText();

		System.out.println(passWordWarning); // Password must be between 4 and 20 characters!

		Assert.assertEquals(passWordWarning, "Password must be between 4 and 20 characters!",
				"Password_Warning Message is not Displayed.....");

	}

}
