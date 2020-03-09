package com.logigear.control.common;

import java.util.List;

import com.logigear.control.base.IClickable;

public interface IComboBox extends IClickable {
	void select(String text);

	void select(int index);

	String getSelected();

	List<String> getOptions();

	void waitForSelectedOptionToBePresent(String option, int timeOutInSecond);

	void selectStartsWithTextItem(String text);
}
