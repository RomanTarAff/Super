package ui.page.metamask;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;


public class SignApprovePage extends BasePage {

    private static final Logger log = Logger.getLogger(SignApprovePage.class);

    private final Button rejectBtn = new Button(By.cssSelector("button[class='button btn--rounded btn-secondary']"));
    private final Button approveBtn = new Button(By.cssSelector("button[class='button btn--rounded btn-primary']"));
    private final Button arrowDown = new Button(By.xpath("//div[@data-testid='signature-request-scroll-button']"));


    public SignApprovePage() {
        super(By.xpath("//div[@class='signature-request-content']"), true);
        log.info("Sign approve page is opened");
    }

    public void reject() {
        rejectBtn.press();
    }

    public void sign() {
        arrowDown.press();
        approveBtn.press();
    }
}
