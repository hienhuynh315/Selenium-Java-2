package com.logigear.trainning.control;

public interface ITextBox extends IElement {

	void setValue(String value);

	void enter(CharSequence... value);

	void clear();

}
