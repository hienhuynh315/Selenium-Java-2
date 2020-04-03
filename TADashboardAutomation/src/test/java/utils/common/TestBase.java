package utils.common;

import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Parameters;

import com.logigear.trainning.driver.DriverProperty;
import com.logigear.trainning.driver.DriverUtils;

import utils.config.ModuleFactory;
import utils.helper.BrowserSettingHelper;

@Guice(modules = ModuleFactory.class)
public class TestBase {

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(String browser, Method method) throws Throwable {
//		DriverProperty property = BrowserSettingHelper.getDriverProperty(Constants.BROWSER_SETTING_FILE, browser);
		if (browser == null) {
			DriverUtils.setBrowser("chrome");
		} else if (browser.equals("firefox")) {
			DriverUtils.setBrowser(browser);

		} else if (browser.equals("chrome")) {
			DriverUtils.setBrowser(browser);

		}
	}

	@AfterClass(alwaysRun = true)
	public void afterMethod() {
		DriverUtils.getDriver().quit();
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
