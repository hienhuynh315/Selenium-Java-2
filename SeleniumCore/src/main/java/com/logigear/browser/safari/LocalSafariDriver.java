package com.logigear.browser.safari;

import org.openqa.selenium.safari.SafariDriver;

import com.logigear.driver.manager.DriverManager;

public class LocalSafariDriver extends DriverManager {

	@Override
	public void createWebDriver() {
		this.webDriver = new SafariDriver();
	}
}
