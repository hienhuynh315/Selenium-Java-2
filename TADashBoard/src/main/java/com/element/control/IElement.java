package com.element.control;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface IElement {

	WebElement getElement();

	boolean isDisplayed();

	boolean isDisplayed(int timeOutInSeconds);

	void waitForDisplay(int timeOutInSeconds);

	boolean isEnabled();

	void waitForElementEnabled();

	void waitForElementEnabled(int timeOutInSeconds);

	List<WebElement> getElements();

	String getText();

	void moveTo();

	String getValue();

	void waitForDisplay();

	String getLocatorAsString();

	By getLocator();

	void scrollToView();

	void setDynamicValue(Object... args);

	void click();

	String getAttribute(String attributeName);

}
