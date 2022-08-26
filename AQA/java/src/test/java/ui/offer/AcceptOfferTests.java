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
import ui.page.app.offer.AcceptOfferPage;
import ui.page.metamask.ConfirmTransactionPage;
import ui.page.metamask.HomePage;

import java.util.List;
import java.util.stream.Collectors;

import static core.config.ConfigurationManager.configuration;
import static org.testng.Assert.assertEquals;

public class AcceptOfferTests extends BaseUiTests {

    private SearchNftResponse nftWithoutOffer_1;

    @BeforeClass
    public void getNftsWithoutOffers() {
        SearchNftRequest search = SearchNftRequest.builder()
                .search("Lesley")
                .statuses(List.of(Status.HAS_OFFERS.getValue()))
                .group(Group.IN_WALLET.getValue())
                .limit(100)
                .sort(Sort.MOST_RECENT.getValue())
                .build();
        List<SearchNftResponse> nfts = nftService.searchNftItems(search, System.getProperty(Account.MINT.getENV()))
                .asClass(SearchNftResponseList.class).getNfts().stream().filter(nft -> nft.getHighestOffer().getPrice().getSymbol().equals("4KB"))
                .collect(Collectors.toList());
        nftWithoutOffer_1 = nfts.get(0);

        initMetamask(Account.MINT);
    }

    @AfterMethod
    public void toMainPage() {
        DriverManager.getInstance().getDriver().get(configuration().url());
    }

    @AfterClass
    public void closeJob() {
        DriverManager.getInstance().closeBrowser();
    }

    @Test(testName = "Accept offer reject", priority = 0)
    public void acceptOfferReject() {

        HomePage homePage = new HomePage();
        homePage.header.search(nftWithoutOffer_1.getName());
        NftDetailsPage nftDetailsPage = homePage.header.openFirstNftFromSearch();
        AcceptOfferPage acceptOfferPage = nftDetailsPage.acceptOffer();
        acceptOfferPage.acceptOffer();
        BasePage.switchTo(Page.METAMASK, true);
        ConfirmTransactionPage confirmTransactionPage = new ConfirmTransactionPage();
        confirmTransactionPage.reject();
        BasePage.switchTo(Page.MAIN);
        assertEquals(acceptOfferPage.getError(), "MetaMask Tx Signature: User denied transaction signature.");
    }

    @Test(testName = "Accept offer from nft details", priority = 1)
    public void acceptOfferFromNftDetails() {

        HomePage homePage = new HomePage();
        homePage.header.search(nftWithoutOffer_1.getName());
        NftDetailsPage nftDetailsPage = homePage.header.openFirstNftFromSearch();
        AcceptOfferPage acceptOfferPage = nftDetailsPage.acceptOffer();
        acceptOfferPage.acceptOffer();
        BasePage.switchTo(Page.METAMASK, true);
        ConfirmTransactionPage confirmTransactionPage = new ConfirmTransactionPage();
        confirmTransactionPage.confirm();
        BasePage.switchTo(Page.MAIN);
        acceptOfferPage.waitForSale();
        assertEquals(acceptOfferPage.getSaleMessage(), "Sale Complete!");

        DriverManager.getInstance().getDriver().get(configuration().url());
        homePage.header.search(nftWithoutOffer_1.getName());
        NftDetailsPage nftDetailsPage_1 = homePage.header.openFirstNftFromSearch();
        nftDetailsPage_1.reload();
        nftDetailsPage_1.reload();
        assertEquals(nftDetailsPage_1.getNftOwnerId(), configuration().url() + "/" + Account.BUY.getAddress().toLowerCase());
    }
}
