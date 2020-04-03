package pages.dashboard.example;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.logigear.trainning.common.control.Button;
import com.logigear.trainning.common.control.ComboBox;
import com.logigear.trainning.common.control.IButton;
import com.logigear.trainning.common.control.IComboBox;
import com.logigear.trainning.common.control.ILabel;
import com.logigear.trainning.common.control.ITextBox;
import com.logigear.trainning.common.control.Label;
import com.logigear.trainning.common.control.TextBox;
import com.logigear.trainning.driver.DriverUtils;

import data.dashboard.example.AddNewPagData;
import utils.common.Constants;

public class MainPage extends DashBoardGeneralPage {

	public boolean doesDashBoardMainPageDisplay() {
		return headerMenu.isDisplayed();
	}

	public void selectSettingMenu(String item) {
		settingIcon.waitForDisplay();
		settingIcon.waitForElementEnabled();
		settingIcon.click();
		settingMenuItem.setDynamicValue(item);
		settingMenuItem.waitForDisplay();
		settingMenuItem.click();
	}

	public boolean isSettingIconClickable() {
		return settingMenuItem.isEnabled();
	}

	public boolean doesNewPageDialogDiplay() {
		return newPageLabel.isDisplayed(Constants.SHORT_TIME_OUT_IN_SECOND);
	}

	public int numberOfNewPageDialogDisplay() {
		List<WebElement> listOfElement = newPageLabel.getElements();
		return listOfElement.size();
	}

	public void submitNewPageForm(AddNewPagData data) {
		String pageName = data.getPageName();
		String parrentPage = data.getParrentPage();
		newPageLabel.waitForDisplay();
		if (!(pageName.equals(""))) {
			pageNameTextBox.enter(pageName);
		}
		if (!(data.isPublic.equals(""))) {
			publicCheckBox.click();
		}
		if (!(parrentPage.equals(""))) {
			parrentPageComboBox.select(parrentPage);
		}
		newPageOKButton.click();
	}

	public boolean doesNewPageItemDisplay(String namePage) {
		mainMenuItem.setDynamicValue(namePage);
		return mainMenuItem.isDisplayed(Constants.SHORT_TIME_OUT_IN_SECOND);
	}

	public boolean doesChildPageDisplay(String parrentPage, String childName) {
		mainMenuItem.setDynamicValue(parrentPage);
		ILabel submenu = new Label(mainMenuItem.getLocatorAsString() + "//li[a[.='%s']]");
		submenu.setDynamicValue(childName);
		mainMenuItem.click();
		return submenu.isDisplayed(Constants.SHORT_TIME_OUT_IN_SECOND);
	}

	public void deletePage(String name) {
		mainMenuItem.setDynamicValue(name);
		mainMenuItem.click();
		settingIcon.waitForElementEnabled();
		selectSettingMenu("Delete");
		DriverUtils.acceptAlert();
	}

	public void deleteChildPage(String parrentPage, String childName) {
		mainMenuItem.setDynamicValue(parrentPage);
		mainMenuItem.moveTo();
		ILabel submenu = new Label(mainMenuItem.getLocatorAsString() + "//li[a[.='%s']]");
		submenu.setDynamicValue(childName);
		submenu.waitForDisplay();
		submenu.click();
		selectSettingMenu("Delete");
		DriverUtils.acceptAlert();
	}

	// declare label
	private ILabel newPageLabel = new Label("//h2[text()='New Page']");
	private ILabel headerMenu = new Label("//a[contains(.,'Repository')]");
	private ILabel settingIcon = new Label("//li[@class='mn-setting']");
	private ILabel settingMenuItem = new Label("//li[@class='mn-setting']//li/a[.='%s']");
	private ILabel mainMenuItem = new Label("//div[@id='main-menu']//li[a[.='%s']]");

	// declare Textbox
	private ITextBox pageNameTextBox = new TextBox("id=name");

	// declare SelectBox
	private IComboBox parrentPageComboBox = new ComboBox("id=parent");

	// declare Button
	private IButton newPageOKButton = new Button("id=OK");
	private IButton publicCheckBox = new Button("id=ispublic");
}
