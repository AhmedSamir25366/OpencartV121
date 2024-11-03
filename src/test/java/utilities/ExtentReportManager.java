package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName; // this will be the report name
	
	
	
	
	//when we run onStart method , it will capture the testContext and the testContext represents which test method we are executing
	//and the test method details will be stored in the testContext variable 
	
	public void onStart(ITestContext testContext)
	{
		// these three lines will generate the data
		
		/*
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.HH.mm.ss");
		Date dt = new Date();
		String currentdatetimestamp = df.format(dt);
        */

		
		// or
		
		// this line only will generate the time as well and we will use this approach
		// String timeStamp = new SimpleDateFormat("yyyy.MM.HH.mm.ss").format(new Date()); 
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.HH.mm.ss").format(new Date()); // time stamp
		repName = "Test-Report-" + timeStamp + ".html"; // the ultimate report name 
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // specify the location of report
		
		sparkReporter.config().setDocumentTitle("Opencart Automation Report"); // Title of report
		sparkReporter.config().setReportName("Opencart Functional Testing"); // name of report
		sparkReporter.config().setTheme(Theme.DARK); // theme
		
		// specific related project information which we have to write inside the report
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Admin");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name")); // It will display the tester name 
		extent.setSystemInfo("Environment", "QA");
		
        // from the test method (testContext) we get the xml file and from xml file we get the operating system
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os); // operating system
		
		
	    // from the test method (testContext) we get the xml file and from xml file we get the browser name
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser); // browser 
		
// from the test method (testContext) we get the xml file and from xml file we capture the group names which we wrote in the xml file
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString() ); // if groups are not empty , add the groups names to the report 
		}
		
		
	}
	
	
	
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName()); // to display the name of the class which is executed
		test.assignCategory(result.getMethod().getGroups()); // to display group name of the test case class in report
		test.log(Status.PASS, result.getName()+ "got successfully executed");
	}
	
	
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display group name of the test case class in report
		test.log(Status.FAIL, result.getName()+ "got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		// if a failure happens , we have to take a screenshot by the captureScreen() method
		try // try block will try to capture a screenshot 
		{
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (IOException e1)
		{
			e1.printStackTrace(); // this is a predefined method which we can access from the even , and it display the exception message
		}
	}
	
	
	public void onTestSkipped(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		test.log(Status.SKIP, result.getName()+ "got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestResult result)
	{
		extent.flush(); // this method will consolidate all the information from the report and finally it will generate it
		
		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName; // the path of the report
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI()); // this method will open the report on the browser automatically 
		} catch (IOException e2)
		{
			e2.printStackTrace();
		}
		
		
		// this code will send the report automatically 
		
		/*
		try {
			
			URL url = new URL("file:///" + System.getProperty("user.dir")+ "\\reports\\" + repName);
			
			// create the email message 
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url) );
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));
			email.setSSLOnConnect(true);
			email.setFrom("pavanoltraining@gmail.com"); // sender
			email.setSubject("Test Results"); 
			email.setMsg("Please find Attached Report....");
			email.addTo("pavankumar.busyqa@gmail.com"); //Receiver
			email.attach(url, "extent report", "please check report...");
			email.send(); // send the email
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		*/
		
		
		
        }
	


}
