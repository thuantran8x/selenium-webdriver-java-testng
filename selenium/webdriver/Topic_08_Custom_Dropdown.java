package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;   // Nhấn ctrl+shift+o để import vào 
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Wait cho các trạng thái của element visible/presence/ invisible/ staleness
		explicitWait = new WebDriverWait(driver, 30);
		// Ép kiểu tường minh 
		jsExecutor = (JavascriptExecutor) driver;
		
		
		//Wait cho việc tìm element (findElement) 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropdownlist("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "5");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "5");
		selectItemInCustomDropdownlist("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "15");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "15");
		selectItemInCustomDropdownlist("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "19"); 
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "19");
		selectItemInCustomDropdownlist("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "3"); 
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "3");
		// span#number-button>span.ui-selectmenu-icon
		//ul#number-menu div
		//5
	}
	
	@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdownlist("i.dropdown", "div.item>span.text", "Jenny Hess");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
		selectItemInCustomDropdownlist("i.dropdown", "div.item>span.text", "Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");
		selectItemInCustomDropdownlist("i.dropdown", "div.item>span.text", "Matt");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
		
	}
	
	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdownlist("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		selectItemInCustomDropdownlist("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		selectItemInCustomDropdownlist("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
		
	}
	@Test
	public void TC_04_Angular_Select() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		selectItemInCustomDropdownlist("ng-select[bindvalue='provinceCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-option-label", "Thành phố Hồ Chí Minh");
		// Cách 1 - Text nó ko nằm trong HTML mà nằm trong dom 
		//String actualText = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='provinceCode'] span.ng-value-label\").innerText");
		//Assert.assertEquals(actualText, "Thành phố Hồ Chí Minh");
		// Cách 2- GetText 
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getText(), "Thành phố Hồ Chí Minh");
		// Cách 3 - get atribution
		//Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getAttribute("innerText"), "Thành phố Hồ Chí Minh");
		sleepInSecond(3);
		
		selectItemInCustomDropdownlist("ng-select[bindvalue='districtCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-option-label", "Quận Bình Tân");
		
		selectItemInCustomDropdownlist("ng-select[bindvalue='wardCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-option-label", "Phường Tân Tạo");
		
		
		
		
		
	}
	@Test
	public void TC_05_Angular_Enter() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		enterToCustomDropdownlist("ng-select[bindvalue='provinceCode'] input[role='combobox']", "div[role='option']>span.ng-option-label", "Thành phố Hồ Chí Minh");
		enterToCustomDropdownlist("ng-select[bindvalue='districtCode'] input[role='combobox']", "div[role='option']>span.ng-option-label", "Quận Bình Tân");
		enterToCustomDropdownlist("ng-select[bindvalue='wardCode'] input[role='combobox']", "div[role='option']>span.ng-option-label", "Phường Tân Tạo");
	}
	
		
		
	public void selectItemInCustomDropdownlist(String parentLocator, String childLocator, String expectedTextItem) {
		// Step 1: Click vào 1 element cho nó xổ hết ra cac item
				driver.findElement(By.cssSelector(parentLocator)).click();
				sleepInSecond(2);
				
				// Step 2: Chờ cho các etem load hết ra thành công 
				//Lưu ý 1: Locator phải chứa hết tất cả các item 
				//Lưu ý 1: Locator phải đến note cuối cùng chưa text
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
				sleepInSecond(5);
				// Step 3: Tìm item cần chọn
				// - B1: Nếu item cần chọn nằm trong vùng nhìn thấy thì ko cần scroll tới elemen tìm tiếp 
				//- B2 : Nếu item cần chọn nằm ở dưới thì scroll tới elemen
				
				// Lấy hết tất cả element(item) ra sau đó duyệt qua tưng item
				List<WebElement> allITems = driver.findElements(By.cssSelector(childLocator));
				
				// Duyệt qua từng item getText của item ra
				for (WebElement item : allITems) {
					String actualText = item.getText();
					System.out.println("Actual Text ="  + actualText);
					
					// Nếu text = text mình muốn (item cần click vào)
					if (actualText.equals(expectedTextItem)) {
						
						// - B1: Nếu item cần chọn nằm trong vùng nhìn thấy thì ko cần scroll tới elemen tìm tiếp 
						//- B2 : Nếu item cần chọn nằm ở dưới thì scroll tới elemen
						jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
						sleepInSecond(2);
						// Step 4: Click vào item đó 
						
						item.click();
						sleepInSecond(2);
						//Thoát khỏi vòng lặp ko có kiểm tra element tiếp theo nữa 
						break;
					}
					
				}
				
		
	}
	
	public void enterToCustomDropdownlist(String parentLocator, String childLocator, String expectedTextItem) {
		// Step 1: Lấy đến thẻ input(textbox) để sendkey vào 
				driver.findElement(By.cssSelector(parentLocator)).sendKeys(expectedTextItem);
				sleepInSecond(2);
				
				// Step 2: Chờ cho các etem load hết ra thành công 
				//Lưu ý 1: Locator phải chứa hết tất cả các item 
				//Lưu ý 1: Locator phải đến note cuối cùng chưa text
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
				
				// Step 3: Tìm item cần chọn
				// - B1: Nếu item cần chọn nằm trong vùng nhìn thấy thì ko cần scroll tới elemen tìm tiếp 
				//- B2 : Nếu item cần chọn nằm ở dưới thì scroll tới elemen
				
				// Lấy hết tất cả element(item) ra sau đó duyệt qua tưng item
				List<WebElement> allITems = driver.findElements(By.cssSelector(childLocator));
				
				// Duyệt qua từng item getText của item ra
				for (WebElement item : allITems) {
					String actualText = item.getText();
					System.out.println("Actual Text ="  + actualText);
					
					// Nếu text = text mình muốn (item cần click vào)
					if (actualText.equals(expectedTextItem)) {
						
						// - B1: Nếu item cần chọn nằm trong vùng nhìn thấy thì ko cần scroll tới elemen tìm tiếp 
						//- B2 : Nếu item cần chọn nằm ở dưới thì scroll tới elemen
						jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
						sleepInSecond(2);
						// Step 4: Click vào item đó 
						
						item.click();
						sleepInSecond(2);
						//Thoát khỏi vòng lặp ko có kiểm tra element tiếp theo nữa 
						break;
					}
					
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
}
