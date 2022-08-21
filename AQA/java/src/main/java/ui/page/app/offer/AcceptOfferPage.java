package ui.page.app.offer;

import core.control.Button;
import core.control.TextLabel;
import core.selenium.wait.DriverWait;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;
import ui.page.app.sell.CancelListingDialog;

public class AcceptOfferPage extends BasePage {

    private static final Logger log = Logger.getLogger(AcceptOfferPage.class);

    private final TextLabel title = new TextLabel(By.xpath("//p[@class='confirmation-modal-header__title']"));
    private final TextLabel body = new TextLabel(By.xpath("//p[@class='confirmation-modal-body__message']"));
    private final Button acceptOfferBtn = new Button(By.xpath("//div[@class='modal-footer step-modal__footer']/button"));
    private final TextLabel error = new TextLabel(By.xpath("//div[@class='step-modal__footer-error']"));
    private final TextLabel saleMsg = new TextLabel(By.xpath("//div[@class='step-modal__footer-finished']"));

    public AcceptOfferPage() {
        super(By.cssSelector("div[class='modal-header step-modal__header']"));
        log.info("Accept offer dialog is opened");
    }

    public String getTitle() {
        return title.getText();
    }

    public String getBody() {
        return body.getText();
    }

    public void acceptOffer() {
        acceptOfferBtn.press();
    }

    public String getError() {
        return error.getTextWithout();
    }

    public String getSaleMessage() {
        return saleMsg.getTextWithout();
    }

    public void waitForSale() {
        DriverWait.waitElementPresent(By.xpath("//div[@class='step-modal__footer-finished']"));
    }
}
