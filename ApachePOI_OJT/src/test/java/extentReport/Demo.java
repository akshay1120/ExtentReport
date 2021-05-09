package extentReport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Demo 
{
	ExtentReports extent;
	ExtentTest logger;
	WebDriver driver;
	
	@BeforeTest
	public void setup() 
	{
		extent = new ExtentReports (System.getProperty("user.dir")+"/test-output/ExtentReport.html");
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///C:/Users/Akshay%20S%20Jain/Desktop/Selenium/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void getTitle_Fail() throws Exception 
	{
		logger = extent.startTest("getTitle_Fail");
		String title = driver.getTitle();  
		Assert.assertEquals(title, "no title");
	}
	
	@Test
	public void getTitle_Pass() throws Exception 
	{
		logger = extent.startTest("getTitle_Pass");
		String title = driver.getTitle();  
		Assert.assertEquals(title, "JavaByKiran | Log in");
	}
	
	@Test
	public void getTitle_Skip() throws Exception 
	{
		logger = extent.startTest("getTitle_Pass");
		throw new SkipException ("skipping a testcase");
	}
	
	@AfterMethod
	public void checkResult(ITestResult result) throws Exception
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.log(LogStatus.FAIL, "Test case is failed");
			String screenshotPath = null ;
			screenshotPath = TakeScreenShotEx.getScreenhot(driver , "getTitle_Fail");
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
		}
		
		if(result.getStatus()==ITestResult.SKIP)
		{
			logger.log(LogStatus.SKIP, "Test case is skipped");
		}
		
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.log(LogStatus.PASS, "Test case is passed");
		}
		extent.endTest(logger);
	}
	
	@AfterSuite
	public void tearDown()
	{
		extent.flush();
		extent.close();
		driver.close();
	}
}