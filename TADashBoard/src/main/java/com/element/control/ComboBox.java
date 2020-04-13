package com.element.control;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class ComboBox extends Element implements IComboBox {

	public ComboBox(By locator) {
		super(locator);
	}

	public ComboBox(String locator) {
		super(locator);
	}

	public ComboBox(String locator, Object... value) {
		super(locator, value);
	}

	@Override
	public void select(String text) {
		Select select = new Select(getElement());
		select.selectByVisibleText(text);

	}

	@Override
	public void select(int index) {
		Select select = new Select(getElement());
		select.selectByIndex(index);

	}

	@Override
	public String getSelected() {
		Select select = new Select(getElement());
		return select.getFirstSelectedOption().getText();
	}

}
