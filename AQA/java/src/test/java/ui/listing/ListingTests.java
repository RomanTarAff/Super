package ui.listing;

import api.enums.DatePeriod;
import org.apache.log4j.Logger;
import org.testng.annotations.*;
import ui.BasePage;
import ui.enums.Page;
import ui.page.NftDetailsPage;
import ui.page.app.sell.CancelListingDialog;
import ui.page.app.sell.ListingDialog;
import ui.page.app.sell.SellNftPage;
import ui.page.metamask.ConfirmTransactionPage;
import ui.page.metamask.HomePage;
import ui.page.metamask.SignApprovePage;
import util.DateHelper;

import java.util.List;

import static core.config.ConfigurationManager.configuration;
import static org.testng.Assert.*;

public class ListingTests extends BaseListingTests {

    private static final Logger log = Logger.getLogger(ListingTests.class);

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
        assertEquals(sellNftPage.getNftName(), nftNameFromNftDetails);
        assertEquals(sellNftPage.getNftCollection(), nftCollectionFromNftDetails);
        assertNotNull(sellNftPage.getNftImageSrc());
        assertEquals(sellNftPage.getNftLikes(), nftLikesFromNftDetails);
        assertEquals(sellNftPage.getNftTokenId(), String.format("Token ID# %s", nftWithoutListing_1.getTokenId()));

        //center
        assertEquals(sellNftPage.getMainTitle(), "Sell NFT");
        assertEquals(sellNftPage.getScheduledTitle(), "Schedule listing end time");
        assertEquals(sellNftPage.getScheduledBody(), "Choose when you want your listing to end");
        sellNftPage.openCurrencyMenu();
        //list of currencies
        sellNftPage.selectCurrency(currency);
        sellNftPage.setPrice(price);
        assertEquals(sellNftPage.getListingEndsPhrase(), "Listing ends in 6 months");
        //dates
        assertEquals(sellNftPage.getSummaryPrice(), price);
        assertEquals(sellNftPage.getFeesTitle(), "Fees");
        assertEquals(sellNftPage.getFeesBody(), "Listing is free! At the time of the sale, the following fees will be deducted.");
        assertEquals(sellNftPage.getFeesValue(), "GigaMart fee: 2.5% 0%");
//        assertTrue(sellNftPage.getRoyalties().contains(String.format("Creator Royalties: %s", getTestCollectionRoyalties()).replaceAll("\n", "")),
//                "Royalties is not correct");
        assertEquals(sellNftPage.getYouWillReceivePhrase(), String.format("You will receive %s 4KB at the time of the sale.", price));
        ListingDialog listingDialog = sellNftPage.listIt();
        BasePage.switchTo(Page.METAMASK, true);
        SignApprovePage signApprovePage = new SignApprovePage();
        signApprovePage.sign();
        BasePage.switchTo(Page.MAIN);
        listingDialog.waitForListingCompleted();
        assertEquals(listingDialog.getTitle(), "Complete your listing");
        assertEquals(listingDialog.getFirstStepTitle(), "Initialize your wallet: click \"Confirm\" in your wallet extension");
        assertEquals(listingDialog.getFirstStepSubTitle(), "When selling on GigaMart for the first time, you must initialize your wallet. This requires a one-time gas fee.");
        assertFalse(listingDialog.isFirstStepNotReady(), "First step is completed");

        assertEquals(listingDialog.getSecondStepTitle(), "Approve this item for sale");
        assertEquals(listingDialog.getSecondStepSubTitle(), "To get set up for listings for the first time, you must approve this item for sale, which requires a one-time gas fee.");
        assertFalse(listingDialog.isSecondStepNotReady(), "Second step is completed");

        assertEquals(listingDialog.getThirdStepTitle(), "Confirm your sell. Waiting for signature...");
        assertEquals(listingDialog.getThirdStepSubTitle(), "Accept the signature request in your wallet extension and wait for your listing to process.");
        assertFalse(listingDialog.isThirdStepNotReady(), "Third step is completed");

        assertFalse(listingDialog.isListingNotReady(), "Listing should be ready");

        NftDetailsPage nftDetailsPage1 = listingDialog.viewYourItem();
        nftDetailsPage1.reload();
        sleep(3);
        assertTrue(nftDetailsPage1.isCancelListingDisplayed(), "Cancel listing btn should be present");

        //listing tab
        nftDetailsPage1.expandListingTab();
        assertEquals(nftDetailsPage1.getListingTabTitle(), "LISTING");
        assertEquals(nftDetailsPage1.getListingCount(), "1");
        assertEquals(nftDetailsPage1.getListingPrice(1), price);
        assertEquals(nftDetailsPage1.getListingExpiration(), "in 6mon");
        assertEquals(nftDetailsPage1.getListingFrom(0), "you");

        //item history
        assertEquals(nftDetailsPage1.getItemHistoryEvent(1), "Listing");
        assertEquals(nftDetailsPage1.getItemHistoryPrice(1), price);
        assertEquals(nftDetailsPage1.getItemHistoryFrom(0), "you");
        assertEquals(nftDetailsPage1.getItemHistoryWhen(0), "1m ago");
        assertEquals(nftDetailsPage1.getUrl(),
                configuration().url() + "/collections/" + getTestCollectionData().getContractAddress() + "/" + nftWithoutListing_1.getTokenId());
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
        assertEquals(sellNftPage.getNftName(), nftNameFromNftDetails);
        assertEquals(sellNftPage.getNftCollection(), nftCollectionFromNftDetails);
        assertNotNull(sellNftPage.getNftImageSrc());
        assertEquals(sellNftPage.getNftLikes(), nftLikesFromNftDetails);
        assertEquals(sellNftPage.getNftTokenId(), String.format("Token ID# %s", nftWithoutListing_2.getTokenId()));

        //center
        assertEquals(sellNftPage.getMainTitle(), "Sell NFT");
        assertEquals(sellNftPage.getScheduledTitle(), "Schedule listing end time");
        assertEquals(sellNftPage.getScheduledBody(), "Choose when you want your listing to end");
        sellNftPage.openCurrencyMenu();
        //list of currencies
        sellNftPage.selectCurrency(currency);
        sellNftPage.setPrice(price);
        assertEquals(sellNftPage.getListingEndsPhrase(), "Listing ends in 6 months");
        //dates
        assertEquals(sellNftPage.getSummaryPrice(), "< 0.01");
        assertEquals(sellNftPage.getFeesTitle(), "Fees");
        assertEquals(sellNftPage.getFeesBody(), "Listing is free! At the time of the sale, the following fees will be deducted.");
        assertEquals(sellNftPage.getFeesValue(), "GigaMart fee: 2.5% 0%");
        //assertTrue(sellNftPage.getRoyalties().contains(String.format("Creator Royalties: %s", getTestCollectionRoyalties())));
        assertEquals(sellNftPage.getYouWillReceivePhrase(), "You will receive < 0.01 ETH at the time of the sale.");
        ListingDialog listingDialog = sellNftPage.listIt();
        BasePage.switchTo(Page.METAMASK, true);
        SignApprovePage signApprovePage = new SignApprovePage();
        signApprovePage.sign();
        BasePage.switchTo(Page.MAIN);
        listingDialog.waitForListingCompleted();
        assertEquals(listingDialog.getTitle(), "Complete your listing");
        assertEquals(listingDialog.getFirstStepTitle(), "Initialize your wallet: click \"Confirm\" in your wallet extension");
        assertEquals(listingDialog.getFirstStepSubTitle(), "When selling on GigaMart for the first time, you must initialize your wallet. This requires a one-time gas fee.");
        assertFalse(listingDialog.isFirstStepNotReady(), "First step is completed");

        assertEquals(listingDialog.getSecondStepTitle(), "Approve this item for sale");
        assertEquals(listingDialog.getSecondStepSubTitle(), "To get set up for listings for the first time, you must approve this item for sale, which requires a one-time gas fee.");
        assertFalse(listingDialog.isSecondStepNotReady(), "Second step is completed");

        assertEquals(listingDialog.getThirdStepTitle(), "Confirm your sell. Waiting for signature...");
        assertEquals(listingDialog.getThirdStepSubTitle(), "Accept the signature request in your wallet extension and wait for your listing to process.");
        assertFalse(listingDialog.isThirdStepNotReady(), "Third step is completed");

        assertFalse(listingDialog.isListingNotReady(), "Listing should be ready");

        NftDetailsPage nftDetailsPage1 = listingDialog.viewYourItem();
        nftDetailsPage1.reload();
        sleep(4);
        assertFalse(nftDetailsPage1.isCancelListingDisplayed(), "Cancel listing btn should be not present");

        //listing tab
        nftDetailsPage1.expandListingTab();
        assertEquals(nftDetailsPage1.getListingTabTitle(), "LISTING");
        assertEquals(nftDetailsPage1.getListingCount(), "1");
        assertEquals(nftDetailsPage1.getListingPrice(1), "< 0.01");
        assertEquals(nftDetailsPage1.getListingPriceUsd(1), "$0.00");
        assertEquals(nftDetailsPage1.getListingExpiration(), "in 6mon");
        assertEquals(nftDetailsPage1.getListingFrom(0), "you");

        //item history
        assertEquals(nftDetailsPage1.getItemHistoryEvent(1), "Listing");
        assertEquals(nftDetailsPage1.getItemHistoryPrice(1), "< 0.01");
        assertEquals(nftDetailsPage1.getItemHistoryPriceUsd(1), "$0.00");
        assertEquals(nftDetailsPage1.getItemHistoryFrom(0), "you");
        assertEquals(nftDetailsPage1.getItemHistoryWhen(0), "1m ago");
        assertEquals(nftDetailsPage1.getUrl(),
                configuration().url() + "/collections/" + getTestCollectionData().getContractAddress() + "/" + nftWithoutListing_2.getTokenId());
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
        assertFalse(sellNftPage.isValidPrice());
        assertEquals(sellNftPage.getErrorMessage(), "Max 4 characters after the decimal");

        //ETH
        sellNftPage.openCurrencyMenu();
        sellNftPage.selectCurrency(currencyETH);
        sellNftPage.setPrice(invalidPrice);
        assertFalse(sellNftPage.isValidPrice());
        assertEquals(sellNftPage.getErrorMessage(), "Max 18 characters after the decimal");

        //dates
        sellNftPage.setPrice("1");
        sellNftPage.switchDate();
        assertEquals(sellNftPage.getDefaultDate(), "in 6 months");
        sellNftPage.openDateMenu();
        assertEquals(sellNftPage.getAllDates(), List.of("in 1 day", "in 3 days", "in 5 days", "in 7 days", "in 10 days", "in 6 months", "Custom"));

        sellNftPage.reload();
        sellNftPage.switchDate();

        sellNftPage.openDateMenu();
        sellNftPage.selectDate("in 1 day");
        sellNftPage.setPrice("1");
        assertEquals(sellNftPage.getListingEndsPhrase(), "Listing ends in 1 day");
        assertEquals(sellNftPage.getListingEndsDate(), DateHelper.getDatePlus(DatePeriod.DAY_1));

        sellNftPage.openDateMenu();
        sellNftPage.selectDate("in 3 days");
        sellNftPage.setPrice("1");
        assertEquals(sellNftPage.getListingEndsPhrase(), "Listing ends in 3 days");
        assertEquals(sellNftPage.getListingEndsDate(), DateHelper.getDatePlus(DatePeriod.DAYS_3));

        sellNftPage.reload();
        sellNftPage.switchDate();
        sellNftPage.openDateMenu();
        sellNftPage.selectDate("in 5 days");
        sellNftPage.setPrice("1");
        assertEquals(sellNftPage.getListingEndsPhrase(), "Listing ends in 5 days");
        assertEquals(sellNftPage.getListingEndsDate(), DateHelper.getDatePlus(DatePeriod.DAYS_5));

        sellNftPage.openDateMenu();
        sellNftPage.selectDate("in 7 days");
        sellNftPage.setPrice("1");
        assertEquals(sellNftPage.getListingEndsPhrase(), "Listing ends in 7 days");
        assertEquals(sellNftPage.getListingEndsDate(), DateHelper.getDatePlus(DatePeriod.DAYS_7));

        sellNftPage.openDateMenu();
        sellNftPage.selectDate("in 10 days");
        sellNftPage.setPrice("1");
        assertEquals(sellNftPage.getListingEndsPhrase(), "Listing ends in 10 days");
        assertEquals(sellNftPage.getListingEndsDate(), DateHelper.getDatePlus(DatePeriod.DAYS_10));

        sellNftPage.openDateMenu();
        sellNftPage.selectDate("in 6 months");
        sellNftPage.setPrice("1");
        assertEquals(sellNftPage.getListingEndsPhrase(), "Listing ends in 6 months");
        assertEquals(sellNftPage.getListingEndsDate(), DateHelper.getDatePlus(DatePeriod.MONTHS_6));
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
        assertEquals(listingDialog.getListingFailedStatus(), "Listing creation failed");
        assertEquals(listingDialog.getModalErrorMessage(), "MetaMask Message Signature: User denied message signature.");
    }

    @Test(testName = "Cancel listing from nft details", priority = 4)
    public void cancelListingFromNftDetails() {
        String price = faker.random().nextInt(1, 5).toString();
        String currency = "4KB";

        HomePage homePage = new HomePage();
        homePage.header.search(nftWithoutListing_5.getName());
        NftDetailsPage nftDetailsPage = homePage.header.openFirstNftFromSearch();
        SellNftPage sellNftPage = nftDetailsPage.sellNFT();
        sellNftPage.openCurrencyMenu();
        sellNftPage.selectCurrency(currency);
        sellNftPage.setPrice(price);
        ListingDialog listingDialog = sellNftPage.listIt();
        BasePage.switchTo(Page.METAMASK, true);
        SignApprovePage signApprovePage = new SignApprovePage();
        signApprovePage.sign();
        BasePage.switchTo(Page.MAIN);
        listingDialog.waitForListingCompleted();
        NftDetailsPage nftDetailsPage1 = listingDialog.viewYourItem();
        nftDetailsPage1.reload();
        sleep(3);
        CancelListingDialog cancelListingDialog = nftDetailsPage1.cancelListing();
        assertEquals(cancelListingDialog.getTitle(), "Confirmation");
        assertEquals(cancelListingDialog.getBody(), "Are you sure you want to cancel this listing?");
        cancelListingDialog.cancelListing();
        BasePage.switchTo(Page.METAMASK, true);
        ConfirmTransactionPage confirmTransactionPage = new ConfirmTransactionPage();
        confirmTransactionPage.confirm();
        BasePage.switchTo(Page.MAIN);
        cancelListingDialog.waitForListingCancelled();
        nftDetailsPage1.reload();
        sleep(3);

        assertTrue(nftDetailsPage1.isSellNftDisplayed(), "Sell nft btn should be present");
        nftDetailsPage1.expandListingTab();
        assertEquals(nftDetailsPage1.getContentFromListingTab(), "No information yet");

        //item history
        assertTrue(nftDetailsPage1.getItemHistoryEvent(1).contains("Listing"), "Event from 1 row should be listing");
        assertEquals(nftDetailsPage1.getItemHistoryEventStatus(1), "CANCELED");
        assertEquals(nftDetailsPage1.getItemHistoryPrice(1), price);
        assertEquals(nftDetailsPage1.getItemHistoryFrom(0), "you");
        assertEquals(nftDetailsPage1.getItemHistoryWhen(0), "1m ago");
        assertEquals(nftDetailsPage1.getUrl(),
                configuration().url() + "/collections/" + getTestCollectionData().getContractAddress() + "/" + nftWithoutListing_5.getTokenId());
    }

    @Test(testName = "Cancel listing reject", priority = 5)
    public void cancelListingReject() {
        String price = faker.random().nextInt(1, 5).toString();
        String currency = "4KB";

        HomePage homePage = new HomePage();
        homePage.header.search(nftWithoutListing_7.getName());
        NftDetailsPage nftDetailsPage = homePage.header.openFirstNftFromSearch();
        SellNftPage sellNftPage = nftDetailsPage.sellNFT();
        sellNftPage.openCurrencyMenu();
        sellNftPage.selectCurrency(currency);
        sellNftPage.setPrice(price);
        ListingDialog listingDialog = sellNftPage.listIt();
        BasePage.switchTo(Page.METAMASK, true);
        SignApprovePage signApprovePage = new SignApprovePage();
        signApprovePage.sign();
        BasePage.switchTo(Page.MAIN);
        listingDialog.waitForListingCompleted();
        NftDetailsPage nftDetailsPage1 = listingDialog.viewYourItem();
        nftDetailsPage1.reload();
        sleep(3);
        CancelListingDialog cancelListingDialog = nftDetailsPage1.cancelListing();
        assertEquals(cancelListingDialog.getTitle(), "Confirmation");
        assertEquals(cancelListingDialog.getBody(), "Are you sure you want to cancel this listing?");
        cancelListingDialog.cancelListing();
        BasePage.switchTo(Page.METAMASK, true);
        ConfirmTransactionPage confirmTransactionPage = new ConfirmTransactionPage();
        confirmTransactionPage.reject();
        BasePage.switchTo(Page.MAIN);
        assertFalse(nftDetailsPage1.isSellNftDisplayed(), "Sell nft btn should be not present");
        assertTrue(nftDetailsPage1.isCancelListingDisplayed(), "Cancel listing btn should be present");
    }

    //    @Test(testName = "Cancel listing from three dot menu")
//    public void cancelListingFromThreeDotMenu() {
//
//    }
}
