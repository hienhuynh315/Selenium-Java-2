package com.logigear.browser.chrome;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logigear.driver.DriverUtils;
import com.logigear.driver.manager.DriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalChromeDriver extends DriverManager {

	@Override
	public void createWebDriver() {

		if (getVersion() == null) {
			WebDriverManager.chromedriver().setup();
		} else {
			WebDriverManager.chromedriver().version(getVersion()).setup();
		}

		ChromeOptions ops = new ChromeOptions();
		if (DriverUtils.isBrowserLog()) {
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
			ops.setCapability("goog:loggingPrefs", logPrefs);
			ops.setCapability("enableNetwork", true);
		}
		ops.merge(getCapabilities());
		ops.addArguments(getArguments());
		String downloadFilepath = System.getProperty("user.dir")
				+ "\\downloads";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		chromePrefs.put("safebrowsing.enabled", "true");
		ops.setExperimentalOption("prefs", chromePrefs);

		if (getArguments().size() != 0
				&& getArguments().get(0).equals("--headless")) {
			ChromeDriverService driverService = ChromeDriverService
					.createDefaultService();
			ChromeDriver driver = new ChromeDriver(driverService, ops);
			HashMap<String, Object> commandParams = new HashMap<>();
			commandParams.put("cmd", "Page.setDownloadBehavior");
			HashMap<String, String> params = new HashMap<>();
			params.put("behavior", "allow");
			params.put("downloadPath", downloadFilepath);
			commandParams.put("params", params);
			ObjectMapper objectMapper = new ObjectMapper();
			HttpClient httpClient = HttpClientBuilder.create().build();
			String command = null;
			String u = driverService.getUrl().toString() + "/session/"
					+ driver.getSessionId() + "/chromium/send_command";
			HttpPost request = new HttpPost(u);
			request.addHeader("content-type", "application/json");
			try {
				command = objectMapper.writeValueAsString(commandParams);
				request.setEntity(new StringEntity(command));
				httpClient.execute(request);
			} catch (IOException e) {
				e.printStackTrace();
			}

			this.webDriver = driver;
		} else {
			this.webDriver = new ChromeDriver(ops);
		}
	}
}
