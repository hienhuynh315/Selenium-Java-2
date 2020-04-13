package dashboard.tests;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.inject.Inject;

import dashboard.common.TestBase;
import dashboard.pages.DashBoardGeneralPage;
import dashboard.pages.LoginPage;
import dashboard.pages.MainPage;
import dashboard.pages.OverviewPage;
import utils.helper.Logger;

public class TC002_DA_LOGIN extends TestBase {

	@BeforeMethod
	public void setUp() {
		userName = "testaa";
		invalidPassword = "abc";
		Logger.info("Pre Condition 1. Navigate to Portal");
		dashBoardGeneralPage.navigateToDashBoard();
	}

	@Test(description = "Verify that user fails to login specific repository successfully via Dashboard login page with incorrect credentials", enabled = true)
	public void tc002_DA_LOGIN() {

		Logger.info("1. Login to DarshBoard");
		loginPage.login(userName, invalidPassword);

		Logger.verify("Verify that error message display");
//		DriverUtils.waitForAlertDisplay();
//		assertEquals(DriverUtils.getAlertText(), "Username or password is invalid");
	}

	private String userName, invalidPassword;

	@Inject
	OverviewPage overviewPage;

	@Inject
	LoginPage loginPage;

	@Inject
	MainPage mainPage;

	@Inject
	DashBoardGeneralPage dashBoardGeneralPage;

}
