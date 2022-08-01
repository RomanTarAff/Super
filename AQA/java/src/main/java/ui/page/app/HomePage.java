package ui.page.app;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;
import ui.form.Table;
import ui.page.metamask.ActivityPage;

public class HomePage extends BasePage {

    private static final Logger log = Logger.getLogger(ActivityPage.class);

    public HomePage() {
        super(By.cssSelector("div[class='home']"));
        log.info("Home page is opened");
    }
}
