package core.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;

import static core.config.ConfigurationManager.configuration;

public class BrowserFactory {

    public static WebDriver createDriver() {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        return new ChromeDriver(getOptions());
    }

    public static ChromeOptions getOptions() {
        String path = new File("").getAbsolutePath();
        File metamask = new File(path + "/metamask.crx");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--dns-prefetch-disable");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--lang=us");
        chromeOptions.addExtensions(metamask);
        chromeOptions.setHeadless(configuration().headless());
        return chromeOptions;
    }

    public static ChromeOptions getSelenoidOptions() {
        String path = new File("").getAbsolutePath();
        File metamask = new File(path + "/metamask.crx");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addExtensions(metamask);
        chromeOptions.setHeadless(configuration().headless());
        chromeOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("sessionTimeout", "10m");
            put("enableVideo", true);
            put("enableVNC", true);
        }});
        return chromeOptions;
    }
}
