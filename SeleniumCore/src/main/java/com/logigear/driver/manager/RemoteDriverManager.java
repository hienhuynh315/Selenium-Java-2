package com.logigear.driver.manager;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class RemoteDriverManager extends DriverManager {

	private String remoteUrl;

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
}
