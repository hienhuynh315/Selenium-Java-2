package com.logigear.browser.phantomjs;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.logigear.driver.manager.DriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalPhantomJSDriver extends DriverManager {

	@Override
	public void createWebDriver() {

		if (getVersion() == null) {
			WebDriverManager.phantomjs().setup();
		} else {
			WebDriverManager.phantomjs().version(getVersion()).setup();
		}

		this.webDriver = new PhantomJSDriver();
	}
}
