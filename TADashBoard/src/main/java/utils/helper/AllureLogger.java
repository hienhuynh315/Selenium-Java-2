package utils.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.element.control.Element;

import io.qameta.allure.Step;

public class AllureLogger {

	private static List<String> currentLogs = new ArrayList<String>();

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Logger.class);

	@Step("{0}")
	public static void logToAllure(String message) {
		saveLog(message);
		log.info(message);
	}

	private static void saveLog(String message) {
		String currentMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
		if (currentMethod.equals("setUp")) {
			currentLogs.clear();
		} else if (message.contains("TEST CASE:")) {
			currentLogs.clear();
		}
		currentLogs.add(message);
	}

	public static Logger getLogger(Class<Element> class1) {
		// TODO Auto-generated method stub
		return null;
	}
}
