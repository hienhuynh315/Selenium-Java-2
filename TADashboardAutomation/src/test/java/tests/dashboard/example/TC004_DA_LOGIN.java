package tests.dashboard.example;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.inject.Inject;

import data.dashboard.example.ExampleData;
import pages.dashboard.example.DashBoardGeneralPage;
import pages.dashboard.example.LoginPage;
import pages.dashboard.example.MainPage;
import pages.dashboard.example.OverviewPage;
import utils.common.Constants;
import utils.common.TestBase;
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

	@Inject
	ExampleData exampleData;

}
