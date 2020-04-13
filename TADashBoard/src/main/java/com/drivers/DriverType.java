package com.drivers;

import java.util.Arrays;

public enum DriverType {
	Chrome, Firefox, IE, Edge, Safari, PhantomJS;

	public static String asString() {
		return Arrays.toString(DriverType.values()).replaceAll("^.|.$", "");
	}
}
