package finalProject.finalProject;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest extends testData {

	@BeforeTest
	public void mySetup() {
		Setup();
	}

	@Test(priority = 1, enabled = false)
	public void checkWibsiteLanguage(String expectedLanguage) {

		String actualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
		Assert.assertEquals(actualLanguage, expectedLanguage);
	}

	@Test(priority = 2)
	public void checkCurrency() {

		String actualCurrency = driver.findElement(By.cssSelector(".sc-cugefK.dUbuDW")).getText();
		Assert.assertEquals(actualCurrency, expectedCurrency);

	}

	@Test(priority = 3)
	public void checkContactNumber() {
		String actualNumber = driver.findElement(By.linkText("+966554400000")).getText();
		Assert.assertEquals(actualNumber, expectedNumber);

	}

	@Test(priority = 4)
	public void checkQitaflogo() {
		WebElement theFooter = driver.findElement(By.tagName("footer"));
		boolean actualLogo = theFooter.findElement(By.cssSelector(".sc-ekulBa.iOOTo"))
				.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-lcpuFF.jipXfR")).isDisplayed();
		Assert.assertEquals(actualLogo, true);
	}

	@Test(priority = 5)
	public void checkHotelTabIsNotSelected() {

		WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String actualValue = hotelTab.getDomAttribute("aria-selected");
		Assert.assertEquals(actualValue, expectedcheckHotelTabIsNotSelected);

	}

	@Test(priority = 6)
	public void flightDepartureDate() {

		List<WebElement> dates = driver.findElements(By.cssSelector(".sc-dXfzlN.iPVuSG"));
		String actualDepartureDate = dates.get(0).getText();
		Assert.assertEquals(actualDepartureDate, expectedDepartureDay);

	}

	@Test(priority = 7)
	public void flightReturnDate() {

		List<WebElement> dates = driver.findElements(By.cssSelector(".sc-dXfzlN.iPVuSG"));
		String actualRetuenDate = dates.get(1).getText();
		Assert.assertEquals(actualRetuenDate, dayAfterTomorrow);

	}

	@Test(priority = 8)
	public void changeTheWebsite() {

		driver.get(websites[randomindex]);
		if (driver.getCurrentUrl().contains("en")) {

			checkWibsiteLanguage("en");

		} else {
			checkWibsiteLanguage("ar");
		}

	}

	@Test(priority = 9)
	public void hotelTab() {
		WebElement theHotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		theHotelTab.click();

		WebElement searchField = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input "));

		if (driver.getCurrentUrl().contains("en")) {
			searchField.sendKeys(countriesInEn[randomCountreEn]);

		} else {
			searchField.sendKeys(countriesInAr[randomCountreAr]);
		}

	}

	@Test(priority = 10)
	public void hotelSearchBox() {
		WebElement roomsAndAdults = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		Select myselect = new Select(roomsAndAdults);

		myselect.selectByValue(allrooms[randomRoom]);

		WebElement searchButton = driver.findElement(By.cssSelector(
				".sc-jTzLTM.sc-1vkdpp9-6.iKBWgG.js-HotelSearchBox__SearchButton.btn.btn-primary.btn-block"));

		searchButton.click();
	}

	@Test(priority = 11)
	public void checkThResultItsIsretrived() throws InterruptedException {

		Thread.sleep(10000);
		String results = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']")).getText();

		if (driver.getCurrentUrl().contains("en")) {
			assertEquals(results.contains("found"), true);

		} else {
			assertEquals(results.contains("مكان إقامة"), true);
		}

	}

	@AfterTest
	public void afterTheTest() {

	}

}
