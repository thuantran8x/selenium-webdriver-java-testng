package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Add_Employee() throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		// textbox
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		// button
		driver.findElement(By.id("btnLogin")).click();

		sleepInSecond(5);

		// At Dashboart page: "Add Emploiee" sub-menu link is not displayed
		// (undisplayed)
		Assert.assertFalse(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());

		// Open 'Add Employee' Page
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");

		// At 'Add Employee' Page : "Add Emploiee" sub-menu link is displayed
		Assert.assertTrue(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());
		
		String firstName, lastName, editFirstName, editLastName;
		firstName = "Mary";
		lastName = "Man";
		editFirstName = "Mama";
		editLastName = "Hang";

		// Enter to Firtname/Lastname
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);

		String emploieeID = driver.findElement(By.id("employeeId")).getAttribute("value");

		// gan bien emploieeID = 0397

		// Click Save
		driver.findElement(By.id("btnSave")).click();
		sleepInSecond(3);
		
		By firstNameTextBoxBy =  By.id("personal_txtEmpFirstName");
		By lastNameTextBoxBy =  By.id("personal_txtEmpLastName");
		By employeeIDTextBoxBy =  By.id("personal_txtEmployeeId");

		// Verify "Firtname/Lastname/employeeID" textbox are disabled(is not enabled)
		Assert.assertFalse(driver.findElement(firstNameTextBoxBy).isEnabled());
		Assert.assertFalse(driver.findElement(lastNameTextBoxBy).isEnabled());
		Assert.assertFalse(driver.findElement(employeeIDTextBoxBy).isEnabled());

		// Verify 'Lastname/Firtname/ImployeeID' value matching with input value
		Assert.assertEquals(driver.findElement(firstNameTextBoxBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameTextBoxBy).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(employeeIDTextBoxBy).getAttribute("value"), emploieeID);

		// Click button
		driver.findElement(By.id("btnSave")).click();
		// Verify "Firtname/Lastname/employeeID" textbox are enabled)
		Assert.assertTrue(driver.findElement(firstNameTextBoxBy).isEnabled());
		Assert.assertTrue(driver.findElement(lastNameTextBoxBy).isEnabled());
		Assert.assertTrue(driver.findElement(employeeIDTextBoxBy).isEnabled());

		// Edit firstname/Lastname

		driver.findElement(firstNameTextBoxBy).clear();
		driver.findElement(firstNameTextBoxBy).sendKeys(editFirstName);
		driver.findElement(lastNameTextBoxBy).clear();
		driver.findElement(lastNameTextBoxBy).sendKeys(editLastName);
		// Click button save
		driver.findElement(By.id("btnSave")).click();
		sleepInSecond(3);

		// Verify "Firtname/Lastname/employeeID" textbox are disabled(is not enabled)
		Assert.assertFalse(driver.findElement(firstNameTextBoxBy).isEnabled());
		Assert.assertFalse(driver.findElement(lastNameTextBoxBy).isEnabled());
		Assert.assertFalse(driver.findElement(employeeIDTextBoxBy).isEnabled());
		
		// Click to 'Immigration' tab
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		//Click to 'Add' button 
		driver.findElement(By.cssSelector("input#btnAdd")).click();
		//Enter to 'Immigration' number textbox
		driver.findElement(By.id("immigration_number")).sendKeys("31195855");
		driver.findElement(By.cssSelector("textarea#immigration_comments")).sendKeys("Mary's\nPassport\nID");
		
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//td[@class='document']/a")).click();
		
		//verify
		Assert.assertEquals(driver.findElement(By.id("immigration_number")).getAttribute("value"), "31195855");
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea#immigration_comments")).getAttribute("value"), "Mary's\nPassport\nID");
		

	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// Gọi hàm khác để dùng
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
