package utils.helper;

import java.io.FileReader;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;

import com.drivers.DriverProperty;
import com.drivers.DriverType;
import com.drivers.RunningMode;


public class BrowserSettingHelper {
	public static DriverProperty getDriverProperty(String file, String sectionName) throws Exception {
		DriverProperty property = new DriverProperty();

		Ini ini = new Ini(new FileReader(file));
		Section section = ini.get(sectionName);
		if (section == null) {
			throw new Exception(String.format("Cannot find '%s' in file '%s'", sectionName, file));
		}
		String runningMode = section.get("mode");
		String driverType = section.get("driver");
		String remoteUrl = section.get("remoteUrl");
		String capabilities = section.get("capabilities");
		String args = section.get("arguments");
		String version = section.get("version");
		property.setDriverType(converStringToDriverType(driverType));
		property.setRemoteUrl(remoteUrl);
		property.setRunningMode(converStringToRunningMode(runningMode));
		property.setCapabilities(capabilities);
		property.setArguments(args);
		property.setVersion(version);

		return property;
	}

	private static RunningMode converStringToRunningMode(String mode) throws Exception {
		try {
			return RunningMode.valueOf(mode);
		} catch (Exception e) {
			throw new Exception(String.format("Don't allow the '%s'. Please use %s for your configuration", mode,
					RunningMode.asString()));
		}
	}

	private static DriverType converStringToDriverType(String type) throws Exception {
		try {
			return DriverType.valueOf(type);
		} catch (Exception e) {
			throw new Exception(String.format("Don't allow the '%s'. Please use %s for your configuration", type,
					DriverType.asString()));
		}
	}

}
