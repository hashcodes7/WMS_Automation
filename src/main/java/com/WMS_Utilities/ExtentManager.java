package com.WMS_Utilities;
 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
 
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
 
public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentHtmlReporter htmlReporter;
    private static boolean isInitialized = false;
 
    public static ExtentReports getInstance(String reportName) {
        if (!isInitialized) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fullPath = System.getProperty("user.dir") + "\\Reports\\" + reportName + "_" + timestamp + ".html";
 
            htmlReporter = new ExtentHtmlReporter(fullPath);
            htmlReporter.config().setDocumentTitle("PLM Automation");
            htmlReporter.config().setReportName("PLM Automation - Test Report");
            htmlReporter.config().setTheme(Theme.STANDARD);
 
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            isInitialized = true;
        }
        return extent;
    }
 
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
        isInitialized = false;
    }
}