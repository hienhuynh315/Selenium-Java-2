package tests.dashboard.example;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.inject.Inject;
import com.logigear.driver.DriverUtils;

import data.dashboard.example.ExampleData;
import pages.dashboard.example.DashBoardGeneralPage;
import pages.dashboard.example.LoginPage;
import pages.dashboard.example.MainPage;
import pages.dashboard.example.OverviewPage;
import utils.common.Constants;
import utils.common.TestBase;
import utils.helper.Logger;

public class TC006_DA_LOGIN extends TestBase {

	@BeforeMethod
	public void setUp() {

		Logger.info("Pre Condition 1. Navigate to Portal");
		dashBoardGeneralPage.navigateToDashBoard();
	}

	@Test(description = "Verify that Password input is case sensitive", enabled = true)
	public void tc006_DA_LOGIN() {

		Logger.info("1. Login to DarshBoard");
		loginPage.login(Constants.USERNAME_TEST, Constants.PASSWORD_TEST);

		Logger.verify("Verify that Main page display");
		assertTrue(mainPage.doesDashBoardMainPageDisplay(), "Dashboard header should display");

		Logger.info("2. Logout");
		mainPage.selectHeadMenu("test>Logout");

		Logger.info("3. Login to DarshBoard");
		loginPage.login(Constants.USERNAME_TEST, Constants.PASSWORD_TEST.toLowerCase());

		Logger.verify("Verify that error message display");
		DriverUtils.waitForAlertDisplay();
		assertEquals(DriverUtils.getAlertText(), "Username or password is invalid");

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
