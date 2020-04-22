package dashboard.pages;

import com.drivers.DriverUtils;
import com.element.control.Button;
import com.element.control.ComboBox;
import com.element.control.IButton;
import com.element.control.IComboBox;
import com.element.control.ITextBox;
import com.element.control.TextBox;

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
