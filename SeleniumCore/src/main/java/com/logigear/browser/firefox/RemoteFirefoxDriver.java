package com.logigear.browser.firefox;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.logigear.driver.manager.RemoteDriverManager;

public class RemoteFirefoxDriver extends RemoteDriverManager {

	@Override
	public void createWebDriver() {
		FirefoxOptions ops = new FirefoxOptions();
		ops.merge(getCapabilities());
		ops.addArguments(getArguments());
		ops.setAcceptInsecureCerts(true);
		ops.addPreference("security.insecure_field_warning.contextual.enabled", false);
		ops.addPreference("security.insecure_password.ui.enabled", false);
		this.webDriver = new RemoteWebDriver(getRemoteUrl(), ops);
	}
}
