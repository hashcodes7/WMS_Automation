package TestData_Testcases;

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
import com.WMS_Utilities.WMS_TestBase;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.DataProviders;

@Test(enabled = true, groups= {"P3_TC"})
public class TC01_P3_Check_MeasurementType extends WMS_TestBase {

	WebDriver driver;
	DashboardPage dashboardPage;
	MainMenuPage mainMenuPage;
	MaterialPage materialPage;
	Color_Page color_Page;
	ProductDetailsPage productDetailsPage;
	LineSheetPage lineSheetPage;
	Measurements_Page MeasurementsPage;

	boolean Capture = true;
	private String garmentDeveloper_URL;

	/**
	 * Note: Ensure you are logged in as a Garment Developer user before executing
	 * this test case
	 */

	@BeforeMethod
	public void setUp() throws InterruptedException {

			driver = invokeBrowser();
			garmentDeveloper_URL = GarmentDeveloper_URL;
			LaunchSpecific_URL(garmentDeveloper_URL);
			dashboardPage = new DashboardPage(driver);
			mainMenuPage = new MainMenuPage(driver);
			materialPage = new MaterialPage(driver);
			color_Page = new Color_Page(driver);
			lineSheetPage = new LineSheetPage(driver);
			productDetailsPage = new ProductDetailsPage(driver);
			MeasurementsPage = new Measurements_Page(driver);

			setReport("TC01_P3_Check_MeasurementType");
		}
	}

	@Test(priority = 0, dataProvider = "TC01_P3_Check_MeasurementType", dataProviderClass = DataProviders.class)
	public void TC41_P3_Check_MeasurementType(String TestType, String product, String season, String source,
			String specification, String measurementSet) throws Exception {

			test = extent.createTest(":::TC01_P3_Check_MeasurementType:::");
		}



		try {

			test.log(Status.INFO,"This test case covers TestData_Scripts(P3) Quonda 'Tc_001_Measurement Sets in PLM Environment' ");

			System.out.println("Browser Launched successfully");
			test.log(Status.INFO, "Browser Launched successfully");
			addScreenShot("Browser Launched successfully", test, Capture);

			System.out.println("login to flex PLM application successfully");
			test.log(Status.INFO, "login to flex PLM application successfully: URL " + garmentDeveloper_URL);
			addScreenShot("Login successful", test, Capture);

			dashboardPage.openLeftPanel();
			System.out.println("Clicked on open Left panel");
			test.log(Status.INFO, "Clicked on open Left panel");
			addScreenShot("Clicked on open Left panel", test, Capture);

			mainMenuPage.openSubMenu1(MainMenuEnum.LIBRARIES.menu(), MainMenuEnum.LIBRARIES_PRODUCT.menu(), true);
			addScreenShot("Clicked on Product Under Main menu of Libraries", test, Capture);
			System.out.println("Clicked on Product Under Main menu of Libraries ");
			test.log(Status.INFO, "Clicked on Product Under Main menu of Libraries ");
			WaitforPage(4000);

//			String product = "2706LFA";
			MeasurementsPage.selectProduct(product);
			test.log(Status.INFO, "Product selected: " + product);
			addScreenShot("Product selected: " + product, test, Capture);

			MeasurementsPage.clickDetailsTab();
			test.log(Status.INFO, "Clicked on Details tab");
			addScreenShot("Clicked on Details tab", test, Capture);

//			String season = "Levi's S1 2025 Female Accessories";
			MeasurementsPage.selectSeason(season, test);
			System.out.println("Selected season " + season);
			test.log(Status.INFO, "Selected season " + season);
			addScreenShot("Selected season " + season, test, Capture);

//			String source = "Primary";
			MeasurementsPage.select_Source(source, test);
			System.out.println("Selected Primary source " + source);
			test.log(Status.INFO, "Selected Primary source " + source);
			addScreenShot("Selected Primary source " + source, test, Capture);

//			String specification = "S1 2025 - 0WP6Y - 2706LFA -";
			MeasurementsPage.selectSpecifications(specification, test);
			System.out.println("Selected specifications " + specification);
			test.log(Status.INFO, "Selected specification " + specification);
			addScreenShot("Selected specification " + specification, test, Capture);

			MeasurementsPage.NavigateTo_measurement();
			test.log(Status.INFO, "Navigated to measurement page ");
			System.out.println("Navigated to measurement page  ");
			addScreenShot("Navigated to measurement page  ", test, Capture);

//			String measurementSet = "004 : Test_auto_05";
			MeasurementsPage.selectMeasurementSet(measurementSet);
			test.log(Status.INFO, "Selected measurement set: " + measurementSet);
			addScreenShot("Selected measurement set: " + measurementSet, test, Capture);

			// Check the measurement type
			String measurementType = MeasurementsPage.getMeasurementType();
			test.log(Status.INFO, "Measurement type: " + measurementType);
			addScreenShot("Measurement type: " + measurementType, test, Capture);

			// Validate the measurement type is not empty
			if (measurementType == null || measurementType.isEmpty()) {
				test.log(Status.FAIL, "Measurement type is empty");
				addScreenShot("Measurement type is empty", test, Capture);
				throw new AssertionError("Measurement type should not be empty");
			} else {
				test.log(Status.PASS, "Measurement type is not empty: " + measurementType);
				addScreenShot("Measurement type is not empty: " + measurementType, test, Capture);
			}

			// Wait and close left panel
			WaitforPage(4000);
			dashboardPage.closeLeftPanel();
			System.out.println("Clicked on close Left panel");
			test.log(Status.INFO, "Clicked on close Left panel");
			addScreenShot("Clicked on close Left panel", test, Capture);

			// Logout
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
