package P2_TestCases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.WMS_ApplicationPages.Color_Page;
import com.WMS_ApplicationPages.DashboardPage;
import com.WMS_ApplicationPages.LineSheetPage;
import com.WMS_ApplicationPages.MainMenuEnum;
import com.WMS_ApplicationPages.MainMenuPage;
import com.WMS_ApplicationPages.MaterialPage;
import com.WMS_ApplicationPages.Measurements_Page;

import com.WMS_ApplicationPages.ProductDetailsPage;
import com.WMS_ApplicationPages.ProductPage;
import com.WMS_Utilities.WMS_TestBase;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.DataProviders;

@Test(enabled = true, groups= {"P2_TC"})
public class TC173_P2_Add_size_based_on_set_values_from_Grade_Table extends WMS_TestBase {

	WebDriver driver;
	DashboardPage dashboardPage;
	MainMenuPage mainMenuPage;
	MaterialPage materialPage;
	Color_Page color_Page;
	ProductDetailsPage productDetailsPage;
	LineSheetPage lineSheetPage;
	Measurements_Page MeasurementsPage;

	ProductPage productPage;

	boolean Capture = true;

	/**
	 * Note: Ensure you are logged in as a Garment Developer user before executing this test case
	 */

	@BeforeMethod
	public void setUp() throws InterruptedException {

			driver = invokeBrowser();
			LaunchSpecific_URL(GarmentDeveloper_URL);
			dashboardPage = new DashboardPage(driver);
			mainMenuPage = new MainMenuPage(driver);
			materialPage = new MaterialPage(driver);
			color_Page = new Color_Page(driver);
			lineSheetPage = new LineSheetPage(driver);
			productDetailsPage = new ProductDetailsPage(driver);
			MeasurementsPage = new Measurements_Page(driver);

			productPage = new ProductPage(driver);

			setReport("TC173_P2_Add_size_based_on_set_values_from_Grade_Table");
		}
	}

	@Test(priority = 0, dataProvider = "TC173_P2_Add_size_based_on_set_values_from_Grade_Table", dataProviderClass = DataProviders.class)
	public void P2_TC173_Add_size_based_on_set_values_from_Grade_Table(String TestType, String season,
			String productName, String specification, String measurementSet, String newSize)
			throws Exception {

			test = extent.createTest(":::TC173_P2_Add_size_based_on_set_values_from_Grade_Table:::");
		}



		try {

			test.log(Status.INFO, "This test case covers P1 measurment module from TC173 to TC181");

			System.out.println("Browser Launched successfully");
			test.log(Status.INFO, "Browser Launched successfully");
			addScreenShot("Browser Launched successfully", test, Capture);

			System.out.println("login to flex PLM application successfully");
			test.log(Status.INFO, "login to flex PLM application successfully: URL " + GarmentDeveloper_URL);
			addScreenShot("Login successful", test, Capture);

			dashboardPage.openLeftPanel();
			System.out.println("Clicked on open Left panel");
			test.log(Status.INFO, "Clicked on open Left panel");
			addScreenShot("Clicked on open Left panel", test, Capture);

			mainMenuPage.clickOnMySeasons();
			test.log(Status.INFO, "Clicked on MySeasons");
			addScreenShot("Clicked on Main menu of My Seasons", test, Capture);

//			String season = "Levi's S1 2025 Female Accessories";
			mainMenuPage.chooseMySeasonType(season);
			System.out.println("season type is choosen");
			test.log(Status.INFO, "season type is choosen: " + season);
			addScreenShot("Season type is choosen", test, Capture);

			lineSheetPage.selectLineSheet(MainMenuEnum.SESSION_LINE_SHEET.menu());
			test.log(Status.INFO, "Clicked on Line Sheets");
			addScreenShot("Clicked on Line Sheets", test, Capture);

//			String productName = "2706LFA";
			lineSheetPage.filterProductByName(productName, test);
			System.out.println("Clicked on product name");
			test.log(Status.INFO, "Clicked on product name: " + productName);
			addScreenShot("Clicked on product name", test, Capture);
			WaitforPage(4000);

			Assert.assertTrue(productPage.isPC5DetailsPageDisplayed(test), "PC5 Details Page is not displayed.");
			System.out.println("PC5 Details Page is displayed");
			test.log(Status.PASS, "PC5 Details Page is displayed: " + productName);
			addScreenShot("PC5 Details Page is displayed", test, Capture);
			WaitforPage(4000);

			MeasurementsPage.clickDetailsTab();
			test.log(Status.INFO, "Clicked on Details tab");
			addScreenShot("Clicked on Details tab", test, Capture);
			WaitforPage(4000);

//			String specification = "S1 2025 - 0WP6Y - 2706LFA -";
			MeasurementsPage.selectSpecifications(specification, test);
			System.out.println("Selected specifications " + specification);
			test.log(Status.INFO, "Selected specification " + specification);
			addScreenShot("Selected specification " + specification, test, Capture);
			WaitforPage(4000);

			MeasurementsPage.NavigateTo_measurement();
			test.log(Status.INFO, "Navigated to measurement page ");
			System.out.println("Navigated to measurement page  ");
			addScreenShot("Navigated to measurement page  ", test, Capture);
			WaitforPage(4000);

//			String measurementSet = "003 : Test_auto_01";
			MeasurementsPage.selectMeasurementSet(measurementSet);
			test.log(Status.INFO, "Selected measurement set: " + measurementSet);
			addScreenShot("Selected measurement set: " + measurementSet, test, Capture);
			WaitforPage(4000);

			MeasurementsPage.clickUpdate();
			test.log(Status.INFO, "Clicked on Update");
			addScreenShot("Clicked on Update", test, Capture);
			WaitforPage(4000);

			MeasurementsPage.clickedOnGradeRuleTab();
			test.log(Status.INFO, "Clicked on Grade Rule Tab");
			addScreenShot("Clicked on Grade Rule Tab", test, Capture);
			WaitforPage(4000);

//			String newSize = "XP";
			MeasurementsPage.enterNewSize(newSize);
			test.log(Status.INFO, "Entered new size: " + newSize);
			addScreenShot("Entered new size: " + newSize, test, Capture);
			WaitforPage(4000);

			MeasurementsPage.clickAddButton();
			test.log(Status.INFO, "Clicked on Add button");
			addScreenShot("Clicked on Add button", test, Capture);
			WaitforPage(4000);

			Assert.assertTrue(MeasurementsPage.isNewSizeAdded(newSize), "New size was not added correctly.");
			test.log(Status.PASS, "New size added correctly: " + newSize);
			addScreenShot("New size added correctly", test, Capture);
			WaitforPage(4000);

			MeasurementsPage.clickSaveAndCheckIn();
			test.log(Status.INFO, "Clicked on Save & Check In");
			addScreenShot("Clicked on Save & Check In", test, Capture);
			WaitforPage(4000);

			Assert.assertTrue(MeasurementsPage.areSizesVisibleWithNewSize(newSize, test),
					"New size is not visible along with existing sizes.");
			test.log(Status.PASS, "New size is visible along with existing sizes: " + newSize);
			addScreenShot("New size is visible along with existing sizes", test, Capture);

			WaitforPage(4000);
			dashboardPage.closeLeftPanel();
			System.out.println("Clicked on close Left panel");
			test.log(Status.INFO, "Clicked on close Left panel");
			addScreenShot("Clicked on close Left panel", test, Capture);

			dashboardPage.Logout();
			System.out.println("Logout successful");
			test.log(Status.INFO, "Logout successful");
			addScreenShot("Logout successful", test, Capture);

		} catch (Exception e) {
			System.out.println("Test case failed due to application slowness" + e);
		test.log(Status.FAIL, "Test case failed due to application slowness");
		throw e;

	@AfterMethod
	public void tearDown() {
		extent.flush();
		driver.quit();
	}
}

//MeasurementsPage1.clickChooseFile();
//test.log(Status.INFO, "Clicked on Choose File");
//addScreenShot("Clicked on Choose File", test, Capture);
