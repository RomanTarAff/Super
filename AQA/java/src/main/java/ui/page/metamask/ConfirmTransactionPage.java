package ui.page.metamask;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;

public class ConfirmTransactionPage extends BasePage {

    private static final Logger log = Logger.getLogger(ConfirmTransactionPage.class);

    private final Button rejectBtn = new Button(By.cssSelector(".btn-secondary"));
    private final Button confirmBtn = new Button(By.cssSelector(".btn-primary"));

    public ConfirmTransactionPage() {
        super(By.xpath("//div[contains(@class,'confirm-page-container')]"));
        log.info("Confirm Transaction page is opened");
    }

    public void reject() {
        rejectBtn.press();
    }

    public void confirm() {
        confirmBtn.press();
    }


}
