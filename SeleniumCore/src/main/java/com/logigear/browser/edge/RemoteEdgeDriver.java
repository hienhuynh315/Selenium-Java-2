package com.logigear.browser.edge;

import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.logigear.driver.manager.RemoteDriverManager;

public class RemoteEdgeDriver extends RemoteDriverManager {

	@Override
	public void createWebDriver() {
		EdgeOptions ops = new EdgeOptions();
		ops.merge(getCapabilities());
		this.webDriver = new RemoteWebDriver(getRemoteUrl(), ops);
	}
}
