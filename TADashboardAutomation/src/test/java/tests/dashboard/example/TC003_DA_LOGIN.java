package tests.dashboard.example;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.inject.Inject;
import com.logigear.trainning.driver.DriverUtils;

import pages.dashboard.example.DashBoardGeneralPage;
import pages.dashboard.example.LoginPage;
import pages.dashboard.example.MainPage;
import pages.dashboard.example.OverviewPage;
import utils.common.TestBase;
import utils.helper.Logger;

public class TC003_DA_LOGIN extends TestBase {

	@BeforeMethod
	public void setUp() {
		userName = "test";
		invalidPassword = "abc";
		Logger.info("Pre Condition 1. Navigate to Portal");
		dashBoardGeneralPage.navigateToDashBoard();
	}

	@Test(description = "Verify that user fails to log in specific repository successfully via Dashboard login page with correct username and incorrect password", enabled = true)
	public void tc003_DA_LOGIN() {

		Logger.info("1. Login to DashBoard");
		loginPage.login(userName, invalidPassword);

		Logger.verify("Verify that error message display");
		assertEquals(DriverUtils.getAlertText(), "Username or password is invalid");
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
