package core.selenium;

import core.config.BrowserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static core.config.ConfigurationManager.configuration;

public class TargetFactory {
    private static final Logger logger = LogManager.getLogger(TargetFactory.class);

    public synchronized WebDriver createInstance(String browser) {
        Target execution = Target.valueOf(configuration().execution().toUpperCase());
        WebDriver webdriver = null;

        switch (execution) {
            case LOCAL:
                webdriver = BrowserFactory.createDriver();
                System.out.println("Instance was created");
                break;
            case REMOTE:
                webdriver = createRemoteInstance(BrowserFactory.getSelenoidOptions());
                break;
        }
        return webdriver;
    }

    private synchronized RemoteWebDriver createRemoteInstance(MutableCapabilities capability) {
        RemoteWebDriver remoteWebDriver = null;
        try {
            String gridURL = "http://localhost:4444/wd/hub";

            remoteWebDriver = new RemoteWebDriver(new URL(gridURL), capability);
        } catch (java.net.MalformedURLException e) {
            logger.error("Grid URL is invalid or Grid is not available");
            logger.error(String.format("Browser: %s", capability.getBrowserName()), e);
        } catch (IllegalArgumentException e) {
            logger.error(String.format("Browser %s is not valid or recognized", capability.getBrowserName()), e);
        }

        return remoteWebDriver;
    }

    enum Target {
        LOCAL, REMOTE
    }
}
