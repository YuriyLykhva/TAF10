package util;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static page.BasePage.getDriver;

public class TestListener implements ITestListener {


    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log("Test '" + iTestResult.getName() + "' PASSED");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log("Test '" + iTestResult.getName() + "' FAILED");
        saveScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log("Test '" + iTestResult.getName() + "' SKIPPED");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log("Test Started....");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log("Test finished");
    }

    private void saveScreenshot() {
        Shutterbug.shootPage(getDriver(), Capture.VIEWPORT).save();
        Shutterbug.shootPage(getDriver(), Capture.FULL_SCROLL).save();
    }

    private void log(String methodName) {
        System.out.println(methodName);
    }
}
