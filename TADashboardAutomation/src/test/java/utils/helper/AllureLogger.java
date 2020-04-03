package utils.helper;

import java.util.ArrayList;
import java.util.List;
import io.qameta.allure.Step;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.logigear.trainning.common.control.Element;

public class AllureLogger {
	

	private static List<String> currentLogs = new ArrayList<String>();

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AllureLogger.class);

	private AllureLogger() {
        // hide default constructor for this util class
    }

    /**
     * Uses the @Step annotation to log the given log message to Allure.
     *
     * @param message the message to log to the allure report
     */
    @Step("{0}")
    public static void logToAllure(String message) {
    	log.debug("Logged to allure: " + message);
    }

    /**
     * Logs the start of a step to your allure report.
     * Other steps will be sub-steps until you call stepFinish.
     *
     * @param stepName the name of the step
     */
	
	
	
	
	
	
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
		if (currentMethod.equals("setUp")) {
			currentLogs.clear();
		} else if (message.contains("TEST CASE:")) {
			currentLogs.clear();
		}
		currentLogs.add(message);
	}

	public static List<String> getCurrentLogs() {
		System.out.println("Get Current Logs: " + currentLogs.toString());
		return currentLogs;
	}

	public static AllureLogger getLogger(Class<Element> class1) {
		// TODO Auto-generated method stub
		return null;
	}
}
