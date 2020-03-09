package com.logigear.control.common;

import com.logigear.control.base.IEditable;

public interface ICheckBox extends IEditable {

	void check();

	void uncheck();

	void set(boolean value);

	void setAll(boolean value);

	boolean isChecked();

	void waitForCheckBoxIsChecked();
}
