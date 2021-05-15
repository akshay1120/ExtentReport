package robotClass;

import java.awt.AWTException;
import java.awt.Robot;

import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.event.MouseEvent;

public class Demo 
{
	public void robotAction(int key)
	{
		Robot robot=null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			
			e.printStackTrace();
		}
		robot.keyPress(key);
	}
	@Test
	public void test01()throws Exception{
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("file:///C:/Users/Akshay%20S%20Jain/Desktop/Selenium/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
		driver.manage().window().maximize();
		WebElement logo =driver.findElement(By.xpath("//img"));
		Thread.sleep(10000);
		
		
	robotAction(KeyEvent.VK_1);
	robotAction(KeyEvent.BUTTON3_DOWN_MASK);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(5000);
		robot.keyPress(KeyEvent.VK_ENTER);
		Actions act = new Actions(driver);
		act.moveToElement(logo).perform();;
		act.pause(2000).perform();
		act.contextClick(logo).perform();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(2000);
		//robot.m
		int a = logo.getLocation().getX();
		int y=  logo.getLocation().getY();
		
		//Robot robot = new Robot();
		robot.mouseMove(a, y);
		
		robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(5000);
		driver.findElement(By.id("email")).click();
		robot.keyPress(KeyEvent.VK_K);
		robot.keyPress(KeyEvent.VK_I);
		robot.keyPress(KeyEvent.VK_R);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_2);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_DECIMAL);	
	}
}
