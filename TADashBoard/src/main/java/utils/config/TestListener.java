package utils.config;

import java.io.File;
import java.util.UUID;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.drivers.DriverUtils;

import utils.common.Common;
import utils.helper.Logger;

public class TestListener implements ITestListener {

	public void onTestFailure(ITestResult result) {
		// Take screenshot attach to Allure
		DriverUtils.takeScreenshot("Click on Screenshot for failure: ");

		// default report
		String path = DriverUtils.captureScreenshot(UUID.randomUUID().toString(), "screenshots");

		if (!path.isEmpty()) {
			String script = Common.screenshotURI(path);
			Reporter.log(script);
			new File(path).delete();
		}
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
