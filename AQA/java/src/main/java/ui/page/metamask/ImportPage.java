package ui.page.metamask;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;

public class ImportPage extends BasePage {

    private static final Logger log = Logger.getLogger(ImportPage.class);

    private final Button importBtn = new Button(By.xpath("//div[.//i[@class='fa fa-download fa-2x']]/button"));

    public ImportPage() {
        super(By.cssSelector("div[class='select-action__body']"));
        log.info("Import page is opened");
    }

    public AgreePage importAcc() {
        importBtn.press();
        log.info("Import is clicked");
        return new AgreePage();
    }
}
