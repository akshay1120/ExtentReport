package register;
//2,147,483,647 = max value of int
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterNewMembership_1 
{
	WebDriver driver;
	
	@Test(dataProvider = "dp_register")
	  public void RegisterTest_1(String name , String mobile , String email , String pass) 
	  {
		  	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("file:///C:/Users/Akshay%20S%20Jain/Desktop/Selenium/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
			driver.findElement(By.partialLinkText("Register")).click();
			driver.findElement(By.id("name")).sendKeys(name);
			driver.findElement(By.id("mobile")).sendKeys(mobile);
			driver.findElement(By.id("email")).sendKeys(email);
			driver.findElement(By.id("password")).sendKeys(pass);
			driver.findElement(By.xpath("//button")).click();
	  
			//Alert al = driver.switchTo().alert();
			//al.accept();
	  }
	
	@DataProvider
	public String [][] dp_register() throws Exception
	{
		FileInputStream fis = new FileInputStream("Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet3");
		
		int row = sh.getPhysicalNumberOfRows();
		
		String[][] data = new String [row-1][sh.getRow(row-1).getLastCellNum()];
		
		for(int i=1 ; i<row ; i++)
		{
			int col = sh.getRow(i).getLastCellNum();
			
			for (int j=0 ; j<col ; j++)
			{
				Cell cell = sh.getRow(i).getCell(j);
				
				if (cell.getCellTypeEnum()==CellType.STRING)
					data[i-1][j]=cell.getStringCellValue();
					
				else if (cell.getCellTypeEnum()==CellType.NUMERIC)
				{
					String value = String.valueOf((long)cell.getNumericCellValue());
					
					data[i-1][j]=value;
				}
			}
			System.out.println();
		}
		wb.close();
		return data ;
	}
}
