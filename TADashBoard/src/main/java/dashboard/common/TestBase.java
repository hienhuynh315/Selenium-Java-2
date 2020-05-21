package dashboard.common;

import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.drivers.DriverCreationException;
import com.drivers.DriverProperty;
import com.drivers.DriverUtils;

import utils.common.Constants;
import utils.helper.BrowserSettingHelper;
import utils.helper.PropertiesHelper;

@Guice(modules = ModuleFactory.class)
public class TestBase {

	@Parameters({ "browser", "url" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(String browser, Method method, @Optional String url) throws Throwable {
		DriverUtils.setTimeOut(Integer.parseInt(PropertiesHelper.getPropValue("driver.timeout")));
		DriverUtils.setShortTimeOut(Integer.parseInt(PropertiesHelper.getPropValue("driver.shortTimeout")));
		DriverProperty property = BrowserSettingHelper.getDriverProperty(Constants.BROWSER_SETTING_FILE, browser);
		setProperty(property);
		setURL(url);
		openBrowser();
	}

	public void openBrowser() throws DriverCreationException {
		DriverUtils.getDriver(getProperty());
		DriverUtils.setURL(getURL());
		DriverUtils.maximizeBrowser();
		DriverUtils.setBrowserSize(Constants.BROWSER_SIZE_WIDTH, Constants.BROWSER_SIZE_HEIGHT);
	}

	@AfterClass(alwaysRun = true)
	public void afterMethod() {
		DriverUtils.quitBrowser();
	}

	public DriverProperty getProperty() {
		return property;
	}

	public void setProperty(DriverProperty property) {
		this.property = property;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}

	private String url;

	private DriverProperty property;
}
