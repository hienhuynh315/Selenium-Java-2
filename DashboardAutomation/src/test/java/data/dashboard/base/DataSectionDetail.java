package data.dashboard.base;

import data.dashboard.base.DataBase.FieldType;

public class DataSectionDetail {

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

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

	private String section;
	private String field;
	private String value;
	private FieldType type;

}
