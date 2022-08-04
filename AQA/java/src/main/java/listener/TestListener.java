package listener;

import org.apache.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;
import util.Screen;

public class TestListener implements ITestListener {

    private static final Logger log = Logger.getLogger(TestListener.class);

    public void onTestStart(ITestResult result) {
        log.info("=========== Start test: " + result.getMethod().getMethodName() + "===============");
    }
    public void onTestSuccess(ITestResult result) {
        log.info("=========== Passed test: " + result.getMethod().getMethodName() + "===============");
    }
    public void onTestFailure(ITestResult result) {
        Screen.captureScreenshot(result.getMethod().getMethodName());
        log.info("=========== Failed test: " + result.getMethod().getMethodName() + "===============");
    }
    public void onTestSkipped(ITestResult result) {
        Screen.captureScreenshot(result.getMethod().getMethodName());
        log.info("=========== Skipped test: " + result.getMethod().getMethodName() + "===============");
    }
}
