package basic;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DahboardPage 
{
	WebDriver driver;
	
	@Test(dataProvider = "dp")
	public void Dahboard_Page (String Heading)
	{
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///C:/Users/Akshay%20S%20Jain/Desktop/Selenium/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		
		List<WebElement> CoursesHeadings  =driver.findElements(By.xpath("//h3"));
		
		ArrayList <String> actCoursesHeadings  = new ArrayList <String>();
		
		ArrayList <String> expCoursesHeadings = new ArrayList <String>();
		expCoursesHeadings.add(Heading);
		
		for (WebElement element : CoursesHeadings)
		{
			actCoursesHeadings.add(element.getText());
			System.out.println();
		}
		
		Assert.assertEquals(actCoursesHeadings, expCoursesHeadings);
	}
		
		@DataProvider
		public String [][] dp() throws Exception
		{
			FileInputStream fis = new FileInputStream("dashboard.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet("Sheet1");
			
			int row = sh.getPhysicalNumberOfRows();
			
			String[][] data = new String [row-1][sh.getRow(row-1).getLastCellNum()];
			
			for(int i=1 ; i<row ; i++)
			{
				int col = sh.getRow(i).getLastCellNum();
				
				for (int j=0 ; j<col-2 ; j++)
				{
					Cell cell = sh.getRow(i).getCell(j);
					
					if (cell.getCellTypeEnum()==CellType.STRING)
						data[i][j]=cell.getStringCellValue();
						
					else if (cell.getCellTypeEnum()==CellType.NUMERIC)
					{
						String value = String.valueOf((long)cell.getNumericCellValue());
						
						data[i][j]=value;
					}
				}
				System.out.println();
			}
			wb.close();
 			return data ;
		}
}
