package tests.dashboard.example;

import static org.testng.Assert.assertEquals;
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

public class TC011_DA_MP extends TestBase {

	@BeforeMethod
	public void setUp() {

		Logger.info("Pre Condition 1. Navigate to Portal");
		dashBoardGeneralPage.navigateToDashBoard();
	}

	@Test(description = "Verify that user is unable open more than 1 New Page dialog", enabled = true)
	public void tc011_DA_MP() {

		Logger.info("1. Login to Portal");
		loginPage.login(Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);

		Logger.verify("Verify that Main page display");
		assertTrue(mainPage.doesDashBoardMainPageDisplay(), "Dashboard header should displays");

		Logger.info("2. Select Setting > Add page");
		mainPage.selectSettingMenu("Add Page");

		Logger.verify("Verify that New page dialog display");
		assertTrue(mainPage.doesNewPageDialogDiplay(), "New page dialog should displays");

		Logger.verify("Verify that user is unable open more than 1 New Page dialog");
		assertTrue(mainPage.isSettingIconClickable(), "Setting icon should not be clickable");
		assertEquals(mainPage.numberOfNewPageDialogDisplay(), 1, "There are one New Page should display");
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
