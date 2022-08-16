package core.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static DriverManager instance  = new DriverManager();

    private DriverManager() {}

    public static DriverManager getInstance() {
        return instance;
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void setDriver(WebDriver driverOb) {
       driver.set(driverOb);
    }

    public String getSessionId() {
         return ((RemoteWebDriver) getDriver()).getSessionId().toString();
    }

    public void closeBrowser() {
        getDriver().close();
        getDriver().quit();
        driver.remove();
    }
}
