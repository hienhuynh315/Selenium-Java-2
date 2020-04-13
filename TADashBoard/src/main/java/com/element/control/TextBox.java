package com.element.control;

import org.openqa.selenium.By;

public class TextBox extends Element implements ITextBox {

	public TextBox(String locator) {
		super(locator);
	}

	public TextBox(By locator) {
		super(locator);
	}

	public TextBox(String locator, Object... value) {
		super(locator, value);
	}

	@Override
	public void enter(CharSequence... value) {
		try {
			getElement().sendKeys(value);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void setValue(String value) {
		try {
			String js = String.format("arguments[0].value='%s';", value);
			jsExecutor().executeScript(js, getElement());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void clear() {
		try {
			getElement().clear();
		} catch (Exception e) {
			throw e;
		}

	}

}
