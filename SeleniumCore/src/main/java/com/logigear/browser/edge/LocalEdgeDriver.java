package com.logigear.browser.edge;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.logigear.driver.manager.DriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalEdgeDriver extends DriverManager {

	@Override
	public void createWebDriver() {

		if (getVersion() == null) {
			WebDriverManager.edgedriver().setup();
		} else {
			WebDriverManager.edgedriver().version(getVersion()).setup();
		}

		EdgeOptions ops = new EdgeOptions();
		ops.merge(getCapabilities());
		this.webDriver = new EdgeDriver(ops);
	}
}
