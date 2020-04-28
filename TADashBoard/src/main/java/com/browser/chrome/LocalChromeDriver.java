package com.browser.chrome;

import java.util.HashMap;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.drivers.DriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalChromeDriver extends DriverManager {

	@Override
	public void createWebDriver() {

		if (getVersion() == null) {
			WebDriverManager.chromedriver().setup();
		} else {
			WebDriverManager.chromedriver().version(getVersion()).setup();
		}

		ChromeOptions ops = new ChromeOptions();
		// To solve error:  Timed out receiving message from renderer for latest version on chrome
		ops.setPageLoadStrategy(PageLoadStrategy.NONE); 
		ops.merge(getCapabilities());
		ops.addArguments(getArguments());
		String downloadFilepath = System.getProperty("user.dir") + "\\downloads";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		chromePrefs.put("safebrowsing.enabled", "true");
		ops.setExperimentalOption("prefs", chromePrefs);

		this.webDriver = new ChromeDriver(ops);
	}
}
