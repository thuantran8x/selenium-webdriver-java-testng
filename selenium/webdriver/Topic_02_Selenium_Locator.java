package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://vi-vn.facebook.com/r.php");
	}

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("password_step_input")).sendKeys("123456");
		sleepInSecond(3);
	
	}

	@Test
	public void TC_02_Class() {
		driver.findElement(By.className("registration_redesign")).isDisplayed();
		sleepInSecond(3);
	}

	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("reg_email__")).sendKeys("thuantran@gmail.com");
		sleepInSecond(3);
	}
	
	@Test
	public void TC_04_LinkText() {
		//click xong nó sẽ chuyển qua màn hình khác 
		driver.findElement(By.linkText("Bạn đã có tài khoản?")).click();
		sleepInSecond(3);
	
	} 
	
	@Test
	public void TC_05_PartialLinkText() {
		driver.findElement(By.partialLinkText("tài khoản?")).click();
		sleepInSecond(3);
	
	}
	
	@Test
	public void TC_06_TagName() {
		//Bao nhiêu thẻ input ở màn ' quên mật khẩu"
	 int inputSize = driver.findElements(By.tagName("input")).size();
	 System.out.print("Input size =  " + inputSize);
	 sleepInSecond(3);
	 
	}
	
	@Test
	public void TC_07_Css() {
		//Css cú pháp thông thường: tagname[attribute='value']
		driver.findElement(By.cssSelector("input[id='identify_email']")).sendKeys("thuantran@gmail.com");
		sleepInSecond(3);
		
	
	}
	
	@Test
	public void TC_08_Xpath() {
		// Xpath cơ bản 
		driver.findElement(By.xpath("//button[@name='did_submit']")).click();
		sleepInSecond(3);
	
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	//Gọi hàm khác để dùng 
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
