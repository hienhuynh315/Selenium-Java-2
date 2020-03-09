package utils.common;

import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Parameters;

import com.logigear.driver.DriverProperty;
import com.logigear.driver.DriverUtils;
import com.logigear.exception.DriverCreationException;
import com.logigear.helper.BrowserSettingHelper;

import utils.config.ModuleFactory;
import utils.helper.PropertiesHelper;

@Guice(modules = ModuleFactory.class)
public class TestBase {

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(String browser, Method method) throws Throwable {
		DriverUtils.setTimeOut(Integer
				.parseInt(PropertiesHelper.getPropValue("driver.timeout")));
		DriverUtils.setShortTimeOut(Integer.parseInt(
				PropertiesHelper.getPropValue("driver.shortTimeout")));
		DriverProperty property = BrowserSettingHelper
				.getDriverProperty(Constants.BROWSER_SETTING_FILE, browser);

		setProperty(property);
		setEnv(System.getProperty("profile"));
		openBrowser();
	}

	@AfterClass(alwaysRun = true)
	public void afterMethod() {
		DriverUtils.quitBrowser();
	}

	public void openBrowser() throws DriverCreationException {
		DriverUtils.getDriver(getProperty());
		DriverUtils.setEnv(getEnv());
		DriverUtils.maximizeBrowser();
		DriverUtils.setBrowserSize(Constants.BROWSER_SIZE_WIDTH,
				Constants.BROWSER_SIZE_HEIGHT);
	}

	public DriverProperty getProperty() {
		return property;
	}

	public void setProperty(DriverProperty property) {
		this.property = property;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	private String env;
	private DriverProperty property;
}
