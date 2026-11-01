
package api.utilities;

// Extent Report 5.x

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Initialize Extent Report
    @Override
    public void onStart(ITestContext testContext) {

        String timeStamp = new SimpleDateFormat(
                "yyyy.MM.dd.HH.mm.ss").format(new Date());

        String reportName = "Test-Report-" + timeStamp + ".html";

        String reportPath = System.getProperty("user.dir")
                + "/reports/" + reportName;

        // Create reports folder if it doesn't exist
        File reportDir = new File(
                System.getProperty("user.dir") + "/reports");

        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        sparkReporter = new ExtentSparkReporter(reportPath);

        sparkReporter.config().setDocumentTitle(
                "RestAssuredAutomationProject");

        sparkReporter.config().setReportName(
                "Pet Store Users API");

        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();

        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "Pet Store Users API");
        extent.setSystemInfo("Operating System",
                System.getProperty("os.name"));
        extent.setSystemInfo("User Name",
                System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Framework", "REST Assured + TestNG");
    }

    // Create a test entry
    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest = extent.createTest(
                result.getName());

        extentTest.assignCategory(
                result.getMethod().getGroups());

        test.set(extentTest);
    }

    // Test Passed
    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().log(Status.PASS, "Test Passed");
    }

    // Test Failed
    @Override
    public void onTestFailure(ITestResult result) {

        test.get().log(Status.FAIL, "Test Failed");

        test.get().log(Status.FAIL,
                result.getThrowable());
    }

    // Test Skipped
    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().log(Status.SKIP, "Test Skipped");

        if (result.getThrowable() != null) {
            test.get().log(Status.SKIP,
                    result.getThrowable());
        }
    }

    // Finish execution and generate report
    @Override
    public void onFinish(ITestContext testContext) {

        extent.flush();

        test.remove();
    }
}
