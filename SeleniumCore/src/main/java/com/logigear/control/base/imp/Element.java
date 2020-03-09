package com.logigear.control.base.imp;

import java.util.List;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.logigear.control.base.IElement;
import com.logigear.driver.DriverUtils;

public class Element implements IElement {

	private Logger logger = Logger.getLogger(Element.class);
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
		case "link":
			return By.linkText(body);
		case "xpath":
			return By.xpath(body);
		case "text":
			return By.xpath(String.format("//*[contains(text(), '%s')]", body));
		case "name":
			return By.name(body);
		default:
			return By.xpath(locator);
		}
	}

	/**
	 * Set value for dynamic control.
	 * 
	 * @Example TextBox myTextBox = new TextBox("//input[@value='%s']"); </br>
	 *          myTextBox.setDynamicValue("example");
	 * @param args
	 */
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
	public void scrollToView() {
		jsExecutor().executeScript("arguments[0].scrollIntoView(true);",
				getElement());
	}

	@Override
	public WebElement getElement() {
		if (DriverUtils.isWaitForAjax()) {
			DriverUtils.waitForAjaxJQueryProcess();
		}
		return getDriver().findElement(getLocator());
	}

	@Override
	public void setAttributeJS(String attributeName, String value) {
		try {
			logger.debug(String.format("Set attribute for %s",
					getLocator().toString()));
			jsExecutor().executeScript(
					String.format("arguments[0].setAttribute('%s','%s');",
							attributeName, value),
					getElement());
		} catch (Exception e) {
			logger.error(String.format("Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
			throw e;
		}

	}

	@Override
	public List<WebElement> getElements() {
		if (DriverUtils.isWaitForAjax()) {
			DriverUtils.waitForAjaxJQueryProcess();
		}
		return getDriver().findElements(getLocator());
	}

	@Override
	public int count() {
		if (DriverUtils.isWaitForAjax()) {
			DriverUtils.waitForAjaxJQueryProcess();
		}
		return getDriver().findElements(getLocator()).size();
	}

	@Override
	public String getText() {
		try {
			logger.debug(String.format("Get text of element %s",
					getLocator().toString()));
			return getElement().getText().trim();
		} catch (Exception e) {
			logger.error(String.format("Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
			throw e;
		}

	}

	@Override
	public String getValue() {
		try {
			logger.debug(String.format("Get value of element %s",
					getLocator().toString()));
			return getElement().getAttribute("value").trim();
		} catch (Exception e) {
			logger.error(String.format("Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
			throw e;
		}

	}

	@Override
	public String getAttribute(String attributeName) {
		try {
			logger.debug(String.format("Get attribute '%s' of element %s",
					attributeName, getLocator().toString()));
			String elementAttribute = getElement().getAttribute(attributeName);
			return elementAttribute != null ? elementAttribute.trim()
					: elementAttribute;
		} catch (Exception e) {
			logger.error(String.format("Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
			throw e;
		}

	}

	@Override
	public boolean isDisplayed() {
		try {
			logger.debug(String.format("is control displayed or not: %s",
					getLocator().toString()));
			return getElement().isDisplayed();
		} catch (Exception e) {
			logger.error(String.format(
					"IsDisplayed: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
			return false;
		}
	}

	@Override
	public boolean isExist() {
		try {
			logger.debug(String.format("is control exist or not: %s",
					getLocator().toString()));
			getElement();
			return true;
		} catch (Exception e) {
			logger.error(
					String.format("IsExist: Has error with control '%s': %s",
							getLocator().toString(), e.getMessage()));
			return false;
		}
	}

	@Override
	public boolean isDisplayed(int timeOutInSeconds) {
		try {
			waitForDisplay(timeOutInSeconds);
			return isDisplayed();
		} catch (Exception e) {
			logger.error(String.format(
					"IsDisplayed: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
			return false;
		}
	}

	@Override
	public boolean isEnabled() {
		try {
			logger.debug(String.format("is control enabled or not: %s",
					getLocator().toString()));
			return getElement().isEnabled();
		} catch (Exception e) {
			logger.error(
					String.format("IsEnabled: Has error with control '%s': %s",
							getLocator().toString(), e.getMessage()));
			return false;
		}

	}

	@Override
	public boolean isSelected() {
		try {
			logger.debug(String.format("is control selected or not: %s",
					getLocator().toString()));
			return getElement().isSelected();
		} catch (Exception e) {
			logger.error(
					String.format("IsSelected: Has error with control '%s': %s",
							getLocator().toString(), e.getMessage()));
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
			if (DriverUtils.isWaitForAjax()) {
				DriverUtils.waitForAjaxJQueryProcess();
			}
			logger.info(String.format("Wait for control display %s",
					getLocator().toString()));
			WebDriverWait wait = new WebDriverWait(getDriver(),
					timeOutInSeconds);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(getLocator()));
		} catch (Exception e) {
			logger.error(String.format(
					"WaitForDisplay: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
		}
	}

	@Override
	public void waitForVisible() {
		waitForDisplay(DriverUtils.getTimeOut());
	}

	@Override
	public void waitForVisible(int timeOutInSeconds) {
		try {
			if (DriverUtils.isWaitForAjax()) {
				DriverUtils.waitForAjaxJQueryProcess();
			}
			logger.info(String.format("Wait for control display %s",
					getLocator().toString()));
			WebDriverWait wait = new WebDriverWait(getDriver(),
					timeOutInSeconds);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(getLocator()));
		} catch (Exception e) {
			logger.error(String.format(
					"WaitForDisplay: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
		}
	}

	@Override
	public void waitForTextToBePresent(String text, int timeOutInSecond) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(),
					timeOutInSecond);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return getElement().getText().contains(text);
				}
			});
		} catch (Exception e) {
			logger.error(String.format(
					"waitForTextToBePresent: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
		}

	}

	@Override
	public void waitForTextToBeNotPresent(String text, int timeOutInSecond) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(),
					timeOutInSecond);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return !getElement().getText().contains(text);
				}
			});
		} catch (Exception e) {
			logger.error(String.format(
					"waitForTextToBeNotPresent: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
		}

	}

	@Override
	public void waitForElementNotPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(),
					DriverUtils.getTimeOut());
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return (getElements().size() < 1);
				}
			});
		} catch (Exception e) {
			logger.error(String.format(
					"waitForElementNotPresent: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
		}
	}

	@Override
	public void waitForElementNotDisplay() {
		waitForElementNotDisplay(DriverUtils.getTimeOut());
	}

	@Override
	public void waitForElementNotDisplay(int timeOutInSecond) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(),
					timeOutInSecond);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return !getElement().isDisplayed();
				}
			});
		} catch (Exception e) {
			logger.error(String.format(
					"waitForElementNotPresent: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
		}
	}

	@Override
	public void waitForElementNotPresent(int timeOutInSecond) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(),
					timeOutInSecond);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return (getElements().size() < 1);
				}
			});
		} catch (Exception e) {
			logger.error(String.format(
					"waitForElementNotPresent: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
		}
	}

	@Override
	public void waitForValueNotPresentInAttribute(String attribute,
			String value, int timeOutInSecond) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(),
					timeOutInSecond);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return !getElement().getAttribute(attribute)
							.contains(value);
				}
			});
		} catch (Exception e) {
			logger.error(String.format(
					"waitForValueNotPresentInAttribute: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
		}

	}

	@Override
	public void waitForValuePresentInAttribute(String attribute, String value,
			int timeOutInSecond) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(),
					timeOutInSecond);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return getElement().getAttribute(attribute).contains(value);
				}
			});
		} catch (Exception e) {
			logger.error(String.format(
					"waitForValuePresentInAttribute: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
		}
	}

	@Override
	public void waitForElementEnabled(int timeOutInSecond) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(),
					timeOutInSecond);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return getElement().isEnabled();
				}
			});
		} catch (Exception e) {
			logger.error(String.format(
					"waitForElementEnabled: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
		}

	}

	@Override
	public void waitForElementEnabled() {
		waitForElementEnabled(DriverUtils.getTimeOut());
	}

	@Override
	public void waitForElementDisabled(int timeOutInSecond) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(),
					timeOutInSecond);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return !getElement().isEnabled();
				}
			});
		} catch (Exception e) {
			logger.error(String.format(
					"waitForElementDisabled: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
		}

	}

	@Override
	public void waitForElementDisabled() {
		waitForElementDisabled(DriverUtils.getTimeOut());
	}

	@Override
	public void waitForPresent() {
		waitForPresent(DriverUtils.getTimeOut());
	}

	@Override
	public void waitForPresent(int timeOutInSecond) {
		try {
			long startTime = System.currentTimeMillis() / 1000;
			long currentTime = System.currentTimeMillis() / 1000;
			while ((currentTime - startTime) < timeOutInSecond) {
				if (isDisplayed()) {
					break;
				}
				currentTime = System.currentTimeMillis() / 1000;
			}
		} catch (Exception e) {
			logger.error(String.format(
					"waitForPresent: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
		}
	}

	@Override
	public void moveTo() {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(getElement()).build().perform();
	}

	@Override
	public void mouseHoverJScript() {
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', 				true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		jsExecutor().executeScript(mouseOverScript, getElement());

	}

	@Override
	public String getClassName() {
		return getAttribute("class");
	}

	@Override
	public String getLocatorAsString() {
		return this.locator;
	}

	@Override
	public void submit() {
		getElement().submit();
	}

	@Override
	public String getTagName() {
		return getElement().getTagName();
	}

	@Override
	public WebElement getActiveElement() {
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		return (WebElement) executor
				.executeScript("return document.activeElement");
	}

	@Override
	public int getHeight() {
		return getElement().getSize().getHeight();
	}

	@Override
	public int getWidth() {
		return getElement().getSize().getWidth();
	}

	@Override
	public void waitForElementClickable() {
		try {
			if (DriverUtils.isWaitForAjax()) {
				DriverUtils.waitForAjaxJQueryProcess();
			}
			logger.info(String.format("Wait for control display %s",
					getLocator().toString()));
			WebDriverWait wait = new WebDriverWait(getDriver(),
					DriverUtils.getTimeOut());
			wait.until(ExpectedConditions.elementToBeClickable(getLocator()));
		} catch (Exception e) {
			logger.error(String.format(
					"WaitForDisplay: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
		}
	}

	@Override
	public int getX() {
		return getElement().getLocation().x;
	}

	@Override
	public int getY() {
		return getElement().getLocation().y;
	}

	public void highLightElement() {
		jsExecutor().executeScript("arguments[0].style.border='3px solid red';",
				getElement());
	}

	public String getCssValue(String arg0) {
		return getElement().getCssValue(arg0);
	}
}
