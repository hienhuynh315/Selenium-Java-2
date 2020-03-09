package utils.helper;

import java.io.PrintWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TestRunGetting {

	public static void main(String[] args) throws Exception {
		JSONArray caseData = null;
		JSONObject obj = null;
		String classString = "";
		for (int i = 0; i < caseData.size(); i++) {
			obj = (JSONObject) caseData.get(i);
			if (obj.get("custom_auto_method") != null) {
				if (args[1].equals("true")) {
					if (obj.get("status_id").toString().equals("5")) {
						if (classString.equals("")) {
							classString = classString
									+ obj.get("custom_auto_method");
						} else {
							classString = classString + ","
									+ obj.get("custom_auto_method");
						}
					}
				} else {
					if (classString.equals("")) {
						classString = classString
								+ obj.get("custom_auto_method");
					} else {
						classString = classString + ","
								+ obj.get("custom_auto_method");
					}
				}
			}
		}
		PrintWriter out = new PrintWriter(
				System.getProperty("user.dir") + "/TestRun.txt");
		out.println(classString);
		out.close();
		System.out.println("Get list of test cases successfully!!!");
	}
}
