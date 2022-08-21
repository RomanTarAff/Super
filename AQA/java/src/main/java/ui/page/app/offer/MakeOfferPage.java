package ui.page.app.offer;

import core.control.Button;
import core.control.TextField;
import core.control.TextLabel;
import core.selenium.wait.DriverWait;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;
import ui.page.app.sell.CancelListingDialog;

public class MakeOfferPage extends BasePage {

    private static final Logger log = Logger.getLogger(MakeOfferPage.class);

    private final TextLabel title = new TextLabel(By.xpath("//h1[@class='offer-modal-header__title']"));
    private final TextLabel nftImg = new TextLabel(By.xpath("//div[@class='nft-summary offer-modal-info']//img"));
    private final TextLabel nftName = new TextLabel(By.xpath("//div[@class='nft-summary offer-modal-info']//div[@class='title']//a[contains(@class,'nft-summary__link')]"));
    private final TextLabel nftCollection = new TextLabel(By.xpath("//div[@class='nft-summary offer-modal-info']//div[@class='title']//a[contains(@class,'collection')]"));
    private final TextLabel nftTokenId = new TextLabel(By.xpath("//p[@class='nft-summary__token-address']"));
    private final TextLabel nftOwner = new TextLabel(By.xpath("//div[@class='text-overflow nft-summary__owner']"));
    private final TextLabel nftOwnerId = new TextLabel(By.xpath("//div[@class='text-overflow nft-summary__owner']/a"));
    private final TextLabel nftCreatedDate = new TextLabel(By.xpath("//p[@class='nft-summary__created']"));

    private final TextField price = new TextField(By.cssSelector("input[type='number']"));
    private final Button currencyMenu = new Button(By.xpath("//div[@class='input-wrapper']//div[@class='dropdown__toggle']"));
    private final Button dateMenu = new Button(By.xpath("//div[.//h3[text()=' Offer expiration ']]//div[@class='dropdown__toggle']/button[contains(@class,'wide-button text-left')]"));
    private String itemDate = "//p[text()='%s']";
    private final Button fourKb = new Button(By.xpath("//p[text()='4KB']"));
    private final Button makeOfferBtn = new Button(By.xpath("//div[@class='modal-footer offer-modal__footer']//button"));
    private final Button closeMakeOfferModal = new Button(By.xpath("//button[contains(@class,'close')]"));

    public MakeOfferPage() {
        super(By.xpath("//div[@class='body offer-modal__body']"));
        log.info("Make offer dialog is opened");
    }

    public String getTitle() {
        return title.getText();
    }

    public String getNftImageSrc() {
        return nftImg.getElement().getAttribute("src");
    }

    public String getNftName() {
        return nftName.getText();
    }

    public String getNftCollection() {
        return nftCollection.getText();
    }

    public String getOwner() {
        return nftOwner.getText();
    }

    public String getOwnerId() {
        return nftOwnerId.getElement().getAttribute("href");
    }

    public String getNftTokenId() {
        return nftTokenId.getText();
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

    public void makeOffer() {
        makeOfferBtn.press();
    }

    public void closeDialog() {
        closeMakeOfferModal.press();
    }

    public void waitForOffer() {
        DriverWait.waitElementPresent(By.cssSelector("div[class='notification-container success']"));
    }

}
