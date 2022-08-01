package ui.page.metamask;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;

public class CongratsPage extends BasePage {

    private static final Logger log = Logger.getLogger(CongratsPage.class);

    private final Button submitBtn = new Button(By.cssSelector("button[role='button']"));

    public CongratsPage() {
        super(By.cssSelector("div[class='end-of-flow__emoji']"));
        log.info("Congrats page is opened");
    }

    public WhatsNewPage submit() {
        submitBtn.press();
        log.info("Submit is clicked");
        return new WhatsNewPage();
    }
}
