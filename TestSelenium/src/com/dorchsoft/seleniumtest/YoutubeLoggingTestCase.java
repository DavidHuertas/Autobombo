package com.dorchsoft.seleniumtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

//import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class YoutubeLoggingTestCase {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "/Library/Autobombo/geckodriver");
		driver = new FirefoxDriver();
		baseUrl = "https://accounts.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testYoutubeLoggingTestCase() throws Exception {
		driver.get(baseUrl
				+ "/signin/v2/identifier?continue=https%3A%2F%2Fwww.youtube.com%2Fsignin%3Fapp%3Ddesktop%26action_handle_signin%3Dtrue%26next%3D%252F%26hl%3Des%26feature%3Dsign_in_button&hl=es&service=youtube&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		driver.findElement(By.id("identifierId")).clear();
		driver.findElement(By.id("identifierId")).sendKeys("dhuertas.eng");
		driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
//		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("nevermind1991");
		driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
		Thread.sleep(5000);
		assertEquals("YouTube", driver.getTitle());
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		assertEquals("David Huertas", driver.findElement(By.cssSelector("div.yt-masthead-picker-name")).getText());
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
