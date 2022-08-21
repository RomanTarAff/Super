package ui.form;

import core.control.Button;
import core.control.TextLabel;
import org.openqa.selenium.By;
import ui.page.app.collection.CollectionDetailsPage;
import ui.page.app.offer.RenewOfferPage;

public class Table {

    private final TextLabel countOfRows = new TextLabel(By.xpath("//div[contains(@class,'table-body-row')]"));
    public FilterTags filterTags = new FilterTags();
    public FiltersPanel filtersPanel = new FiltersPanel();

    //by name
    private TextLabel getItemByName(String name) {
        return new TextLabel(By
                .xpath(String.format("//div[contains(@class,'table-body-row')][.//div[contains(@class,'table-cell')][.//p[@class='short-nft-item__title'][text()='%s']]]", name)));
    }

    private TextLabel getEventByName(String name) {
        return new TextLabel(By
                .xpath(String.format("//div[contains(@class,'table-body-row')][.//div[contains(@class,'table-cell')][.//*[text()='%s']]]//div[@class='nft-events-short__event']", name)));
    }

    private TextLabel getContentByName(String name) {
        return new TextLabel(By
                .xpath(String.format("//div[contains(@class,'table-body-row')][.//div[contains(@class,'table-cell')][.//p[@class='short-nft-item__title'][text()='%s']]]//div[@class='with-content avatar-wrapper']//div[@class='content']", name)));
    }

    private TextLabel getPriceByName(String name) {
        return new TextLabel(By
                .xpath(String.format("//div[contains(@class,'table-body-row')][.//div[contains(@class,'table-cell')][.//p[@class='short-nft-item__title'][text()='%s']]]//div[@class='price-wrapper']//span", name)));
    }

    private TextLabel getPriceCurrencyByName(String name) {
        return new TextLabel(By
                .xpath(String.format("//div[contains(@class,'table-body-row')][.//div[contains(@class,'table-cell')][.//p[@class='short-nft-item__title'][text()='%s']]]//div[@class='price-wrapper']//div[@class='currency-price']", name)));
    }

    private TextLabel getFromByName(String name) {
        return new TextLabel(By
                .xpath(String.format("//div[contains(@class,'table-body-row')][.//div[contains(@class,'table-cell')][.//p[@class='short-nft-item__title'][text()='%s']]]//div[contains(@class,'table-cell')]//a[contains(@class,'text-link-item')]", name)), 0);
    }

    private TextLabel getToByName(String name) {
        return new TextLabel(By
                .xpath(String.format("//div[contains(@class,'table-body-row')][.//div[contains(@class,'table-cell')][.//p[@class='short-nft-item__title'][text()='%s']]]//div[contains(@class,'table-cell')]//a[contains(@class,'text-link-item')]", name)), 1);
    }

    private TextLabel getWhenByName(String name) {
        return new TextLabel(By
                .xpath(String.format("//div[contains(@class,'table-body-row')][.//div[contains(@class,'table-cell')][.//p[@class='short-nft-item__title'][text()='%s']]]//div[contains(@class,'table-cell')]//a[contains(@class,'text-link-item')]", name)), 2);
    }
    
    //by index
    private TextLabel getNameByRow(int row) {
        return new TextLabel(By
                .xpath(String.format("//div[contains(@class,'table-body-row')][%d][.//div[contains(@class,'table-cell')]]//a[contains(@class,'short-nft-item__title')]/div", row)));
    }

    private TextLabel getEventByRow(int row) {
        return new TextLabel(By
                .xpath(String.format("//div[contains(@class,'table-body-row')][%d][.//div[contains(@class,'table-cell')]]//div[@class='nft-events-short__event']", row)));
    }

    private TextLabel getStatusByRow(int row) {
        return new TextLabel(By
                .xpath(String.format("//div[contains(@class,'table-body-row')][%d][.//div[contains(@class,'table-cell')]]//div[@class='nft-events-short__status text-caption']", row)));
    }

    private TextLabel getCollectionByRow(int row) {
        return new TextLabel(By
                .xpath(String.format("//div[contains(@class,'table-body-row')][%d][.//div[contains(@class,'table-cell')]]//div[contains(@class,'collection-name')]//div[@class='text-overflow']", row)));
    }

    private TextLabel getPriceByRow(int row) {
        return new TextLabel(By
                .xpath(String.format("//div[contains(@class,'table-body-row')][%d][.//div[contains(@class,'table-cell')]]//div[contains(@class,'price-wrapper')]//span", row)));
    }

    private TextLabel getFromByRow(int row) {
        return new TextLabel(By
                .xpath(String.format("//*[@row-key='%d'][@column-key='6']", row - 1)));
    }

    private TextLabel getWhenByRow(int row) {
        return new TextLabel(By
                .xpath(String.format("//*[@row-key='%d'][@column-key='8']//div[@class='date']", row - 1)));
    }

    private Button getCancelOfferByRow(int row) {
        return new Button(By
                .xpath(String.format("//*[@row-key='%d'][@column-key='9']//button", row - 1)));
    }

    private Button getRenewOfferByRow(int row) {
        return new Button(By
                .xpath(String.format("//*[@row-key='%d'][@column-key='9']//*[text()=' Renew Offer ']", row - 1)));
    }

    private Button getRowByIndexAndGetCollection(int row) {
        return new Button(By.xpath(String.format("//div[contains(@class,'table-body-row')][%d][.//div[contains(@class,'table-cell')]]//div[contains(@class,'ui-collection-name')]", row)));
    }
    
    
    //my offers table
    public TextLabel getExpirationByRow(int row) {
        return new TextLabel(By
                .xpath(String.format("//*[@row-key='%d'][@column-key='3']", row - 1)));
    }

    public TextLabel getSentByRow(int row) {
        return new TextLabel(By
                .xpath(String.format("//div[contains(@class,'table-body-row')][%d]//div[@class='time-status']", row)));
    }

    public Button getCancelOfferFromMyOffers(int row) {
        return new Button(By
                .xpath(String.format("//*[@row-key='%d'][@column-key='5']//button", row - 1)));
    }

    public Button getRenewOfferFromMyOffers(int row) {
        return new Button(By
                .xpath(String.format("//*[@row-key='%d'][@column-key='5']//*[text()=' Renew ']", row - 1)));
    }

    //methods
    public String getName(String name) {
        return getItemByName(name).getText();
    }

    public String getEventValue(String name) {
        return getEventByName(name).getText();
    }

    public String getEventValueWithout(String name) {
        return getEventByName(name).getTextWithout();
    }

    public String getContent(String name) {
        return getContentByName(name).getText();
    }

    public String getPrice(String name) {
        return getPriceByName(name).getText();
    }

    public String getPriceCurrency(String name) {
        return getPriceCurrencyByName(name).getText();
    }

    public String getFrom(String name) {
        return getFromByName(name).getText();
    }

    public String getTo(String name) {
        return getToByName(name).getText();
    }

    public String getWhen(String name) {
        return getWhenByName(name).getText();
    }

    public String getName(int row) {
        return getNameByRow(row).getText();
    }

    public CollectionDetailsPage clickOnCollection(int row) {
        getRowByIndexAndGetCollection(row).pressWithout();
        return new CollectionDetailsPage();
    }

    public String getEventValue(int row) {
        return getEventByRow(row).getText();
    }

    public String getStatus(int row) {
        return getStatusByRow(row).getText();
    }

    public String getCollection(int row) {
        return getCollectionByRow(row).getText();
    }

    public String getCollectionWithout(int row) {
        return getCollectionByRow(row).getTextWithout();
    }

    public String getPrice(int row) {
        return getPriceByRow(row).getText();
    }

    public String getPriceWithout(int row) {
        return getPriceByRow(row).getTextWithout();
    }

    public String getFrom(int row) {
        return getFromByRow(row).getText();
    }

    public String getFromWithout(int row) {
        return getFromByRow(row).getTextWithout();
    }

    public String getWhen(int row) {
        return getWhenByRow(row).getText();
    }

    public void cancelOffer(int row) {
        getCancelOfferByRow(row).press();
    }

    public RenewOfferPage renewOffer(int row) {
        getRenewOfferByRow(row).press();
        return new RenewOfferPage();
    }

    public void cancelOfferFromMyOffers(int row) {
        getCancelOfferFromMyOffers(row).press();
    }

    public RenewOfferPage renewOfferFromMyOffers(int row) {
        getRenewOfferFromMyOffers(row).pressWithout();
        return new RenewOfferPage();
    }

}
