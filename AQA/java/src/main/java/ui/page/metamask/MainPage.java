package ui.page.metamask;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;

public class MainPage extends BasePage {

    private static final Logger log = Logger.getLogger(MainPage.class);

    private final Button logoBtn = new Button(By.xpath("//div[contains(@class,'logo')]"));
    private final Button accountMenu = new Button(By.xpath("//button[contains(@class,'account-menu__icon')]"));
    private final Button importPk = new Button(By.xpath("//div[@class='account-menu__item__text']"), 1);

    public void logo() {
        logoBtn.press();
    }

    public void openAccountMenu() {
        accountMenu.press();
    }

    public ImportPrivateKeyPage importPrivateKey() {
        importPk.press();
        return new ImportPrivateKeyPage();
    }

    public MainPage() {
        super(By.xpath("//div[contains(@class,'logo')]"));
        log.info("Main page is opened");
    }
}
