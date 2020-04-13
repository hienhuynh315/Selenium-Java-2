package dashboard.tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.inject.Inject;

import dashboard.common.Constants;
import dashboard.common.TestBase;
import dashboard.data.AddNewPagData;
import dashboard.pages.DashBoardGeneralPage;
import dashboard.pages.LoginPage;
import dashboard.pages.MainPage;
import dashboard.pages.OverviewPage;
import utils.helper.Logger;

public class TC016_DA_MP extends TestBase {

	@BeforeMethod
	public void setUp() {
		parrentName = "Overview";
		childName = "Link";
		addNewPagData.setParrentPage(parrentName);
		addNewPagData.setPageName(childName);
		addNewPagData.setIsPublic("");

		Logger.info("Pre Condition 1. Navigate to Portal");
		dashBoardGeneralPage.navigateToDashBoard();
	}

	@Test(description = "Verify that non Public pages can only be accessed and visible to their creators with condition that all parent pages above it are Public", enabled = true)
	public void tc015_DA_MP() {

		Logger.info("1. Login to DarshBoard");
		loginPage.login(Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);

		Logger.verify("Verify that Main page display");
		assertTrue(mainPage.doesDashBoardMainPageDisplay(), "Dashboard header should display");

		Logger.info("2. Select Setting > Add page");
		mainPage.selectSettingMenu("Add Page");

		Logger.verify("Verify that New page dialog display");
		assertTrue(mainPage.doesNewPageDialogDiplay(), "New page dialog should display");

		Logger.info("3. Enter Page Name field");
		Logger.info("4. Click Ok Button");
		mainPage.submitNewPageForm(addNewPagData);

		Logger.info("5. Logout ");
		mainPage.selectHeadMenu("administrator>Logout");

		Logger.info("6. Login with other account ");
		loginPage.login(Constants.USERNAME_TEST, Constants.PASSWORD_TEST);

		Logger.verify("Child page is not displayed in 'Overview' tab");
		assertFalse(mainPage.doesChildPageDisplay(parrentName, childName), "Child Page should not display");

	}

	@AfterMethod
	public void cleanUp() {

		Logger.info("Post-condition 1. Logout ");
		mainPage.selectHeadMenu("test>Logout");

		Logger.info("Post-condition 2. Login with Administrator ");
		loginPage.login(Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);

		Logger.info("Post-condition 3. Clean Data ");
		mainPage.deleteChildPage(parrentName, childName);
	}

	private String parrentName, childName;

	@Inject
	OverviewPage overviewPage;

	@Inject
	LoginPage loginPage;

	@Inject
	MainPage mainPage;

	@Inject
	DashBoardGeneralPage dashBoardGeneralPage;

	@Inject
	AddNewPagData addNewPagData;

}
