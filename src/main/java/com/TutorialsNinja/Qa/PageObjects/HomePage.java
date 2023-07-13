package com.TutorialsNinja.Qa.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 Locators also called Elements or Objects.
 WebElement is Interface in Selenium
 @FindBy (Locator = value of Locator) Annotation is from pageFactory designPattern -- Auto initialize webElemenr
 Create WebElement is privete. so Only in this class update/changes is done.

PageFactory.initElements(driver, this);    driver = SearchContext,  this = current_Class_Object with WebElement ( HomePageClass )
 As initElements(SearchContext, Class) but will only replace the fields of an alreadyinstantiated Page Object
Parameters:  ===>> searchContext -->  The driver that will be used to look up the elements
					 ===>>>page  -->  The object with WebElement and List<WebElement> fields that should be proxied.
 */

public class HomePage 
{

	WebDriver driver;                                      // Create WebDriver
	
	//  Objects  ----   WebElements   -----   Locator  --> Locate WebElements using  PageObjectModel and PageFactoryDesignPattern, by FindBy () Annotation
	
	@FindBy (xpath = "//span[text() = 'My Account']" )                                    // Create Locators of MyAccount
	private WebElement myAccountDropMenu;                                             // Create WebElement of  MyAccount Dropdown Menu
	
	
	
	@FindBy (linkText = "Login")                                                                    //  Create Locators
	private WebElement  loginOption ;                                                      // Create WebElement
	
	@FindBy ( linkText = "Register")
	 private WebElement registerOption ;
	
	@FindBy ( name = "search")
	private WebElement searchBoxField ;
	
	
	@FindBy ( xpath = "//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	
	// Create Constructor of HomePage_Class ---- So , when we create Object of HomePage_Class, then constructor get invoke and 
	
	public HomePage (WebDriver driver)                                     // Create Constructor, with Parameter as WebDriver
	{
		this.driver = driver;
		
									// Initialize WebElement as PageFactory Design Pattern, (avoid Stale Element Exceptions)
		                                   // initialize webElemetn which are therein HomePage,  instructing PageFactory, to initialize webElemenr automatically,  and make connection between  webElement and Locators
		                                 // when create object of HomePage Class, constructor of HomePage is called and initialize webElement and Locators.. to avoid stale Element exception.
		
		
		PageFactory.initElements(driver,this);                     // this --  HomePage.class  ---> represent HomePage_Class with WebElement

	}
	
	
	
	
	//    Actions ---> Methods  --> Performs Actions...
	
	
	
						public void clickOnMyAccountDropMenu()                                 
									{																												// Click on MyAccount     (Fyi ---> method is able to access, Private WebElement)
										myAccountDropMenu.click();                                                             // Methods are created with public access so we can access in other Class using Class_Object
									}
						
						
						public  LoginPage selectLoginOption()
									{																										// Select Login_Test from DropDown
										loginOption.click();
										
										return new LoginPage (driver);
									}
						
	
	
	public LoginPage navigateToLoginPage()                                     //  Combine Two Method  --> clickOnMyAccountDropMenu() Method    and    selectLoginOption()  Method
			{
				myAccountDropMenu.click();
				loginOption.click();
				
				return new LoginPage (driver) ;
			}
	
	public RegisterPage  selectRegisterOption()
	{
		registerOption.click();
		
		return new RegisterPage(driver);
		
	}
	
	
	public RegisterPage  navigateToRegisterPage()                                // Combine Two Methods -->  clickOnMyAccountDropMenu() Method   and      selectRegisterOption()  Method
	{
		myAccountDropMenu.click();
		
		registerOption.click();
		
		return new RegisterPage(driver);
	}
	
	public void enterProductIntoSearchBoxField (String ProductText)
	{
		searchBoxField.sendKeys(ProductText) ;
	}

	
	public SearchPage clickOnSearchButton ()
	{
		searchButton.click();
		
		return new SearchPage(driver);
	}
	
	
	
	public SearchPage searchForProduct (String productText)
	{
		searchBoxField.sendKeys(productText) ;
		
		searchButton.click();
		
		return new SearchPage(driver);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
