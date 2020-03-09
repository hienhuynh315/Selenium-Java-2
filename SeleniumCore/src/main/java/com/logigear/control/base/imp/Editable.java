package com.logigear.control.base.imp;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.logigear.control.base.IEditable;
import com.logigear.driver.DriverUtils;

public class Editable extends Clickable implements IEditable {

	private Logger logger = Logger.getLogger(Editable.class);

	public Editable(String locator) {
		super(locator);
	}

	public Editable(By locator) {
		super(locator);
	}

	public Editable(String locator, Object... value) {
		super(locator, value);
	}

	@Override
	public void setText(String value) {

	}

	@Override
	public void enter(CharSequence... value) {
		try {
			logger.debug(String.format("Enter '%s' for %s", value, getLocator().toString()));
			getElement().sendKeys(value);
		} catch (Exception e) {
			logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}

	}

	@Override
	public void tab() {
		try {
			logger.debug(String.format("Enter TAB for %s", getLocator().toString()));
			getElement().sendKeys(Keys.TAB);
			if (DriverUtils.isWaitForAjax()) {
				DriverUtils.waitForAjaxJQueryProcess();
			}
		} catch (Exception e) {
			logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}

	}

	@Override
	public void space() {
		try {
			logger.debug(String.format("Enter SPACE for %s", getLocator().toString()));
			getElement().sendKeys(Keys.SPACE);
			if (DriverUtils.isWaitForAjax()) {
				DriverUtils.waitForAjaxJQueryProcess();
			}
		} catch (Exception e) {
			logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}

	}

	@Override
	public void setValue(String value) {
		try {
			String js = String.format("arguments[0].value='%s';", value);
			logger.debug(String.format("Set value '%s' for %s", value, getLocator().toString()));
			jsExecutor().executeScript(js, getElement());
		} catch (Exception e) {
			logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}
	}

	@Override
	public void setDisplayStyle(String value) {
		try {
			String js = String.format("arguments[0].style.display='%s';", value);
			logger.debug(String.format("Set display style value '%s' for %s", value, getLocator().toString()));
			jsExecutor().executeScript(js, getElement());
		} catch (Exception e) {
			logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}
	}

	@Override
	public void clear() {
		try {
			logger.debug(String.format("Clean text for %s", getLocator().toString()));
			getElement().clear();
		} catch (Exception e) {
			logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}

	}

	@Override
	public boolean isContentEditable() {
		try {
			String js = "return arguments[0].isContentEditable;";
			return (boolean) jsExecutor().executeScript(js, getElement());
		} catch (Exception e) {
			logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}
	}

	@Override
	public boolean isContentReadOnly() {
		try {
			String js = "return arguments[0].readOnly;";
			return (boolean) jsExecutor().executeScript(js, getElement());
		} catch (Exception e) {
			logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}
	}
}
