package com.logigear.trainning.driver;

import org.openqa.selenium.WebDriver;

import utils.helper.ReflectionUtils;

public class DriverManagerFactory {
	protected static final ThreadLocal<DriverManager> DRIVERS = new ThreadLocal<>();

	private static int timeOut;
	private static int shortTimeOut;
	private static boolean browserLog;

	protected static WebDriver getDriver() {
		return DRIVERS.get().getWebDriver();
	}

	protected static boolean isWaitForAjax() {
		return DRIVERS.get().isWaitForAjax;
	}

	protected static void setWaitForAjax(boolean isWait) {
		DRIVERS.get().isWaitForAjax = isWait;
	}

	protected static void setWebDriver(DriverManager driverManager) {
		DRIVERS.set(driverManager);
	}

	protected static void createWebDriver(DriverProperty property) throws DriverCreationException {

		Object obj = ReflectionUtils.initWebDriver(property);
		if (obj == null) {
			throw new DriverCreationException(String.format("Cannot create the %s driver", property.getDriverType()));
		}
		setWebDriver((DriverManager) obj);

	}

	/**
	 * @return the timeOut
	 */
	public static int getTimeOut() {
		return timeOut;
	}

	/**
	 * @param timeOut the timeOut to set
	 */
	public static void setTimeOut(int timeOut) {
		DriverManagerFactory.timeOut = timeOut;
	}

	/**
	 * @return the shortTimeOut
	 */
	public static int getShortTimeOut() {
		return shortTimeOut;
	}

	/**
	 * @param timeOut the timeOut to set
	 */
	public static void setShortTimeOut(int timeOut) {
		DriverManagerFactory.shortTimeOut = timeOut;
	}

	public static boolean isBrowserLog() {
		return browserLog;
	}

	public static void setBrowserLog(boolean browserLog) {
		DriverManagerFactory.browserLog = browserLog;
	}
}
