package E2E_Testcases;
 
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
 
import com.WMS_ApplicationPages.CreateNewColorPage;
import com.WMS_ApplicationPages.DashboardPage;
import com.WMS_ApplicationPages.MainMenuEnum;
import com.WMS_ApplicationPages.MainMenuPage;
import com.WMS_ApplicationPages.Palette_Page;
import com.WMS_Utilities.WMS_TestBase;
import com.aventstack.extentreports.Status;
 
import Test_Rail.Test_Rail_Actions;
import resources.DataProviders;
 
@Test(enabled = true, groups = { "E2E_TC" })
public class easytest extends WMS_TestBase {
    
    WebDriver driver;
    DashboardPage dashboardPage;
    MainMenuPage mainMenuPage;
    Palette_Page palettepage;
    CreateNewColorPage CNCP;
    
    boolean Capture = true;
    public Test_Rail_Actions testactions = new Test_Rail_Actions();
 
    @BeforeMethod
    public void setUp() throws InterruptedException {
        driver = invokeBrowser();
LaunchSpecific_URL("https://wcadmin:wcadmindev12@flexplmdev3.levi.com/Windchill/rfa/jsp/main/Main.jsp");
        dashboardPage = new DashboardPage(driver);
        mainMenuPage = new MainMenuPage(driver);
        palettepage = new Palette_Page(driver);
        CNCP = new CreateNewColorPage(driver);
        setReport("easytest");
    }
 
    @Test(priority = 0, dataProvider = "TC292_P0_E2E_CreateColors_Solid", dataProviderClass = DataProviders.class)
    public void P0_TC_292_E2E_CreateColors_Solid(String TestType, String colortype, String redvalue, String bluevalue, String greenvalue,
                                                 String colorfamily, String standardprovider, String colorcode, String colorname,
                                                 String providercolorname) throws Exception {
        test = extent.createTest(":::easytest :::");
 
        long startTime = System.nanoTime();
 
        try {
            test.log(Status.INFO, "This Testcase covers TC_292");
            test.log(Status.INFO, "Browser Launched successfully");
            test.log(Status.INFO, "Login to Flex PLM application successfully: " + URL);
 
            dashboardPage.openLeftPanel();
            test.log(Status.INFO, "Left panel opened");
            addScreenShot("Left panel opened", test, Capture);
 
mainMenuPage.libraryColurmenu(MainMenuEnum.LIBRARIES.menu(), MainMenuEnum.LIBRARIES_COLOR.menu());
            test.log(Status.INFO, "In libraries Color/Look menu clicked");
            addScreenShot("In libraries Color/Look menu clicked", test, Capture);
 
            Thread.sleep(5000);
 
            CNCP.clickplussign();
            test.log(Status.INFO, "Clicked on Add color sign");
            addScreenShot("Clicked on Add color sign", test, Capture);
 
            CNCP.SelectColorType(colortype, test);
            test.log(Status.INFO, "Clicked On: " + colortype + " and color page opened");
            addScreenShot("Clicked On: " + colortype + " and color page opened", test, Capture);            
 
            String title = driver.getTitle();
            Assert.assertTrue(title.equalsIgnoreCase("Create Color"));
            test.log(Status.PASS, "Create color page opened");
 
            CNCP.EnterSolidColors_Values(redvalue, bluevalue, greenvalue, colorfamily, standardprovider, colorcode,
                                          colorname, providercolorname, test);
            test.log(Status.INFO, "All the required fields for seasonal color BFF are filled");
            addScreenShot("All the required fields for seasonal color BFF are filled", test, Capture);
 
            Thread.sleep(5000);
 
            CNCP.clickcreatebutton();
            test.log(Status.INFO, "Color is created");
            addScreenShot("Color is created", test, Capture);
 
            Thread.sleep(5000);
 
            CNCP.Validate_SolidColors(colorname, test);
            test.log(Status.PASS, "Validated fields in view color page");
            addScreenShot("Validated fields in view color page", test, Capture);
 
            dashboardPage.closeLeftPanel();
            test.log(Status.INFO, "Clicked on close Left panel");
            addScreenShot("Clicked on close Left panel", test, Capture);
 
            dashboardPage.Logout();
            test.log(Status.INFO, "Logout successful");
            addScreenShot("Logout successful", test, Capture);
 
        } catch (Exception e) {
            test.log(Status.FAIL, "Test case failed due to application issue: " + e.getMessage());
            addScreenShot("Failure occurred", test, true);
            throw e;
        }
    }
 
    @AfterMethod
    public void tearDown() {
        extent.flush();
        driver.quit();
    }
}