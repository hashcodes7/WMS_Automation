package P2_TestCases;

import java.awt.AWTException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.WMS_ApplicationPages.Color_Page;
import com.WMS_ApplicationPages.DashboardPage;
import com.WMS_ApplicationPages.MainMenuEnum;
import com.WMS_ApplicationPages.MainMenuPage;
import com.WMS_ApplicationPages.MaterialPage;
import com.WMS_ApplicationPages.VendorPortalPage;
import com.WMS_Utilities.WMS_TestBase;
import com.aventstack.extentreports.Status;

import Test_Rail.Test_Rail_Actions;
import resources.DataProviders;

@Test(enabled = true, groups= {"P2_TC"})
public class TC322_P2_VendorPortalAccess_DocumentSubTypeImagesVendorImages extends WMS_TestBase {
	WebDriver driver;
	DashboardPage dashboardPage;
	MainMenuPage mainMenuPage;
	MaterialPage materialPage;
	Color_Page color_Page;
	VendorPortalPage vendorPage;

	boolean Capture = true;
	public Test_Rail_Actions testactions = new Test_Rail_Actions();
	List<HashMap<String, String>> data_ItemTable = null;

	String batchNo;
	public static XSSFSheet templatesheet = null;
	List<HashMap<String, String>> BaseTemplate = null;

	/**
	 * Note: Ensure you are logged in as a Vendor user before executing this test case
	 */
	@BeforeMethod
	public void setUp() throws InterruptedException {

			driver = invokeBrowser();
			LaunchSpecific_URL(VendorPortal_URl);
			dashboardPage = new DashboardPage(driver);
			mainMenuPage = new MainMenuPage(driver);
			materialPage = new MaterialPage(driver);
			color_Page = new Color_Page(driver);
			vendorPage = new VendorPortalPage(driver);

			setReport(
					"TC322_P2_Vendor Portal Access to Document Sub type Images Vendor Images And Verify all the Image Type list values under Document sub type Images Vendor Images");
		}
	}

	@Test(priority = 0, dataProvider = "TC322_P2_VendorPortalAccess_DocumentSubTypeImagesVendorImages", dataProviderClass = DataProviders.class)
	public void P2_TC322_VendorPortalAccess_DocumentSubTypeImagesVendorImages(String TestType, String documentType,
			String vendorDOCName, String newVendorName, String newImgLayout, String newImgDescription,
			String newImgType) throws Exception {

			test = extent.createTest(
					":::TC322_P2_Vendor Portal Access to Document Sub type Images Vendor Images And Verify all the Image Type list values under Document sub type Images Vendor Images:::");
		}


		// ...............................browser launched time starts

		long startTime = System.nanoTime();

		try {

			test.log(Status.INFO, "This test case covers P2 Vendor Portal module TC322");

			System.out.println("Browser Launched successfully");
			test.log(Status.INFO, "Browser Launched successfully");
			addScreenShot("Browser Launched successfully", test, Capture);

			System.out.println("login to flex PLM application successfully");
			test.log(Status.INFO, "login to flex PLM application successfully: URL - " + VendorPortal_URl);
			addScreenShot("login successful", test, Capture);

			System.out.println("login successful");
			test.log(Status.INFO, "login successful");

			dashboardPage.openLeftPanel();
			System.out.println("Clicked on open Left plane");
			test.log(Status.INFO, "Clicked on open Left plane");
			addScreenShot("Clicked on open Left plane", test, Capture);
			WaitforPage(4000);
			
			mainMenuPage.selectsubmenu(MainMenuEnum.LIBRARIES_RETAILDOCUMENT.menu());
			System.out.println("Clicked on Retail Document Under Material");
			test.log(Status.INFO, "Clicked on Retail Document Under Material");
			addScreenShot("Clicked on Retail Document Under Material", test, Capture);
			WaitforPage(5000);

//			String documentType = "Vendor Images";
			vendorPage.selectvendorDocumentsAndclickOnSearch(documentType, test);
			test.log(Status.INFO, "sub-type Images Vendor Images choosen");
			addScreenShot("sub-type Images Vendor Images choosen", test, Capture);

//			String vendorDOCName = "Commercial Sample Image - TEST_AUTO_IMG	";
			vendorPage.clickOnDocumentName(vendorDOCName);
			test.log(Status.INFO, "Clicked on Document Name");
			addScreenShot("Clicked on Document Name", test, Capture);

			vendorPage.clickOnUpdateInActionsMenu();
			test.log(Status.INFO, "Clicked on Update in Actions menu");
			addScreenShot("Clicked on Update in Actions menu", test, Capture);

//			String newVendorName = "ARVIND LIMITED 5151 IN";
//			String newImgLayout = "1x1";
//			String newImgDescription = "TEST_AUTO_Update_IMG_01";
//			String newImgType = "Commercial Sample Image";

			vendorPage.updateAttributesForVendorImgDocumnet(newVendorName, newImgLayout, newImgDescription, newImgType,
					test);
			test.log(Status.INFO, "Updated all the required attributes");
			addScreenShot("Updated all the required attributes", test, Capture);

			vendorPage.verifyListOfValuesInImgTypeDropdown(test);

			WaitforPage(2000);
			vendorPage.clickOnSaveButton();
			test.log(Status.INFO, "Clicked on Save button");
			addScreenShot("Clicked on Save button", test, Capture);

			vendorPage.verifyUpdated_Attributes(newVendorName, newImgLayout, newImgDescription, newImgType);
			test.log(Status.PASS, "Verified all the updated attributes on the Document details page");
			addScreenShot("Verified all the updated attributes on the Document details page", test, Capture);

			WaitforPage(4000);
			dashboardPage.closeLeftPanel();
			System.out.println("Clicked on close Left plane");
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
