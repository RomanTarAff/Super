package ui.offer;

import api.enums.Account;
import api.enums.Group;
import api.enums.Sort;
import api.enums.Status;
import api.model.request.nft.SearchNftRequest;
import api.model.response.nft.SearchNftResponse;
import api.model.response.nft.SearchNftResponseList;
import core.selenium.DriverManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.BasePage;
import ui.BaseUiTests;
import ui.enums.Page;
import ui.page.NftDetailsPage;
import ui.page.app.*;
import ui.page.app.offer.CancelOfferPage;
import ui.page.app.offer.MakeOfferPage;
import ui.page.metamask.ConfirmTransactionPage;
import ui.page.metamask.HomePage;
import ui.page.metamask.SignApprovePage;

import java.util.List;

import static core.config.ConfigurationManager.configuration;
import static org.testng.Assert.assertEquals;

public class CancelOfferTests extends BaseUiTests {

    private static final Logger log = Logger.getLogger(CreateOfferTests.class);

    private SearchNftResponse nftWithoutOffer_1;
    private SearchNftResponse nftWithoutOffer_2;

    @BeforeClass
    public void getNftsWithoutOffers() {
        SearchNftRequest search = SearchNftRequest.builder()
                .search("Lesley")
                .statuses(List.of(Status.NO_OFFERS.getValue()))
                .group(Group.IN_WALLET.getValue())
                .limit(100)
                .sort(Sort.MOST_RECENT.getValue())
                .build();
        List<SearchNftResponse> nfts = nftService.searchNftItems(search, System.getProperty(Account.MINT.getENV()))
                .asClass(SearchNftResponseList.class).getNfts();
        nftWithoutOffer_1 = nfts.get(0);
        nftWithoutOffer_2 = nfts.get(1);
        log.info("Start browser");
        log.info("NFT 1: " + nftWithoutOffer_1);
        log.info("NFT 2: " + nftWithoutOffer_2);

        initMetamask(Account.BUY);
    }

    @AfterMethod
    public void toMainPage() {
        DriverManager.getInstance().getDriver().get(configuration().url());
    }

    @AfterClass
    public void closeJob() {
        DriverManager.getInstance().closeBrowser();
    }

    @Test(testName = "Cancel offer reject")
    public void cancelOfferReject() {
        String price = faker.random().nextInt(1, 5).toString();

        HomePage homePage = new HomePage();
        homePage.header.search(nftWithoutOffer_1.getName());
        NftDetailsPage nftDetailsPage = homePage.header.openFirstNftFromSearch();
        UnreviewedCollectionDialog unreviewedCollectionDialog = nftDetailsPage.makeOffer();
        MakeOfferPage makeOfferPage = unreviewedCollectionDialog.agreeOffer();
        makeOfferPage.openCurrencyMenu();
        makeOfferPage.select4Kb();
        makeOfferPage.setPrice(price);
        makeOfferPage.makeOffer();
        BasePage.switchTo(Page.METAMASK, true);
        SignApprovePage signApprovePage = new SignApprovePage();
        signApprovePage.sign();
        BasePage.switchTo(Page.MAIN);
        makeOfferPage.waitForOffer();
        assertEquals(nftDetailsPage.getNotification(), "Offer successfully created");

        nftDetailsPage.reload();
        nftDetailsPage.expandOffersTab();
        CancelOfferPage cancelOfferPage = nftDetailsPage.cancelOfferFromOffersTab(1);
        cancelOfferPage.cancelOffer();
        BasePage.switchTo(Page.METAMASK, true);
        ConfirmTransactionPage confirmTransactionPage = new ConfirmTransactionPage();
        confirmTransactionPage.reject();
        BasePage.switchTo(Page.MAIN);
    }

    @Test(testName = "Cancel offer from offers tab")
    public void makeOffer() {

        String price = faker.random().nextInt(1, 5).toString();

        HomePage homePage = new HomePage();
        homePage.header.search(nftWithoutOffer_2.getName());
        NftDetailsPage nftDetailsPage = homePage.header.openFirstNftFromSearch();
        UnreviewedCollectionDialog unreviewedCollectionDialog = nftDetailsPage.makeOffer();
        MakeOfferPage makeOfferPage = unreviewedCollectionDialog.agreeOffer();
        makeOfferPage.openCurrencyMenu();
        makeOfferPage.select4Kb();
        makeOfferPage.setPrice(price);
        makeOfferPage.makeOffer();
        BasePage.switchTo(Page.METAMASK, true);
        SignApprovePage signApprovePage = new SignApprovePage();
        signApprovePage.sign();
        BasePage.switchTo(Page.MAIN);
        makeOfferPage.waitForOffer();
        assertEquals(nftDetailsPage.getNotification(), "Offer successfully created");

        CancelOfferPage cancelOfferPage = nftDetailsPage.cancelOfferFromOffersTab(1);
        cancelOfferPage.cancelOffer();
        BasePage.switchTo(Page.METAMASK, true);
        ConfirmTransactionPage confirmTransactionPage = new ConfirmTransactionPage();
        confirmTransactionPage.confirm();
        BasePage.switchTo(Page.MAIN);
        makeOfferPage.waitForOffer();
        assertEquals(nftDetailsPage.getNotification(), "Offer canceled. We are updating the information, please wait a minute");

        //offers tab
        nftDetailsPage.reload();
        nftDetailsPage.expandOffersTab();
        assertEquals(nftDetailsPage.getContentFromOffersTab(), "");

        //item history
        nftDetailsPage.reload();
        assertEquals(nftDetailsPage.getItemHistoryEvent(1), "Offer");
        assertEquals(nftDetailsPage.getItemHistoryEventStatus(1), "CANCELED");
        assertEquals(nftDetailsPage.getItemHistoryPrice(1), price);
        assertEquals(nftDetailsPage.getItemHistoryFrom(0), "you");
        assertEquals(nftDetailsPage.getItemHistoryWhen(0), "1m ago");

        //my offers
        homePage.openUsersMenu();
        MyProfilePage myProfilePage = homePage.header.openMyProfile();
        myProfilePage.openTab("Offers");
        MyOffersPage myOffersPage = new MyOffersPage();
        myOffersPage.status("Offers made");

        assertEquals(myOffersPage.table.getCollectionWithout(1), getTestCollectionData().getName());
        assertEquals(myOffersPage.table.getExpirationByRow(1).getTextWithout(), "Canceled");
        assertEquals(myOffersPage.table.getPriceWithout(1), price);
    }
}
