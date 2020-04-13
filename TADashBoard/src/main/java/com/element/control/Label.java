package com.element.control;

import org.openqa.selenium.By;

public class Label extends Element implements ILabel {

	public Label(String locator) {
		super(locator);
	}

	public Label(By locator) {
		super(locator);
	}

	public Label(String locator, Object... value) {
		super(locator, value);
	}

}
