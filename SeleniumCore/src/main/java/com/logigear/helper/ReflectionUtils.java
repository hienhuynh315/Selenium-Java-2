package com.logigear.helper;

import java.lang.reflect.Method;

import com.logigear.common.Constants;
import com.logigear.driver.DriverProperty;
import com.logigear.driver.RunningMode;

public class ReflectionUtils {

	public static Object initWebDriver(DriverProperty property) {

		RunningMode mode = property.isRemoteMode() ? RunningMode.Remote
				: RunningMode.Local;
		String packageName = String.format(Constants.BROWSER_PACKAGE_NAME,
				property.getDriverType().toString().toLowerCase());
		String className = String.format(Constants.BROWSER_CLASS_NAME, mode,
				property.getDriverType().toString());

		try {
			Method method;
			String fullClassName = packageName + "." + className;
			Class<?> clzz = Class.forName(fullClassName);
			Object obj = clzz.newInstance();

			if (property.isRemoteMode()) {
				// Set Remote Url
				Method setRemoteUrlMethod = clzz.getSuperclass()
						.getDeclaredMethod("setRemoteUrl",
								new Class[] { String.class });
				setRemoteUrlMethod.invoke(obj, property.getRemoteUrl());
			}

			// Set Capability
			Method setCapabilitiesMethod = clzz.getSuperclass()
					.getDeclaredMethod("setCapabilities",
							new Class[] { String.class });
			setCapabilitiesMethod.invoke(obj, property.getCapabilities());

			// Set Arguments
			Method setArguments = clzz.getSuperclass().getDeclaredMethod(
					"setArguments", new Class[] { String.class });
			setArguments.invoke(obj, property.getArguments());

			// Set Browser Name
			Method setBrowserName = clzz.getSuperclass().getDeclaredMethod(
					"setBrowserName", new Class[] { String.class });
			setBrowserName.invoke(obj, property.getDriverType().toString());

			// Set Version
			if (property.getVersion() != null) {
				Method setVersion = clzz.getSuperclass().getDeclaredMethod(
						"setVersion", new Class[] { String.class });
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
