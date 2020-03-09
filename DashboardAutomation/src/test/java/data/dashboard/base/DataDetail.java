package data.dashboard.base;

import data.dashboard.base.DataBase.FieldType;

public class DataDetail {

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public FieldType getType() {
		return type;
	}

	public void setType(FieldType type) {
		this.type = type;
	}

	public String getVerticalSection() {
		return verticalSection;
	}

	public void setVerticalSection(String verticalSection) {
		this.verticalSection = verticalSection;
	}

	private String verticalSection;
	private String field;
	private String value;
	private FieldType type;

}
