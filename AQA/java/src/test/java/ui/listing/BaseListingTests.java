package ui.listing;

import api.enums.Account;
import api.enums.Group;
import api.enums.Sort;
import api.enums.Status;
import api.model.request.nft.SearchNftRequest;
import api.model.response.nft.SearchNftResponse;
import api.model.response.nft.SearchNftResponseList;
import base.BaseTests;
import core.selenium.DriverManager;
import org.apache.log4j.Logger;
import org.testng.annotations.*;
import ui.BaseUiTests;

import java.util.List;

public class BaseListingTests extends BaseUiTests {

    private static final Logger log = Logger.getLogger(BaseListingTests.class);

    protected SearchNftResponse nftWithoutListing_1;
    protected SearchNftResponse nftWithoutListing_2;
    protected SearchNftResponse nftWithoutListing_3;
    protected SearchNftResponse nftWithoutListing_4;
    protected SearchNftResponse nftWithoutListing_5;
    protected SearchNftResponse nftWithoutListing_6;
    protected SearchNftResponse nftWithoutListing_7;

    @BeforeTest
    public void getNftNotOnSale() {

        SearchNftRequest search = SearchNftRequest.builder()
                .search("Lesley")
                .statuses(List.of(Status.NOT_LISTED.getValue()))
                .group(Group.IN_WALLET.getValue())
                .limit(100)
                .sort(Sort.MOST_RECENT.getValue())
                .build();
        List<SearchNftResponse> nfts = nftService.searchNftItems(search, MINT_TOKEN)
                .asClass(SearchNftResponseList.class).getNfts();
        nftWithoutListing_1 = nfts.get(0);
        nftWithoutListing_2 = nfts.get(1);
        nftWithoutListing_3 = nfts.get(2);
        nftWithoutListing_4 = nfts.get(3);
        nftWithoutListing_5 = nfts.get(4);
        nftWithoutListing_6 = nfts.get(5);
        nftWithoutListing_7 = nfts.get(6);
    }

    @BeforeClass
    public void initBrowser() {
        log.info("Start browser");
        log.info("Listing 1: " + nftWithoutListing_1);
        log.info("Listing 2: " + nftWithoutListing_2);
        log.info("Listing 3: " + nftWithoutListing_3);
        log.info("Listing 4: " + nftWithoutListing_4);
        log.info("Listing 5: " + nftWithoutListing_5);
        log.info("Listing 6: " + nftWithoutListing_6);
        log.info("Listing 7: " + nftWithoutListing_7);
        initMetamask(Account.MINT);
    }

    @AfterClass
    public void closeJob() {
        DriverManager.getInstance().closeBrowser();
    }

}
