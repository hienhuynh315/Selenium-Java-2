package dashboard.tests;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.inject.Inject;

import dashboard.common.Constants;
import dashboard.common.TestBase;
import dashboard.pages.DashBoardGeneralPage;
import dashboard.pages.LoginPage;
import dashboard.pages.MainPage;
import dashboard.pages.OverviewPage;
import utils.helper.Logger;

public class TC007_DA_LOGIN extends TestBase {

	@BeforeMethod
	public void setUp() {

		Logger.info("Pre Condition 1. Navigate to Portal");
		dashBoardGeneralPage.navigateToDashBoard();
	}

	@Test(description = "Verify that \"Username\" is not case sensitive", enabled = true)
	public void tc007_DA_LOGIN() {

		Logger.info("1. Login to DarshBoard");
		loginPage.login(Constants.USERNAME_TAADMIN, Constants.PASSWORD_TAADMIN);

		Logger.verify("Verify that Main page display");
		assertTrue(mainPage.doesDashBoardMainPageDisplay(), "Dashboard header should display");

		Logger.info("2. Logout");
		mainPage.selectHeadMenu("test>Logout");

		Logger.info("3. Login to DarshBoard");
		loginPage.login(Constants.USERNAME_TAADMIN.toLowerCase(), Constants.PASSWORD_TAADMIN);

		Logger.verify("Verify that Main page display");
		assertTrue(mainPage.doesDashBoardMainPageDisplay(), "Dashboard header should display");
	}

	@Inject
	OverviewPage overviewPage;

	@Inject
	LoginPage loginPage;

	@Inject
	MainPage mainPage;

	@Inject
	DashBoardGeneralPage dashBoardGeneralPage;

}
