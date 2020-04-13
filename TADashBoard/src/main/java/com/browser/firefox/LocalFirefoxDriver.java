package com.browser.firefox;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.drivers.DriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalFirefoxDriver extends DriverManager {

	@Override
	public void createWebDriver() {

		if (getVersion() == null) {
			WebDriverManager.firefoxdriver().setup();
		} else {
			WebDriverManager.firefoxdriver().version(getVersion()).setup();
		}

		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
		FirefoxOptions ops = new FirefoxOptions();
		ops.merge(getCapabilities());
		ops.addArguments(getArguments());
		ops.setAcceptInsecureCerts(true);
		ops.addPreference("security.insecure_field_warning.contextual.enabled", false);
		ops.addPreference("security.insecure_password.ui.enabled", false);
		String downloadFilepath = System.getProperty("user.home") + "/Downloads";
		ops.addPreference("browser.download.folderList", 2);
		ops.addPreference("browser.download.dir", downloadFilepath);
		ops.addPreference("browser.download.useDownloadDir", true);
		ops.addPreference("browser.download.manager.alertOnEXEOpen", false);
		ops.addPreference("browser.download.manager.showWhenStarting", false);
		ops.addPreference("browser.download.manager.focusWhenStarting", false);
		ops.addPreference("browser.download.manager.closeWhenDone", true);
		ops.addPreference("browser.download.manager.showAlertOnComplete", false);
		ops.addPreference("browser.download.manager.useWindow", false);
		ops.addPreference("browser.helperApps.alwaysAsk.force", false);
		ops.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		ops.addPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
		ops.addPreference("pdfjs.disabled", true);

		this.webDriver = new FirefoxDriver(ops);
	}
}
