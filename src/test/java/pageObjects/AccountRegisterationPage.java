package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegisterationPage extends BasePage {
	
	
	// child class constructor 
	public AccountRegisterationPage(WebDriver driver)
	{
		super(driver);
	}
	
	// Locators
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	// Action Methods -- for every element we have to create an action method with set -- should be public
	public void setFirstName(String fname)
	{
		txtFirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String tel)
	{
		txtTelephone.sendKeys(tel);
	}
	
	public void setPassword(String pwd)
	{
		txtConfirmPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void setPrivacyPolicy()
	{
		chkPolicy.click();
	}
	
	public void clickContinue()
	{
		// to perform click action on this button
		
		// solution 1 
		btnContinue.click();
		
		// solution 2
		//btnContinue.submit();
		
		// solution 3
		//btnContinue.submit();
		//Actions act = new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
		
		// solution 4
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("argument[0].click();", btnContinue);
		
		// solution 5
		//btnContinue.sendKeys(Keys.RETURN);
		
		// solution 6
		//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
					
	}
	
    //This methods will not do any validations , it will just capture the text value of the confirmation message and return it
	public String getConfirmationMsg()
	{
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}
	
	
	
	
	
}
