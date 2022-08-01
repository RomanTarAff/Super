package ui.page.app.sell;

import core.control.Button;
import core.control.TextLabel;
import core.selenium.wait.DriverWait;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;

public class CancelListingDialog extends BasePage {

    private static final Logger log = Logger.getLogger(CancelListingDialog.class);

    private final TextLabel title = new TextLabel(By.xpath("//p[@class='confirmation-modal-header__title']"));
    private final TextLabel body = new TextLabel(By.xpath("//p[@class='confirmation-modal-body__message']"));
    private final Button cancelListingBtn = new Button(By.xpath("//div[@class='modal-footer confirmation-modal-footer']/button"));

    public CancelListingDialog() {
        super(By.cssSelector("div[class='modal-header confirmation-modal-header']"));
        log.info("Cancel listing dialog is opened");
    }

    public String getTitle() {
        return title.getText();
    }

    public String getBody() {
        return body.getText();
    }

    public void cancelListing() {
        cancelListingBtn.press();
    }

    public void waitForListingCancelled() {
        DriverWait.waitElementNotPresent(By.xpath("//p[@class='confirmation-modal-header__title']")); //title
    }

}
