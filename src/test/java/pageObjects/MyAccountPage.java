package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	// child constructor which extends the parent constructor  
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	// Locators
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']") // added in step6
	WebElement lnkLogout;
	
	// Action Method -- It is not validation 
	public boolean isMyAccountPAgeExists()
	{
		try 
		{
		return (msgHeading.isDisplayed()); // if the element is displayed it will return true 
		} catch (Exception e)
		{
			return false;
		}
	}
	
	public void clickLogout()
	{
		lnkLogout.click();
	}
	

}
