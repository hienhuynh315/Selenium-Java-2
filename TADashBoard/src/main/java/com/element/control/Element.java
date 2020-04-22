package com.element.control;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.drivers.DriverUtils;
import com.google.common.base.Function;

public class Element implements IElement {

	public String locator;
	private By byLocator;
	private String dynamicLocator;

	public Element(String locator) {
		this.locator = locator;
		this.dynamicLocator = locator;
		this.byLocator = getByLocator();
	}

	public Element(By byLocator) {
		this.byLocator = byLocator;
	}

	public Element(String locator, Object... args) {
		this.dynamicLocator = locator;
		this.locator = String.format(dynamicLocator, args);
		this.byLocator = getByLocator();
	}

	protected WebDriver getDriver() {
		return DriverUtils.getWebDriver();
	}

	protected JavascriptExecutor jsExecutor() {
		return (JavascriptExecutor) getDriver();
	}

	private By getByLocator() {
		String body = this.locator.replaceAll("[\\w\\s]*=(.*)", "$1").trim();
		String type = this.locator.replaceAll("([\\w\\s]*)=.*", "$1").trim();
		switch (type) {
		case "css":
			return By.cssSelector(body);
		case "id":
			return By.id(body);
		case "text":
			return By.xpath(String.format("//*[contains(text(), '%s')]", body));
		case "name":
			return By.name(body);
		default:
			return By.xpath(locator);
		}
	}

	@Override
	public void setDynamicValue(Object... args) {
		this.locator = String.format(this.dynamicLocator, args);
		this.byLocator = getByLocator();
	}

	@Override
	public By getLocator() {
		return this.byLocator;
	}

	@Override
	public WebElement getElement() {
		return getDriver().findElement(getLocator());
	}

	@Override
	public List<WebElement> getElements() {
		List<WebElement> listOfElements = getDriver().findElements(getLocator());
		return listOfElements;
	}

	@Override
	public String getLocatorAsString() {
		return this.locator;
	}

	@Override
	public void scrollToView() {
		jsExecutor().executeScript("arguments[0].scrollIntoView(true);", getElement());
	}

	@Override
	public String getText() {
		try {
			return getElement().getText().trim();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public String getValue() {
		try {
			return getElement().getAttribute("value").trim();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public boolean isDisplayed(int timeOutInSeconds) {
		try {
			waitForDisplay(timeOutInSeconds);
			return isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String getAttribute(String attributeName) {
		try {
			String elementAttribute = getElement().getAttribute(attributeName);
			return elementAttribute != null ? elementAttribute.trim() : elementAttribute;
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public boolean isDisplayed() {
		try {
			return getElement().isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void waitForDisplay() {
		waitForDisplay(DriverUtils.getTimeOut());
	}

	@Override
	public void waitForDisplay(int timeOutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(getLocator()));
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void waitForElementEnabled() {
		waitForElementEnabled(DriverUtils.getTimeOut());
	}

	@Override
	public void waitForElementEnabled(int timeOutInSecond) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSecond);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return getElement().isEnabled();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean isEnabled() {
		try {
			return getElement().isEnabled();
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void moveTo() {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(getElement()).build().perform();
	}

	@Override
	public void click() {
		try {
			getElement().click();
		} catch (Exception e) {
			throw e;
		}
	}

}
