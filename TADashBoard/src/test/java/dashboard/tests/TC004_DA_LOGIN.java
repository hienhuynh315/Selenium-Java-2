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

public class TC004_DA_LOGIN extends TestBase {

	@BeforeMethod
	public void setUp() {

		Logger.info("Pre Condition 1. Navigate to Portal");
		dashBoardGeneralPage.navigateToDashBoard();
	}

	@Test(description = "Verify that user is able to log in different repositories successfully after logging out current repository", enabled = true)
	public void tc004_DA_LOGIN() {

		Logger.info("1. Login to DarshBoard  with repo = SampleRepository");
		loginPage.login(Constants.REPOSITORY_SAMPLE, Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);

		Logger.verify("Verify that Main page display");
		assertTrue(mainPage.doesDashBoardMainPageDisplay(), "Dashboard header should display");

		Logger.info("2. Logout");
		mainPage.selectHeadMenu("administrator>Logout");

		Logger.info("3. Login to DarshBoard with repo = SampleRepositoryLV2");
		loginPage.login(Constants.REPOSITORY_LV2, Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);
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
