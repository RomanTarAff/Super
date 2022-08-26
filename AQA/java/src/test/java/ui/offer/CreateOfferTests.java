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
import ui.page.app.collection.CollectionDetailsPage;
import ui.page.app.offer.MakeOfferPage;
import ui.page.metamask.ActivityPage;
import ui.page.metamask.HomePage;
import ui.page.metamask.SignApprovePage;

import java.util.List;

import static core.config.ConfigurationManager.configuration;
import static org.testng.Assert.*;

public class CreateOfferTests extends BaseUiTests {

    private static final Logger log = Logger.getLogger(CreateOfferTests.class);
    
    private SearchNftResponse nftWithoutOffer_1;
    private SearchNftResponse nftWithoutOffer_2;
    private SearchNftResponse nftWithoutOffer_3;

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
        nftWithoutOffer_3 = nfts.get(2);
        log.info("Start browser");
        log.info("NFT 1: " + nftWithoutOffer_1);
        log.info("NFT 2: " + nftWithoutOffer_2);
        log.info("NFT 3: " + nftWithoutOffer_3);
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

    @Test(testName = "Make offer reject")
    public void makeOfferReject() {
        HomePage homePage = new HomePage();
        homePage.header.search(nftWithoutOffer_1.getName());
        NftDetailsPage nftDetailsPage = homePage.header.openFirstNftFromSearch();
        UnreviewedCollectionDialog unreviewedCollectionDialog = nftDetailsPage.makeOffer();
        MakeOfferPage makeOfferPage = unreviewedCollectionDialog.agreeOffer();
        makeOfferPage.openCurrencyMenu();
        makeOfferPage.select4Kb();
        makeOfferPage.setPrice("1");
        makeOfferPage.makeOffer();
        BasePage.switchTo(Page.METAMASK, true);
        SignApprovePage signApprovePage = new SignApprovePage();
        signApprovePage.reject();
        BasePage.switchTo(Page.MAIN);
        assertEquals(nftDetailsPage.getNotification(), "MetaMask Message Signature: User denied message signature.");
    }

    @Test(testName = "Make offer")
    public void makeOffer() {

        String price = faker.random().nextInt(1, 5).toString();

        HomePage homePage = new HomePage();
        homePage.header.search(nftWithoutOffer_2.getName());
        NftDetailsPage nftDetailsPage = homePage.header.openFirstNftFromSearch();
        UnreviewedCollectionDialog unreviewedCollectionDialog = nftDetailsPage.makeOffer();
        assertEquals(unreviewedCollectionDialog.getTitle(), "This is an unreviewed collection");
        assertEquals(unreviewedCollectionDialog.getItemName(), "Name");
        assertEquals(unreviewedCollectionDialog.getItemTotalSales(), "Total Sales");
        assertEquals(unreviewedCollectionDialog.getItemTotalVolume(), "Total Volume");
        assertEquals(unreviewedCollectionDialog.getItemContractAddress(), "Contract Address");
        assertEquals(unreviewedCollectionDialog.getItemTotalItems(), "Total Items");
        assertEquals(unreviewedCollectionDialog.getCollectionName(), getTestCollectionData().getName());
        assertEquals(unreviewedCollectionDialog.getTotalSales(), getTestCollectionData().getTotalSaleCount());
        //assertNotNull(unreviewedCollectionDialog.getPriceUsd());
        assertTrue(unreviewedCollectionDialog.getContractAddress().contains(getTestCollectionData().getContractAddress().substring(0, 6)));
        assertEquals(unreviewedCollectionDialog.getTotalItems(), getTestCollectionData().getTotalNFTItems());
        assertEquals(unreviewedCollectionDialog.getAgreement(), "I have reviewed this information and verified that this is the correct collection. I also understand that blockchain transactions are irreversible");
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

        assertEquals(nftDetailsPage.getHighestOfferTitle(), "HIGHEST OFFER");
        assertEquals(nftDetailsPage.getHighestOfferPrice(), price);
        assertEquals(nftDetailsPage.getYourOfferTitle(), "Your offer!");

        //offers tab
        nftDetailsPage.reload();
        nftDetailsPage.expandOffersTab();
        assertEquals(nftDetailsPage.getOffersTabTitle(), "OFFERS");
        assertEquals(nftDetailsPage.getOffersCount(), "1");
        assertEquals(nftDetailsPage.getOfferPrice(1), price);
        assertEquals(nftDetailsPage.getOfferExpirationDate(1), "in 6mon");
        assertEquals(nftDetailsPage.getOfferFrom(1), "you");

        //item history
        nftDetailsPage.reload();
        assertEquals(nftDetailsPage.getItemHistoryEvent(1), "Offer");
        assertEquals(nftDetailsPage.getItemHistoryPrice(1), price);
        assertEquals(nftDetailsPage.getItemHistoryFrom(0), "you");
        assertEquals(nftDetailsPage.getItemHistoryWhen(0), "1m ago");

        //activity
        ActivityPage activityPage = homePage.openActivityPage();
        activityPage.table.filterTags.closeFilter("Sold");
        activityPage.table.filtersPanel.openStatusTab();
        activityPage.table.filtersPanel.statusOfferMade();

        assertEquals(activityPage.table.getEventValue(nftWithoutOffer_2.getName()), "Offer");
        assertEquals(activityPage.table.getCollection(1), getTestCollectionData().getName());
        assertEquals(activityPage.table.getPrice(1), price);
        assertEquals(activityPage.table.getFrom(1), "you");

        //my activity
        homePage.openUsersMenu();
        MyProfilePage myProfilePage = homePage.header.openMyProfile();
        myProfilePage.openTab("My Activity");
        MyActivityPage myActivityPage = new MyActivityPage();
        myActivityPage.table.filterTags.closeFilter("Sold");
        myActivityPage.table.filtersPanel.openStatusTab();
        myActivityPage.table.filtersPanel.statusOfferMade();

        assertEquals(myActivityPage.table.getEventValue(nftWithoutOffer_2.getName()), "Offer");
        assertEquals(myActivityPage.table.getCollectionWithout(1), getTestCollectionData().getName());
        assertEquals(myActivityPage.table.getPriceWithout(1), price);
        assertEquals(myActivityPage.table.getFromWithout(1), "you");

        //my offers
        myProfilePage.openTab("Offers");
        MyOffersPage myOffersPage = new MyOffersPage();
        myOffersPage.status("Offers made");

        assertEquals(myOffersPage.table.getCollectionWithout(1), getTestCollectionData().getName());
        assertEquals(myOffersPage.table.getPriceWithout(1), price);

        //collections activity
        CollectionDetailsPage collectionDetailsPage = myOffersPage.table.clickOnCollection(1);
        collectionDetailsPage.openTab("Activity");
        CollectionActivityPage collectionActivityPage = new CollectionActivityPage();
        collectionActivityPage.table.filterTags.closeFilter("Sold");
        collectionActivityPage.table.filtersPanel.openStatusTab();
        collectionActivityPage.table.filtersPanel.statusOfferMade();

        assertEquals(collectionActivityPage.table.getEventValue(nftWithoutOffer_2.getName()), "Offer");
        assertEquals(collectionActivityPage.table.getCollection(1), getTestCollectionData().getName());
        assertEquals(collectionActivityPage.table.getPrice(1), price);
        assertEquals(collectionActivityPage.table.getFrom(1), "you");
    }

    @Test(testName = "Make few offers")
    public void makeFewOffers() {

        String price_1 = faker.random().nextInt(1, 3).toString();
        String price_2 = faker.random().nextInt(4, 7).toString();

        HomePage homePage = new HomePage();
        homePage.header.search(nftWithoutOffer_3.getName());
        NftDetailsPage nftDetailsPage = homePage.header.openFirstNftFromSearch();
        UnreviewedCollectionDialog unreviewedCollectionDialog = nftDetailsPage.makeOffer();
        MakeOfferPage makeOfferPage = unreviewedCollectionDialog.agreeOffer();
        makeOfferPage.openCurrencyMenu();
        makeOfferPage.select4Kb();
        makeOfferPage.setPrice(price_1);
        makeOfferPage.makeOffer();
        BasePage.switchTo(Page.METAMASK, true);
        SignApprovePage signApprovePage = new SignApprovePage();
        signApprovePage.sign();
        BasePage.switchTo(Page.MAIN);
        makeOfferPage.waitForOffer();
        assertEquals(nftDetailsPage.getNotification(), "Offer successfully created");

        nftDetailsPage.reload();
        nftDetailsPage.makeOffer();
        unreviewedCollectionDialog.agreeOffer();
        makeOfferPage.openCurrencyMenu();
        makeOfferPage.select4Kb();
        makeOfferPage.setPrice(price_2);
        makeOfferPage.makeOffer();
        BasePage.switchTo(Page.METAMASK, true);
        SignApprovePage signApprovePage_1 = new SignApprovePage();
        signApprovePage_1.sign();
        BasePage.switchTo(Page.MAIN);
        makeOfferPage.waitForOffer();

        //offers panel
        nftDetailsPage.reload();
        nftDetailsPage.expandOffersTab();
        assertEquals(nftDetailsPage.getOffersTabTitle(), "OFFERS");
        assertEquals(nftDetailsPage.getOffersCount(), "2");
        assertEquals(nftDetailsPage.getOfferPrice(1), price_1);
        assertEquals(nftDetailsPage.getOfferExpirationDate(1), "in 6mon");
        assertEquals(nftDetailsPage.getOfferFrom(1), "you");

        assertEquals(nftDetailsPage.getOfferPrice(2), price_2);
        assertEquals(nftDetailsPage.getOfferExpirationDate(2), "in 6mon");
        assertEquals(nftDetailsPage.getOfferFrom(2), "you");

        assertEquals(nftDetailsPage.getHighestOfferTitle(), "HIGHEST OFFER");
        assertEquals(nftDetailsPage.getHighestOfferPrice(), price_2);
        assertEquals(nftDetailsPage.getYourOfferTitle(), "Your offer!");

        //item history
        nftDetailsPage.reload();
        assertEquals(nftDetailsPage.getItemHistoryEvent(1), "Offer");
        assertEquals(nftDetailsPage.getItemHistoryPrice(1), price_2);
        assertEquals(nftDetailsPage.getItemHistoryFrom(0), "you");
        assertEquals(nftDetailsPage.getItemHistoryWhen(0), "1m ago");

        assertEquals(nftDetailsPage.getItemHistoryEvent(2), "Offer");
        assertEquals(nftDetailsPage.getItemHistoryPrice(2), price_1);
        assertEquals(nftDetailsPage.getItemHistoryFrom(1), "you");
        assertEquals(nftDetailsPage.getItemHistoryWhen(1), "1m ago");
    }
}
