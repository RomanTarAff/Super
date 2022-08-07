package ui.page.metamask;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;

public class WelcomePage extends BasePage {

    private static final Logger log = Logger.getLogger(WelcomePage.class);

    private final Button startBtn = new Button(By.cssSelector("button[role='button']"));

    public WelcomePage() {
        super(true, By.cssSelector("div[class='welcome-page']"));
        log.info("Welcome page is opened");
    }

    public ImportPage start() {
        startBtn.press();
        log.info("Start is clicked");
        return new ImportPage();
    }
}
