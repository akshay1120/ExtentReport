package login;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login_dp1 
{
	@Test(dataProvider = "dp")
	public void test_1 (String Uname , String Pass)
	{
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///C:/Users/Akshay%20S%20Jain/Desktop/Selenium/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
		driver.findElement(By.id("email")).sendKeys(Uname);
		driver.findElement(By.id("password")).sendKeys(Pass);
		driver.findElement(By.xpath("//button")).click();
	}
	
	@DataProvider
	public Object[][] dp() throws Exception 
	{
		String data = null ;
		FileInputStream fis = new FileInputStream("Data.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Sheet2");
		
		int row = sh.getPhysicalNumberOfRows();
		String arr[][] = new String[row][sh.getRow(row-1).getLastCellNum()];
		
		for (int i=0 ; i<row ; i++ )
		{
			int col = sh.getRow(i).getLastCellNum();
			
			for(int j=0 ; j<col ; j++) 
			{
				XSSFCell cell = sh.getRow(i).getCell(j);
				
				if(cell.getCellTypeEnum()==CellType.STRING)
				{
					data=cell.getStringCellValue();
				}
				
				else if(cell.getCellTypeEnum()==CellType.NUMERIC)
				{
					data=String.valueOf((long)cell.getNumericCellValue());
				}
				
				arr[i][j]=data;
			}
		}
		wb.close();
		return arr;
	}
}