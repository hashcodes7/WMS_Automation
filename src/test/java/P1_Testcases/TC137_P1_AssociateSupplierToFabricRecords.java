package P1_Testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.WMS_ApplicationPages.DashboardPage;
import com.WMS_ApplicationPages.MainMenuEnum;
import com.WMS_ApplicationPages.MainMenuPage;
import com.WMS_ApplicationPages.MaterialPage;
import com.WMS_Utilities.WMS_TestBase;
import com.aventstack.extentreports.Status;

import resources.DataProviders;

@Test(enabled = true, groups= {"P1_TC"})
public class TC137_P1_AssociateSupplierToFabricRecords extends WMS_TestBase {

	WebDriver driver;
	DashboardPage dashboardPage;
	MainMenuPage mainMenuPage;
	MaterialPage materialPage;

	boolean Capture = true;

	@BeforeMethod
	public void setUp() throws InterruptedException {

			driver = invokeBrowser();
			LaunchSpecific_URL(PDS_URL);
			dashboardPage = new DashboardPage(driver);
			mainMenuPage = new MainMenuPage(driver);
			materialPage = new MaterialPage(driver);
			setReport("TC137_P1_AssociateSupplierToFabricRecords verification");
		}
	}

	@Test(priority = 0, dataProvider = "associateSupplierData", dataProviderClass = DataProviders.class)
	public void P1_TC137_AssociateSupplierToFabricRecords(String TestType, String materialType, String materialName,
			String supplier) throws Exception {

			test = extent.createTest(":::TC137_P1_AssociateSupplierToFabricRecords verification:::");
		}



		try {

			test.log(Status.INFO, "This test case covers P1 material module from TC137 to TC142");

			System.out.println("Browser Launched successfully");
			test.log(Status.INFO, "Browser Launched successfully");
			addScreenShot("Browser Launched successfully", test, Capture);

			System.out.println("login to flex PLM application successfully");
			test.log(Status.INFO, "login to flex PLM application successfully: URL " + PDS_URL);
			addScreenShot("Login successful", test, Capture);

			dashboardPage.openLeftPanel();
			System.out.println("Clicked on open Left panel");
			test.log(Status.INFO, "Clicked on open Left panel");
			addScreenShot("Clicked on open Left panel", test, Capture);

			mainMenuPage.openSubMenu1(MainMenuEnum.LIBRARIES.menu(), MainMenuEnum.LIBRARIES_MATERIAL.menu(), true);
			addScreenShot("Clicked on Main menu of Libraries", test, Capture);
			System.out.println("Clicked on Material");
			test.log(Status.INFO, "Clicked on Material");
			WaitforPage(4000);

//			String materialType = "Knits";
			materialPage.selectMaterialType(materialType, test);
			System.out.println("Fabric Record Type Was choosen");
			test.log(Status.INFO, "Fabric Record Type Was choosen: " + materialType);
			addScreenShot("Fabric Record Type Was choosen", test, Capture);
			WaitforPage(4000);

//			String materialName = "FA771046 Demo_Fabric_Material_02";
			materialPage.searchMaterial(materialName, test);
			System.out.println("Searched for the Material");
			test.log(Status.INFO, "Searched for the Material: " + materialName);
			addScreenShot("Searched for the Material", test, Capture);
			WaitforPage(4000);

			materialPage.clickOnMaterial(materialName);
			System.out.println("Ciicked On material: " + materialName);
			test.log(Status.INFO, "Ciicked On material: " + materialName);
			addScreenShot("Ciicked On material: ", test, Capture);

//			String supplier = "3M INDUSTRIAL ADHESIVE & TAPES 603112 US";
			materialPage.addSupplier(supplier, test);
			System.out.println("Searched and selected supplier");
			test.log(Status.INFO, "Searched and selected supplier:: " + supplier);
			addScreenShot("Searched and selected supplier:", test, Capture);
			WaitforPage(5000);

			boolean isSupplierInDropdown = materialPage.verifySupplierInDropdown(supplier, test);
			if (isSupplierInDropdown) {
				System.out.println("Supplier appears in the dropdown menu for the material.");
				test.log(Status.PASS, "Verification: Supplier appears in the dropdown menu for the material.");
				addScreenShot("Supplier appears in the dropdown menu for the material", test, Capture);
			} else {
				System.out.println("Supplier does not appear in the dropdown menu for the material.");
				test.log(Status.FAIL, "Supplier does not appear in the dropdown menu for the material.");
				addScreenShot("Supplier does not appear in the dropdown menu for the material", test, Capture);
			}

			boolean isNoColorInDropdown = materialPage.verifyNoColorInDropdown(test);
			if (isNoColorInDropdown) {
				System.out.println("'No color' created under 'Colors' tab for the material-supplier combination");
				test.log(Status.PASS,
						"Verification: 'No color' created under 'Colors' tab for the material-supplier combination");
				addScreenShot("'No color' created under 'Colors' tab for the material-supplier combination", test,
						Capture);
			} else {
				System.out.println("'No color' does not appear in the Material color dropdown.");
				test.log(Status.FAIL, "'No color' does not appear in the Material color dropdown.");
				addScreenShot("'No color' does not appear in the Material color dropdown", test, Capture);
			}

			WaitforPage(4000);
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
