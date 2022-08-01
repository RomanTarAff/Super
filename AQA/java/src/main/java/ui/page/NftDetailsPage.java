package ui.page;

import core.control.Button;
import core.control.TextLabel;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;
import ui.form.Header;
import ui.page.app.UnreviewedCollectionDialog;
import ui.page.app.offer.MakeOfferPage;
import ui.page.app.sell.CancelListingDialog;
import ui.page.app.sell.SellNftPage;

public class NftDetailsPage extends BasePage {

    private static final Logger log = Logger.getLogger(NftDetailsPage.class);

    private final TextLabel nftName = new TextLabel(By.xpath("//span[@class='main-info__name text-h2']"), 1);
    private final TextLabel nftCollection = new TextLabel(By.xpath("//div[@class='wrapper-right']//div[contains(@class,'collection-name')]"));
    private final TextLabel nftOwner = new TextLabel(By.xpath("//div[@class='main-info__flex-owner text-footnote']"), 1);
    private final TextLabel nftOwnerId = new TextLabel(By.xpath("//div[@class='ui-user-name']/a"), 1);
    private final TextLabel nftLikes = new TextLabel(By.xpath("//div[@class='wrapper-right']//div[.//div[@class='like-button']]//span[@class='nft-action-button__label']"));
    private final TextLabel priceAmount = new TextLabel(By.xpath("//div[@class='wrapper-right']//div[contains(@class,'item-buy-area__price')]//span[@class='price-amount']"));
    private final TextLabel priceCurrency = new TextLabel(By.xpath("//div[@class='my-item-buy-area']//div[contains(@class,'price-wrapper')]//img"));
    private final TextLabel priceUsd = new TextLabel(By.xpath("//div[@class='currency-price']"));
    private final TextLabel highestOfferTitle = new TextLabel(By.xpath("//span[@class='text-subhead price-buy-area__title']"));
    private final TextLabel highestOfferPrice = new TextLabel(By.xpath("//div[@class='wrapper-right']//div[contains(@class,'item-buy-area__highest-offer')]//span[@class='price-amount']"));
    private final TextLabel yourOfferTitle = new TextLabel(By.xpath("//div[@class='price-buy-area__highest-offer']"));

    private final TextLabel notification = new TextLabel(By.xpath("//div[@class='notification-text']"));

    private final Button makeOfferBtn = new Button(By.xpath("//div[@class='wrapper-right']//button[contains(@class,'makeoffer-button')]"));
    private final Button makeNewOfferBtn = new Button(By.xpath("//div[@class='wrapper-right']//button[text()=' Make new offer']"));
    private final Button cancelThisOfferBtn = new Button(By.xpath("//div[@class='wrapper-right']//button[text()=' Cancel this offer ']"));
    private final Button sellNftBtn = new Button(By.xpath("//div[@class='wrapper-right']//button[text()=' Sell NFT ']"));
    private final Button buyNowBtn = new Button(By.xpath("//div[@class='wrapper-right']//button[text()=' BUY NOW ']"));
    private final Button cancelListingBtn = new Button(By.xpath("//div[@class='wrapper-right']//button[text()=' Cancel Listing ']"));

    //listings
    private final Button listingTab = new Button(By.xpath("//div[contains(@class,'nft-details-expansion-panel')][.//span[text()='LISTING']]"));
    private final TextLabel listingNoInformationTab = new TextLabel(By.xpath("//div[contains(@class,'nft-details-expansion-panel')][.//span[text()='LISTING']]//div[contains(@class,'content-no-data')]"));
    private final TextLabel listingBlockTitle = new TextLabel(By.xpath("//div[contains(@class,' history-panel')]//span[@class='text-body']"));
    private final TextLabel listingCount = new TextLabel(By.xpath("//div[contains(@class,' history-panel')]//div[@class='activator-counter text-footnote']"));
    private String listingPriceByRow
            = "//div[contains(@class,'nft-details')][.//span[text()='LISTING']]//div[contains(@class,'table-body-row')][%d]//span[@class='price-amount']";
    private String listingCurrencyByRow
            = "//div[contains(@class,'nft-details')][.//span[text()='LISTING']]//div[contains(@class,'table-body-row')][%d]//img";
    private String listingPriceUsdByRow
            = "//div[contains(@class,'nft-details')][.//span[text()='LISTING']]//div[contains(@class,'table-body-row')][%d]//div[@class='currency-price']";
    private TextLabel listingExpirationDate =
            new TextLabel(By.xpath("//div[contains(@class,'nft-details')][.//span[text()='LISTING']]//div[contains(@class,'table-body-row')]//div[contains(@class,'table-cell')]"), 1);
    private String listingFromByIndex
            = "//div[contains(@class,'nft-details')][.//span[text()='LISTING']]//div[contains(@class,'table-body-row')]//div[@row-key='%d'][@column-key='2']";

    //offers
    private final Button offersTab = new Button(By.xpath("//div[contains(@class,'nft-details-expansion-panel')][.//span[text()='OFFERS']]"));
    private final TextLabel offersNoInformationTab = new TextLabel(By.xpath("//div[contains(@class,'nft-details-expansion-panel')][.//span[text()='OFFERS']]//div[contains(@class,'content-no-data')]"));
    private final TextLabel offersBlockTitle = new TextLabel(By.xpath("//div[contains(@class,' offers-panel')]//span[@class='text-body']"));
    private final TextLabel offersCount = new TextLabel(By.xpath("//div[contains(@class,' offers-panel')]//div[@class='activator-counter text-footnote']"));
    private String offerPriceByRow
            = "//div[contains(@class,'nft-details')][.//span[text()='OFFERS']]//div[contains(@class,'table-body-row')][%d]//span[@class='price-amount']";
    private String offerCurrencyByRow
            = "//div[contains(@class,'nft-details')][.//span[text()='OFFERS']]//div[contains(@class,'table-body-row')][%d]//img";
    private String offerPriceUsdByRow
            = "//div[contains(@class,'nft-details')][.//span[text()='OFFERS']]//div[contains(@class,'table-body-row')][%d]//div[@class='currency-price']";
    private String offerExpirationByRow
            = "//div[contains(@class,'nft-details')][.//span[text()='OFFERS']]//div[contains(@class,'table-body-row')][%d]//div[contains(@class,'table-cell')][2]";
    private String offerFromByRow
            = "//div[contains(@class,'nft-details')][.//span[text()='OFFERS']]//div[contains(@class,'table-body-row')][%d]//div[contains(@class,'table-cell')][3]";
    private String cancelOfferFromOffersTabByRow
            = "//div[contains(@class,'nft-details')][.//span[text()='OFFERS']]//div[contains(@class,'table-body-row')][%d]//button";

    //item history
    private String itemHistoryByRow
            = "//div[contains(@class,'wrapper__history')][.//span[text()='ITEM HISTORY']]//div[contains(@class,'table-body-row')][%d]";
    private String itemHistoryEventByRow
            = "//div[contains(@class,'wrapper__history')][.//span[text()='ITEM HISTORY']]//div[contains(@class,'table-body-row')][%d]//div[@class='nft-events-short']";
    private String itemHistoryStatusByRow
            = "//div[contains(@class,'wrapper__history')][.//span[text()='ITEM HISTORY']]//div[contains(@class,'table-body-row')][%d]//div[@class='nft-events-short__status text-caption']";
    private String itemHistoryCurrencyByRow
            = "//div[contains(@class,'wrapper__history')][.//span[text()='ITEM HISTORY']]//div[contains(@class,'table-body-row')][%d]//div[contains(@class,'price-wrapper')]//img";
    private String itemHistoryPriceByRow
            = "//div[contains(@class,'wrapper__history')][.//span[text()='ITEM HISTORY']]//div[contains(@class,'table-row')][%d]//span[@class='price-amount']";
    private String itemHistoryPriceUsdByRow
            = "//div[contains(@class,'wrapper__history')][.//span[text()='ITEM HISTORY']]//div[contains(@class,'table-row')][%d]//div[@class='currency-price']";
    private String itemHistoryFromByIndex
            = "//div[contains(@class,'wrapper__history')][.//span[text()='ITEM HISTORY']]//div[@row-key='%d'][@column-key='3']";
    private String itemHistoryToByIndex
            = "//div[contains(@class,'wrapper__history')][.//span[text()='ITEM HISTORY']]//div[@row-key='%d'][@column-key='4']";
    private String itemHistoryWhenByIndex
            = "//div[contains(@class,'wrapper__history')][.//span[text()='ITEM HISTORY']]//div[@row-key='%d'][@column-key='5']";

    public NftDetailsPage() {
        super(By.xpath("//div[@class='two-panels-block']"));
        log.info("NFT details page is opened");
    }

    public String getNftName() {
        return nftName.getText();
    }

    public String getNftCollection() {
        return nftCollection.getText();
    }

    public String getNftOwner() {
        return nftOwner.getText();
    }

    public String getNftOwnerId() {
        return nftOwnerId.getElement().getAttribute("href");
    }

    public String getNftLikes() {
        return nftLikes.getText();
    }

    public String getPrice() {
        return priceAmount.getText();
    }

    public String getPriceCurrency() {
        return priceCurrency.getText();
    }

    public String getPriceUsd() {
        return priceUsd.getText();
    }

    public String getHighestOfferTitle() {
        return highestOfferTitle.getText();
    }

    public String getHighestOfferPrice() {
        return highestOfferPrice.getText();
    }

    public String getYourOfferTitle() {
        return yourOfferTitle.getText();
    }

    public String getNotification() {
        return notification.getText();
    }

    //buttons

    public MakeOfferPage makeOffer() {
        makeOfferBtn.press();
        return new MakeOfferPage();
    }

    public MakeOfferPage makeNewOffer() {
        makeNewOfferBtn.press();
        return new MakeOfferPage();
    }

    public CancelListingDialog cancelThisOffer() {
        cancelThisOfferBtn.press();
        return new CancelListingDialog();
    }

    public SellNftPage sellNFT() {
        sellNftBtn.pressWithout();
        return new SellNftPage();
    }

    public UnreviewedCollectionDialog buyNow() {
        buyNowBtn.pressWithout();
        return new UnreviewedCollectionDialog();
    }

    public CancelListingDialog cancelListing() {
        cancelListingBtn.pressWithout();
        return new CancelListingDialog();
    }

    public boolean isMakeOfferDisplayed() {
        return makeOfferBtn.isPresent();
    }

    public boolean isMakeNewOfferDisplayed() {
        return makeNewOfferBtn.isPresent();
    }

    public boolean isCancelThisOfferDisplayed() {
        return cancelThisOfferBtn.isPresent();
    }

    public boolean isCancelListingDisplayed() {
        return cancelListingBtn.isPresent();
    }

    public boolean isBuyNowDisplayed() {
        return buyNowBtn.isPresent();
    }

    public boolean isSellNftDisplayed() {
        return sellNftBtn.isPresent();
    }

    //listings
    public String getListingTabTitle() {
        return listingBlockTitle.getText();
    }

    public String getListingCount() {
        return listingCount.getText();
    }

    public void expandListingTab() {
        listingTab.press();
    }

    public String getContentFromListingTab() {
        return listingNoInformationTab.getText();
    }

    public String getListingPrice(int row) {
        return new TextLabel(By.xpath(String.format(listingPriceByRow, row))).getText();
    }

    public String getListingPriceUsd(int row) {
        return new TextLabel(By.xpath(String.format(listingPriceUsdByRow, row))).getText();
    }

    public String getListingExpiration() {
        return listingExpirationDate.getText();
    }

    public String getListingFrom(int index) {
        return new TextLabel(By.xpath(String.format(listingFromByIndex, index))).getText();
    }

    //offers
    public String getOffersTabTitle() {
        return offersBlockTitle.getText();
    }

    public String getOffersCount() {
        return offersCount.getText();
    }

    public void expandOffersTab() {
        offersTab.press();
    }

    public String getContentFromOffersTab() {
        return offersNoInformationTab.getText();
    }

    public String getOfferPrice(int row) {
        return new TextLabel(By.xpath(String.format(offerPriceByRow, row))).getText();
    }

    public String getOfferPriceUsd(int row) {
        return new TextLabel(By.xpath(String.format(offerPriceUsdByRow, row))).getText();
    }

    public String getOfferExpirationDate(int row) {
        return new TextLabel(By.xpath(String.format(offerExpirationByRow, row))).getText();
    }

    public String getOfferFrom(int row) {
        return new TextLabel(By.xpath(String.format(offerFromByRow, row))).getText();
    }

    public CancelListingDialog cancelOfferFromOffersTab(int row) {
        Button b = new Button(By.xpath(String.format(cancelOfferFromOffersTabByRow, row)));
        b.press();
        return new CancelListingDialog();
    }

    //item history
    public String getItemHistoryEvent(int row) {
        return new TextLabel(By.xpath(String.format(itemHistoryEventByRow, row))).getText();
    }

    public String getItemHistoryEventStatus(int row) {
        return new TextLabel(By.xpath(String.format(itemHistoryStatusByRow, row))).getText();
    }

    public String getItemHistoryPrice(int row) {
        return new TextLabel(By.xpath(String.format(itemHistoryPriceByRow, row))).getText();
    }

    public String getItemHistoryPriceUsd(int row) {
        return new TextLabel(By.xpath(String.format(itemHistoryPriceUsdByRow, row))).getText();
    }

    public String getItemHistoryFrom(int index) {
        return new TextLabel(By.xpath(String.format(itemHistoryFromByIndex, index))).getText();
    }

    public String getItemHistoryTo(int index) {
        return new TextLabel(By.xpath(String.format(itemHistoryToByIndex, index))).getText();
    }

    public String getItemHistoryWhen(int index) {
        return new TextLabel(By.xpath(String.format(itemHistoryWhenByIndex, index))).getText();
    }
}
