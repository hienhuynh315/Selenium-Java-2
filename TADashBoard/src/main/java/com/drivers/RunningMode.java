package com.drivers;

import java.util.Arrays;

public enum RunningMode {
	Local, Remote;

	public static String asString() {
		return Arrays.toString(RunningMode.values()).replaceAll("^.|.$", "");
	}
}
