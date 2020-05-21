package utils.helper;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestResult;
import org.testng.Reporter;

import com.element.control.Element;

import io.qameta.allure.Step;

public class Logger {
	
	private static String methodName;
	private static String className;
	private static List<String> currentLogs = new ArrayList<String>();
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Logger.class);

//	@Step("{message}")
	public static void info(String message) {
		saveLog(message);
		Reporter.log("<b>INFO: </b>" + message);
		log.info(message);
	}

	public static void infoAfterMethod(String message, ITestResult testResult) {
		saveLog(message);
		Reporter.setCurrentTestResult(testResult);
		Reporter.log("<b>INFO: </b>" + message);
		log.info(message);
	}

	@Step("VERIFY POINT: {message}")
	public static void verify(String message) {
		saveLog(message);
		Reporter.log("<b style=\"color: #44aa44;\">VERIFY POINT: </b>" + message);
		log.info("VERIFY POINT: " + message);
	}

	public static void fail(String message) {
		saveLog(message);
		Reporter.log("<b style=\"color: #ff4444;\">FAIL: </b>" + message);
		log.info("FAIL: " + message);
	}

	public static void error(String message) {
		saveLog(message);
		Reporter.log("<b style=\"color: #ff6e00;\">ERROR: </b>" + message);
		log.info("ERROR: " + message);
	}

	private static void saveLog(String message) {
		String currentMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
		String currentClass = Thread.currentThread().getStackTrace()[3].getClassName();
		if (!currentMethod.equals(methodName) || (currentMethod.equals(methodName) && !currentClass.equals(className))) {
			currentLogs.clear();
		}
		methodName = currentMethod;
		className = currentClass;
		currentLogs.add(message);
	}

//	private static void saveLog(String message) {
//		String currentMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
//		if (currentMethod.equals("setUp")) {
//			currentLogs.clear();
//		} else if (message.contains("TEST CASE:")) {
//			currentLogs.clear();
//		}
//		currentLogs.add(message);
//	}

	public static List<String> getCurrentLogs() {
		System.out.println("Get Current Logs: " + currentLogs.toString());
		return currentLogs;
	}

	public static Logger getLogger(Class<Element> class1) {
		// TODO Auto-generated method stub
		return null;
	}
}
