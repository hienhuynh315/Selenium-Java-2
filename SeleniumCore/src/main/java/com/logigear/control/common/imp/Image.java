package com.logigear.control.common.imp;

import org.openqa.selenium.By;

import com.logigear.control.base.imp.Clickable;
import com.logigear.control.common.IImage;

public class Image extends Clickable implements IImage {

	public Image(By locator) {
		super(locator);
	}

	public Image(String locator) {
		super(locator);
	}

	public Image(String locator, Object... value) {
		super(locator, value);
	}

	@Override
	public String getSource() {
		return getElement().getAttribute("src");
	}

	@Override
	public String getAlt() {
		return getElement().getAttribute("alt");
	}

}
