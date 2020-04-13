package com.browser.chrome;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.drivers.RemoteDriverManager;


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
