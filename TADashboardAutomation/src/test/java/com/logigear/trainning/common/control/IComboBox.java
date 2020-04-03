package com.logigear.trainning.common.control;

public interface IComboBox extends IElement {
	void select(String text);

	void select(int index);

	String getSelected();
}
