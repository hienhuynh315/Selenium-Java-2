package utils.helper;

import java.lang.reflect.Method;

import com.drivers.DriverProperty;
import com.drivers.RunningMode;

public class ReflectionUtils {

	public static Object initWebDriver(DriverProperty property) {

		RunningMode mode = property.isRemoteMode() ? RunningMode.Remote : RunningMode.Local;
		String packageName = String.format("com.browser.%s", property.getDriverType().toString().toLowerCase());
		String className = String.format("%s%sDriver", mode, property.getDriverType().toString());
		try {
			Method method;
			String fullClassName = packageName + "." + className;
			Class<?> clzz = Class.forName(fullClassName);
			Object obj = clzz.newInstance();

			if (property.isRemoteMode()) {
				// Set Remote Url
				Method setRemoteUrlMethod = clzz.getSuperclass().getDeclaredMethod("setRemoteUrl",
						new Class[] { String.class });
				setRemoteUrlMethod.invoke(obj, property.getRemoteUrl());
			}

			// Set Capability
			Method setCapabilitiesMethod = clzz.getSuperclass().getDeclaredMethod("setCapabilities",
					new Class[] { String.class });
			setCapabilitiesMethod.invoke(obj, property.getCapabilities());

			// Set Version to handle issue launcher webdriver
			if (property.getVersion() != null) {
				Method setVersion = clzz.getSuperclass().getDeclaredMethod("setVersion", new Class[] { String.class });
				setVersion.invoke(obj, property.getVersion().toString());
			}

			// Create Web Driver
			method = clzz.getDeclaredMethod("createWebDriver");
			method.invoke(obj);
			return obj;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
