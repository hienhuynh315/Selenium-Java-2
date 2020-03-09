package tests.dashboard.example;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.inject.Inject;

import data.dashboard.example.ExampleData;
import pages.dashboard.example.DashBoardGeneralPage;
import pages.dashboard.example.OverviewPage;
import utils.common.Constants;
import utils.common.TestBase;
import utils.helper.Logger;

public class TC001_Example extends TestBase {

	@BeforeMethod
	public void setUp() {
		dataPath = Constants.BAT_DATA_PATH + "ExampeFileJson.json";

		exampleData.setJsonFile(dataPath);

		Logger.info("Pre Condition 1. Navigate to Portal");
	}

	@Test(groups = "bat", description = "descripton of test case", enabled = true)
	public void tc001_Example() {

		Logger.info("1. Login to Portal");
		dashBoardGeneralPage.navigateToDashBoard();

		assertTrue(true, "Verify that ... displays");
	}

	@AfterMethod
	public void cleanUp() {
	}

	private String dataPath;

	@Inject
	OverviewPage overviewPage;

	@Inject
	DashBoardGeneralPage dashBoardGeneralPage;
	@Inject
	ExampleData exampleData;

}
