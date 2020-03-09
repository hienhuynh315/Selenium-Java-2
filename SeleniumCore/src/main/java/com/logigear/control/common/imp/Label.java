package com.logigear.control.common.imp;

import org.openqa.selenium.By;

import com.logigear.control.base.imp.Editable;
import com.logigear.control.common.ILabel;

public class Label extends Editable implements ILabel {

	public Label(By locator) {
		super(locator);
	}

	public Label(String locator) {
		super(locator);
	}

	public Label(String locator, Object... value) {
		super(locator, value);
	}

}
