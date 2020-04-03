package com.logigear.trainning.driver;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.common.Constants;

public class DriverUtils {

	public static void setBrowser(String browser) {
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER_PATH);
			driver = new FirefoxDriver();
		}
	}

	public static void setTimeOut(int timeoutSec) {
		DriverManagerFactory.setTimeOut(timeoutSec);
	}

	public static int getTimeOut() {
		return DriverManagerFactory.getTimeOut();
	}

	public static void setShortTimeOut(int timeoutSec) {
		DriverManagerFactory.setShortTimeOut(timeoutSec);
	}

	public static int getShortTimeOut() {
		return DriverManagerFactory.getShortTimeOut();
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static WebDriver getWebDriver() {
		return getDriver();
	}

	public static void acceptAlert() {
		getDriver().switchTo().alert().accept();
		delay(1);// wait for alert close
	}

	public static void delay(int timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void refreshPage() {
		driver.navigate().refresh();
	}

	public static void navigate(String url) {
		logger.debug("Navigate to " + url);
		try {
			getDriver().get(url);
		} catch (Exception e) {
			logger.error("An error occurred when nagivating " + e.getMessage());
		}
	}

	public static String captureScreenshot(String filename, String filepath) {
		logger.info("Capture screenshot");
		String path = "";
		try {
			// Taking the screen using TakesScreenshot Class
			File objScreenCaptureFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);

			// Storing the image in the local system.
			File dest = new File(
					System.getProperty("user.dir") + File.separator + filepath + File.separator + filename + ".png");
			FileUtils.copyFile(objScreenCaptureFile, dest);
			path = dest.getAbsolutePath();
		} catch (Exception e) {
			logger.error("An error occurred when capturing screen shot: " + e.getMessage());
		}
		return path;
	}

	private static Logger logger = Logger.getLogger(DriverUtils.class);
	protected static WebDriver driver;
}
