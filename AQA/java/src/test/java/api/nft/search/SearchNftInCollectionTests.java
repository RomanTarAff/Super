package api.nft.search;

import api.BaseApiTests;
import api.enums.Sort;
import api.enums.Status;
import api.model.request.nft.SearchNftRequest;
import api.model.response.nft.SearchNftResponse;
import api.model.response.nft.SearchNftResponseList;
import api.util.conditions.Conditions;
import com.google.common.collect.Ordering;
import org.testng.annotations.Test;
import util.DateHelper;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static java.net.HttpURLConnection.HTTP_OK;

public class SearchNftInCollectionTests extends BaseApiTests {

    private final String EJIK_COLLECTION_ID = "5e6ef1e3-be31-515d-9de5-07b2b89ff137";

    @Test(testName = "Search nfts in random collection most recent")
    public void searchMostRecent() {
        SearchNftRequest search = SearchNftRequest.builder()
                .collections(List.of(EJIK_COLLECTION_ID))
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

        //check collection id
        nfts.getNfts().forEach(nft -> {
            soft.assertEquals(nft.getCollection().getId(), EJIK_COLLECTION_ID);
        });
        soft.assertAll();
    }

    @Test(testName = "Search nfts in random collection most popular")
    public void searchMostPopular() {
        SearchNftRequest search = SearchNftRequest.builder()
                .collections(List.of(EJIK_COLLECTION_ID))
                .limit(50)
                .sort(Sort.MOST_POPULAR.getValue())
                .build();

        SearchNftResponseList nfts = nftService.searchNftItems(search, MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        //check most popular filter
        List<Integer> likes = nfts.getNfts()
                .stream().map(SearchNftResponse::getLikesCount).collect(Collectors.toList());
        soft.assertTrue(Ordering.natural().reverse().isOrdered(likes));

        //check collection id
        nfts.getNfts().forEach(nft -> {
            soft.assertEquals(nft.getCollection().getId(), EJIK_COLLECTION_ID);
        });
        soft.assertAll();
    }

    @Test(testName = "Search nfts in random collection trending")
    public void searchTrending() {
        SearchNftRequest search = SearchNftRequest.builder()
                .collections(List.of(EJIK_COLLECTION_ID))
                .limit(50)
                .sort(Sort.MOST_POPULAR.getValue())
                .build();

        SearchNftResponseList nfts = nftService.searchNftItems(search, MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        //check most popular filter
        List<Integer> likes = nfts.getNfts()
                .stream().map(SearchNftResponse::getTrendingScore).collect(Collectors.toList());
        soft.assertTrue(Ordering.natural().reverse().isOrdered(likes));

        //check collection id
        nfts.getNfts().forEach(nft -> {
            soft.assertEquals(nft.getCollection().getId(), EJIK_COLLECTION_ID);
        });
        soft.assertAll();
    }

    //todo price 2 tests

    @Test(testName = "Search nfts in random collection on sale")
    public void searchOnSale() {
        SearchNftRequest search = SearchNftRequest.builder()
                .collections(List.of(EJIK_COLLECTION_ID))
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
            soft.assertEquals(nft.getCollection().getId(), EJIK_COLLECTION_ID);
            soft.assertNotNull(nft.getListing(), "Listing should not be null");
        });
        soft.assertAll();
    }

    @Test(testName = "Search nfts in random collection has offers")
    public void searchHasOffers() {
        SearchNftRequest search = SearchNftRequest.builder()
                .collections(List.of(EJIK_COLLECTION_ID))
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
            soft.assertEquals(nft.getCollection().getId(), EJIK_COLLECTION_ID);
            soft.assertNotNull(nft.getHighestOffer(), "Highest offer should not be null");
        });
        soft.assertAll();
    }

    @Test(testName = "Search nfts in random collection no offers")
    public void searchNoOffers() {
        SearchNftRequest search = SearchNftRequest.builder()
                .collections(List.of(EJIK_COLLECTION_ID))
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
            soft.assertEquals(nft.getCollection().getId(), EJIK_COLLECTION_ID);
            soft.assertNull(nft.getOffer(), "NFT should be without offers");
        });
        soft.assertAll();
    }
}
