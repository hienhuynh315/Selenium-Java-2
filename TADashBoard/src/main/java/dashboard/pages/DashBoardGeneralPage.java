package dashboard.pages;

import com.drivers.DriverUtils;
import com.element.control.ILabel;
import com.element.control.Label;

import utils.common.Constants;
import utils.helper.PropertiesHelper;

public class DashBoardGeneralPage {

	public void navigateToDashBoard() {
		DriverUtils.navigate(PropertiesHelper.getPropValue("profile.dashboard.url" + DriverUtils.getURL()));
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
		DriverUtils.delay(5);
	}

	public boolean doesHeaderMenuDisplay(String menu) {
		headMenu.setDynamicValue(menu);
		return headMenu.isDisplayed();
	}

	private ILabel headMenu = new Label("//ul[@class='head-menu']/li[a[contains(.,'%s')]]");
}
