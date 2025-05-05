package com.WMS_Utilities;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.*;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.*;

public class WMS_TestBase implements WMS_GlobalProperties {
    public WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;
    private static boolean isSuiteRun = false;
    private static boolean isReportInitialized = false;
    private static String cachedReportName;
    private static ExtentHtmlReporter htmlReporter;
//     ====== SUITE LEVEL SETUP ======
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        isSuiteRun = true;
        extent = ExtentManager.getInstance("SuiteReport");
    }
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        if (isSuiteRun) {
            ExtentManager.flushReport();
        }
    }

    // ====== BROWSER SETUP ======
    public WebDriver invokeBrowser() {
        System.setProperty(CHROME_KEY, CHROME_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }
    // ====== URL METHODS ======
    public void launchUrl() {
        driver.get(URL);
    }
    public void Administratorlaunchurl() {
        driver.get(Administrator_URL);
    }
    public void LaunchSpecific_URL(String url) {
        driver.get(url);
    }
    public void URLTimeStamp() {
        driver.get(URLTimeScreenSots);
    }
    // ====== TAB/WINDOW HANDLING ======
    public void openNewWindow() {
        String newTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
driver.findElement(By.id("open-tab")).sendKeys(newTab);
    }
    public void openNewTabAndSwitch() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }
    public void switchToFirstTabAndCloseSecond() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)).close();
        driver.switchTo().window(tabs.get(0));
    }
    // ====== WEBTABLE UTILITY ======
    public List<HashMap<String, String>> readWebTable(WebElement headerTable, WebElement dataTable) {
        List<HashMap<String, String>> data_WebTable = new ArrayList<>();
        List<WebElement> headerFields = headerTable.findElements(By.xpath("./datatable-header-cell/div/span/span"));
        List<WebElement> dataRows = dataTable.findElements(By.xpath(
                "./datatable-row-wrapper/datatable-body-row/div[contains(@class,'datatable-row-center')]"));
        for (WebElement row : dataRows) {
            HashMap<String, String> dataRow = new HashMap<>();
            List<WebElement> dataFields = row.findElements(By.xpath("./datatable-body-cell/div/div"));
            if (headerFields.size() == dataFields.size()) {
                for (int i = 0; i < headerFields.size(); i++) {
                    String key = headerFields.get(i).getAttribute("innerText").trim();
                    String value = dataFields.get(i).getAttribute("innerText").trim();
                    dataRow.put(key, value);
                    System.out.println(key + ": " + value);
                }
                System.out.println("-------------------");
                data_WebTable.add(dataRow);
            }
        }
        return data_WebTable;
    }
    public String returnFieldValueForTable(List<HashMap<String, String>> data_ItemTable, String key, String field) {
        for (HashMap<String, String> row : data_ItemTable) {
            if (row.containsValue(key) && row.containsKey(field)) {
                return row.get(field);
            }
        }
        return null;
    }
    public WebElement selectSingleRow(String key) {
        String xpath = "//*[contains(text(),'" + key + "')]/parent::div/parent::datatable-body-cell" +
                "/parent::div/parent::datatable-body-row/div[@class='datatable-row-group datatable-row-left ng-star-inserted']" +
                "/datatable-body-cell/div/label/input";
        return driver.findElement(By.xpath(xpath));
    }
    public String getUserInputFieldData(String specs, String fieldName) {
        for (String fieldValue : specs.split("-")) {
            String[] parts = fieldValue.split(":");
            if (parts[0].equals(fieldName)) return parts[1];
        }
        return null;
    }
    public void verifyCompleteSpecifics(ExtentTest testParam, List<HashMap<String, String>> tableData, String specs, String key) {
        boolean found = false;
        for (HashMap<String, String> row : tableData) {
            if (row.containsValue(key)) {
                testParam.log(Status.PASS, "Found Key " + key);
                found = true;
                for (String fieldValue : specs.split("-")) {
                    String[] parts = fieldValue.split(":");
                    String field = parts[0];
                    String expected = parts[1];
                    if (row.containsKey(field)) {
                        String actual = row.get(field);
                        if (actual.equals(expected)) {
                            testParam.log(Status.PASS, "Field " + field + " matches: " + actual);
                        } else {
                            testParam.log(Status.FAIL, "Field " + field + " mismatch. Expected: " + expected + ", Found: " + actual);
                        }
                    } else {
                        testParam.log(Status.INFO, "Field " + field + " does not exist.");
                    }
                }
                break;
            }
        }
        if (!found) {
            testParam.log(Status.FAIL, "Key " + key + " not found in table.");
        }
    }
 
    // ====== REPORT & SCREENSHOT ======
    public void setReport(String reportName) throws InterruptedException {
        if (!isReportInitialized) {
            cachedReportName = reportName + autoGenSerialNo() + ".html";
            htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Reports\\" + cachedReportName);
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            htmlReporter.config().setDocumentTitle("PLM Automation");
            htmlReporter.config().setReportName("PLM Automation-Test Report");
            htmlReporter.config().setTheme(Theme.STANDARD);
            isReportInitialized = true;
        }
    }
    public void addScreenShot(String testName, ExtentTest testParam, boolean capture) {
        if (capture) {
            try {
                WMS_WebDriverUtilities utils = new WMS_WebDriverUtilities(driver);
                String screenshotPath = utils.takeScreenshot(testName);
                testParam.addScreenCaptureFromPath(screenshotPath);
                System.out.println("Execution Time: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) + "0");
            } catch (Exception e) {
                System.out.println("Screenshot capture failed.");
            }
        }
    }
    // ====== UTILITIES ======
    public void WaitforPage(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }
    public String autoGenSerialNo() throws InterruptedException {
        WaitforPage(4000);
        String serial = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        System.out.println("Test case Number: " + serial);
        WaitforPage(4000);
        return serial;
    }
}