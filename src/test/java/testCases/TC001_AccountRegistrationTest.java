package testCases;

import java.time.Duration;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

// every class is considering one test case
public class TC001_AccountRegistrationTest extends BaseClass {
   // Test Methods -- should be public 
	
	@Test (groups={"Regression", "Master"})
	public void verify_account_registration()
	{
		
		logger.info("********** Starting TC001_AccountRegistrationTest *********");
		try {
		HomePage hp = new HomePage(driver); // we created an object from the page object class
		hp.clickMyAccount();
		logger.info("********** Clicked on MyAccount Link *********");
		hp.clickRegister();
		logger.info("********** Clicked on Register Link *********");
		
		logger.info("********** Providing customer details *********");
		AccountRegisterationPage regpage = new AccountRegisterationPage(driver); // we created an object from the page object class
		regpage.setFirstName(randomeString().toUpperCase()); // it will generate a random first name 
		regpage.setLastName(randomeString().toUpperCase()); // it will generate a random first name 
		regpage.setEmail(randomeString() + "@gmail.com");// it will generate a random email
		regpage.setTelephone(randomeNumber()); // it will generate a random Telephone number in the form of string 
		
		// we captured the return password value and stored it in a variable called password  
		// because if we call this method two times in password and confirmation password , it will return 2 different values
		String password = randomeAlphaNumberic(); 
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy(); // it will click on check box
		regpage.clickContinue(); // it will click on register link 
		
		
		logger.info("********** Validating expected message *********");
		String confmsg = regpage.getConfirmationMsg(); // we store the confirmation message in a variable
		
		// Apply Validations 
		if (confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else 
		{
			logger.error("Test faild..");
			logger.debug("Debug logs..");
		  	Assert.assertTrue(false);
		}
		} 
		catch (Exception e)
		{
	
			Assert.fail();
		}
		
		logger.info("**** Finished TC001_AccountRegistrationTest ******");
		
		
		
		
		

	}
	



}
