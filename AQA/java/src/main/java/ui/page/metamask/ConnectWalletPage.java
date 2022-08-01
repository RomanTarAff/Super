package ui.page.metamask;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;

public class ConnectWalletPage extends BasePage {

    private static final Logger log = Logger.getLogger(ConnectWalletPage.class);

    private final Button approve = new Button(By.cssSelector("button[role='button']"), 1);

    public ConnectWalletPage() {
        super(By.cssSelector("div[class='choose-account-list__wrapper']"));
        log.info("Connect wallet page is opened");
    }

    public void approve() {
        approve.press();
    }
}
