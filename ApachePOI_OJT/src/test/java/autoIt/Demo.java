package autoIt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo 
{
	public static void main(String[] args) throws Exception 
	{
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("file:///C:/AutoIT/upload.html");
		driver.manage().window().maximize();
		driver.findElement(By.id("input")).click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\AutoIT\\demo.exe");
	}
}