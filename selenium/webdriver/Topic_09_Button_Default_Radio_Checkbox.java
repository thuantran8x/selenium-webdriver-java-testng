package webdriver;

import static org.testng.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

public class Topic_09_Button_Default_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		By loginButtonBy = By.cssSelector("button.fhs-btn-login");
		//Verify login button is disabled
		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
		//isEnabled: Nếu 1 element mà enabled ->> True 
		//isEnabled: Nếu 1 element mà disabled ->> false 
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("automationc@hotmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("12345678");
		sleepInSecond(2);
		
		//Verify login button is Enabled
		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
		// Verify background color = RED
		String loginButtonBackgroundColorRgb = driver.findElement(loginButtonBy).getCssValue("background-color");
		System.out.println("RGB color =" + loginButtonBackgroundColorRgb);
		
		//Verify = RGB 
		Assert.assertEquals(loginButtonBackgroundColorRgb, "rgb(201, 33, 39)");
		
		//Convert qua Hexa
		String loginButtonBackGroundColorHexa = Color.fromString(loginButtonBackgroundColorRgb).asHex();
		System.out.println("Hexa color =" + loginButtonBackGroundColorHexa);
		
		//Verify = Hexa 
		Assert.assertEquals(loginButtonBackGroundColorHexa.toUpperCase(), "#C92127");
		
		driver.navigate().refresh();
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		//Remove disabled attribute 
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(loginButtonBy));
		sleepInSecond(2);
		
		driver.findElement(loginButtonBy).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		
		
	}

	@Test
	public void TC_02_Default_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		
		By oneDotEightPetrolRadio =  By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input");
		By twoDotZeroPetrolRadio =  By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		By threeDotSixPetrolRadio =  By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::input");
		
		Assert.assertFalse(driver.findElement(oneDotEightPetrolRadio).isSelected());
		
		driver.findElement(oneDotEightPetrolRadio).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(oneDotEightPetrolRadio).isSelected());
		
		driver.findElement(twoDotZeroPetrolRadio).click();
		sleepInSecond(2);
		
		//Deselected
		Assert.assertFalse(driver.findElement(oneDotEightPetrolRadio).isSelected());
		
		//Selected
		Assert.assertTrue(driver.findElement(twoDotZeroPetrolRadio).isSelected());
		
		//Enabled
		Assert.assertTrue(driver.findElement(oneDotEightPetrolRadio).isEnabled());
		Assert.assertTrue(driver.findElement(twoDotZeroPetrolRadio).isEnabled());
		
		//Disabled 
		Assert.assertFalse(driver.findElement(threeDotSixPetrolRadio).isEnabled());
		
		
		
		//Defaul -- thẻ input :
		//Action : click
		//Verify được 
		
		
		//Custom- the input 
		//ACtion : ko click được 
		//verify được
		
		
	}

	@Test
	public void TC_03_Default_Checkbox() {
         driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		By Luggagecheckbox =  By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
		By Heatedcheckbox =  By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input");
		By Towbarcheckbox =  By.xpath("//label[text()='Towbar preparation']/preceding-sibling::input");
		By Leathercheckbox =  By.xpath("//label[text()='Leather trim']/preceding-sibling::input");
		
		
		//Select
		//Cách 1 --- chưa viết hàm 
      //  if (!driver.findElement(Luggagecheckbox).isSelected()) {
        //	driver.findElement(Luggagecheckbox).click();
			
		//}
		
       // if (!driver.findElement(Heatedcheckbox).isSelected()) {
        //	driver.findElement(Heatedcheckbox).click();
			
	//	}
		
	//	Cách 2: dùng hàm 
		//Select
		checkToCheckbox (Luggagecheckbox);
		checkToCheckbox (Heatedcheckbox);
		
		
        // Verify Selected ko dùng hàm 
       // Assert.assertTrue(driver.findElement(Luggagecheckbox).isSelected());
       // Assert.assertTrue(driver.findElement(Heatedcheckbox).isSelected());
      //  Assert.assertTrue(driver.findElement(Leathercheckbox).isSelected());
       // Dùng hàm 
		Assert.assertTrue(isElementSelected(Luggagecheckbox));
		Assert.assertTrue(isElementSelected(Heatedcheckbox));
		Assert.assertTrue(isElementSelected(Leathercheckbox));
		
		// Verify Disable ko dùng hàm 
      //  Assert.assertFalse(driver.findElement(Towbarcheckbox).isEnabled());
      //  Assert.assertFalse(driver.findElement(Leathercheckbox).isEnabled());
		//dùng hàm 
		Assert.assertFalse(isElementEnabled(Towbarcheckbox));
		Assert.assertFalse(isElementEnabled(Leathercheckbox));
        
      //De- select
       //  Cách 1: khi chưa viết hàm 
        //if (driver.findElement(Luggagecheckbox).isSelected()) {
        	//driver.findElement(Luggagecheckbox).click();
			
	//	}
		
      //  if (driver.findElement(Heatedcheckbox).isSelected()) {
     //   	driver.findElement(Heatedcheckbox).click();
	//		
		//}
        
        //Cách 2: Dùng hàm 
        unCheckToCheckbox(Luggagecheckbox);
        unCheckToCheckbox(Heatedcheckbox);
        
        
        // Verify De-selected 
        //Assert.assertFalse(driver.findElement(Luggagecheckbox).isSelected());
       // Assert.assertFalse(driver.findElement(Heatedcheckbox).isSelected());
       // Assert.assertFalse(driver.findElement(Towbarcheckbox).isSelected());
     // Dùng hàm 
     		Assert.assertFalse(isElementSelected(Luggagecheckbox));
     		Assert.assertFalse(isElementSelected(Heatedcheckbox));
     		Assert.assertFalse(isElementSelected(Towbarcheckbox));
     		
	
	}
	
	
	@Test
	public void TC_04_Multiple_Checkbox() {
         driver.get("https://automationfc.github.io/multiple-fields/");
         
         List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
         System.out.println("Checkbox size = " + checkboxes.size());
         
         //Action - select 
         for (WebElement checkbox : checkboxes) {
        	 if (!checkbox.isSelected()) {
        		 checkbox.click();
        		 sleepInMiliSecond(500);
         } 
         }
        	 //Verify- select
        	 for (WebElement checkbox : checkboxes) {
        		 Assert.assertTrue(checkbox.isSelected());
        		 
        	 
        	 }
			
		
	//Action - Deselect 
    for (WebElement checkbox : checkboxes) {
   	 if (checkbox.isSelected()) {
   		 checkbox.click();
   		sleepInMiliSecond(500);
    } 
    }
   	 //Verify- Deselect
   	 for (WebElement checkbox : checkboxes) {
   		 Assert.assertFalse(checkbox.isSelected());
   		 
   	 
   	 }
		
	}
    

	
	
	//Tạo hàm để tối ưu code 
	
	public void checkToCheckbox (By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	//Tạo hàm để tối ưu code 
	public void unCheckToCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
	}
	}

	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			return true;
		}else {
			return false;
		
		}
	}
		
	public boolean isElementEnabled(By by) {
		if (driver.findElement(by).isEnabled()) {
			return true;
		}else {
			return false;
		
		}
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
	
	public void sleepInMiliSecond(long milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
