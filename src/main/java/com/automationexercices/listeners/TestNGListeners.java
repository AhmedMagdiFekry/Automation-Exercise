package com.automationexercices.listeners;

import com.automationexercices.FileUtils;
import com.automationexercices.drivers.WebDriverProvider;
import com.automationexercices.media.ScreenRecordManager;
import com.automationexercices.media.ScreenshotManager;
import com.automationexercices.utils.dataReader.PropertyReader;
import com.automationexercices.utils.logs.LogsManager;
import com.automationexercices.utils.report.AllureAttachmentManager;
import com.automationexercices.utils.report.AllureConstants;
import com.automationexercices.utils.report.AllureEnvironmentManager;
import com.automationexercices.utils.report.AllureReportGenerator;
import com.automationexercices.validations.Validation;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;

public class TestNGListeners implements IExecutionListener, IInvokedMethodListener, ITestListener {
    public void onExecutionStart() {
        LogsManager.info("Test Execution Started.");
        cleanTestDirectories();
        LogsManager.info("Test directories cleaned.");
        createTestDirectories();
        LogsManager.info("Test directories created.");
        PropertyReader.loadProperties();
        LogsManager.info("Properties loaded.");
        AllureEnvironmentManager.setAllureEnvironmentVariables();
    }

    public void onExecutionFinish() {
        AllureReportGenerator.generateReports(false);
        AllureReportGenerator.copyHistory();
        AllureReportGenerator.generateReports(true);
        AllureReportGenerator.openReport(AllureReportGenerator.renameReport());
    }
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {

            ScreenRecordManager
                    .startRecording();

            LogsManager.info("Starting test: " + testResult.getMethod().getMethodName());
        }
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriver driver = null;
        if (method.isTestMethod()) {
            ScreenRecordManager.stopRecording(testResult.getName());
            if (testResult.getInstance() instanceof WebDriverProvider provider)
                driver = provider.getWebDriver(); //initialize driver from WebDriverProvider

            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS -> ScreenshotManager.takeFullPageScreenShot(driver, "passed-" + testResult.getName());
                case ITestResult.FAILURE -> ScreenshotManager.takeFullPageScreenShot(driver, "failed-" + testResult.getName());
                case ITestResult.SKIP -> ScreenshotManager.takeFullPageScreenShot(driver, "skipped-" + testResult.getName());
            }
            Validation.assertAll();
            AllureAttachmentManager.attachLogs();
            AllureAttachmentManager.attachRecords(testResult.getName());
        }
    }


    public void onTestSuccess(ITestResult result) {
        LogsManager.info("Test passed: " + result.getName() + "passed");

    }

    public void onTestFailure(ITestResult result) {
        LogsManager.info("Test failed: " + result.getName() + "failed");
    }

    public void onTestSkipped(ITestResult result) {
        LogsManager.info("Test skipped: " + result.getName() + "skipped");
    }
    // clean and create directories before starting the suite (logs,screenshots,allure results,records)
    private void cleanTestDirectories() {
        FileUtils.cleanDirectory(AllureConstants.RESULTS_FOLDER.toFile());
        FileUtils.cleanDirectory(new File(ScreenshotManager.Screenshot_Path));
        FileUtils.cleanDirectory(new File(ScreenRecordManager.RECORDINGS_PATH));
        FileUtils.forceDelete(new File(LogsManager.LOGS_PATH + File.separator + "Logs.log"));

    }
    private void createTestDirectories() {
    FileUtils.createDirectory(ScreenshotManager.Screenshot_Path);
    FileUtils.createDirectory(ScreenRecordManager.RECORDINGS_PATH);
    }

}
