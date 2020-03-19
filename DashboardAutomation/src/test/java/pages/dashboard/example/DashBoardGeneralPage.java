package pages.dashboard.example;

import com.logigear.control.common.ILabel;
import com.logigear.control.common.imp.Label;
import com.logigear.driver.DriverUtils;

import utils.common.Constants;

public class DashBoardGeneralPage {

	public void navigateToDashBoard() {
		DriverUtils.navigate(Constants.DASHBOARD_URL);
	}

	public void selectHeadMenu(String path) {
		String item[] = path.split(">");
		headMenu.setDynamicValue(item[0]);
		headMenu.waitForDisplay();
		headMenu.click();
		if (item.length > 1) {
			ILabel submenu = new Label(headMenu.getLocatorAsString() + "/ul/li[.='%s']");
			submenu.setDynamicValue(item[1]);
			submenu.click();
		}
		DriverUtils.waitForPageLoad();
	}

	public boolean doesHeaderMenuDisplay(String menu) {
		headMenu.setDynamicValue(menu);
		return headMenu.isDisplayed();
	}

	private ILabel headMenu = new Label("//ul[@class='head-menu']/li[a[contains(.,'%s')]]");
}
