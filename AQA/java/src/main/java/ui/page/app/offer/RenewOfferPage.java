package ui.page.app.offer;

import core.control.Button;
import core.control.TextField;
import core.selenium.wait.DriverWait;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;

public class RenewOfferPage extends BasePage {

    private static final Logger log = Logger.getLogger(MakeOfferPage.class);

    private final TextField price = new TextField(By.cssSelector("input[type='number']"));
    private final Button currencyMenu = new Button(By.xpath("//div[@class='input-wrapper']//div[@class='dropdown__toggle']"));
    private final Button dateMenu = new Button(By.xpath("//div[.//h3[text()=' Offer expiration ']]//div[@class='dropdown__toggle']/button[contains(@class,'wide-button text-left')]"));
    private String itemDate = "//p[text()='%s']";
    private final Button fourKb = new Button(By.xpath("//p[text()='4KB']"));
    private final Button renewOfferBtn = new Button(By.xpath("//div[@class='modal-footer offer-modal__footer']//button"));
    private final Button closeMakeOfferModal = new Button(By.xpath("//button[contains(@class,'close')]"));

    public RenewOfferPage() {
        super(By.xpath("//div[@class='body offer-modal__body']"));
        log.info("Renew offer dialog is opened");
    }

    public void setPrice(String value) {
        price.setValue(value);
    }

    public void openCurrencyMenu() {
        currencyMenu.press();
    }

    public void openDateMenu() {
        dateMenu.press();
    }

    public void selectDate(String date) {
        new Button(By.xpath(String.format(itemDate, date))).press();
    }

    public void select4Kb() {
        fourKb.press();
    }

    public void renewOffer() {
        renewOfferBtn.press();
    }

    public void closeDialog() {
        closeMakeOfferModal.press();
    }

    public void waitForOffer() {
        DriverWait.waitElementPresent(By.cssSelector("div[class='notification-container success']"));
    }
}
