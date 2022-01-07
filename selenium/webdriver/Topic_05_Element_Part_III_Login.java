package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_III_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName,fullname, emailAddress, nonExistedEmailAddress, password;     //nonexisted email là email chưa từng đk
	
	//Khai bao bien By  (global variable)
	By emailTextboxBy = By.id("email");
	By passwordTextboxBy = By.id("pass");
	By loginButtonBy = By.id("send2");
	

	@BeforeClass // Chạy trước cho testcase đầu tiên (--- Biến dùng chung) 
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		firstName = "Mery";
		lastName = "Han";
		fullname = firstName + " " + lastName;
		emailAddress = "meryhan" + getRandomNumber() + "@hotmail.net";
		password = "123456789" ;
		nonExistedEmailAddress = "meryho" + getRandomNumber() + "@live.com";
		
	}
	@BeforeMethod   //chay cho tung test case, chạy trước tất cả các testcase 
	public void beforeMethod() { 
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
	
	}

	@Test
	public void TC_01_Empty_Email_And_Password() {
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(loginButtonBy).click();
		
		//Verify error messager displayed
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
		
	}

	@Test
	public void TC_02_Invalid_Email() {
		driver.findElement(emailTextboxBy).sendKeys("123@432");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(loginButtonBy).click();
		
		//Verify error messager displayed 
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}

	@Test
	public void TC_03_Register_Incorrect_Confirm_Email() {
		driver.findElement(emailTextboxBy).sendKeys("abcd@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("123");
		driver.findElement(loginButtonBy).click();
		
		//Verify error messager displayed 
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	
	}


   @Test
   public void TC_04_Create_New_Account_Success(){
	   driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	   // Existed Email (Se tao ra email đã đăng ký thành công)
	   driver.findElement(By.id("firstname")).sendKeys(firstName);
	   driver.findElement(By.id("lastname")).sendKeys(lastName);
	   driver.findElement(By.id("email_address")).sendKeys(emailAddress);
	   driver.findElement(By.id("password")).sendKeys(password);
	   driver.findElement(By.id("confirmation")).sendKeys(password);
	   driver.findElement(By.xpath("//button[@title='Register']")).click();
	   
	   //veryfi 
	   Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "MY DASHBOARD");
	   Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
	//   Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, Mery Han!"); //fix cứng dữ liệu - nên tạo thêm 1 biến fullname ở trên 
	   Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, " + fullname +  "!");
	   
	   String contactInfomation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
	   Assert.assertTrue(contactInfomation.contains(fullname));   //verify text chua name
	   Assert.assertTrue(contactInfomation.contains(emailAddress));    //verify chua text email...
	   
	   driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();   
	   driver.findElement(By.xpath("//a[@title ='Log Out']")).click();    // logout ra 
	   
	   Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title img")).isDisplayed());
	   
	   
	   
	   
	   

   }

   @Test
   public void TC_05_Incorrect_Email_Or_Password() {
	   // Existed Email + Incorrect Password 
	   //meryhan321@hotmail.net/ mat khau dung la  12345678  (đã đk)
	   driver.findElement(emailTextboxBy).sendKeys(emailAddress);   
	   driver.findElement(passwordTextboxBy).sendKeys("123435");
	   driver.findElement(loginButtonBy).click();
	   
	   Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");
	   
	   
	   
	          
	   
	   // Non Existed Email + correct/valid password -> Unsuccess
	   driver.findElement(emailTextboxBy).clear();  
	   driver.findElement(emailTextboxBy).sendKeys(nonExistedEmailAddress);   //meryhan321@hotmail.net/ mat k  la  12345678   // khởi tạo 1 biến nonExistedEmailAddress bên trên và dùng duy nhất chỗ này 
	   driver.findElement(passwordTextboxBy).clear();
	   driver.findElement(passwordTextboxBy).sendKeys("password");
	   driver.findElement(loginButtonBy).click();
	   
	   Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");
	   
	   
	   
  }
   
   @Test
   public void TC_06_Valid_Email_And_Address() {
	   //ĐN với thông tin hợp lệ 
	   driver.findElement(emailTextboxBy).sendKeys(emailAddress);  
	   driver.findElement(passwordTextboxBy).sendKeys(password);
	   driver.findElement(loginButtonBy).click();
	  
	    //veryfi 
	   Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "MY DASHBOARD");
	   Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, " + fullname +  "!");
	   
	   String contactInfomation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
	    //verify text chua name
	   Assert.assertTrue(contactInfomation.contains(fullname));   
	 //verify chua text email...
	   Assert.assertTrue(contactInfomation.contains(emailAddress));    
	   
	   
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
		public int getRandomNumber() {
			Random rand = new Random();
			return rand.nextInt(9999);
			
		}
}
