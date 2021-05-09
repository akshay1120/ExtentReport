package login;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Login_dp 
{
	public String readCell(String fileName, String sheetName, int row, int col) 
	{
		FileInputStream fis;
		Workbook wb=null;
		try 
		{
			fis = new FileInputStream(fileName);
			 wb = WorkbookFactory.create(fis);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		Sheet sh = wb.getSheet(sheetName);
		return sh.getRow(row).getCell(col).toString();
	}
	

	 @Test
		public void loginTest() 
	 {
		 	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("file:///C:/Users/Akshay%20S%20Jain/Desktop/Selenium/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
			driver.findElement(By.id("email")).sendKeys(readCell("Data.xlsx", "Sheet1", 1, 0));
			String pass = readCell("Data.xlsx", "Sheet1", 1, 1);//123456.0
			driver.findElement(By.id("password")).sendKeys(pass.substring(0, pass.indexOf('.')));//123456
			driver.findElement(By.xpath("//button")).click();
	 }
}
