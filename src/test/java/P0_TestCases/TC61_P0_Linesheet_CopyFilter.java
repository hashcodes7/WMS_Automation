package P0_TestCases;

import java.awt.AWTException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.WMS_ApplicationPages.ColorWayPage;
import com.WMS_ApplicationPages.CreateNewProductPage;
import com.WMS_ApplicationPages.DashboardPage;
import com.WMS_ApplicationPages.LSEProductSegmentationPage;
import com.WMS_ApplicationPages.LineSheetPage;
import com.WMS_ApplicationPages.MainMenuEnum;
import com.WMS_ApplicationPages.MainMenuPage;

import com.WMS_ApplicationPages.Palette_Page;
import com.WMS_ApplicationPages.PopUpPage;
import com.WMS_ApplicationPages.ProductDetailsPage;

import com.WMS_ApplicationPages.ProductPage;
import com.WMS_ApplicationPages.SeasonPage;
import com.WMS_Utilities.WMS_TestBase;
import com.WMS_Utilities.WMS_WebDriverUtilities;
import com.aventstack.extentreports.Status;

import Test_Rail.Test_Rail_Actions;
import resources.DataProviders;

@Test(enabled = true, groups= {"P0_TC"})
public class TC61_P0_Linesheet_CopyFilter extends WMS_TestBase {

	WebDriver driver;
	DashboardPage dashboardPage;
	MainMenuPage mainMenuPage;
	SeasonPage seasonPage;
	Palette_Page palettePage;

	ProductPage productPage;
	LineSheetPage lineSheetPage;
	CreateNewProductPage createNewProductPage;
	ProductDetailsPage productDetailsPage;
	LSEProductSegmentationPage lseProductSegmentationPage;
	PopUpPage popUpPage;
	ColorWayPage colorWayPage;

	boolean Capture = true;
	public Test_Rail_Actions testactions = new Test_Rail_Actions();

	List<HashMap<String, String>> data_ItemTable = null;

	String batchNo;
	public static XSSFSheet templatesheet = null;
	List<HashMap<String, String>> BaseTemplate = null;

	@BeforeMethod
	public void setUp() throws InterruptedException {

			driver = invokeBrowser();
			LaunchSpecific_URL(Administrator_URL);
			dashboardPage = new DashboardPage(driver);
			mainMenuPage = new MainMenuPage(driver);
			seasonPage = new SeasonPage(driver);
			palettePage = new Palette_Page(driver);

			productPage = new ProductPage(driver);
			lineSheetPage = new LineSheetPage(driver);
			createNewProductPage = new CreateNewProductPage(driver);
			productDetailsPage = new ProductDetailsPage(driver);
			lseProductSegmentationPage = new LSEProductSegmentationPage(driver);
			popUpPage = new PopUpPage(driver);
			colorWayPage = new ColorWayPage(driver);

			setReport("TC61_P0_Linesheet_Filter Copy Verification");
		}
	}

	@Test(priority = 0, dataProvider = "linesheetCopyFilterData", dataProviderClass = DataProviders.class)
	public void P0_TC_061_Filters_Verify_Filters_Copy(String TestType, String mySeasonType, String filterName, String copyFilterName) throws Exception {



			test = extent.createTest(":::TC61_P0_Linesheet_Filter Copy Verification:::");

		}



		// ...............................browser launched time starts

		long startTime = System.nanoTime();
		try {

			System.out.println("Browser Launched successfully");
			test.log(Status.INFO, "Browser Launched successfully");

			test.log(Status.INFO, "This test case covers Linesheet- Views & Filters module from 123 to 125");

			System.out.println("login to flex PLM application successfully");
			test.log(Status.INFO, "login to flex PLM application successfully: " + Administrator_URL);

			Thread.sleep(5000);

			dashboardPage.openLeftPanel();
			System.out.println("Clicked on open Left plane");
			test.log(Status.INFO, "Clicked on open Left plane");
			addScreenShot("Clicked on open Left plane", test, Capture);

			mainMenuPage.clickOnMySeasons();
			test.log(Status.INFO, "Clicked on MySeasons");
			addScreenShot("Clicked on Main menu of My Seasons", test, Capture);

//			String mySeasonType = "Levi's S1 2025 Female Accessories";
			mainMenuPage.chooseMySeasonType(mySeasonType);
			System.out.println("season type is choosen");
			test.log(Status.INFO, "season type is choosen: " + mySeasonType);
			addScreenShot("Season type is choosen", test, Capture);

			lineSheetPage.selectLineSheet(MainMenuEnum.SESSION_LINE_SHEET.menu());
			test.log(Status.INFO, "Clicked on Line Sheets");
			addScreenShot("Clicked on Line Sheets", test, Capture);

//			String filterName = "TestFilter two";
			lineSheetPage.chooseFilter(filterName,test);
			System.out.println("filter was choosen");
			test.log(Status.INFO, "filter was choosen: " + filterName);
			addScreenShot("filter was choosen", test, Capture);

			lineSheetPage.clickOnEditIcon(test);
			System.out.println("clicked on edit Icon");
			test.log(Status.INFO, "clicked on edit Icon");
			addScreenShot("clicked on edit Icon", test, Capture);

			String currentFilterAttributes = lineSheetPage.getCurrentFilterAttribues(test);
			System.out.println(currentFilterAttributes);

			lineSheetPage.clickCopyFilterButton(test);
			System.out.println("Clicked On Copy filter button");
			test.log(Status.INFO, "Clicked On Copy button");
			addScreenShot("Clicked On Copy button", test, Capture);

//			String copyFilterName = "CopiedTestFilterTest4";
			lineSheetPage.enterNewFilterName(copyFilterName,test);
			System.out.println("Entered New filter name");
			test.log(Status.INFO, "Entered New filter name: " + copyFilterName);
			addScreenShot("Entered New filter name", test, Capture);

			lineSheetPage.saveCopiedFilter(test);
			System.out.println("Clicked On Save Copied Filter ");
			test.log(Status.INFO, "Clicked On Save Copied Filter");
			addScreenShot("Clicked On Save Copied Filter", test, Capture);

			String copyfilterAttributes = lineSheetPage.getcopyFilterAttributes(test);
			System.out.println(copyfilterAttributes);

			lineSheetPage.clickUpdateButton(test);
			test.log(Status.INFO, "Clicked on Update Button");
			addScreenShot("Clicked on Update Button", test, Capture);

			lineSheetPage.clickReturnButton(test);
			test.log(Status.INFO, "Clicked on Return Button");
			addScreenShot("Clicked on Return Button", test, Capture);

			lineSheetPage.selectLineSheet(MainMenuEnum.SESSION_LINE_SHEET.menu());
			test.log(Status.INFO, "Clicked on Line Sheets");
			addScreenShot("Clicked on Line Sheets", test, Capture);

			lineSheetPage.clickFiltersDropdown(test);
			test.log(Status.INFO, "Clicked on Filters dropdown");
			addScreenShot("Clicked on Filters dropdown", test, Capture);

			// Verify filter appears in search criteria
			boolean isFilterPresent = lineSheetPage.isCopyFilterPresentInSearchCriteria(copyFilterName,test);
			Assert.assertTrue(isFilterPresent, "Copy Filter is not present in search criteria");
			test.log(Status.PASS, "Verified Copy filter appears in search criteria: " + copyFilterName);
			addScreenShot("Verified Copy filter appears in search criteria", test, Capture);

			Assert.assertEquals(copyfilterAttributes, currentFilterAttributes, "Filter was not copied successfully");
			System.out.println("Filter was copied successfully ");
			test.log(Status.PASS, "Filter was copied successfully");
			
			dashboardPage.closeLeftPanel();
			System.out.println("Clicked on close Left plane");
			test.log(Status.INFO, "Clicked on close Left plane");
			addScreenShot("Clicked on close Left plane", test, Capture);

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
