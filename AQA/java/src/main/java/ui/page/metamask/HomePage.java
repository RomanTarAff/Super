package ui.page.metamask;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;
import ui.form.Header;

public class HomePage extends BasePage {

    private static final Logger log = Logger.getLogger(HomePage.class);
    public final Header header = new Header();

    private final Button usersMenuBtn = new Button(By.cssSelector("button[class='action-item secondary header-short-profile__action']"));
    private final Button activityBtn = new Button(By.cssSelector("a[href='/activity'][class='header-navigation__link']"));

    public HomePage() {
        super(By.xpath("//div[@class='home']"));
        sleep(3);
        log.info("Home page is opened");
    }

    public ActivityPage openActivityPage() {
        activityBtn.press();
        return new ActivityPage();
    }

    public void openUsersMenu() {
        usersMenuBtn.press();
        log.info("Users menu is clicked");

    }
}
