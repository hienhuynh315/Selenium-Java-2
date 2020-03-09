package com.logigear.control.common.imp;

import org.openqa.selenium.By;

import com.logigear.control.base.imp.Editable;
import com.logigear.control.common.ITextBox;

public class TextBox extends Editable implements ITextBox {

	public TextBox(String locator) {
		super(locator);
	}

	public TextBox(By locator) {
		super(locator);
	}

	public TextBox(String locator, Object... value) {
		super(locator, value);
	}
}
