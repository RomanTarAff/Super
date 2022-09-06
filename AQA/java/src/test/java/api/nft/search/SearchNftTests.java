package api.nft.search;

import api.BaseApiTests;
import api.enums.Account;
import api.enums.Group;
import api.enums.Sort;
import api.enums.Status;
import api.model.request.nft.SearchNftRequest;
import api.model.response.nft.SearchNftResponseList;
import api.util.conditions.Conditions;
import base.BaseTests;
import com.google.common.collect.Ordering;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import util.DateHelper;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.testng.Assert.*;

public class SearchNftTests extends BaseApiTests {

    private static final Logger log = Logger.getLogger(BaseTests.class);

    @Test(testName = "Search nfts in wallet most recent")
    public void searchInWalletMostRecent() {
        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .limit(15)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        SearchNftResponseList nfts = nftService.searchNftItems(search, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        //check most recent filter
        List<Instant> createdAt = nfts.getNfts()
                .stream().map(nft -> DateHelper.getInstantFromString(nft.getCreatedAt())).collect(Collectors.toList());
        soft.assertTrue(Ordering.natural().reverse().isOrdered(createdAt));

        //check user id
        nfts.getNfts().forEach(nft -> {
            soft.assertEquals(nft.getOwnerAddress(), userBuy.getEthAddress());
            soft.assertEquals(nft.getOwnerId(), userBuy.getId());
        });
        soft.assertAll();
    }

    @Test(testName = "Search nfts in wallet recently listed", enabled = false)
    public void searchInWalletRecentlyListed() {
        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .limit(30)
                .sort(Sort.RECENTLY_LISTED.getValue())
                .build();

        SearchNftResponseList nfts = nftService.searchNftItems(search, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        //check most recent filter
        List<Instant> createdAt = nfts.getNfts()
                .stream().map(nft -> DateHelper.getInstantFromString(nft.getCreatedAt())).collect(Collectors.toList());
        soft.assertTrue(Ordering.natural().reverse().isOrdered(createdAt));

        //check user id
        nfts.getNfts().forEach(nft -> {
            assertEquals(nft.getOwnerAddress(), userBuy.getEthAddress());
            assertEquals(nft.getOwnerId(), userBuy.getId());
        });
    }
//
//    // 2 tests price
//
    @Test(testName = "Search nfts in wallet on sale")
    public void searchInWalletOnSale() {
        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .statuses(List.of(Status.ON_SALE.getValue()))
                .limit(15)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        SearchNftResponseList nfts = nftService.searchNftItems(search, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        //check most recent filter
        List<Instant> createdAt = nfts.getNfts()
                .stream().map(nft -> DateHelper.getInstantFromString(nft.getCreatedAt())).collect(Collectors.toList());
        soft.assertTrue(Ordering.natural().reverse().isOrdered(createdAt));

        //check collection id and on sale
        nfts.getNfts().forEach(nft -> {
            soft.assertEquals(nft.getOwnerAddress(), userBuy.getEthAddress());
            soft.assertNotNull(nft.getListing(), "Listing should not be null");
        });
        soft.assertAll();
    }

    @Test(testName = "Search nfts in wallet has offers")
    public void searchHasOffers() {
        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .statuses(List.of(Status.HAS_OFFERS.getValue()))
                .limit(15)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        SearchNftResponseList nfts = nftService.searchNftItems(search, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        //check most recent filter
        List<Instant> createdAt = nfts.getNfts()
                .stream().map(nft -> DateHelper.getInstantFromString(nft.getCreatedAt())).collect(Collectors.toList());
        soft.assertTrue(Ordering.natural().reverse().isOrdered(createdAt));

        //check collection id and on sale
        nfts.getNfts().forEach(nft -> {
            assertEquals(nft.getOwnerAddress(), userBuy.getEthAddress());
            soft.assertNotNull(nft.getHighestOffer(), "Highest offer should not be null");
        });
        soft.assertAll();
    }

    @Test(testName = "Search nfts in wallet no offers")
    public void searchNoOffers() {
        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .statuses(List.of(Status.NO_OFFERS.getValue()))
                .limit(5)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        SearchNftResponseList nfts = nftService.searchNftItems(search, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        //check most recent filter
        List<Instant> createdAt = nfts.getNfts()
                .stream().map(nft -> DateHelper.getInstantFromString(nft.getCreatedAt())).collect(Collectors.toList());
        soft.assertTrue(Ordering.natural().reverse().isOrdered(createdAt));

        //check collection id and on sale
        nfts.getNfts().forEach(nft -> {
            soft.assertEquals(nft.getOwnerAddress(), userBuy.getEthAddress());
            soft.assertNull(nft.getHighestOffer(), "NFT should be without offer");
        });
        soft.assertAll();
    }

    @Test(testName = "Search nfts favorites")
    public void searchFavorites() {
        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.FAVORITES.getValue())
                .limit(5)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        SearchNftResponseList nfts = nftService.searchNftItems(search, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        //check most recent filter
        List<Instant> createdAt = nfts.getNfts()
                .stream().map(nft -> DateHelper.getInstantFromString(nft.getCreatedAt())).collect(Collectors.toList());
        soft.assertTrue(Ordering.natural().reverse().isOrdered(createdAt));

        //check collection id and isLiked
        nfts.getNfts().forEach(nft -> {
            soft.assertEquals(nft.getOwnerAddress(), userBuy.getEthAddress());
            soft.assertTrue(nft.isLiked(), "NFT should be liked in favorites group");
        });
        soft.assertAll();
    }

    @Test(testName = "Search nfts limit value", enabled = false)
    public void searchLimitValue() {
        int limit = faker.random().nextInt(10, 20);
        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .limit(limit)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        SearchNftResponseList nfts = nftService.searchNftItems(search, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        //check most recent filter
        List<Instant> createdAt = nfts.getNfts()
                .stream().map(nft -> DateHelper.getInstantFromString(nft.getCreatedAt())).collect(Collectors.toList());
        assertTrue(Ordering.natural().reverse().isOrdered(createdAt));
        assertEquals(nfts.getNfts().size(), limit);
    }

}
