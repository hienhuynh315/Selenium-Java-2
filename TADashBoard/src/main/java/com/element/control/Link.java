package com.element.control;

import org.openqa.selenium.By;

public class Link extends Element implements ILink {

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
