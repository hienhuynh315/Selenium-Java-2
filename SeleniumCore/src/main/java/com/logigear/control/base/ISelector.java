package com.logigear.control.base;

import java.util.List;

public interface ISelector {

	void select(String name);

	void select(int index);

	String isSelected();

	boolean waitSelected(String name);

	List<String> getOptions();

	String getOptionsAsText();
}
