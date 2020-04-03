package pages.dashboard.example;

import com.logigear.trainning.common.control.Button;
import com.logigear.trainning.common.control.ComboBox;
import com.logigear.trainning.common.control.IButton;
import com.logigear.trainning.common.control.IComboBox;
import com.logigear.trainning.common.control.ITextBox;
import com.logigear.trainning.common.control.TextBox;
import com.logigear.trainning.driver.DriverUtils;

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
