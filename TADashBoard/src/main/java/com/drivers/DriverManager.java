package com.drivers;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import utils.helper.JsonHelper;


public abstract class DriverManager {

	protected WebDriver webDriver;

	private String browserName;

	public boolean isWaitForAjax = true;

	private String capabilities;
	private String arguments;
	private String version;

	public WebDriver getWebDriver() {
		return webDriver;
	}

	protected abstract void createWebDriver();

	protected DesiredCapabilities getCapabilities() {
		return JsonHelper.convertJsonToCapabilities(this.capabilities);
	}

	public void setCapabilities(String caps) {
		this.capabilities = caps;
	}

	protected List<String> getArguments() {
		return JsonHelper.convertJsonToArguments(this.arguments);
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
