package com.nagarro.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestGoogle {

	private WebDriver driver;

	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void testTitle() {
		driver.get("https://www.google.com");
		String actualTitle = driver.getTitle();
		System.out.println("Page Title: " + actualTitle);
		Assert.assertTrue(actualTitle.contains("Google"), "Title does not match!");
	}

	@Test
	public void testAmazonTitle() {
		driver.get("https://www.amazon.com");
		String actualTitle = driver.getTitle();
		System.out.println("Page Title: " + actualTitle);
		Assert.assertTrue(actualTitle.contains("Amazon"), "Title does not match!");
	}

	@Test
	public void testAmazonSearch() throws InterruptedException {
		driver.get("https://www.amazon.com");
		WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchBox.sendKeys("Phone");
		searchBox.sendKeys(Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement searchHeading = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space()='Results']")));
		Assert.assertTrue(searchHeading.isDisplayed(), "The Search header is not visible.");
	}


	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
