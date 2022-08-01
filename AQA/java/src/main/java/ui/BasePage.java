package ui;

import core.selenium.DriverManager;
import core.selenium.wait.DriverWait;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.enums.Page;
import ui.form.Header;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    private static final Logger log = Logger.getLogger(BasePage.class);
    public Header header = new Header();

    protected BasePage(By by) {
        DriverWait.waitForPageLoaded();
        DriverWait.waitElementPresent(by);
    }

    protected BasePage(By by, boolean reload) {
        if (reload) DriverManager.getInstance().getDriver().navigate().refresh();
        DriverWait.waitForPageLoaded();
        DriverWait.waitElementPresent(by);
    }

    public void reload() {
        DriverManager.getInstance().getDriver().navigate().refresh();
        DriverWait.waitForPageLoaded();
        sleep(3);
    }

    public void reloadSimple() {
        DriverManager.getInstance().getDriver().navigate().refresh();
        sleep(3);
    }

    public String getUrl() {
        return DriverManager.getInstance().getDriver().getCurrentUrl();
    }

    public static void switchTo(Page page) {
        switchTo(page, false);
    }

    public static void switchTo(Page page, Boolean reload) {
        log.info("Session id " + DriverManager.getInstance().getSessionId());
        Set<String> allWindowHandles = DriverManager.getInstance().getDriver().getWindowHandles();
        List<String> windows = new ArrayList<>(allWindowHandles);
        String metamask = windows.get(0);
        String main = windows.get(1);
        log.info("Windows size: " + windows.size());
        log.info("Windows main: " + main);
        log.info("Windows metamask: " + metamask);
        switch (page) {
            case METAMASK:
                DriverManager.getInstance().getDriver().switchTo().window(metamask);
                log.info(String.format("Switch to Metamask page %s", metamask));
                if (reload) {
                    DriverManager.getInstance().getDriver().navigate().refresh();
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DriverManager.getInstance().getDriver().navigate().refresh();
                }
                break;
            case MAIN:
                DriverManager.getInstance().getDriver().switchTo().window(main);
                log.info(String.format("Switch to Main page %s", main));
                break;
        }
    }

//    public static void switchTo(Page page, Boolean reload) {
//        log.info("Session id " + DriverManager.getInstance().getSessionId());
//        switch (page) {
//            case METAMASK:
//                String mainWindowHandle = DriverManager.getInstance().getDriver().getWindowHandle();
//                Set<String> allWindowHandles = DriverManager.getInstance().getDriver().getWindowHandles();
//                log.info("Windows size on main page: " + allWindowHandles.size());
//                log.info("Windows on main page: " + allWindowHandles.toString());
//                Iterator<String> iterator = allWindowHandles.iterator();
//                while (iterator.hasNext()) {
//                    String metamaskWindow = iterator.next();
//                    if (!mainWindowHandle.equalsIgnoreCase(metamaskWindow)) {
//                        DriverManager.getInstance().getDriver().switchTo().window(metamaskWindow);
//                        log.info("Switch to Metamask page");
//                        if(reload) {
//                            DriverManager.getInstance().getDriver().navigate().refresh();
//                            try {
//                                TimeUnit.SECONDS.sleep(3);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            DriverManager.getInstance().getDriver().navigate().refresh();
//                        }
//                    }
//                }
//                break;
//            case MAIN:
//                String metamaskWindowHandle = DriverManager.getInstance().getDriver().getWindowHandle();
//                Set<String> allWindowHandle = DriverManager.getInstance().getDriver().getWindowHandles();
//                log.info("Windows size on metamask page: " + allWindowHandle.size());
//                log.info("Windows on metamask page: " + allWindowHandle.toString());
//                Iterator<String> iteratorr = allWindowHandle.iterator();
//                while (iteratorr.hasNext()) {
//                    String mainWindow = iteratorr.next();
//                    if (!metamaskWindowHandle.equalsIgnoreCase(mainWindow)) {
//                        DriverManager.getInstance().getDriver().switchTo().window(mainWindow);
//                        log.info("Switch to Main page");
//                    }
//                }
//                break;
//        }
//    }

    public void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
