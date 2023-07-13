package com.TutorialsNinja.Qa.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{

	WebDriver driver;
	
	// Object -- WebElement --- Locators

	@FindBy (id = "input-email")
	private WebElement emailAddressField;
	
	
	@FindBy ( id = "input-password" )
	private WebElement passwordField;
	
	@FindBy ( xpath =  "//input[@type='submit']" )
	private WebElement loginButton;
	
	
	@FindBy (xpath =  "//div[contains(@class,'alert-dismissible')]"  )
	private WebElement emailPasswordNotMatching ;

	
	
	// Create Constructor of Class 
	
	
	public  LoginPage (WebDriver driver1)
	{
		this.driver = driver1;
		
		PageFactory.initElements(driver, this);
	}
	
	
	// Actions -->  methods ---> Perform Actions....
	
	
	
	public void enterEmailAddress( String emailText )
	{
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterPassword (String passwordText)
	{
		passwordField.sendKeys(passwordText);
	}
	
	public AccountPage clickOnLoginButton ()
	{
		loginButton.click();
		
		return new AccountPage(driver);
	}
	
	
	
	public AccountPage login( String emailText,  String passwordText )                     //  Combine Three Method --->  enterEmailAddress( String emailText ) Method,   enterPassword (String passwordText) Method,   clickOnLoginButton () Method 
	{
		emailAddressField.sendKeys(emailText);
		
		passwordField.sendKeys(passwordText);
			
		loginButton.click();
		
		return new AccountPage(driver);
	}
	
	public String  retrieveEmailPasswordNotMatchingWarningMessageText()
	{
		String warningText = emailPasswordNotMatching.getText();
		
		return warningText;
	}
	
}
