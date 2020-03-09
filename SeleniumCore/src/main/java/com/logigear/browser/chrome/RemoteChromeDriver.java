package com.logigear.browser.chrome;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.logigear.driver.manager.RemoteDriverManager;

public class RemoteChromeDriver extends RemoteDriverManager {

	@Override
	public void createWebDriver() {
		ChromeOptions ops = new ChromeOptions();
		ops.merge(getCapabilities());
		ops.addArguments(getArguments());
		this.webDriver = new RemoteWebDriver(getRemoteUrl(), ops);
	}
}
