package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import java.net.URL;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseTest;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;

    public ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    String repName;

    public void onStart(ITestContext testContext) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "IPL-Automation-Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

        sparkReporter.config().setDocumentTitle("IPL Test Automation Report");
        sparkReporter.config().setReportName("IPL Web Test Automation Report");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", testContext.getCurrentXmlTest().getParameter("browser"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
    }

    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, result.getName() + " passed");
    }

    public void onTestFailure(ITestResult result) {

        test.get().log(Status.FAIL, result.getName() + " failed");
        test.get().log(Status.FAIL, result.getThrowable());

        try {
            
        	BaseTest base = (BaseTest) result.getInstance();

            String imgPath = base.captureScreen(result.getName());

            test.get().addScreenCaptureFromPath(imgPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, result.getName() + " skipped");
    }

    public void onFinish(ITestContext testContext) {

        extent.flush();

        String path = System.getProperty("user.dir") + "\\reports\\" + repName;

        try {
            Desktop.getDesktop().browse(new File(path).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}