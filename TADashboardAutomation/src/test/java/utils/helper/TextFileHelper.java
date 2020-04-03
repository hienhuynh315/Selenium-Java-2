package utils.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFileHelper {
	public static List<String> readTextFileToList(String filePath) {
		Scanner input;
		List<String> list = new ArrayList<String>();
		File file = new File(filePath);
		try {
			input = new Scanner(file);

			while (input.hasNextLine()) {
				list.add(input.nextLine());
			}
			input.close();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			return list;
		}
	}
}
