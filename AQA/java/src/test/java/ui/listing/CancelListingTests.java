package ui.listing;

import core.selenium.DriverManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ui.BasePage;
import ui.enums.Page;
import ui.page.NftDetailsPage;
import ui.page.app.sell.CancelListingDialog;
import ui.page.app.sell.ListingDialog;
import ui.page.app.sell.SellNftPage;
import ui.page.metamask.ConfirmTransactionPage;
import ui.page.metamask.HomePage;
import ui.page.metamask.SignApprovePage;

import static core.config.ConfigurationManager.configuration;

public class CancelListingTests extends BaseListingTests {

    private static final Logger log = Logger.getLogger(CreateListingTests.class);

    @Test(testName = "Cancel listing from nft details")
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
        soft.assertEquals(cancelListingDialog.getTitle(), "Confirmation");
        soft.assertEquals(cancelListingDialog.getBody(), "Are you sure you want to cancel this listing?");
        cancelListingDialog.cancelListing();
        BasePage.switchTo(Page.METAMASK, true);
        ConfirmTransactionPage confirmTransactionPage = new ConfirmTransactionPage();
        confirmTransactionPage.confirm();
        BasePage.switchTo(Page.MAIN);
        cancelListingDialog.waitForListingCancelled();
        nftDetailsPage1.reload();
        sleep(3);

        soft.assertTrue(nftDetailsPage1.isSellNftDisplayed(), "Sell nft btn should be present");
        nftDetailsPage1.expandListingTab();
        soft.assertEquals(nftDetailsPage1.getContentFromListingTab(), "No information yet");

        //item history
        soft.assertTrue(nftDetailsPage1.getItemHistoryEvent(1).contains("Listing"), "Event from 1 row should be listing");
        soft.assertEquals(nftDetailsPage1.getItemHistoryEventStatus(1), "CANCELED");
        soft.assertEquals(nftDetailsPage1.getItemHistoryPrice(1), price);
        soft.assertEquals(nftDetailsPage1.getItemHistoryFrom(0), "you");
        soft.assertEquals(nftDetailsPage1.getItemHistoryWhen(0), "1m ago");
        soft.assertEquals(nftDetailsPage1.getUrl(),
                configuration().url() + "/collections/" + getTestCollectionData().getContractAddress() + "/" + nftWithoutListing_5.getTokenId());
        soft.assertAll();
    }

//    @Test(testName = "Cancel listing from three dot menu")
//    public void cancelListingFromThreeDotMenu() {
//
//    }

    @Test(testName = "Cancel listing reject")
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
        soft.assertEquals(cancelListingDialog.getTitle(), "Confirmation");
        soft.assertEquals(cancelListingDialog.getBody(), "Are you sure you want to cancel this listing?");
        cancelListingDialog.cancelListing();
        BasePage.switchTo(Page.METAMASK, true);
        ConfirmTransactionPage confirmTransactionPage = new ConfirmTransactionPage();
        confirmTransactionPage.reject();
        BasePage.switchTo(Page.MAIN);
        soft.assertFalse(nftDetailsPage1.isSellNftDisplayed(), "Sell nft btn should be not present");
        soft.assertTrue(nftDetailsPage1.isCancelListingDisplayed(), "Cancel listing btn should be present");
        soft.assertEquals(nftDetailsPage1.getNotification(), "MetaMask Tx Signature: User denied transaction signature.");
        soft.assertAll();
    }

}
