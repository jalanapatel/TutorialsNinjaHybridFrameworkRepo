package com.TutorialsNinja.Qa.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage 
{

			WebDriver driver;
	
			@FindBy ( xpath = "//div[@id='content']/h1")
			WebElement accountSuccessPageHeading;
	
	
	public  AccountSuccessPage (WebDriver driver1)                 // Constructor
	{
		   this.driver = driver1;
		   
		   PageFactory.initElements(driver, this);          // this = AccountPage.class
	}
	
	
	public String retrieveAccountSuccessPageHeading  ()
	{
		  String accountSuccessPageHeadingText = accountSuccessPageHeading.getText();

		   return accountSuccessPageHeadingText;
	}
	
	
	
	
	
	
	
	
	
	
	
}
