package com.drivers;

public class DriverProperty {

	private String remoteUrl;
	private boolean isRemoteMode = false;
	private DriverType driverType;
	private RunningMode runningMode;
	private String capabilities;
	private String arguments;
	private String version;

	public String getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(String capabilities) {
		this.capabilities = capabilities;
	}

	public RunningMode getRunningMode() {
		return runningMode;
	}

	public void setRunningMode(RunningMode runningMode) {
		this.runningMode = runningMode;
	}

	public boolean isRemoteMode() {
		if (getRunningMode() != null && getRunningMode() == RunningMode.Remote) {
			isRemoteMode = true;
		}
		return isRemoteMode;
	}

	public String getRemoteUrl() {
		return remoteUrl;
	}

	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}

	public DriverType getDriverType() {
		return driverType;
	}

	public void setDriverType(DriverType driverType) {
		this.driverType = driverType;
	}

	/**
	 * @return the arguments
	 */
	public String getArguments() {
		return arguments;
	}

	/**
	 * @param arguments the arguments to set
	 */
	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
}
