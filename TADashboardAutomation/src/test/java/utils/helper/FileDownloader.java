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

import com.logigear.trainning.driver.DriverUtils;

import utils.common.Common;

public class FileDownloader {

	private WebDriver driver;

	/**
	 * @return the downloadPath
	 */

	public FileDownloader(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param downloadPath
	 *            the downloadPath to set
	 */
//	public void setDownloadPath(String downloadPath) {
//		this.downloadPath = downloadPath;
//	}

}
