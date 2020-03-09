package com.logigear.control.common.imp;

import org.openqa.selenium.By;

import com.logigear.control.base.imp.Element;
import com.logigear.control.common.IFrame;

public class Frame extends Element implements IFrame {

	public Frame(By locator) {
		super(locator);
	}

	public Frame(String locator) {
		super(locator);
	}

	public Frame(String locator, Object... value) {
		super(locator, value);
	}

	@Override
	public void switchTo() {
		getDriver().switchTo().frame(getElement());
	}

	@Override
	public void switchToMainDocument() {
		getDriver().switchTo().defaultContent();
	}

}
