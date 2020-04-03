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

public class TC008_DA_LOGIN extends TestBase {

	@BeforeMethod
	public void setUp() {

		Logger.info("Pre Condition 1. Navigate to Portal");
		dashBoardGeneralPage.navigateToDashBoard();
	}

	@Test(description = "Verify that password with special characters is working correctly", enabled = true)
	public void tc008_DA_LOGIN() {

		Logger.info("1. Login to DarshBoard");
		loginPage.login(Constants.USERNAME_TC008, Constants.PASSWORD_TC008);

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

	@Inject
	ExampleData exampleData;

}
