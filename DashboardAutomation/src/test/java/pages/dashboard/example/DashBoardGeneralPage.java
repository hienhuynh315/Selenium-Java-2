package pages.dashboard.example;

import com.logigear.control.common.IButton;
import com.logigear.control.common.ILabel;
import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.Label;
import com.logigear.driver.DriverUtils;

import utils.common.Constants;


public class DashBoardGeneralPage  {
	
	public void clickButton() {
		itemLabel.click();
		itemButton.click();
	}
	
	public void navigateToDashBoard() {
		DriverUtils.navigate(Constants.DASHBOARD_URL);
	}


	private ILabel itemLabel = new Label(
			"id=label");
	private IButton itemButton = new Button(
			"id=button");
}
