package dashboard.tests;

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

public class TC012_DA_MP extends TestBase {

	@BeforeMethod
	public void setUp() {
		addNewPagData.setParrentPage("");
		addNewPagData.setPageName("Link");
		addNewPagData.setIsPublic("");

		Logger.info("Pre Condition 1. Navigate to Portal");
		dashBoardGeneralPage.navigateToDashBoard();
	}

	@Test(description = "Verify that user is able to add additional pages besides Overview page successfully", enabled = true)
	public void tc012_DA_MP() {

		Logger.info("1. Login to Portal");
		loginPage.login(Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);

		Logger.verify("Verify that Main page display");
		assertTrue(mainPage.doesDashBoardMainPageDisplay(), "Dashboard header should displays");

		Logger.info("2. Select Setting > Add page");
		mainPage.selectSettingMenu("Add Page");

		Logger.verify("Verify that New page dialog display");
		assertTrue(mainPage.doesNewPageDialogDiplay(), "New page dialog should display");

		Logger.info("3. Enter Page Name field");
		Logger.info("4. Click Ok Button");
		mainPage.submitNewPageForm(addNewPagData);

		Logger.verify("New page is displayed besides 'Overview' page");
		assertTrue(mainPage.doesNewPageItemDisplay(addNewPagData.getPageName()), "New page item should display");

	}

	@AfterMethod
	public void cleanUp() {
		mainPage.deletePage(addNewPagData.getPageName());
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
	AddNewPagData addNewPagData;

}
