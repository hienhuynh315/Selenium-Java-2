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
import io.qameta.allure.Description;
import utils.helper.AllureLogger;
import utils.helper.Logger;

public class TC001_DA_LOGIN extends TestBase {

	@BeforeMethod
	public void setUp() {
		Logger.info("Pre Condition 1. Navigate to Portal");
		dashBoardGeneralPage.navigateToDashBoard();

	}

	@Test(description = "Verify that user can login specific repository successfully via Dashboard login page with correct credentials", enabled = true)
	@Description("Test Description: Verify that user can login specific repository successfully ")
	public void tc001_DA_LOGIN() {

		Logger.info("Step 1. Login to DarshBoard");
		loginPage.login(Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);

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
