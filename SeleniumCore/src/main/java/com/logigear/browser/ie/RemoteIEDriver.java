package com.logigear.browser.ie;

import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.logigear.driver.manager.RemoteDriverManager;

public class RemoteIEDriver extends RemoteDriverManager {

	@Override
	public void createWebDriver() {
		InternetExplorerOptions ops = new InternetExplorerOptions();
		ops.merge(getCapabilities());
		this.webDriver = new RemoteWebDriver(getRemoteUrl(), ops);
	}

}
