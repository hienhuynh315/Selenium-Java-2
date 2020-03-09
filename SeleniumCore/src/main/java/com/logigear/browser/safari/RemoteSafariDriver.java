package com.logigear.browser.safari;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.logigear.driver.manager.RemoteDriverManager;

public class RemoteSafariDriver extends RemoteDriverManager {

	@Override
	public void createWebDriver() {
		SafariOptions ops = new SafariOptions();
		ops.merge(getCapabilities());
		this.webDriver = new RemoteWebDriver(getRemoteUrl(), ops);
	}
}
