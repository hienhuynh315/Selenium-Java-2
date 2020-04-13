package com.element.control;

import org.openqa.selenium.By;

public class Button extends Element implements IButton {

	public Button(String locator) {
		super(locator);
	}

	public Button(By locator) {
		super(locator);
	}

	public Button(String locator, Object... value) {
		super(locator, value);
	}

}
