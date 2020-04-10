package com.logigear.trainning.chrome;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.logigear.trainning.driver.RemoteDriverManager;


public class RemoteChromeDriver extends RemoteDriverManager {

	@Override
	public void createWebDriver() {
		ChromeOptions ops = new ChromeOptions();
		ops.merge(getCapabilities());
		ops.addArguments(getArguments());
		// Failed create webdriver
		this.webDriver = new RemoteWebDriver(getRemoteUrl(), ops);
	}
}
