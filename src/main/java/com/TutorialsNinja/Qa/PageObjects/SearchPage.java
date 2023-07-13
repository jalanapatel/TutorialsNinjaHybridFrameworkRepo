package com.TutorialsNinja.Qa.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage
{

			WebDriver driver;
			
			
	// WebElement ------>  Locators   -----> Object
			

	
			@FindBy ( linkText = "HP LP3065") 
			private WebElement valid_HP_LP3065_Product;
	
	
			@FindBy ( xpath = "//div[@id='content']/h2/following-sibling::p" )
			private WebElement noProductMessage ; 
			
						
			
	
	
	
			// Create Constructor
			
			public SearchPage (WebDriver driver1)
			{
					this.driver = driver1;
					
					PageFactory.initElements(driver, this);                                //  this = SearchPage.class
			}
	
	
			// Create Methods to Perform__Actions
			
	
			
			public boolean displayStatusOfHPValidProduct()
			{
				boolean  display_Status_Flag = valid_HP_LP3065_Product.isDisplayed() ;
				
				return display_Status_Flag;
			}
	
			  
			
				public String  retrieveNoProductMesssageText ()
				{
						String noProductMesssageText =	noProductMessage.getText();
			
						return noProductMesssageText;
				}
	
	
	
	
}
