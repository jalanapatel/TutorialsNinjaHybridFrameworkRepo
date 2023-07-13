package com.TutorialsNinja.Qa.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage
{
	
		WebDriver driver;
	
		
//  Locators  ---> WebElements  ---->   Object
		
		
		@FindBy  ( linkText = "Edit your account information" )
	     private	WebElement  editAccountInformationOption;
		
		
		
// Create Constructor
		
		public AccountPage(WebDriver driver1)
		{
			this.driver = driver1;
			
			PageFactory.initElements(driver, this);                   // this = AccountPage.class
			
		}
		
		
		
//  Actoions  ---> Methods ---> Performing Action
		
		
		public boolean  getDisplayStatusofedtitYourAccountInformationOption()
		{
			boolean displayStatus = editAccountInformationOption.isDisplayed();
			
			return displayStatus;
		}
		
		

}
