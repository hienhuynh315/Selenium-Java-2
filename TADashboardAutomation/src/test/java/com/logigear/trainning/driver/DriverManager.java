package com.logigear.trainning.driver;



import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

	protected WebDriver webDriver;

	private String browserName;

	public boolean isWaitForAjax = true;
	public String env = "";

	private String version;

	public WebDriver getWebDriver() {
		return webDriver;
	}

	protected abstract void createWebDriver();


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
