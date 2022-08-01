package util;

import core.selenium.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;

public class Screen {

    private static final Logger log = Logger.getLogger(Screen.class);

    public static void captureScreenshot(String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getInstance().getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            String path = new File("").getAbsolutePath();
            FileHandler.copy(source, new File(path + "/screenshots/" + screenshotName + ".png"));
            log.info("Screenshot taken");
        } catch (Exception e) {
            log.info("Exception while taking screenshot " + e.getMessage());
        }
    }
}
