package com.logigear.driver;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.logigear.driver.manager.DriverManagerFactory;
import com.logigear.exception.DriverCreationException;

public class DriverUtils extends DriverManagerFactory {

	public static void getDriver(DriverProperty property) throws DriverCreationException {
		logger.debug(String.format("Creating the %s driver", property.getDriverType().name().toString()));
		createWebDriver(property);
	}

	protected static JavascriptExecutor jsExecutor() {
		return (JavascriptExecutor) getDriver();
	}

	public static boolean isWaitForAjax() {
		return DRIVERS.get().isWaitForAjax;
	}

	public static void setWaitForAjax(boolean isWait) {
		DRIVERS.get().isWaitForAjax = isWait;
	}

	public static String getBrowserName() {
		return DRIVERS.get().getBrowserName();
	}

	public static String getEnv() {
		return DRIVERS.get().env;
	}

	public static void setEnv(String env) {
		if (env != null) {
			DRIVERS.get().env = env;
		}
	}

	public static WebDriver getWebDriver() {
		return getDriver();
	}

	public static void navigate(String url) {
		logger.debug("Navigate to " + url);
		try {
			getDriver().get(url);
		} catch (Exception e) {
			logger.error("An error occurred when nagivating " + e.getMessage());
		}
	}

	public static void setTimeOut(int timeoutSec) {
		DriverManagerFactory.setTimeOut(timeoutSec);
	}

	public static int getTimeOut() {
		return DriverManagerFactory.getTimeOut();
	}

	public static void setShortTimeOut(int timeoutSec) {
		DriverManagerFactory.setShortTimeOut(timeoutSec);
	}

	public static int getShortTimeOut() {
		return DriverManagerFactory.getShortTimeOut();
	}

	public static void maximizeBrowser() {
		try {
			logger.debug("Maximize browser");
			DriverManagerFactory.getDriver().manage().window().maximize();
		} catch (Exception e) {
			logger.error("An error occurred when maximizing browser" + e.getMessage());
		}
	}

	public static void setBrowserSize(int width, int height) {
		try {
			logger.debug("Resizing browser");
			Dimension browserSize = new Dimension(width, height);
			DriverManagerFactory.getDriver().manage().window().setSize(browserSize);
		} catch (Exception e) {
			logger.error("An error occurred when resizing browser" + e.getMessage());
		}
	}

	public static Object execJavaScript(String script, Object... objs) {
		return ((JavascriptExecutor) getDriver()).executeScript(script, objs);
	}

	public static void quitBrowser() {
		try {
			logger.debug("Quit browser");
			DriverManagerFactory.getDriver().close();
			DriverManagerFactory.getDriver().quit();
		} catch (Exception e) {
			logger.error("An error occurred when quiting browser" + e.getMessage());
		}

	}

	public static void closeBrowser() {
		try {
			logger.debug("Close browser");
			DriverManagerFactory.getDriver().close();
		} catch (Exception e) {
			logger.error("An error occurred when closing browser" + e.getMessage());
		}

	}

	public static void waitForAjaxJQueryProcess() {
		logger.debug("Wait for ajax complete");
		WebDriverWait wait = new WebDriverWait(getWebDriver(), getTimeOut());
		try {
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					Boolean ajaxIsComplete = (Boolean) (((JavascriptExecutor) driver)
							.executeScript("return Ext.Ajax.isLoading() == false;"));
					return ajaxIsComplete;
				}
			});
		} catch (Exception e) {
			logger.error("An error occurred when waitForAjaxJQueryProcess" + e.getMessage());
		}
	}

	public static void waitForJavaScriptIdle() {
		try {
			WebDriverWait wait = new WebDriverWait(getWebDriver(), getTimeOut());

			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					Boolean ajaxIsComplete = (Boolean) (executor.executeScript(
							"if (typeof jQuery != 'undefined') { return jQuery.active == 0; } else {  return true; }"));
					Boolean domIsComplete = (Boolean) (executor
							.executeScript("return document.readyState == 'complete';"));
					return ajaxIsComplete & domIsComplete;
				}
			});
			delay(1);
		} catch (Exception e) {
			logger.error("waitForJavaScriptIdle: An error occurred when waitForAjaxJQueryProcess" + e.getMessage());
		}
	}

	public static void waitForPageLoad() {
		try {
			WebDriverWait wait = new WebDriverWait(getWebDriver(), getTimeOut());

			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					Boolean domIsComplete = (Boolean) (executor
							.executeScript("return document.readyState == 'complete';"));
					return domIsComplete;
				}
			});
			delay(1);
		} catch (Exception e) {
			logger.error("waitForPageLoad: An error occurred when waitForPageLoad" + e.getMessage());
		}
	}

	public static String captureScreenshot(String filename, String filepath) {
		logger.info("Capture screenshot");
		String path = "";
		try {
			// Taking the screen using TakesScreenshot Class
			File objScreenCaptureFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);

			// Storing the image in the local system.
			File dest = new File(
					System.getProperty("user.dir") + File.separator + filepath + File.separator + filename + ".png");
			FileUtils.copyFile(objScreenCaptureFile, dest);
			path = dest.getAbsolutePath();
		} catch (Exception e) {
			logger.error("An error occurred when capturing screen shot: " + e.getMessage());
		}
		return path;
	}

	public static void delay(int timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void smallDelay(int timeInMilisecond) {
		try {
			Thread.sleep(timeInMilisecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isPageReloaded() {
		try {
			WebDriverWait wait = new WebDriverWait(getWebDriver(), getShortTimeOut());

			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					return (Boolean) (executor.executeScript("return document.readyState != 'complete';"));
				}
			});
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					return (Boolean) (executor.executeScript("return document.readyState == 'complete';"));
				}
			});
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void storeCurrentWindowHandle() {
		windowHandleStack.push(getDriver().getWindowHandle());
	}

	public static void switchToNewWindow() {
		for (String winHandle : getDriver().getWindowHandles()) {
			getDriver().switchTo().window(winHandle);
		}
	}

	public static void switchToNewWindow(int windowNumber) {
		Set<String> winHandles = new HashSet<String>();
		winHandles = getDriver().getWindowHandles();
		String winHandle = winHandles.toArray(new String[winHandles.size()])[windowNumber - 1];
		getDriver().switchTo().window(winHandle);
		DriverUtils.delay(2);
		// wait for switch to new window
	}

	public static void openNewTab() {
		ArrayList<String> tabs = new ArrayList<String>(getWebDriver().getWindowHandles());
		if (tabs.size() > 1) {
			switchToNewWindow(tabs.size());
		}
		execJavaScript("window.open()");
		tabs = new ArrayList<String>(getWebDriver().getWindowHandles());
		switchToNewWindow(tabs.size());
	}

	public static void backToPreviousTab() {
		ArrayList<String> tabs = new ArrayList<String>(getWebDriver().getWindowHandles());
		getDriver().close();
		switchToNewWindow(tabs.size() - 1);
	}

	public static boolean isNewWindowOpened() {
		return getDriver().getWindowHandles().size() > 1;
	}

	public static void backToParentWindow() {
		getDriver().close();
		getDriver().switchTo().window(windowHandleStack.pop());
	}

	public static String getAlertText() {
		return getDriver().switchTo().alert().getText();
	}

	public static void waitForAlertDisplay() {
		int i = 0;
		while (i++ < 120) {
			if (isAlertDisplayed()) {
				break;
			}
			delay(1);
		}
	}

	public static void waitForAlertDisplay(int timeOutSec) {
		int i = 0;
		while (i++ < timeOutSec) {
			if (isAlertDisplayed()) {
				break;
			}
			delay(1);
		}
	}

	public static void acceptAlert() {
		getDriver().switchTo().alert().accept();
		delay(1);// wait for alert close
		if (DriverUtils.getBrowserName().toLowerCase().contains("firefox")) {
			delay(5);// handle timeing issue on Firefox
		}
	}

	public static boolean isAlertDisplayed() {
		try {
			getDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public static void dismissAlert() {
		getDriver().switchTo().alert().dismiss();
		delay(1);// wait for alert close
		waitForPageLoad();
	}

	public static void refreshPage() {
		getDriver().navigate().refresh();
		waitForPageLoad();
	}

	public static void scrollToTop() {
		jsExecutor().executeScript("window.scrollTo(0,0);");
	}

	public static void scrollToBottom() {
		jsExecutor().executeScript("window.scrollTo(2000,2000);");
	}

	public static void deleteAllCookies() {
		getWebDriver().manage().deleteAllCookies();
	}

	public static void highLightElement(WebElement element) {
		jsExecutor().executeScript("arguments[0].style.border='3px solid red';", element);
	}

	public static void tryAcceptAlert() {
		try {
			getDriver().switchTo().alert().accept();
			delay(1);// wait for alert close
			waitForPageLoad();
		} catch (Exception ex) {
			// do nothing
		}
	}

	public static LogEntries captureBrowserLog() {
		LogEntries logEntries = getDriver().manage().logs().get(LogType.BROWSER);
		return logEntries;
	}

	public static String getCurrentURL() {
		return getDriver().getCurrentUrl();
	}

	public static WebElement getFocusedElement() {
		return getWebDriver().switchTo().activeElement();
	}

	private static Logger logger = Logger.getLogger(DriverUtils.class);
	private static Stack<String> windowHandleStack = new Stack<String>();
}
