package utils.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;

public class PropertiesHelper {
	/*
	 * Properties mode
	 */
	private static Properties config, profile, defaultProp, props;

	private static Properties initPropsForName(String propFileName) {
		InputStream inputStream = null;
		try {
			System.out.println("Loading properties : " + propFileName);
			inputStream = PropertiesHelper.class.getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				Properties prop = new Properties();
				prop.load(inputStream);
				return prop;
			} else {
				System.err.println(propFileName + " not found !");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
				}
			}
		}
		return null;
	}

	private static void initProps() {
		if (config == null) {
			config = initPropsForName("conf.properties");
			String profileFile = System.getProperty("profile");
			if (profileFile != null) {
				profile = initPropsForName(
						"profiles/link.properties");
			} else {
				profile = initPropsForName("profiles/link.properties");
			}

			String propsFile = System.getProperty("properties");
			if (props != null) {
				props = new Properties();
				try {
					props.load(new StringReader(propsFile));
				} catch (IOException e) {
				}
			}
		}
	}

	public static String getPropValue(String key) {
		return getPropValue(key, null);
	}

	public static String getPropValue(String key, String defaultValue) {
		initProps();

		if (System.getProperty(key) != null) {
			return System.getProperty(key);
		}
		if (props != null && props.containsKey(key)) {
			return props.getProperty(key);
		}
		if (profile != null && profile.containsKey(key)) {
			return profile.getProperty(key);
		}

		if (defaultProp != null && defaultProp.containsKey(key)) {
			return defaultProp.getProperty(key);
		}

		if (config.containsKey(key)) {
			return config.getProperty(key);
		}
		return defaultValue;
	}

}
