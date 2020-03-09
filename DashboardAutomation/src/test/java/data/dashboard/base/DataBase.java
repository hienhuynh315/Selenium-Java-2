package data.dashboard.base;

import com.logigear.helper.JsonHelper;

public class DataBase {

	private String jsonFile = "";

	public String getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(String jsonFile) {
		this.jsonFile = jsonFile;
	}

	public DataBase(String jsonFile) {
		this.jsonFile = jsonFile;
	}

	public <T> T getData() {
		return JsonHelper.getData(jsonFile, this.getClass());
	}

	public enum FieldType {
		EDITABLE, SELECTABLE, RADIOBUTTON, TEXTAREA, DATEFIELD, BUTTON, DROPDOWNBUTTON, NA
	}
}
