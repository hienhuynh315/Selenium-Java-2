package utils.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import com.google.common.io.Files;
import com.logigear.driver.DriverUtils;

import utils.helper.CSVUtils;
import utils.helper.Logger;

public class Common {

	public static String convertImageToURI(String imagePath) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		BufferedImage img;
		File image = new File(imagePath);
		try {
			img = ImageIO.read(image);
			ByteArrayOutputStream convert = new ByteArrayOutputStream();
			ImageIO.write(img, "png", convert);
			String data = DatatypeConverter
					.printBase64Binary(convert.toByteArray());
			return data;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public static String screenshotURI(String imagePath) {
		String randomPopUpId = "id" + UUID.randomUUID().toString();
		String randomButtonId = randomPopUpId + "button";
		String imageString = "data:image/png;base64,"
				+ convertImageToURI(imagePath);
		String htmlScript = "<script>$(document).ready(function(){$( \"#"
				+ randomPopUpId + "\" ).dialog({ autoOpen: false });$( \"#"
				+ randomPopUpId
				+ "\" ).dialog({width:1000},{height:700});$( \"#"
				+ randomButtonId + "\" ).click(function() {$( \"#"
				+ randomPopUpId
				+ "\" ).dialog( \"open\" );});});</script></br><img id=\""
				+ randomButtonId + "\" src=\"" + imageString
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

	public static void waitForDirectoryIncludeFileExtension(String directory,
			String extension) {
		File[] files = new File(directory).listFiles();
		int delayTime = 1;
		while (delayTime < 30) {
			if (files.length > 0) {
				for (File f : files) {
					if (f.getName().endsWith(extension))
						return;
				}
			}
			DriverUtils.delay(1);
			delayTime += 1;
			files = new File(directory).listFiles();
		}
	}

	public static Boolean doesFileExtensionInDirectory(String directory,
			String extension) {
		File[] files = new File(directory).listFiles();
		for (File f : files) {
			if (f.getName().endsWith(extension))
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

	public static String generateCSVFileName(String className) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return className + "_" + dateFormat.format(date).toString() + ".csv";
	}

	public static void waitForFileExist(String filePath, String fileName,
			int timeOut) {
		int count = 1;
		while (!doesFileExist(filePath, fileName) && count < timeOut) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
		}
	}

	public static String getDownloadFolderPath() {
		return System.getProperty("java.io.tmpdir");
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

	public static String getNextBussinessDate() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		cal.add(Calendar.DATE, +1);
		while (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
				|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			cal.add(Calendar.DATE, +1);
		}
		return dateFormat.format(cal.getTime());
	}

	public static String getSSN() {
		String SSN = "%s-%s-%s";
		SSN = String.format(SSN, getRandomNumber(110, 999),
				getRandomNumber(11, 99), getRandomNumber(1000, 9999));
		return SSN;
	}

	public static String getFEIN() {
		String fein = "%s-%s";
		fein = String.format(fein, getRandomNumber(11, 99),
				getRandomNumber(1111110, 9999999));
		return fein;
	}

	public static int getBusinessDaysInRage(Date start, Date end) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(start);
		int w1 = c1.get(Calendar.DAY_OF_WEEK);
		c1.add(Calendar.DAY_OF_WEEK, -w1);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(end);
		int w2 = c2.get(Calendar.DAY_OF_WEEK);
		c2.add(Calendar.DAY_OF_WEEK, -w2);

		long days = (c2.getTimeInMillis() - c1.getTimeInMillis())
				/ (1000 * 60 * 60 * 24);
		long daysWithoutWeekendDays = days - (days * 2 / 7);

		if (w1 == Calendar.SUNDAY && w2 != Calendar.SATURDAY) {
			w1 = Calendar.MONDAY;
		} else if (w1 == Calendar.SATURDAY && w2 != Calendar.SUNDAY) {
			w1 = Calendar.FRIDAY;
		}

		if (w2 == Calendar.SUNDAY) {
			w2 = Calendar.MONDAY;
		} else if (w2 == Calendar.SATURDAY) {
			w2 = Calendar.FRIDAY;
		}

		return (int) daysWithoutWeekendDays - w1 + w2;
	}

	public static String getDayFromToday(int days) {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		cal.add(Calendar.DATE, days);
		return dateFormat.format(cal.getTime());
	}

	public static String getDayFromTodayByYear(int days, int years) {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		cal.add(Calendar.DATE, days);
		cal.add(Calendar.YEAR, years);
		return dateFormat.format(cal.getTime());
	}

	public static String getDayFromTodayTimeZoneByYear(int years,
			String timeZone) {
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
		Calendar c = Calendar.getInstance();
		df.setTimeZone(TimeZone.getTimeZone(timeZone));
		df.format(c.getTime());
		c.add(Calendar.YEAR, years);
		return df.format(c.getTime());
	}

	public static Date convertStringToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
		Date convertedDate = null;
		try {
			convertedDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertedDate;
	}

	public static int getWeekdaysInRage(Date start, Date end) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);

		int count = 0;

		while (endCal.after(startCal)) {
			if (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
					|| startCal
							.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				count++;
			}
			startCal.add(Calendar.DATE, 1);
		}

		return count;
	}

	public static String getBusinessDay(int days) {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);

		for (int i = 1; i <= days; i++) {
			cal.add(Calendar.DATE, 1);
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
					|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				cal.add(Calendar.DATE, 1);
				i--;
			}
		}
		return dateFormat.format(cal.getTime());
	}

	public static String getDateFromDate(String date, int numDays) {
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(df.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, numDays);
		return df.format(c.getTime());
	}

	public static String getDateByTimeZone(String timeZone) {
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
		Calendar c = Calendar.getInstance();
		df.setTimeZone(TimeZone.getTimeZone(timeZone));
		return df.format(c.getTime());
	}

	public static String getDateByTimeZoneWithFormat(String timeZone,
			String format) {
		DateFormat df = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		df.setTimeZone(TimeZone.getTimeZone(timeZone));
		return df.format(c.getTime());
	}

	public static String getDateFromDateWithFormat(String date, int numDays,
			String dateFormat) {
		DateFormat df = new SimpleDateFormat(dateFormat);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(df.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, numDays);
		return df.format(c.getTime());
	}

	public static String getDateFromDateWithFormat(String date, int number,
			String fieldType, String dateFormat) {
		DateFormat df = new SimpleDateFormat(dateFormat);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(df.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		switch (fieldType) {
		case "YEAR":
			c.add(Calendar.YEAR, number);
			break;
		case "MONTH":
			c.add(Calendar.MONTH, number);
			break;
		case "DATE":
			c.add(Calendar.DATE, number);
			break;
		default:
			c.add(Calendar.DATE, number);
			break;
		}
		return df.format(c.getTime());
	}

	public static String getDateFromDate(String date, int number,
			String fieldType) {
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(df.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		switch (fieldType) {
		case "YEAR":
			c.add(Calendar.YEAR, number);
			break;
		case "MONTH":
			c.add(Calendar.MONTH, number);
			break;
		case "DATE":
			c.add(Calendar.DATE, number);
			break;
		default:
			c.add(Calendar.DATE, number);
			break;
		}
		return df.format(c.getTime());
	}

	public static String getTodayWithFormat(String dateFormat) {
		DateFormat df = new SimpleDateFormat(dateFormat);
		Calendar c = Calendar.getInstance();
		return df.format(c.getTime());
	}

	public static int getCurrentYear() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR);
	}

	public static String convertDateFormat(String oldDateFormat, String date,
			String newDateFormat) {
		DateFormat df = new SimpleDateFormat(oldDateFormat);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(df.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DateFormat ndf = new SimpleDateFormat(newDateFormat);
		return ndf.format(c.getTime());
	}

	public static long convertMoneyStringToNumber(String moneyString) {
		try {
			NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
			long myNumber = nf.parse(moneyString.replace("$", "")).longValue();
			return myNumber;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void copyFile(String from, String to) {
		try {
			Files.copy(new File(from).getAbsoluteFile(),
					new File(to).getAbsoluteFile());
		} catch (Exception ex) {
			Logger.error(ex.getMessage());
		}
	}

	public static boolean isDateValid(String dateToValidate) {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(dateToValidate);
			System.out.println(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static String convertNumberFormat(String format, long number) {
		DecimalFormat myFormatter = new DecimalFormat(format);
		return myFormatter.format(number);
	}

	public static String getRandomEmail() {
		return "Test" + Common.getTodayWithFormat("yyMMddhhmmssSSS")
				+ "@aol.com";
	}

	public static String convertRGBToColorCode(String rgbString) {
		String[] hexValue = rgbString.replace("rgba(", "").replace(")", "")
				.split(",");

		int hexValue1 = Integer.parseInt(hexValue[0]);
		hexValue[1] = hexValue[1].trim();
		int hexValue2 = Integer.parseInt(hexValue[1]);
		hexValue[2] = hexValue[2].trim();
		int hexValue3 = Integer.parseInt(hexValue[2]);

		return String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
	}

	public static String convertDateTimeByTimeZoneWithFormat(String oldFormat,
			String date, String timeZone, String newFormat) {
		DateFormat oldDateF = new SimpleDateFormat(oldFormat);
		Date oldDate = null;
		try {
			oldDate = oldDateF.parse(date);
		} catch (ParseException e) {
			Logger.error(e.getMessage());
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oldDate);
		SimpleDateFormat sdf = new SimpleDateFormat(newFormat);
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		return sdf.format(calendar.getTime());
	}

	public static String getBrowserLogByKey(String key) {
		String result = null;
		LogEntries les = DriverUtils.getWebDriver().manage().logs()
				.get(LogType.PERFORMANCE);
		for (LogEntry le : les) {
			if (le.getMessage().contains(key)) {
				result = le.getMessage();
			}
		}
		return result;
	}
}
