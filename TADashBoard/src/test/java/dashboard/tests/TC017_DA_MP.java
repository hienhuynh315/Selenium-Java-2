package dashboard.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.drivers.DriverUtils;
import com.google.inject.Inject;

import dashboard.common.Constants;
import dashboard.common.TestBase;
import dashboard.model.AddNewPagData;
import dashboard.pages.DashBoardGeneralPage;
import dashboard.pages.LoginPage;
import dashboard.pages.MainPage;
import dashboard.pages.OverviewPage;
import utils.helper.Logger;

public class TC017_DA_MP extends TestBase {

	@BeforeMethod
	public void setUp() {
		addNewPagData.setParrentPage("");
		addNewPagData.setPageName("Linka");
		addNewPagData.setIsPublic("true");

		Logger.info("Pre Condition 1. Navigate to Portal");
		dashBoardGeneralPage.navigateToDashBoard();

	}

	@Test(description = "Verify that non Public pages can only be accessed and visible to their creators with condition that all parent pages above it are Public", enabled = true)
	public void tc017_DA_MP() {

		Logger.info("1. Login to Portal");
		loginPage.login(Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);

		Logger.verify("Verify that Main page display");
		softAssertion.assertTrue(mainPage.doesDashBoardMainPageDisplay(), "Dashboard header should display");

		Logger.info("2. Select Setting > Add page");
		mainPage.selectSettingMenu("Add Page");

		Logger.verify("Verify that New page dialog display");
		softAssertion.assertTrue(mainPage.doesNewPageDialogDiplay(), "New page dialog should display");

		Logger.info("3. Enter Page Name field");
		Logger.info("4. Click Ok Button");
		mainPage.submitNewPageForm(addNewPagData);
		parrentName = addNewPagData.getPageName();

		Logger.verify("New page is displayed besides 'Overview' page");
		softAssertion.assertTrue(mainPage.doesNewPageItemDisplay(addNewPagData.getPageName()),
				"New page item should display");

		// set data
		childName = "LinkChild";
		addNewPagData.setParrentPage(parrentName);
		addNewPagData.setPageName(childName);
		addNewPagData.setIsPublic("");

		Logger.info("5. Select Setting > Add page");
		mainPage.selectSettingMenu("Add Page");

		Logger.verify("Verify that New page dialog display");
		softAssertion.assertTrue(mainPage.doesNewPageDialogDiplay(), "New page dialog should display");

		Logger.info("6. Enter Page creation info");
		Logger.info("7. Click Ok Button");
		mainPage.submitNewPageForm(addNewPagData);

		Logger.info("Delete Parrent page ");
		mainPage.deletePage(parrentName);

		Logger.verify("Verify that New page dialog display");
		String textAlert = "Cannot delete page 'Linka' since it has child page(s).\r\n" + 
				"";
		softAssertion.assertEquals(DriverUtils.getAlertText().trim(),
				textAlert.trim(), "New page dialog should display");
		
		DriverUtils.acceptAlert();

	}

	@AfterMethod(alwaysRun = true)
	public void cleanUp() {
		try {
			Logger.info("Post-condition 1. Logout ");
			mainPage.selectHeadMenu(Constants.USERNAME_ADMIN + ">Logout");

			Logger.info("Post-condition 2. Login with Administrator ");
			loginPage.login(Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);

			Logger.info("Post-condition 3. Clean Data ");
			mainPage.deleteChildPage(parrentName, childName);
			mainPage.deletePage(parrentName);
			softAssertion.assertAll();

		} catch (Exception e) {
			softAssertion.assertAll();
		}

	}

	private String parrentName, childName;

	@Inject
	SoftAssert softAssertion;

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
