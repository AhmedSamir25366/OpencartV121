package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 
   data is valid - login success - test pass - logout
   data is valid - login failed - test fail 
  
 
   data is invalid - login success - test fail - logout
   data is invalid - login failed 

 */

public class TC003_LoginDDT extends BaseClass {
	
	// Test Methods 
	//we must define those three parameters to get the data from provider method
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="datadriven") 
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException // we will get these parameters from data provider method
	{
		
		logger.info("**************** Starting TC003_LoginDDT ***************** ");
		
		try {
		// HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		// Login 
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		// My Account
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPAgeExists(); // if we get targetPage that means that the login is successful 
		
		// we will do many conditions
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				Assert.assertTrue(true); // Test case is passed
				macc.clickLogout();
			} else 
			{
				Assert.assertTrue(false); // Test case is failed
			}
		}
		if (exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage == true)
			{
				macc.clickLogout();
				Assert.assertTrue(false); // Test case is failed 
			}
			else 
			{
				Assert.assertTrue(true); // Test case is passed
				
			}
		}
		
		
	} catch(Exception e) 
	{
		Assert.fail();
	}
		Thread.sleep(3000);
		logger.info("**************** Finished TC003_LoginDDT ***************** ");
		
	}

}






















