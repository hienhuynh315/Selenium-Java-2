package com.logigear.control.base;

public interface IEditable extends IClickable {

	void setText(String value);

	void setValue(String value);

	void setDisplayStyle(String value);

	void enter(CharSequence... value);

	void tab();

	void clear();

	boolean isContentEditable();

	boolean isContentReadOnly();

	void space();
}
