package finalProject.finalProject;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class testData {
	
	
	WebDriver driver = new ChromeDriver();
	String URL = "https://www.almosafer.com/en";
	String expectedCurrency = "SAR";
	String expectedNumber = "+966554400000";
	String expectedcheckHotelTabIsNotSelected = "false";

	LocalDate date = LocalDate.now();
	int tomorrw = date.plusDays(1).getDayOfMonth();
	String expectedDepartureDay = String.format("%02d", tomorrw);

	int afterTomorrow = date.plusDays(2).getDayOfMonth();
	String dayAfterTomorrow = String.format("%02d", afterTomorrow);

	String[] websites = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
	Random rand = new Random();
	int randomindex = rand.nextInt(websites.length);
	
	String[] countriesInEn = { "dubai", "jeddah", "riyadh" };
	int randomCountreEn = rand.nextInt(countriesInEn.length);

	String[] countriesInAr = { "جدة", "دبي" };
	int randomCountreAr = rand.nextInt(countriesInAr.length);

	String[] allrooms = { "A", "B" };
	int randomRoom = rand.nextInt(allrooms.length);



	
	public void Setup() {
		
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		WebElement settingButton = driver
				.findElement(By.cssSelector(".sc-jTzLTM.cta__button.cta__saudi.btn.btn-primary"));
		settingButton.click();
	}

}
