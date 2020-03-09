package utils.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;

import com.logigear.driver.DriverUtils;

import utils.common.Common;

public class FileDownloader {

	private String downloadPath = Common.getDownloadFolderPath();
	private WebDriver driver;

	/**
	 * @return the downloadPath
	 */
	public String getDownloadPath() {
		return downloadPath;
	}

	public FileDownloader(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param downloadPath
	 *            the downloadPath to set
	 */
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public String download(String url) {
		Set<org.openqa.selenium.Cookie> cookies = driver.manage().getCookies();

		File file = null;
		try {
			CookieStore cookieStore = new BasicCookieStore();
			Date dt = new Date();
			DateTime dtPlusOne = new DateTime(dt).plusDays(1);
			dt = dtPlusOne.toDate();
			String domain = DriverUtils
					.execJavaScript("return document.domain;").toString();
			for (org.openqa.selenium.Cookie seleniumCookie : cookies) {
				BasicClientCookie basicClientCookie = new BasicClientCookie(
						seleniumCookie.getName(), seleniumCookie.getValue());
				if (seleniumCookie.getExpiry() != null) {
					dt = seleniumCookie.getExpiry();
				}
				if (seleniumCookie.getDomain() != null
						&& !seleniumCookie.getDomain().equals("")) {
					domain = seleniumCookie.getDomain();
				}
				basicClientCookie.setDomain(domain);
				basicClientCookie.setExpiryDate(dt);
				basicClientCookie.setPath(seleniumCookie.getPath());
				cookieStore.addCookie(basicClientCookie);
			}
			String fileName = "";
			HttpClient client = HttpClientBuilder.create()
					.setDefaultCookieStore(cookieStore).build();
			HttpGet request = new HttpGet(url);
			HttpResponse resp = client.execute(request);

			if (resp != null && resp.getStatusLine().getStatusCode() == 200) {
				BufferedInputStream bis = new BufferedInputStream(
						resp.getEntity().getContent());

				for (Header header : resp.getAllHeaders()) {
					if (header.getValue().contains("filename")) {
						fileName = header.getValue().split("=")[1]
								.replaceAll(";", "");
						break;
					}
				}

				if (fileName.isEmpty()) {
					fileName = UUID.randomUUID().toString() + ".pdf";
				}
				file = new File(getDownloadPath() + fileName);
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(file));
				int inByte;
				while ((inByte = bis.read()) != -1) {
					bos.write(inByte);
				}
				bis.close();
				bos.close();
				return file.getAbsolutePath();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}
}
