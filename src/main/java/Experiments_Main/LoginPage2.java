package Experiments_Main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage2
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
	
	
	public  LoginPage2 (WebDriver driver1)
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
	
	public void clickOnLoginButton ()
	{
		loginButton.click();
		
		}
	
	public String  retrieveEmailPasswordNotMatchingWarningMessageText()
	{
		String warningText = emailPasswordNotMatching.getText();
		
		return warningText;
	}
	
}
