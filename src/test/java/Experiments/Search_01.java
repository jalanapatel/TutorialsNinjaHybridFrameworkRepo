package Experiments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TutorialsNinja.Qa.Base.Base_Class;

public class  Search_01 extends Base_Class                                                                          // Inherits Base_Class
{
			WebDriver driver;                                                                                               // Declare global variable
			
			public Search_01()                                              // Create Search_Constructor of Search_Class, with super keyword, super_Class constructor is created, means Base_Class (extended) is Super_Class of Search_Class
			{
				super();                                             // So, Base_Class Constructor is called, and Property file is loaded..
			}
			
			
			
			@BeforeMethod
			public void  setUp()																									// Create SetUp() Method, Launch WebSite...
					{
						driver = initializeBrowserAndOpenApplicationURL(proprty.getProperty("browserName"));
					}
	
		@AfterMethod
			public void tearDown()
					{
						driver.quit();
					}
			
			
			
//  TestCase:01  Verify Search_Test Functionality , Valid__Product..		

		@Test (priority = 1)
		public void verifySearchWithValidProduct()
		{
			
			driver.findElement(By.name("search")).sendKeys(dataProprty.getProperty("valid_Product"));                                 // Enter value in search bar
			
		//	 driver.findElement(By.xpath(" //button[@class='btn btn-default btn-lg']")).click();         // Click on Search_Test Button..		
									
			 driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();         // Click on Search_Test Butt

			Assert.assertTrue( driver.findElement( By.linkText("HP LP3065") ).isDisplayed() );
		}
	
	
//  TestCase: 02   	Verify Search_Test Functionality , InValid__Product / Non-existing Product..
		
		
		
		@Test (priority = 2)
		public void verifySearchWithInvalidProduct()
		{
			
				driver.findElement(By.name("search")).sendKeys(dataProprty.getProperty("Invalid_Product"));
			
				driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
				
				String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
				
				System.out.println(actualSearchMessage);                                                                                                                                    // There is no product that matches the search criteria.
				
				String Expected_No_Product_Match_Msg = dataProprty.getProperty("NoProductMatchSearchResults");
				
				Assert.assertEquals(actualSearchMessage, Expected_No_Product_Match_Msg, "No_Product Message from search message is not Displayed..." );
			
		}
				
		@Test (priority = 3)
		public void verifySearchWithoutAnyProduct()
					
					{
						
				//		driver.findElement(By.name("search")).sendKeys("");
					
						driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
						
						String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
						
						System.out.println(actualSearchMessage);                                                                                                                                    // There is no product that matches the search criteria.
						
						String Expected_Msg_No_Product_Match = dataProprty.getProperty("NoProductMatchSearchResults");
						
						Assert.assertEquals(actualSearchMessage,  Expected_Msg_No_Product_Match , "No_Product Message from search message is not Displayed..." );
					
				   }

		
}
