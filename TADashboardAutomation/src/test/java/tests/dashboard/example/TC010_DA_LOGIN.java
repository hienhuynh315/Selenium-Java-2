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

public class TC010_DA_LOGIN extends TestBase {

	@BeforeMethod
	public void setUp() {

		Logger.info("Pre Condition 1. Navigate to Portal");
		dashBoardGeneralPage.navigateToDashBoard();
	}

	@Test(description = "Verify that the page works correctly for the case when no input entered to Password and Username field	", enabled = true)
	public void tc010_DA_LOGIN() {

		Logger.info("1. Login to DarshBoard");
		loginPage.login(Constants.USERNAME_TC009, Constants.PASSWORD_TC009);

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
