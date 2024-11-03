package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

//this class contains the constructor 
public class BasePage {
	WebDriver driver;
	
	// This is the parent class constructor which will receive the driver and will initiate the constructor
	BasePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
