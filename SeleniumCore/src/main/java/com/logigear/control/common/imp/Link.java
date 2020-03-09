package com.logigear.control.common.imp;

import org.openqa.selenium.By;

import com.logigear.control.base.imp.Editable;
import com.logigear.control.common.ILink;

public class Link extends Editable implements ILink {

	public Link(String locator) {
		super(locator);
	}

	public Link(By locator) {
		super(locator);
	}

	public Link(String locator, Object... value) {
		super(locator, value);
	}

	public String getReference() {
		return getAttribute("href");
	}

}
