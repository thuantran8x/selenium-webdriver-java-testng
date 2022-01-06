package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_III {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	//Khai bao 6 bien By 
	By fullNameTextboxBy = By.id("txtFirstname");
	By emailTextboxBy = By.id("txtEmail");
	By confirmEmailTextboxBy = By.id("txtCEmail");
	By passwordTextboxBy = By.id("txtPassword");
	By confirmPasswordTextboxBy = By.id("txtCPassword");
	By phoneTextboxBy = By.id("txtPhone");
	By registerButtonBy = By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']");
	
	By firstNameErrorMessage = By.id("txtFirstname-error");
	By emaiErrorMessage = By.id("txtEmail-error");
	By confirmEmailErrorMessage = By.id("txtCEmail-error");
	By passwordErrorMessage = By.id("txtPassword-error");
	By confirmPasswordErrorMessage = By.id("txtCPassword-error");
	By phoneErrorMessage = By.id("txtPhone-error");
	
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	@BeforeMethod   //chay cho tung test case 
	public void beforeMethod() { 
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		driver.findElement(fullNameTextboxBy).sendKeys("");
		driver.findElement(emailTextboxBy).sendKeys("");
		driver.findElement(confirmEmailTextboxBy).sendKeys("");
		driver.findElement(passwordTextboxBy).sendKeys("");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("");
		driver.findElement(phoneTextboxBy).sendKeys("");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(firstNameErrorMessage).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emaiErrorMessage).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMessage).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordErrorMessage).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMessage).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneErrorMessage).getText(), "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		driver.findElement(fullNameTextboxBy).sendKeys("Minh Quan");
		driver.findElement(emailTextboxBy).sendKeys("abc@");
		driver.findElement(confirmEmailTextboxBy).sendKeys("abc@");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123456");
		driver.findElement(phoneTextboxBy).sendKeys("0976543123");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(emaiErrorMessage).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMessage).getText(), "Vui lòng nhập email hợp lệ");
		
	}

	@Test
	public void TC_03_Register_Incorrect_Confirm_Email() {
		driver.findElement(fullNameTextboxBy).sendKeys("Minh Quan");
		driver.findElement(emailTextboxBy).sendKeys("abc@gmail.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("abc@");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123456");
		driver.findElement(phoneTextboxBy).sendKeys("0976543123");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(confirmEmailErrorMessage).getText(), "Email nhập lại không đúng");
	
	}


   @Test
   public void TC_04_Register_Invalid_Password() {
	    driver.findElement(fullNameTextboxBy).sendKeys("Minh Quan");
		driver.findElement(emailTextboxBy).sendKeys("abc@gmail.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("abc@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("123");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123");
		driver.findElement(phoneTextboxBy).sendKeys("0976543123");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(passwordErrorMessage).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMessage).getText(), "Mật khẩu phải có ít nhất 6 ký tự");


   }

   @Test
   public void TC_05_Register_Incorrect_Confirm_Password() {
	    driver.findElement(fullNameTextboxBy).sendKeys("Minh Quan");
		driver.findElement(emailTextboxBy).sendKeys("abc@gmail.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("abc@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123654");
		driver.findElement(phoneTextboxBy).sendKeys("0976543123");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMessage).getText(), "Mật khẩu bạn nhập không khớp");
		

  }
   
   @Test
   public void TC_06_Register_Invalid_Phone() {
	    driver.findElement(fullNameTextboxBy).sendKeys("Minh Quan");
		driver.findElement(emailTextboxBy).sendKeys("abc@gmail.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("abc@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123654");
		driver.findElement(phoneTextboxBy).sendKeys("097543");
		driver.findElement(registerButtonBy).click();
	   
		Assert.assertEquals(driver.findElement(phoneErrorMessage).getText(), "Số điện thoại phải từ 10-11 số.");
		
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("7648");
		
		Assert.assertEquals(driver.findElement(phoneErrorMessage).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
		

  }


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
