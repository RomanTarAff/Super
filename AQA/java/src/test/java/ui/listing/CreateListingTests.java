package ui.listing;

import api.enums.DatePeriod;
import core.selenium.DriverManager;
import listener.TestListener;
import org.apache.log4j.Logger;
import org.testng.annotations.*;
import ui.BasePage;
import ui.enums.Page;
import ui.page.NftDetailsPage;
import ui.page.app.sell.ListingDialog;
import ui.page.app.sell.SellNftPage;
import ui.page.metamask.HomePage;
import ui.page.metamask.SignApprovePage;
import util.DateHelper;

import java.util.List;

import static core.config.ConfigurationManager.configuration;

@Listeners(value = TestListener.class)
public class CreateListingTests extends BaseListingTests {

    private static final Logger log = Logger.getLogger(CreateListingTests.class);

    @AfterMethod
    public void toMainPage() {
        DriverManager.getInstance().getDriver().get(configuration().url());
    }

    @Test(testName = "Create listing with 4KB", priority = 0)
    public void createListingWith4KB() {
        String price = faker.random().nextInt(1, 5).toString();
        String currency = "4KB";

        HomePage homePage = new HomePage();
        homePage.header.search(nftWithoutListing_1.getName());
        NftDetailsPage nftDetailsPage = homePage.header.openFirstNftFromSearch();
        String nftNameFromNftDetails = nftDetailsPage.getNftName();
        String nftCollectionFromNftDetails = nftDetailsPage.getNftCollection();
        String nftLikesFromNftDetails = nftDetailsPage.getNftLikes();

        SellNftPage sellNftPage = nftDetailsPage.sellNFT();

        //royalties
        soft.assertEquals(sellNftPage.getNftName(), nftNameFromNftDetails);
        soft.assertEquals(sellNftPage.getNftCollection(), nftCollectionFromNftDetails);
        soft.assertNotNull(sellNftPage.getNftImageSrc());
        soft.assertEquals(sellNftPage.getNftLikes(), nftLikesFromNftDetails);
        soft.assertEquals(sellNftPage.getNftTokenId(), String.format("Token ID# %s", nftWithoutListing_1.getTokenId()));

        //center
        soft.assertEquals(sellNftPage.getMainTitle(), "Sell NFT");
        soft.assertEquals(sellNftPage.getScheduledTitle(), "Schedule listing end time");
        soft.assertEquals(sellNftPage.getScheduledBody(), "Choose when you want your listing to end");
        sellNftPage.openCurrencyMenu();
        //list of currencies
        sellNftPage.selectCurrency(currency);
        sellNftPage.setPrice(price);
        soft.assertEquals(sellNftPage.getListingEndsPhrase(), "Listing ends in 6 months");
        //dates
        soft.assertEquals(sellNftPage.getSummaryPrice(), price);
        soft.assertEquals(sellNftPage.getFeesTitle(), "Fees");
        soft.assertEquals(sellNftPage.getFeesBody(), "Listing is free! At the time of the sale, the following fees will be deducted.");
        soft.assertEquals(sellNftPage.getFeesValue(), "GigaMart fee: 2.5% 0%");
        soft.assertTrue(sellNftPage.getRoyalties().contains(String.format("Creator Royalties: %s", getTestCollectionRoyalties())));
        soft.assertEquals(sellNftPage.getYouWillReceivePhrase(), String.format("You will receive %s 4KB at the time of the sale.", price));
        ListingDialog listingDialog = sellNftPage.listIt();
        BasePage.switchTo(Page.METAMASK, true);
        SignApprovePage signApprovePage = new SignApprovePage();
        signApprovePage.sign();
        BasePage.switchTo(Page.MAIN);
        listingDialog.waitForListingCompleted();
        soft.assertEquals(listingDialog.getTitle(), "Complete your listing");
        soft.assertEquals(listingDialog.getFirstStepTitle(), "Initialize your wallet: click \"Confirm\" in your wallet extension");
        soft.assertEquals(listingDialog.getFirstStepSubTitle(), "When selling on GigaMart for the first time, you must initialize your wallet. This requires a one-time gas fee.");
        soft.assertFalse(listingDialog.isFirstStepNotReady(), "First step is completed");

        soft.assertEquals(listingDialog.getSecondStepTitle(), "Approve this item for sale");
        soft.assertEquals(listingDialog.getSecondStepSubTitle(), "To get set up for listings for the first time, you must approve this item for sale, which requires a one-time gas fee.");
        soft.assertFalse(listingDialog.isSecondStepNotReady(), "Second step is completed");

        soft.assertEquals(listingDialog.getThirdStepTitle(), "Confirm your sell. Waiting for signature...");
        soft.assertEquals(listingDialog.getThirdStepSubTitle(), "Accept the signature request in your wallet extension and wait for your listing to process.");
        soft.assertFalse(listingDialog.isThirdStepNotReady(), "Third step is completed");

        soft.assertFalse(listingDialog.isListingNotReady(), "Listing should be ready");

        NftDetailsPage nftDetailsPage1 = listingDialog.viewYourItem();
        nftDetailsPage1.reload();
        sleep(3);
        soft.assertTrue(nftDetailsPage1.isCancelListingDisplayed(), "Cancel listing btn should be present");

        //listing tab
        nftDetailsPage1.expandListingTab();
        soft.assertEquals(nftDetailsPage1.getListingTabTitle(), "LISTING");
        soft.assertEquals(nftDetailsPage1.getListingCount(), "1");
        soft.assertEquals(nftDetailsPage1.getListingPrice(1), price);
        soft.assertEquals(nftDetailsPage1.getListingExpiration(), "in 6mon");
        soft.assertEquals(nftDetailsPage1.getListingFrom(0), "you");

        //item history
        soft.assertEquals(nftDetailsPage1.getItemHistoryEvent(1), "Listing");
        soft.assertEquals(nftDetailsPage1.getItemHistoryPrice(1), price);
        soft.assertEquals(nftDetailsPage1.getItemHistoryFrom(0), "you");
        soft.assertEquals(nftDetailsPage1.getItemHistoryWhen(0), "1m ago");
        soft.assertEquals(nftDetailsPage1.getUrl(),
                configuration().url() + "/collections/" + getTestCollectionData().getContractAddress() + "/" + nftWithoutListing_1.getTokenId());
        soft.assertAll();
    }

    @Test(testName = "Create listing with ETH", priority = 1)
    public void createListingWithETH() {
        String price = "0.0000000001";
        String currency = "ETH";

        HomePage homePage = new HomePage();
        homePage.header.search(nftWithoutListing_2.getName());
        NftDetailsPage nftDetailsPage = homePage.header.openFirstNftFromSearch();
        String nftNameFromNftDetails = nftDetailsPage.getNftName();
        String nftCollectionFromNftDetails = nftDetailsPage.getNftCollection();
        String nftLikesFromNftDetails = nftDetailsPage.getNftLikes();

        SellNftPage sellNftPage = nftDetailsPage.sellNFT();

        //royalties
        soft.assertEquals(sellNftPage.getNftName(), nftNameFromNftDetails);
        soft.assertEquals(sellNftPage.getNftCollection(), nftCollectionFromNftDetails);
        soft.assertNotNull(sellNftPage.getNftImageSrc());
        soft.assertEquals(sellNftPage.getNftLikes(), nftLikesFromNftDetails);
        soft.assertEquals(sellNftPage.getNftTokenId(), String.format("Token ID# %s", nftWithoutListing_2.getTokenId()));

        //center
        soft.assertEquals(sellNftPage.getMainTitle(), "Sell NFT");
        soft.assertEquals(sellNftPage.getScheduledTitle(), "Schedule listing end time");
        soft.assertEquals(sellNftPage.getScheduledBody(), "Choose when you want your listing to end");
        sellNftPage.openCurrencyMenu();
        //list of currencies
        sellNftPage.selectCurrency(currency);
        sellNftPage.setPrice(price);
        soft.assertEquals(sellNftPage.getListingEndsPhrase(), "Listing ends in 6 months");
        //dates
        soft.assertEquals(sellNftPage.getSummaryPrice(), "< 0.01");
        soft.assertEquals(sellNftPage.getFeesTitle(), "Fees");
        soft.assertEquals(sellNftPage.getFeesBody(), "Listing is free! At the time of the sale, the following fees will be deducted.");
        soft.assertEquals(sellNftPage.getFeesValue(), "GigaMart fee: 2.5% 0%");
        soft.assertTrue(sellNftPage.getRoyalties().contains(String.format("Creator Royalties: %s", getTestCollectionRoyalties())));
        soft.assertEquals(sellNftPage.getYouWillReceivePhrase(), "You will receive < 0.01 ETH at the time of the sale.");
        ListingDialog listingDialog = sellNftPage.listIt();
        BasePage.switchTo(Page.METAMASK, true);
        SignApprovePage signApprovePage = new SignApprovePage();
        signApprovePage.sign();
        BasePage.switchTo(Page.MAIN);
        listingDialog.waitForListingCompleted();
        soft.assertEquals(listingDialog.getTitle(), "Complete your listing");
        soft.assertEquals(listingDialog.getFirstStepTitle(), "Initialize your wallet: click \"Confirm\" in your wallet extension");
        soft.assertEquals(listingDialog.getFirstStepSubTitle(), "When selling on GigaMart for the first time, you must initialize your wallet. This requires a one-time gas fee.");
        soft.assertFalse(listingDialog.isFirstStepNotReady(), "First step is completed");

        soft.assertEquals(listingDialog.getSecondStepTitle(), "Approve this item for sale");
        soft.assertEquals(listingDialog.getSecondStepSubTitle(), "To get set up for listings for the first time, you must approve this item for sale, which requires a one-time gas fee.");
        soft.assertFalse(listingDialog.isSecondStepNotReady(), "Second step is completed");

        soft.assertEquals(listingDialog.getThirdStepSubTitle(), "Confirm your sell. Waiting for signature...");
        soft.assertEquals(listingDialog.getThirdStepSubTitle(), "Accept the signature request in your wallet extension and wait for your listing to process.");
        soft.assertFalse(listingDialog.isThirdStepNotReady(), "Third step is completed");

        soft.assertFalse(listingDialog.isListingNotReady(), "Listing should be ready");

        NftDetailsPage nftDetailsPage1 = listingDialog.viewYourItem();
        nftDetailsPage1.reload();
        sleep(4);
        soft.assertFalse(nftDetailsPage1.isCancelListingDisplayed(), "Cancel listing btn should be not present");

        //listing tab
        nftDetailsPage1.expandListingTab();
        soft.assertEquals(nftDetailsPage1.getListingTabTitle(), "LISTING");
        soft.assertEquals(nftDetailsPage1.getListingCount(), "1");
        soft.assertEquals(nftDetailsPage1.getListingPrice(1), "< 0.01");
        soft.assertEquals(nftDetailsPage1.getListingPriceUsd(1), "$0.00");
        soft.assertEquals(nftDetailsPage1.getListingExpiration(), "in 6mon");
        soft.assertEquals(nftDetailsPage1.getListingFrom(0), "you");

        //item history
        soft.assertEquals(nftDetailsPage1.getItemHistoryEvent(1), "Listing");
        soft.assertEquals(nftDetailsPage1.getItemHistoryPrice(1), "< 0.01");
        soft.assertEquals(nftDetailsPage1.getItemHistoryPriceUsd(1), "$0.00");
        soft.assertEquals(nftDetailsPage1.getItemHistoryFrom(0), "you");
        soft.assertEquals(nftDetailsPage1.getItemHistoryWhen(0), "1m ago");
        soft.assertEquals(nftDetailsPage1.getUrl(),
                configuration().url() + "/collections/" + getTestCollectionData().getContractAddress() + "/" + nftWithoutListing_2.getTokenId());
        soft.assertAll();
    }

    @Test(testName = "Sell NFT page", priority = 2)
    public void sellNftPage() {
        String invalidPrice = "0.0000000000000000000";
        String currencyETH = "ETH";
        String currency4KB = "4KB";

        HomePage homePage = new HomePage();
        homePage.header.search(nftWithoutListing_3.getName());
        NftDetailsPage nftDetailsPage = homePage.header.openFirstNftFromSearch();
        SellNftPage sellNftPage = nftDetailsPage.sellNFT();

        //4KB
        sellNftPage.openCurrencyMenu();
        sellNftPage.selectCurrency(currency4KB);
        sellNftPage.setPrice("0.00001");
        soft.assertFalse(sellNftPage.isValidPrice());
        soft.assertEquals(sellNftPage.getErrorMessage(), "Max 4 characters after the decimal");

        //ETH
        sellNftPage.openCurrencyMenu();
        sellNftPage.selectCurrency(currencyETH);
        sellNftPage.setPrice(invalidPrice);
        soft.assertFalse(sellNftPage.isValidPrice());
        soft.assertEquals(sellNftPage.getErrorMessage(), "Max 18 characters after the decimal");

        //dates
        sellNftPage.setPrice("1");
        sellNftPage.switchDate();
        soft.assertEquals(sellNftPage.getDefaultDate(), "in 6 months");
        sellNftPage.openDateMenu();
        soft.assertEquals(sellNftPage.getAllDates(), List.of("in 1 day", "in 3 days", "in 5 days", "in 7 days", "in 10 days", "in 6 months", "Custom"));

        sellNftPage.reload();
        sellNftPage.switchDate();

        sellNftPage.openDateMenu();
        sellNftPage.selectDate("in 1 day");
        sellNftPage.setPrice("1");
        soft.assertEquals(sellNftPage.getListingEndsPhrase(), "Listing ends in 1 day");
        soft.assertEquals(sellNftPage.getListingEndsDate(), DateHelper.getDatePlus(DatePeriod.DAY_1));

        sellNftPage.openDateMenu();
        sellNftPage.selectDate("in 3 days");
        sellNftPage.setPrice("1");
        soft.assertEquals(sellNftPage.getListingEndsPhrase(), "Listing ends in 3 days");
        soft.assertEquals(sellNftPage.getListingEndsDate(), DateHelper.getDatePlus(DatePeriod.DAYS_3));

        sellNftPage.reload();
        sellNftPage.switchDate();
        sellNftPage.openDateMenu();
        sellNftPage.selectDate("in 5 days");
        sellNftPage.setPrice("1");
        soft.assertEquals(sellNftPage.getListingEndsPhrase(), "Listing ends in 5 days");
        soft.assertEquals(sellNftPage.getListingEndsDate(), DateHelper.getDatePlus(DatePeriod.DAYS_5));

        sellNftPage.openDateMenu();
        sellNftPage.selectDate("in 7 days");
        sellNftPage.setPrice("1");
        soft.assertEquals(sellNftPage.getListingEndsPhrase(), "Listing ends in 7 days");
        soft.assertEquals(sellNftPage.getListingEndsDate(), DateHelper.getDatePlus(DatePeriod.DAYS_7));

        sellNftPage.openDateMenu();
        sellNftPage.selectDate("in 10 days");
        sellNftPage.setPrice("1");
        soft.assertEquals(sellNftPage.getListingEndsPhrase(), "Listing ends in 10 days");
        soft.assertEquals(sellNftPage.getListingEndsDate(), DateHelper.getDatePlus(DatePeriod.DAYS_10));

        sellNftPage.openDateMenu();
        sellNftPage.selectDate("in 6 months");
        sellNftPage.setPrice("1");
        soft.assertEquals(sellNftPage.getListingEndsPhrase(), "Listing ends in 6 months");
        soft.assertEquals(sellNftPage.getListingEndsDate(), DateHelper.getDatePlus(DatePeriod.MONTHS_6));
        soft.assertAll();
    }

    @Test(testName = "Create listing reject", priority = 3)
    public void createListingReject() {
        HomePage homePage = new HomePage();
        homePage.header.search(nftWithoutListing_4.getName());
        NftDetailsPage nftDetailsPage = homePage.header.openFirstNftFromSearch();
        SellNftPage sellNftPage = nftDetailsPage.sellNFT();
        sellNftPage.openCurrencyMenu();
        sellNftPage.selectCurrency("4KB");
        sellNftPage.setPrice("1");
        ListingDialog listingDialog = sellNftPage.listIt();
        BasePage.switchTo(Page.METAMASK, true);
        SignApprovePage signApprovePage = new SignApprovePage();
        signApprovePage.reject();
        BasePage.switchTo(Page.MAIN);
        soft.assertEquals(listingDialog.getListingFailedStatus(), " Listing creation failed");
        soft.assertEquals(listingDialog.getModalErrorMessage(), "MetaMask Message Signature: User denied message signature.");

        listingDialog.closeDialog();
        soft.assertAll();
    }
}
