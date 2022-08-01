package ui.page.metamask;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;

public class WhatsNewPage extends BasePage {

    private static final Logger log = Logger.getLogger(WhatsNewPage.class);

    private final Button closeBtn = new Button(By.cssSelector("button[data-testid='popover-close']"));

    public WhatsNewPage() {
        super(By.cssSelector("section[class='popover-wrap whats-new-popup__popover']"));
        log.info("Whats new page is opened");
    }

    public WalletPage close() {
        closeBtn.press();
        log.info("Close is clicked");
        return new WalletPage();
    }
}
