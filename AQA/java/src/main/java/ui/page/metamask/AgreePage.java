package ui.page.metamask;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;

public class AgreePage extends BasePage {

    private static final Logger log = Logger.getLogger(AgreePage.class);

    private final Button agreeBtn = new Button(By.cssSelector("button[data-testid='page-container-footer-next']"));

    public AgreePage() {
        super(By.cssSelector("button[data-testid='page-container-footer-next']"));
        log.info("Import page is opened");
    }

    public SecretPage agree() {
        agreeBtn.press();
        log.info("Agree is clicked");
        return new SecretPage();
    }
}
