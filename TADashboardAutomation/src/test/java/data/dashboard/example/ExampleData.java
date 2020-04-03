package data.dashboard.example;

import java.util.List;

import data.dashboard.base.DataBase;

public class ExampleData extends DataBase {

	public ExampleData() {
		super("");
	}

	
public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public List<String> getUwAuthority() {
		return uwAuthority;
	}
	public void setUwAuthority(List<String> uwAuthority) {
		this.uwAuthority = uwAuthority;
	}


	// define some thing here
	private String field;
	private List<String> uwAuthority;
}
