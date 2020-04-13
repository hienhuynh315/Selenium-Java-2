package com.browser.ie;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import com.drivers.DriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalIEDriver extends DriverManager {

	@Override
	public void createWebDriver() {

		if (getVersion() == null) {
			WebDriverManager.iedriver().arch32().setup();
		} else {
			WebDriverManager.iedriver().arch32().version(getVersion()).setup();
		}

		InternetExplorerOptions ops = new InternetExplorerOptions();
		ops.merge(getCapabilities());
		this.webDriver = new InternetExplorerDriver(ops);
	}
}
