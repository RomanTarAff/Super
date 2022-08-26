package ui.offer;

import api.enums.Account;
import api.enums.Group;
import api.enums.Sort;
import api.enums.Status;
import api.model.request.nft.SearchNftRequest;
import api.model.response.nft.SearchNftResponse;
import api.model.response.nft.SearchNftResponseList;
import core.selenium.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.BasePage;
import ui.BaseUiTests;
import ui.enums.Page;
import ui.page.NftDetailsPage;
import ui.page.app.MyActivityPage;
import ui.page.app.MyOffersPage;
import ui.page.app.MyProfilePage;
import ui.page.app.UnreviewedCollectionDialog;
import ui.page.app.offer.CancelOfferPage;
import ui.page.app.offer.MakeOfferPage;
import ui.page.app.offer.RenewOfferPage;
import ui.page.metamask.ConfirmTransactionPage;
import ui.page.metamask.HomePage;
import ui.page.metamask.SignApprovePage;

import java.util.List;

import static core.config.ConfigurationManager.configuration;
import static org.testng.Assert.assertEquals;

public class RenewOfferTests extends BaseUiTests {

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

    @Test(testName = "Renew offer from offers tab")
    public void renewOffer() {

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
        confirmTransactionPage.confirm();
        BasePage.switchTo(Page.MAIN);
        makeOfferPage.waitForOffer();
        assertEquals(nftDetailsPage.getNotification(), "Offer canceled. We are updating the information, please wait a minute");

        homePage.openUsersMenu();
        MyProfilePage myProfilePage = homePage.header.openMyProfile();
        myProfilePage.openTab("Offers");
        MyOffersPage myOffersPage = new MyOffersPage();
        myOffersPage.status("Offers made");
        RenewOfferPage renewOfferPage = myOffersPage.table.renewOfferFromMyOffers(1);
        renewOfferPage.renewOffer();
        BasePage.switchTo(Page.METAMASK, true);
        SignApprovePage signApprovePage_1 = new SignApprovePage();
        signApprovePage_1.sign();
        BasePage.switchTo(Page.MAIN);
        makeOfferPage.waitForOffer();
        assertEquals(nftDetailsPage.getNotification(), "Offer successfully created");

        myOffersPage.reload();
        myOffersPage.status("Offers made");
        assertEquals(myOffersPage.table.getCollectionWithout(1), getTestCollectionData().getName());
        assertEquals(myOffersPage.table.getPriceWithout(1), price);
        assertEquals(myOffersPage.table.getExpirationByRow(1).getTextWithout(), "in 6mon");
    }

    @Test(testName = "Renew offer from my activity tab")
    public void renewOfferFromMyActivity() {

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

        nftDetailsPage.reload();
        nftDetailsPage.expandOffersTab();
        CancelOfferPage cancelOfferPage = nftDetailsPage.cancelOfferFromOffersTab(1);
        cancelOfferPage.cancelOffer();
        BasePage.switchTo(Page.METAMASK, true);
        ConfirmTransactionPage confirmTransactionPage = new ConfirmTransactionPage();
        confirmTransactionPage.confirm();
        BasePage.switchTo(Page.MAIN);
        makeOfferPage.waitForOffer();
        assertEquals(nftDetailsPage.getNotification(), "Offer canceled. We are updating the information, please wait a minute");

        homePage.openUsersMenu();
        MyProfilePage myProfilePage = homePage.header.openMyProfile();
        myProfilePage.openTab("My Activity");
        MyActivityPage myActivityPage = new MyActivityPage();
        myActivityPage.table.filterTags.closeFilter("Sold");
        myActivityPage.table.filtersPanel.openStatusTab();
        myActivityPage.table.filtersPanel.statusOfferMade();
        RenewOfferPage renewOfferPage = myActivityPage.table.renewOffer(1);
        renewOfferPage.renewOffer();
        BasePage.switchTo(Page.METAMASK, true);
        SignApprovePage signApprovePage_1 = new SignApprovePage();
        signApprovePage_1.sign();
        BasePage.switchTo(Page.MAIN);
        makeOfferPage.waitForOffer();
        assertEquals(nftDetailsPage.getNotification(), "Offer successfully created");
    }
}
