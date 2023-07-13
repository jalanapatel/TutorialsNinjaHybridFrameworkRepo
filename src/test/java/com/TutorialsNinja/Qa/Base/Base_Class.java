package com.TutorialsNinja.Qa.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.TutorialsNinja.Qa.Utils.Utility_Class;


/*       There are two ways call Property_File, 
						 1) Create_Method --> loadPropertiesFile() Method   and 
						 2) Create Constructor() with Public access      */



public class Base_Class
{
		WebDriver driver;                                                                     // Declare WebDriver Globally...
		
	    public	Properties proprty;                                                      // Declare Property_Class Globally....
	    
	    public Properties dataProprty;                                               // Declare Proprerty_Class, Globally..... and Create dataProprty Object.
		
	    
	    
	    
	//   	public void loadPropertiesFile()                                          //  -- second way to Load Property_File -- >  Create Method to Load Property_File, before launch browser or create constructor of Base_Class with public access
	   
	   public Base_Class()                                                          // Create Constructor, to Load Property_File, before Launch_Browser....
		
		{
				
		   //  Config Properties
		   
		   				proprty = new Properties();                                 // Proprty Object for declaring,          // (System.getProperty("user.dir") --> Path of Project, +  path of File

						File property_File = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\TutorialsNinja\\Qa\\Config\\config.properties");                                  // (System.getProperty("user.dir") --> Path of Project, +  path of File
						
																								//		File property_File = new File("/TutorialsNinjaSeleniumProject/src/main/java/com/TutorialsNinja/Qa/Config/config.properties");
						try 
						{
						FileInputStream fis = new FileInputStream(property_File);
						
						proprty.load(fis);
						
						} 
						catch (Throwable e) 
						{
								e.printStackTrace();
								System.out.println(e);
						}
		
			//  Test_Data Properties 
						
						dataProprty = new Properties();
						
						File test_Data_File = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\TutorialsNinja\\Qa\\TestData\\testData.properties");
						
						try
								{
								FileInputStream fis_Data = new FileInputStream(test_Data_File);
								
								dataProprty.load( fis_Data);
								}
						catch (Throwable e)
								{
									e.printStackTrace();
									System.out.println(e);
								}
	
		}
	
		public  WebDriver  initializeBrowserAndOpenApplicationURL (String browserName)
						{
							// String browserName1 = "Chrome";                                   // Donot require as browserName is received from Parameter
							
							if ( browserName.equalsIgnoreCase("Chrome"))
							{
								driver = new ChromeDriver();
							}
							else if (browserName.equalsIgnoreCase("Firefox"))
							{
								driver = new FirefoxDriver();
							}
							else if (browserName.equalsIgnoreCase("edge"))
							{
								driver = new EdgeDriver();
							}
							else if (browserName.equalsIgnoreCase("safari"))
							{
								driver = new SafariDriver();
							}
							
													
							driver.manage().window().maximize();
				
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utility_Class.implicit_Wait_Time));                                   // 10 --> Implicit_Wait_Time ---> from Utility_Class
				
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utility_Class.page_Load_Time));                     // 5 --> page_Load_Time --> PageLoadTime ----> from Utility_Class
				
							driver.get(proprty.getProperty("url"));
																	
							return driver;
							
						}

}
