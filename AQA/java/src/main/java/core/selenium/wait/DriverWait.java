package core.selenium.wait;

import core.selenium.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class DriverWait {

    private final static long POLLING_TIME = 100;
    private final static long ELEMENT_WAIT_TIMEOUT = 30;
    private final static long PAGE_WAIT_TIMEOUT = 20;
    private final static String SCRIPT_SPINNER = "return window.getComputedStyle(document.querySelector('*[data-delay]'),'::after').getPropertyValue('visibility')";

    private static final Logger log = Logger.getLogger(DriverWait.class);

    public static void waitForPageLoaded() {
        waitForPageLoaded(300L);
    }

    public static void waitForPageLoaded(long preSleep) {
        Function<WebDriver, Boolean> function = driver ->
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            log.info("Wait for page ready state");
            sleep(250);
            boolean result = js.executeScript("return document.readyState").equals("complete");
            if (result) {
                log.info("Page ready state is complete!");
            }
            return result;
        };
        fluentWait(PAGE_WAIT_TIMEOUT).ignoring(TimeoutException.class).until(function);
    }

    public static void waitElementPresent(By by) {
        fluentWait(ELEMENT_WAIT_TIMEOUT).ignoring(TimeoutException.class).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitElementNotPresent(By by) {
        fluentWait(50).ignoring(TimeoutException.class).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void waitElementClickable(By by) {
        fluentWait(ELEMENT_WAIT_TIMEOUT).ignoring(TimeoutException.class).until(ExpectedConditions.elementToBeClickable(by));
    }

    private static void sleep(long msecs) {
        try {
            TimeUnit.MILLISECONDS.sleep(msecs);
        } catch (InterruptedException e) {
        }
    }

    private static FluentWait<WebDriver> fluentWait(long timeoutInSec) {
        FluentWait<WebDriver> wait = new FluentWait<>(DriverManager.getInstance().getDriver());
        wait.withTimeout(Duration.ofSeconds(timeoutInSec));
        wait.pollingEvery(Duration.ofMillis(POLLING_TIME));
        wait.ignoring(NoSuchElementException.class);
        return wait;
    }

    public static void turnOffImplicitWaits() {
        DriverManager.getInstance().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    public static void turnOnImplicitWaits() {
        DriverManager.getInstance().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(ELEMENT_WAIT_TIMEOUT));
    }
}
