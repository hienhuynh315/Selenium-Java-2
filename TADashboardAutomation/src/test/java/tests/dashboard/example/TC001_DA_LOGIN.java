package tests.dashboard.example;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.inject.Inject;

import pages.dashboard.example.DashBoardGeneralPage;
import pages.dashboard.example.LoginPage;
import pages.dashboard.example.MainPage;
import pages.dashboard.example.OverviewPage;
import utils.common.Constants;
import utils.common.TestBase;
import utils.helper.Logger;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class TC001_DA_LOGIN extends TestBase {

	@BeforeMethod
	public void setUp() {

		Logger.info("Pre Condition 1. Navigate to Portal");
		dashBoardGeneralPage.navigateToDashBoard();
	}

	@Test(description = "Verify that user can login specific repository successfully via Dashboard login page with correct credentials", enabled = true)
	public void tc001_DA_LOGIN() {
	
		
		Logger.info("1. Login to DarshBoard");
		loginPage.login(Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);

		Logger.verify("Verify that Main page display");
//		assertTrue(mainPage.doesDashBoardMainPageDisplay(), "Dashboard header should display");
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
