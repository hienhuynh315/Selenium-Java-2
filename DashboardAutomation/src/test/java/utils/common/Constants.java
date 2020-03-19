package utils.common;

import utils.helper.PropertiesHelper;

public class Constants {

	// Common Constants
	public static final String BROWSER_SETTING_FILE = "src/test/resources/browsers.setting.properties";
	public static final int TIME_OUT_IN_SECOND = Integer.parseInt(PropertiesHelper.getPropValue("driver.timeout"));
	public static final int SHORT_TIME_OUT_IN_SECOND = Integer
			.parseInt(PropertiesHelper.getPropValue("driver.shortTimeout"));
	public static final String DATE_FORMAT = PropertiesHelper.getPropValue("date.format");
	public static final String DATE_FORMAT_SHORT = PropertiesHelper.getPropValue("date.format.short");
	public static final Integer BROWSER_SIZE_WIDTH = Integer
			.parseInt(PropertiesHelper.getPropValue("browser.size.width"));
	public static final Integer BROWSER_SIZE_HEIGHT = Integer
			.parseInt(PropertiesHelper.getPropValue("browser.size.height"));
	public static final String USER_HOME_FOLDER = System.getProperty("user.home");
	public static final String USER_DOWNLOAD_FOLDER = USER_HOME_FOLDER + "/Downloads/";
	public static final String DOWNLOAD_FOLDER = System.getProperty("user.dir") + "/downloads/";

	// JSON Constants
	public static final String DATA_PATH = "src/test/resources/data/jsondata/";

	// Dashboard Constants
	public static final String DASHBOARD_URL = PropertiesHelper.getPropValue("profile.dashboard.url");
	public static final String USERNAME_ADMIN = PropertiesHelper.getPropValue("profile.username.admin");
	public static final String REPOSITORY_SAMPLE = PropertiesHelper.getPropValue("profile.repo.sample");
	public static final String REPOSITORY_LV2 = PropertiesHelper.getPropValue("profile.repo.sampleLV2");
	public static final String PASSWORD_ADMIN = PropertiesHelper.getPropValue("profile.password.admin");
	public static final String USERNAME_TEST = PropertiesHelper.getPropValue("profile.username.test");
	public static final String PASSWORD_TEST = PropertiesHelper.getPropValue("profile.password.test");

}
