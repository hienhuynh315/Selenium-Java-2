package utils.config;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.testng.IClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.logigear.trainning.driver.DriverUtils;

import utils.common.Common;
import utils.common.Constants;
import utils.config.TestNGAnotations.TestRailId;
import utils.helper.Logger;

public class TestListener implements ITestListener {

	ScreenRecorderConfig screenRecordConfig;

	public void onTestFailure(ITestResult result) {
		String path = DriverUtils.captureScreenshot(UUID.randomUUID().toString(), "screenshots");

		if (!path.isEmpty()) {
			String script = Common.screenshotURI(path);
			Reporter.log(script);
			new File(path).delete();
		}
		/*
		 * for (LogEntry entry : DriverUtils.captureBrowserLog()) { if (entry.getLevel()
		 * == Level.SEVERE) { Logger.info("URL: " + DriverUtils.getCurrentURL());
		 * Logger.info("BROWSER LOG: " + entry); } }
		 */
	}

	public void onTestStart(ITestResult result) {
		Logger.info(String.format("TEST CASE: %s.%s", result.getTestClass().getName(), result.getName()));
		Logger.info(String.format("TEST DESCRIPTION: %s", result.getMethod().getDescription()));
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSkipped(ITestResult result) {
//		// TODO Auto-generated method stub
//		String path = DriverUtils.captureScreenshot(UUID.randomUUID().toString(), "screenshots");
//
//		if (!path.isEmpty()) {
//			String script = Common.screenshotURI(path);
//			Reporter.log(script);
//			new File(path).delete();
//		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		String profileFile = System.getProperty("profile");
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
}
