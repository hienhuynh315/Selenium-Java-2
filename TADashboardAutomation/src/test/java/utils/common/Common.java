package utils.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

public class Common {

	public static String convertImageToURI(String imagePath) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		BufferedImage img;
		File image = new File(imagePath);
		try {
			img = ImageIO.read(image);
			ByteArrayOutputStream convert = new ByteArrayOutputStream();
			ImageIO.write(img, "png", convert);
			String data = DatatypeConverter.printBase64Binary(convert.toByteArray());
			return data;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public static String screenshotURI(String imagePath) {
		String randomPopUpId = "id" + UUID.randomUUID().toString();
		String randomButtonId = randomPopUpId + "button";
		String imageString = "data:image/png;base64," + convertImageToURI(imagePath);
		String htmlScript = "<script>$(document).ready(function(){$( \"#" + randomPopUpId
				+ "\" ).dialog({ autoOpen: false });$( \"#" + randomPopUpId
				+ "\" ).dialog({width:1000},{height:700});$( \"#" + randomButtonId + "\" ).click(function() {$( \"#"
				+ randomPopUpId + "\" ).dialog( \"open\" );});});</script></br><img id=\"" + randomButtonId
				+ "\" src=\"" + imageString
				+ "\" style=\"border: 4px solid #f6f7fa;width: 150px;cursor: zoom-in;display: block;margin-top: 15px;\"/></br><div style=\"width: 50%; margin: 0 auto;\" id=\""
				+ randomPopUpId + "\" > <a href=\"#" + randomPopUpId
				+ "\"  class=\"ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right\"></a><img style=\"width:1000px;height:800px;\"  src=\""
				+ imageString + "\"/></div>";
		return htmlScript;
	}

	public static boolean doesFileExist(String filePath, String fileName) {
		File f = new File(filePath + fileName);
		if (f.exists() && !f.isDirectory()) {
			return true;
		}
		return false;
	}

	public static void deleteFile(String filePath, String fileName) {
		File f = new File(filePath + fileName);
		if (f.exists() && !f.isDirectory()) {
			f.delete();
		}
	}

	public static void cleanDownloadDirectory(String directory) {
		File file = new File(directory);
		if (file.exists()) {
			File[] files = file.listFiles();
			for (File f : files) {
				if (!f.getName().endsWith(".md")) {
					f.delete();
				}
			}
		}
	}

	public static String getRandomString(String prefix) {
		return prefix + getRandomNumber(1, 99999);
	}

	public static int getRandomNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

	public static String getRandomCharacter() {
		return UUID.randomUUID().toString().split("-")[0];
	}

}
