package Experiments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TutorialsNinja.Qa.Base.Base_Class;
import com.TutorialsNinja.Qa.PageObjects.HomePage;
import com.TutorialsNinja.Qa.PageObjects.SearchPage;

public class  Search_02 extends Base_Class                                                                          // Inherits Base_Class
{
			WebDriver driver;                                                                                               // Declare global variable
			
			public Search_02()                                              // Create Search_Constructor of Search_Class, with super keyword, super_Class constructor is created, means Base_Class (extended) is Super_Class of Search_Class
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
			
			HomePage homePage = new HomePage(driver) ;  
			
			homePage.enterProductIntoSearchBoxField(dataProprty.getProperty("valid_Product"));                                 // Enter value in search bar
			
			homePage.clickOnSearchButton();                                                          // Click on Search_Test Button
			
															//	 driver.findElement(By.xpath(" //button[@class='btn btn-default btn-lg']")).click();         // Click on Search_Test Button..		
									
			    SearchPage searchPage = new SearchPage(driver);
			    
			    Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(), " Valid Product of HP is not Displayed in search result...." ) ;

		
		}
	
	
//  TestCase: 02   	Verify Search_Test Functionality , InValid__Product / Non-existing Product..
		
		
		
		@Test (priority = 2)
		public void verifySearchWithInvalidProduct()
		{
			
				HomePage homePage = new HomePage(driver);
			
				homePage.enterProductIntoSearchBoxField(dataProprty.getProperty("Invalid_Product"));
				
				homePage.clickOnSearchButton();
			
				
				
				SearchPage searchPage = new SearchPage(driver);
				
				String actualSearchMessage = searchPage.retrieveNoProductMesssageText();
							
				System.out.println(actualSearchMessage);                                                                                                                                    // There is no product that matches the search criteria.
				
				String Expected_No_Product_Match_Msg = dataProprty.getProperty("NoProductMatchSearchResults");
				
				Assert.assertEquals(actualSearchMessage, Expected_No_Product_Match_Msg, "No_Product Message from search message is not Displayed..." );
			
		}
				
		@Test (priority = 3)
		public void verifySearchWithoutAnyProduct()
					
					{
						
				//		driver.findElement(By.name("search")).sendKeys("");
			
						HomePage homePage = new HomePage (driver);
						
						homePage.clickOnSearchButton();
				
						
						
						SearchPage searchPage = new SearchPage(driver) ;

						String actualSearchMessage = searchPage.retrieveNoProductMesssageText();
					
						System.out.println(actualSearchMessage);                                                                                                                                    // There is no product that matches the search criteria.
						
						String Expected_Msg_No_Product_Match = dataProprty.getProperty("NoProductMatchSearchResults");
						
						Assert.assertEquals(actualSearchMessage,  Expected_Msg_No_Product_Match , "No_Product Message from search message is not Displayed..." );
					
				   }

		
}
