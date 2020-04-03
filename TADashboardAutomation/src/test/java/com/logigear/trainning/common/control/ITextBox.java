package com.logigear.trainning.common.control;

public interface ITextBox extends IElement {

	void setValue(String value);

	void enter(CharSequence... value);

	void clear();

}
