package com.dorchsoft.seleniumtest;

import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.dorchsoft.seleniumtest.input.AccountsReader;
import com.dorchsoft.seleniumtest.input.VideosReader;
import com.dorchsoft.seleniumtest.model.Account;
import com.dorchsoft.seleniumtest.model.Video;

public class SeleniumTest {

	private static WebDriver driver;
	private static String baseUrl;
	// private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();
	private static List<Account> accounts = AccountsReader.readAccount();
	private static List<Video> videos = VideosReader.readVideo();

	public static void main(String[] args) throws Exception {

		int i = 0;
		
		while (true) {

			Collections.shuffle(accounts);
			
			for (Account account : accounts) {

				System.out.println("STARTING AGAIN ===> VIDEOS PLAYED: " + i);
				setUpBrowser();
				startMainProccess(account, videos);
				tearDown();

				i++;
			}
		}

	}

	public static void setUpBrowser() throws Exception {
		System.setProperty("webdriver.gecko.driver", "/Library/Autobombo/geckodriver");
		baseUrl = "https://accounts.google.com/";
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MINUTES);

	}

	public static void startMainProccess(Account account, List<Video> videos) {

		try {
			driver.get(baseUrl
					+ "/signin/v2/identifier?continue=https%3A%2F%2Fwww.youtube.com%2Fsignin%3Fapp%3Ddesktop%26action_handle_signin%3Dtrue%26next%3D%252F%26hl%3Des%26feature%3Dsign_in_button&hl=es&service=youtube&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
			driver.findElement(By.id("identifierId")).clear();
			driver.findElement(By.id("identifierId")).sendKeys(account.getUser());
			Thread.sleep(RandomUtils.nextInt(5000, 7000));
			driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
			Thread.sleep(RandomUtils.nextInt(3000, 5000));
			driver.findElement(By.name("password")).clear();
			driver.findElement(By.name("password")).sendKeys(account.getPwd());
			Thread.sleep(RandomUtils.nextInt(5000, 7000));
			driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
			Thread.sleep(RandomUtils.nextInt(8000, 10000));
			/*
			 * if (driver.getTitle().equals("YouTube")) {
			 * System.out.println("YOUTUBE CHECKPOINT! Title Checked"); } else {
			 * System.out.println("YOUTUBE CHECKPOINT! Wrong Title, '" +
			 * driver.getTitle() + "' instead of YouTube"); }
			 */
			driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
			/*
			 * if
			 * (driver.findElement(By.cssSelector("div.yt-masthead-picker-name")
			 * ).getText().equals("David Huertas")) {
			 * System.out.println("YOUTUBE CHECKPOINT! Name Checked"); }
			 */
			for (Video video : videos) {
				driver.get(video.getUrl());
				// Thread.sleep(5000); //FOR TEST ONLY
				Integer randomTime=RandomUtils.nextInt(video.getTime()*1000/2, video.getTime()*1000);
				System.out.println("USER CONNECTED --> " + account.getUser());
				System.out.println("PLAYING VIDEO " + video.getTitle() + " --> '" + randomTime/1000 + "' seconds");
				Thread.sleep(randomTime);
			}

		} catch (Exception ex) {
			System.out.println("Error - " + ex);
		}
	}

	public static void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
