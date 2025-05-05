package E2E_Testcases;
 
import java.util.HashMap;
import java.util.List;
 
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
 
import com.WMS_ApplicationPages.DashboardPage;
import com.WMS_ApplicationPages.E2E_Pages;
import com.WMS_ApplicationPages.LineSheetPage;
import com.WMS_ApplicationPages.LineSheet_Edit_Page;
import com.WMS_ApplicationPages.MainMenuEnum;
import com.WMS_ApplicationPages.MainMenuPage;
import com.WMS_ApplicationPages.Techpack_pages;
import com.WMS_Utilities.WMS_TestBase;
import com.aventstack.extentreports.Status;
 
import Test_Rail.Test_Rail_Actions;
import resources.DataProviders;
 
@Test(enabled = true, groups = { "E2E_TC" })
public class TC286_P0_E2E_caryyover_PC5_PC9 extends WMS_TestBase {
 
    WebDriver driver;
    DashboardPage dashboardPage;
    MainMenuPage mainMenuPage;
    LineSheetPage lineSheetPage;
    LineSheet_Edit_Page lineSheetEditPage;
    Techpack_pages techpackPages;
    E2E_Pages e2ePages;
    boolean capture = true;
    public Test_Rail_Actions testActions = new Test_Rail_Actions();
    public static XSSFSheet templatesheet = null;
    List<HashMap<String, String>> baseTemplate = null;
 
    @BeforeMethod
    public void setUp() throws InterruptedException {
        driver = invokeBrowser();
        LaunchSpecific_URL(Global_URL);
        dashboardPage = new DashboardPage(driver);
        mainMenuPage = new MainMenuPage(driver);
        lineSheetPage = new LineSheetPage(driver);
        lineSheetEditPage = new LineSheet_Edit_Page(driver);
        techpackPages = new Techpack_pages(driver);
        e2ePages = new E2E_Pages(driver);
        setReport("TC286_P0_E2E Carryover PC5 and PC9");
    }
 
    @Test(priority = 0, dataProvider = "TC286_P0_E2E_caryyover_PC5_PC9", dataProviderClass = DataProviders.class)
    public void P0_TC_286_Carryover_PC5(String testType, String to_season, String from_season, String linesheetview, String productname, String colorwayname) throws Exception {
 
        test = extent.createTest("::: TC286_P0_E2E Carryover PC5 and PC9 :::");
 
        long startTime = System.nanoTime();
 
        try {
            test.log(Status.INFO, "This testcase covers TC_286");
            test.log(Status.INFO, "Browser Launched successfully");
            test.log(Status.INFO, "Login to Flex PLM application successfully: " + URL);
 
            dashboardPage.openLeftPanel();
            test.log(Status.INFO, "Left panel opened");
            addScreenShot("Left panel opened", test, capture);
 
            mainMenuPage.ClickSeasonMenu(MainMenuEnum.SESSION.menu());
            test.log(Status.INFO, "My Seasons menu clicked");
            addScreenShot("Clicked on My Seasons", test, capture);
 
            lineSheetEditPage.SeasonDropdown(to_season);
            test.log(Status.INFO, "Season selected: " + to_season);
            addScreenShot("Season selected: " + to_season, test, capture);
 
            lineSheetPage.selectLineSheet(MainMenuEnum.SESSION_LINE_SHEET.menu());
            test.log(Status.INFO, "Clicked on Line Sheets");
            addScreenShot("Clicked on Line Sheets", test, capture);
 
            WaitforPage(5000);
 
            lineSheetEditPage.filter_View_Change(linesheetview, test);
            test.log(Status.INFO, "Linesheet view changed to: " + linesheetview);
            addScreenShot("Linesheet view changed", test, capture);
 
            Thread.sleep(5000);
 
            // Carryover Product
            e2ePages.Select_CarryOverProduct();
            test.log(Status.INFO, "Selected carryover products menu");
            addScreenShot("Selected carryover products menu", test, capture);
 
            e2ePages.SelectInitialSeason(from_season);
            test.log(Status.INFO, "Initial season selected: " + from_season);
            addScreenShot("Initial season selected: " + from_season, test, capture);
 
            techpackPages.filterdataa(productname);
            test.log(Status.INFO, "Product filtered: " + productname);
            addScreenShot("Product filtered: " + productname, test, capture);
 
            e2ePages.SelectCarryoverProduct();
            test.log(Status.PASS, "Selected product for carryover completed");
            addScreenShot("Product carryover completed", test, capture);
 
            // Carryover Colorway
            e2ePages.Select_CarryOvercolorways();
            test.log(Status.INFO, "Selected carryover colorway menu");
            addScreenShot("Selected carryover colorway menu", test, capture);
 
            e2ePages.SelectInitialSeason(from_season);
            test.log(Status.INFO, "Initial season selected for colorway: " + from_season);
            addScreenShot("Initial season for colorway: " + from_season, test, capture);
 
            techpackPages.filterdataa(colorwayname);
            test.log(Status.INFO, "Colorway filtered: " + colorwayname);
            addScreenShot("Colorway filtered: " + colorwayname, test, capture);
 
            e2ePages.SelectCarryovercolorway();
            test.log(Status.PASS, "Colorway carryover completed");
            addScreenShot("Colorway carryover completed", test, capture);
 
            // Validate PC5 & PC9
            Thread.sleep(3000);
            e2ePages.validate_CarriedOver_PC5_PC9(productname, colorwayname);
            test.log(Status.PASS, "Validated PC5 and PC9 carried over");
            addScreenShot("PC5 and PC9 validated", test, capture);
 
            dashboardPage.closeLeftPanel();
            test.log(Status.INFO, "Closed Left Panel");
            addScreenShot("Closed Left Panel", test, capture);
 
            dashboardPage.Logout();
            test.log(Status.INFO, "Logout successful");
            addScreenShot("Logout successful", test, capture);
 
        } catch (Exception e) {
            test.log(Status.FAIL, "Test case failed due to exception: " + e.getMessage());
            addScreenShot("Test case failure", test, capture);
            throw e;
        }
    }
 
    @AfterMethod
    public void tearDown() {
        extent.flush();
        driver.quit();
    }
}