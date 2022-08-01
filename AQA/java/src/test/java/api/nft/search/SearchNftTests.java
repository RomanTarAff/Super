package api.nft.search;

import api.BaseApiTests;
import api.enums.Group;
import api.enums.Sort;
import api.enums.Status;
import api.model.request.nft.SearchNftRequest;
import api.model.response.nft.SearchNftResponseList;
import api.util.conditions.Conditions;
import com.google.common.collect.Ordering;
import org.testng.annotations.Test;
import util.DateHelper;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static java.net.HttpURLConnection.HTTP_OK;

public class SearchNftTests extends BaseApiTests {

    @Test(testName = "Search nfts in wallet most recent")
    public void searchInWalletMostRecent() {
        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .limit(50)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        SearchNftResponseList nfts = nftService.searchNftItems(search, MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        //check most recent filter
        List<Instant> createdAt = nfts.getNfts()
                .stream().map(nft -> DateHelper.getInstantFromString(nft.getCreatedAt())).collect(Collectors.toList());
        soft.assertTrue(Ordering.natural().reverse().isOrdered(createdAt));

        //check user id
        nfts.getNfts().forEach(nft -> {
            soft.assertEquals(nft.getOwnerAddress(), userMint.getEthAddress());
            soft.assertEquals(nft.getOwnerId(), userMint.getId());
        });
        soft.assertAll();
    }

    @Test(testName = "Search nfts in wallet recently listed")
    public void searchInWalletRecentlyListed() {
        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .limit(50)
                .sort(Sort.RECENTLY_LISTED.getValue())
                .build();

        SearchNftResponseList nfts = nftService.searchNftItems(search, MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        //check most recent filter
        List<Instant> createdAt = nfts.getNfts()
                .stream().map(nft -> DateHelper.getInstantFromString(nft.getCreatedAt())).collect(Collectors.toList());
        soft.assertTrue(Ordering.natural().reverse().isOrdered(createdAt));

        //check user id
        nfts.getNfts().forEach(nft -> {
            soft.assertEquals(nft.getOwnerAddress(), userMint.getEthAddress());
            soft.assertEquals(nft.getOwnerId(), userMint.getId());
        });
        soft.assertAll();
    }
//
//    // 2 tests price
//
    @Test(testName = "Search nfts in wallet on sale")
    public void searchInWalletOnSale() {
        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .statuses(List.of(Status.ON_SALE.getValue()))
                .limit(50)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        SearchNftResponseList nfts = nftService.searchNftItems(search, MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        //check most recent filter
        List<Instant> createdAt = nfts.getNfts()
                .stream().map(nft -> DateHelper.getInstantFromString(nft.getCreatedAt())).collect(Collectors.toList());
        soft.assertTrue(Ordering.natural().reverse().isOrdered(createdAt));

        //check collection id and on sale
        nfts.getNfts().forEach(nft -> {
            soft.assertEquals(nft.getOwnerAddress(), userMint.getEthAddress());
            soft.assertNotNull(nft.getListing(), "Listing should not be null");
        });
        soft.assertAll();
    }

    @Test(testName = "Search nfts in wallet has offers")
    public void searchHasOffers() {
        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .statuses(List.of(Status.HAS_OFFERS.getValue()))
                .limit(50)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        SearchNftResponseList nfts = nftService.searchNftItems(search, MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        //check most recent filter
        List<Instant> createdAt = nfts.getNfts()
                .stream().map(nft -> DateHelper.getInstantFromString(nft.getCreatedAt())).collect(Collectors.toList());
        soft.assertTrue(Ordering.natural().reverse().isOrdered(createdAt));

        //check collection id and on sale
        nfts.getNfts().forEach(nft -> {
            soft.assertEquals(nft.getOwnerAddress(), userMint.getEthAddress());
            soft.assertNotNull(nft.getHighestOffer(), "Highest offer should not be null");
        });
        soft.assertAll();
    }

    @Test(testName = "Search nfts in wallet no offers")
    public void searchNoOffers() {
        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .statuses(List.of(Status.NO_OFFERS.getValue()))
                .limit(50)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        SearchNftResponseList nfts = nftService.searchNftItems(search, MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        //check most recent filter
        List<Instant> createdAt = nfts.getNfts()
                .stream().map(nft -> DateHelper.getInstantFromString(nft.getCreatedAt())).collect(Collectors.toList());
        soft.assertTrue(Ordering.natural().reverse().isOrdered(createdAt));

        //check collection id and on sale
        nfts.getNfts().forEach(nft -> {
            soft.assertEquals(nft.getOwnerAddress(), userMint.getEthAddress());
            soft.assertNull(nft.getHighestOffer(), "NFT should be without offer");
        });
        soft.assertAll();
    }

    @Test(testName = "Search nfts favorites")
    public void searchFavorites() {
        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.FAVORITES.getValue())
                .limit(50)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        SearchNftResponseList nfts = nftService.searchNftItems(search, MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        //check most recent filter
        List<Instant> createdAt = nfts.getNfts()
                .stream().map(nft -> DateHelper.getInstantFromString(nft.getCreatedAt())).collect(Collectors.toList());
        soft.assertTrue(Ordering.natural().reverse().isOrdered(createdAt));

        //check collection id and isLiked
        nfts.getNfts().forEach(nft -> {
            soft.assertEquals(nft.getOwnerAddress(), userMint.getEthAddress());
            soft.assertTrue(nft.isLiked(), "NFT should be liked in favorites group");
        });
        soft.assertAll();
    }

    @Test(testName = "Search nfts limit value")
    public void searchLimitValue() {
        int limit = faker.random().nextInt(10, 20);
        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.FAVORITES.getValue())
                .limit(limit)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        SearchNftResponseList nfts = nftService.searchNftItems(search, MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        //check most recent filter
        List<Instant> createdAt = nfts.getNfts()
                .stream().map(nft -> DateHelper.getInstantFromString(nft.getCreatedAt())).collect(Collectors.toList());
        soft.assertTrue(Ordering.natural().reverse().isOrdered(createdAt));
        soft.assertEquals(nfts.getNfts().size(), limit);
        soft.assertAll();
    }

}
