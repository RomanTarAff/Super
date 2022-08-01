package ui.page.metamask;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;

public class SignPage extends BasePage {

    private static final Logger log = Logger.getLogger(ImportPage.class);

    private final Button signBtn = new Button(By.cssSelector(".btn-primary"));

    public SignPage() {
        super(By.xpath("//div[contains(@class,'request-signature__container')]"));
        log.info("Sign page is opened");
    }

    public void sign() {
        signBtn.press();
        log.info("Sign is clicked");
    }
}
