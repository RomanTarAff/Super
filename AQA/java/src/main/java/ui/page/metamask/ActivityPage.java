package ui.page.metamask;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;
import ui.form.Table;

public class ActivityPage extends BasePage {

    private static final Logger log = Logger.getLogger(ActivityPage.class);
    public Table table = new Table();

    private final Button agreeBtn = new Button(By.cssSelector("button[data-testid='page-container-footer-next']"));

    public ActivityPage() {
        super(By.cssSelector("div[class='activity-page__container']"));
        log.info("Activity page is opened");
        sleep(3);
    }
}
