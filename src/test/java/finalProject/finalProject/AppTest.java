package finalProject.finalProject;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {

	WebDriver driver = new ChromeDriver();

	String URL = "https://www.almosafer.com/en";

	@BeforeTest
	public void mySetup() {
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		WebElement settingButton = driver
				.findElement(By.cssSelector(".sc-jTzLTM.cta__button.cta__saudi.btn.btn-primary"));
		settingButton.click();
	}

	@Test(priority = 1)
	public void checkWibsiteLanguage(String expectedLanguage) {

		String actualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
		 
		Assert.assertEquals(actualLanguage, expectedLanguage);

	}

	@Test(priority = 2)
	public void checkCurrency() {

		String actualCurrency = driver.findElement(By.cssSelector(".sc-cugefK.dUbuDW")).getText();
		String expectedCurrency = "SAR";

		Assert.assertEquals(actualCurrency, expectedCurrency);

	}

	@Test(priority = 3)
	public void checkContactNumber() {
		String actualNumber = driver.findElement(By.linkText("+966554400000")).getText();
		String expectedNumber = "+966554400000";

		Assert.assertEquals(actualNumber, expectedNumber);

	}

	@Test(priority = 4)
	public void checkQitaflogo() {
		WebElement theFooter = driver.findElement(By.tagName("footer"));
		boolean actualLogo = theFooter.findElement(By.cssSelector(".sc-ekulBa.iOOTo"))
				.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-lcpuFF.jipXfR")).isDisplayed();

		Assert.assertEquals(actualLogo, true);
	}

	
	@Test (priority = 5)
	public void checkHotelTabIsNotSelected() {
		
		WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		
		String actualValue = hotelTab.getDomAttribute("aria-selected");
		String expectedValue = "false";
		
		Assert.assertEquals(actualValue, expectedValue);
		
		
		
	}
	
	@Test (priority = 6)
	public void flightDepartureDate () {
		LocalDate date = LocalDate.now();
		
		List<WebElement> dates = driver.findElements(By.cssSelector(".sc-dXfzlN.iPVuSG"));
		String actualDepartureDate = dates.get(0).getText();
		int tomorrw = date.plusDays(1).getDayOfMonth();
		String expectedDepartureDay = String.format("%02d", tomorrw);
		
		Assert.assertEquals(actualDepartureDate, expectedDepartureDay);

			
	}
	
	@Test(priority = 7)
	public void flightReturnDate() {
		LocalDate date = LocalDate.now();
		
		List<WebElement> dates = driver.findElements(By.cssSelector(".sc-dXfzlN.iPVuSG"));
		String actualRetuenDate = dates.get(1).getText();
		int afterTomorrow = date.plusDays(2).getDayOfMonth();
		String dayAfterTomorrow = String.format("%02d", afterTomorrow);
		
		Assert.assertEquals(actualRetuenDate, dayAfterTomorrow);
		
		
	}
	
	@Test (priority = 8,invocationCount = 4)
	public void changeTheWebsite() {
		
		String [] websites = {"https://www.almosafer.com/en" , "https://www.almosafer.com/ar"} ;
		Random rand = new Random();
		int randomindex = rand.nextInt(websites.length);
		driver.get(websites[randomindex]);
		
		if(driver.getCurrentUrl().contains("en")) {
			
			checkWibsiteLanguage("en");
			
		}else {
			checkWibsiteLanguage("ar");
		}
		
	}
	
	
	
	@AfterTest
	public void afterTheTest() {

	}

}
