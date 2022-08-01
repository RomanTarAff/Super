package ui.listing;

import api.enums.Account;
import api.enums.Group;
import api.enums.Sort;
import api.enums.Status;
import api.model.request.nft.SearchNftRequest;
import api.model.response.nft.SearchNftResponse;
import api.model.response.nft.SearchNftResponseList;
import core.selenium.DriverManager;
import org.testng.annotations.*;
import ui.BaseUiTests;

import java.util.List;

public class BaseListingTests extends BaseUiTests {

    protected SearchNftResponse nftWithoutListing_1;
    protected SearchNftResponse nftWithoutListing_2;
    protected SearchNftResponse nftWithoutListing_3;
    protected SearchNftResponse nftWithoutListing_4;
    protected SearchNftResponse nftWithoutListing_5;
    protected SearchNftResponse nftWithoutListing_6;
    protected SearchNftResponse nftWithoutListing_7;

    @BeforeClass
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

    @BeforeMethod
    public void initBrowser() {
        initMetamask(Account.MINT);
    }

    @AfterMethod
    public void closeJob() {
        DriverManager.getInstance().closeBrowser();
    }

}
