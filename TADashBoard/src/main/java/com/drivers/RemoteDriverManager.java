package com.drivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import utils.helper.JsonHelper;

public abstract class RemoteDriverManager extends DriverManager {

	private String remoteUrl;
	private String capabilities;
	private String version;

	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}

	protected URL getRemoteUrl() {
		try {
			return new URL(this.remoteUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected DesiredCapabilities getCapabilities() {
		return JsonHelper.convertJsonToCapabilities(this.capabilities);
	}

	// set capabilities
	public void setCapabilities(String caps) {
		this.capabilities = caps;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
