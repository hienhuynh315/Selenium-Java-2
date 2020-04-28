package utils.config;

import java.io.File;
import java.util.UUID;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.drivers.DriverUtils;

import utils.common.Common;

public class TestListener implements ITestListener {
	String url = "";

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
		url = context.getCurrentXmlTest().getParameter("url");
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		// set profile properties when run difference link
		System.setProperty("profile", "");
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
}
