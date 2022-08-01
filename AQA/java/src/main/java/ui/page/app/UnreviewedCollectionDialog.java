package ui.page.app;

import core.control.Button;
import core.control.TextLabel;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;
import ui.page.app.buy.CompletePurchaseDialog;
import ui.page.app.offer.MakeOfferPage;

public class UnreviewedCollectionDialog extends BasePage {

    private static final Logger log = Logger.getLogger(UnreviewedCollectionDialog.class);

    private final TextLabel title = new TextLabel(By.xpath("//h1[@class='collection-warning-modal-header__title']"));
    private final TextLabel itemName = new TextLabel(By.xpath("//span[@class='collection-warning-modal__item-name']"), 0);
    private final TextLabel itemTotalSales = new TextLabel(By.xpath("//span[@class='collection-warning-modal__item-name']"), 1);
    private final TextLabel itemTotalVolume = new TextLabel(By.xpath("//span[@class='collection-warning-modal__item-name']"), 2);
    private final TextLabel itemContractAddress = new TextLabel(By.xpath("//span[@class='collection-warning-modal__item-name']"), 3);
    private final TextLabel itemTotalItems = new TextLabel(By.xpath("//span[@class='collection-warning-modal__item-name']"), 4);

    private final TextLabel collectionName = new TextLabel(By.xpath("//li[.//span[text()=' Name ']]/a"));
    private final TextLabel totalSales = new TextLabel(By.xpath("//li[.//span[text()=' Total Sales ']]/span[contains(@class,'item-value')]"));
    private final TextLabel price = new TextLabel(By.xpath("//li[.//span[text()=' Total Volume ']]//span[contains(@class,'price-amount')]"));
    private final TextLabel priceUsd = new TextLabel(By.xpath("//li[.//span[text()=' Total Volume ']]//div[contains(@class,'currency-price')]"));
    private final TextLabel contractAddress = new TextLabel(By.xpath("//li[.//span[text()=' Contract Address ']]//a"));
    private final TextLabel totalItems = new TextLabel(By.xpath("//li[.//span[text()=' Total Items ']]/span[contains(@class,'item-value')]"));
    private final Button agreement = new Button(By.xpath("//span[@class='collection-warning-modal__button-text']"));

    public UnreviewedCollectionDialog() {
        super(By.xpath("//div[@class='modal-wrapper__content collection-warning-modal']"));
        log.info("Unreviewed collection page is opened");
    }

    public String getTitle() {
        return title.getText();
    }

    public String getItemName() {
        return itemName.getText();
    }

    public String getItemTotalSales() {
        return itemTotalSales.getText();
    }

    public String getItemTotalVolume() {
        return itemTotalVolume.getText();
    }

    public String getItemContractAddress() {
        return itemContractAddress.getText();
    }

    public String getItemTotalItems() {
        return itemTotalItems.getText();
    }

    public String getCollectionName() {
        return collectionName.getText();
    }

    public String getTotalSales() {
        return totalSales.getText();
    }

    public String getPrice() {
        return price.getText();
    }

    public String getPriceUsd() {
        return priceUsd.getText();
    }

    public String getContractAddress() {
        return contractAddress.getText();
    }

    public String getTotalItems() {
        return totalItems.getText();
    }

    public CompletePurchaseDialog agree() {
        agreement.press();
        return new CompletePurchaseDialog();
    }

    public String getAgreement() {
        return agreement.getText();
    }

    public MakeOfferPage agreeOffer() {
        agreement.press();
        return new MakeOfferPage();
    }

}
