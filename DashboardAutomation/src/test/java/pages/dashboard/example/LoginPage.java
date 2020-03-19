package pages.dashboard.example;

import com.logigear.control.common.IButton;
import com.logigear.control.common.IComboBox;
import com.logigear.control.common.ITextBox;
import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.ComboBox;
import com.logigear.control.common.imp.TextBox;
import com.logigear.driver.DriverUtils;

public class LoginPage extends DashBoardGeneralPage {

	public void login(String userName, String password) {
		userNameTextBox.enter(userName);
		passwordTextBox.enter(password);
		loginButton.click();
		DriverUtils.delay(5);
	}

	public void login(String repo, String userName, String password) {
		repoComboBox.select(repo);
		userNameTextBox.enter(userName);
		passwordTextBox.enter(password);
		loginButton.click();
		DriverUtils.delay(5);
	}

	private ITextBox userNameTextBox = new TextBox("id=username");
	private ITextBox passwordTextBox = new TextBox("id=password");
	private IComboBox repoComboBox = new ComboBox("id=repository");
	private IButton loginButton = new Button("//div[@class='btn-login']");

}
