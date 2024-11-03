package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; // Log4j2
import org.apache.logging.log4j.Logger; // Log4j2
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

// whenever we use @Parameters annotation we have to import these 2 packages
import org.testng.annotations.Test;  
import org.testng.annotations.Parameters;  


// The BaseClass contains the reusable methods , whatever methods are required for all the test cases those methods we will keep
// inside the BaseClass like setup() method and tearDown() method and randomeNumber() method and randomeAlphaNumberic() method
// and randomeString() method
// The BaseClass is the parent class of all the test cases classes
// we create the BaseClass to achieve the Re-usability and avoid the Duplication 
// The BaseClass contains the Re-usable components 

public class BaseClass {
	  
public static WebDriver driver; // variable
public Logger logger; //variable  for Log4j2.xml file and Logger is a pre-defined class
public Properties p; // Properties is a special class which we have to import


	@BeforeClass(groups = {"Sanity", "Regression", "Master"}) // Master means all test cases will be executed
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException
	{
		// Loading config.properties file & we captured the data from properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties"); //    .// represents the current location of the project
		p = new Properties(); //we created an object from properties class called p and this p represents every property in properties class
		p.load(file);
		
		
		
		// for every test case class we have to create the logs
		// logger variable will get Log4j2.xml file and the logger variable will be used to generate the logs
		logger = LogManager.getLogger(this.getClass()); 
		
		// we will check first whether it is a remote execution or local execution 
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			// os which comes from xml file
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
				
			}
			else if  (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			} 
			else
			{
				System.out.println("No matching os");
				return;
			}
			// browser which comes from xml file
			switch (br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No matching browser"); return;
			
			}
			
			// browser
			switch(br.toLowerCase())
			{
			case "Chrome": driver = new ChromeDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			case "Firefox": driver = new FirefoxDriver(); break;
			default : System.out.println("Invalid browser name.."); return; // return will exit from the entire execution if the browser name is invalid
			}
			
			driver = new RemoteWebDriver(new URL ("http://localhost:4444/wd/hub"), capabilities);
						
		}
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "Chrome": driver = new ChromeDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			case "Firefox": driver = new FirefoxDriver(); break;
			default : System.out.println("Invalid browser name.."); return; // return will exit from the entire execution if the browser name is invalid
			}
		}
			
			
		
		
		
		
		
		
		driver.manage().deleteAllCookies(); // it will delete all the cookies from the web page if we already store some cookies information
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL")); // reading URL from properties file
		driver.manage().window().maximize();
	}
	
	
	
	
	@AfterClass(groups= {"Sanity", "Regression", "Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	// Creating Dynamic Test Data
	// This Method will generate a random string, so when we call this Method we will get a random string 
	public String randomeString()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(5); // Number of characters in the random string 
		return generatedstring;
	}
	
	// This Method will generate a random numbers ,so when we call this Method we will get a random numbers, and it will return a string  
	
	public String randomeNumber()
	{
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	// This Method will return random Alphabetic and random Numbers (both) and we will use this Method in password field to be complex
	public String randomeAlphaNumberic()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedstring + "@" + generatednumber);
	
	}
	
	// This Method will take or capture the screenshot for test case
	
	public String captureScreen(String tname) throws IOException 
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // create the date
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+ "\\screenshots\\" + tname + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
		
	}
	
	

}
