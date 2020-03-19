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

import com.logigear.driver.DriverProperty;
import com.logigear.driver.DriverUtils;
import com.logigear.helper.BrowserSettingHelper;

import utils.common.Common;
import utils.common.Constants;
import utils.config.TestNGAnotations.TestRailId;
import utils.helper.Logger;

public class TestListener implements ITestListener {

	String envScript = null;
	ScreenRecorderConfig screenRecordConfig;
	String env = null;
	APIClient client;
	boolean testRailEnabled = false;
	String testRailTestRunID = null;

	public String getTestrailID(ITestResult result) throws NoSuchMethodException, SecurityException {
		String testID = null;
		IClass obj = result.getTestClass();
		Class<?> newobj = obj.getRealClass();
		Method testMethod = newobj.getMethod(result.getName());

		if (testMethod.isAnnotationPresent(TestRailId.class)) {
			TestRailId testRailId = testMethod.getAnnotation(TestRailId.class);
			testID = Integer.toString(testRailId.testRailId());
			System.out.println("Test Rail ID = " + testID);
		}

		return testID;
	}

	public void postTestRailResult(String path, ITestResult result) {
		String testID = "";
		JSONObject caseData = null;

		try {
			List<String> logs = Logger.getCurrentLogs();
			testID = getTestrailID(result);
			if (testID == null) {
				System.out.println("There is no TestRail ID for the running test");
				return;
			}
			StringBuilder description = new StringBuilder();
			for (String log : logs) {
				description.append(log).append("\n");
			}

			description = description.append("\n").append(result.getThrowable().getMessage()).append(")\n\n");

			Map<String, Comparable> data = new HashMap<String, Comparable>();
			data.put("status_id", new Integer(5));
			data.put("comment", description.toString());

			caseData = (JSONObject) client.sendPost("add_result_for_case/" + testRailTestRunID + "/" + testID, data);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!path.isEmpty()) {
			try {
				client.sendPost("add_attachment_to_result_for_case/" + caseData.get("id").toString() + "/" + testID,
						path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

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
		Reporter.log(envScript);
		Logger.info(String.format("TEST CASE: %s.%s", result.getTestClass().getName(), result.getName()));
		Logger.info(String.format("TEST DESCRIPTION: %s", result.getMethod().getDescription()));
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		if (testRailEnabled) {
			try {
				String testID = getTestrailID(result);
				if ((testID == null)) {
					System.out.println("There is no TestRail ID for the running test");
					return;
				}
				Map<String, Comparable> data = new HashMap<String, Comparable>();
				data.put("status_id", new Integer(1));
				data.put("comment", "Marked as Passed by automation script");
				client.sendPost("add_result_for_case/" + testRailTestRunID + "/" + testID, data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String path = DriverUtils.captureScreenshot(UUID.randomUUID().toString(), "screenshots");

		if (testRailEnabled) {
			postTestRailResult(path, result);
		}

		if (!path.isEmpty()) {
			String script = Common.screenshotURI(path);
			Reporter.log(script);
			new File(path).delete();
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		env = context.getCurrentXmlTest().getParameter("env");
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		String profileFile = System.getProperty("profile");
		if (profileFile != null) {
			env = profileFile;
		} else {
			if (env == null) {
				env = "";
			}
			System.setProperty("profile", env);
		}

		if (env != null) {
			System.out.println("Testing on " + env.toUpperCase());
			envScript = "<script>\r\n" + "$(document).ready(function() {\r\n" + " orgText = $(\"h1\").text();\r\n"
					+ "if(!orgText.includes('on')){" + " $(\"h1\").text(orgText + \" on " + env.toUpperCase()
					+ "\");\r\n" + "};\r\n" + "})" + "</script>";
		}

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
}
