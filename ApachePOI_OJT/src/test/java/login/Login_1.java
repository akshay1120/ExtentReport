package login;
//2,147,483,647
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Login_1 
{
	@Test(dataProvider = "getexcel")
	  public void LoginTest_4(String uname, String pass) 
	  {
		  	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get("file:///C:/Users/Akshay%20S%20Jain/Desktop/Selenium/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
			driver.findElement(By.id("email")).sendKeys(uname);
			driver.findElement(By.id("password")).sendKeys(pass);
			driver.findElement(By.xpath("//button")).click();
			String validTitle = "JavaByKiran | Dashboard";
			String inValidTitle= "JavaByKiran | Log in";
			
			if(driver.getTitle().contains("Log in"))
			{
				Assert.assertEquals(driver.getTitle(), inValidTitle); 
			}
			else 
			{
				Assert.assertEquals(driver.getTitle(), validTitle);
			}
			driver.close();
	  }

	  @DataProvider
	  public Object[][] getexcel() throws Exception 
		{
		  String data = null ;
			FileInputStream fis = new FileInputStream("Data.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet("Sheet1");
			
			int row= sh.getPhysicalNumberOfRows();
			String arr[][] = new String [row-1][sh.getRow(row-1).getLastCellNum()];
			
			for(int i=1;i<row;i++) 
			{
				int col= sh.getRow(i).getLastCellNum();
				
				for(int j=0;j<col;j++) 
				{
					XSSFCell cell = sh.getRow(i).getCell(j);
					
					if(cell.getCellTypeEnum() == CellType.STRING)
					{
						data = cell.getStringCellValue();
					}
						
					
					else if(cell.getCellTypeEnum() == CellType.NUMERIC)
					{
						data= String.valueOf((long)cell.getNumericCellValue());
					}
						
					arr[i-1][j]=data;
				}
			}
			wb.close();
			return arr;	
	}
}
