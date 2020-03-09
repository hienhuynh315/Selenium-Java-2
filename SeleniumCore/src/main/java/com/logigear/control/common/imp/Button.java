package com.logigear.control.common.imp;

import org.openqa.selenium.By;

import com.logigear.control.base.imp.Clickable;
import com.logigear.control.common.IButton;

public class Button extends Clickable implements IButton {

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
