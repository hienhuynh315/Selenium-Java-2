package com.logigear.trainning.control;

public interface IComboBox extends IElement {
	void select(String text);

	void select(int index);

	String getSelected();
}
