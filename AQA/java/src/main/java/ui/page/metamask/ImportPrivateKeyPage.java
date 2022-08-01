package ui.page.metamask;

import core.control.Button;
import core.control.TextField;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;

public class ImportPrivateKeyPage extends BasePage {

    private static final Logger log = Logger.getLogger(ImportPrivateKeyPage.class);

    private final TextField input = new TextField(By.xpath("//input[@id='private-key-box']"));
    private final Button importBtn = new Button(By.cssSelector(".btn-primary"));

    public ImportPrivateKeyPage() {
        super(By.cssSelector("div[class='new-account__form']"));
        log.info("Import private key page is opened");
    }

    public void setPk(String key) {
        input.setValue(key);
        log.info("Set key");
    }

    public void importKey() {
        importBtn.press();
    }
}
