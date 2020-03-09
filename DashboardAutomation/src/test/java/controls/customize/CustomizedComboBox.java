package controls.customize;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.logigear.control.base.imp.Editable;
import com.logigear.control.common.IComboBox;
import com.logigear.control.common.ILabel;
import com.logigear.control.common.imp.Label;
import com.logigear.driver.DriverUtils;

public class CustomizedComboBox extends Editable implements IComboBox {

	public CustomizedComboBox(By locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

	public CustomizedComboBox(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void select(String text) {
		String dynamicItem = "//li[text()='%s']";
		click();
		ILabel item = new Label(dynamicItem);
		item.setDynamicValue(text);
		List<WebElement> allItems = item.getElements();

		if (allItems.size() > 1) {
			for (WebElement webElement : allItems) {
				if (webElement.isDisplayed()) {
					webElement.click();
					break;
				}
			}
		} 
		else {
			item.click();
		}
		DriverUtils.delay(1);
	}

	@Override
	public void selectStartsWithTextItem(String text) {
		String dynamicItem = "//li[starts-with(text(),'%s')]";
		click();
		ILabel item = new Label(dynamicItem);
		item.setDynamicValue(text);
		List<WebElement> allItems = item.getElements();

		if (allItems.size() > 1) {
			for (WebElement webElement : allItems) {
				if (webElement.isDisplayed()) {
					webElement.click();
					break;
				}
			}
		} else {
			item.click();
		}
		DriverUtils.delay(1);
	}

	public void selectByJS(String text) {
		String dynamicItem = "//li[text()='%s']";
		ILabel item = new Label(dynamicItem);
		click();
		item.setDynamicValue(text);
		item.scrollToView();
		item.clickByJs();
		DriverUtils.delay(1);
	}

	public Boolean isContained(String item) {
		String options = "//li[@role='option']";
		ILabel optionsLabel = new Label(options);
		List<WebElement> elementList = optionsLabel.getElements();
		List<String> optionsList = new ArrayList<>();

		for (WebElement we : elementList) {
			if (we.isDisplayed()) {
				optionsList.add(we.getText());
			}
		}

		return optionsList.contains(item);
	}

	public Boolean isContained(List<String> items) {
		String options = "//li[@role='option']";
		ILabel optionsLabel = new Label(options);
		List<WebElement> elementList = optionsLabel.getElements();
		List<String> optionsList = new ArrayList<>();

		for (WebElement we : elementList) {
			if (we.isDisplayed()) {
				optionsList.add(we.getText());
			}
		}

		return optionsList.containsAll(items);
	}

	@Override
	public void select(int index) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getSelected() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void waitForSelectedOptionToBePresent(String option,
			int timeOutInSecond) {
		// TODO Auto-generated method stub

	}
}
