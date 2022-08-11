package api.nft;

import api.BaseApiTests;
import api.enums.Account;
import api.enums.Group;
import api.enums.Sort;
import api.enums.Status;
import api.model.request.nft.SearchNftRequest;
import api.model.response.CodeMessageResponse;
import api.model.response.nft.OffersListResponse;
import api.model.response.nft.SearchNftResponse;
import api.model.response.nft.SearchNftResponseList;
import api.util.conditions.Conditions;
import org.testng.annotations.Test;

import java.util.List;

import static java.net.HttpURLConnection.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class GetNftActiveOffers extends BaseApiTests {

    @Test(testName = "Get NFT active offers")
    public void getNftActiveOffers() {

        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .statuses(List.of(Status.HAS_OFFERS.getValue()))
                .limit(1)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        //search nft
        SearchNftResponse nft = nftService.searchNftItems(search, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class).getNfts().get(0);

        OffersListResponse offers = nftService.getNftActiveOffers(nft.getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(OffersListResponse.class);

        offers.getOffers().forEach(offer -> {
            assertNotNull(offer.getExpiresAt());
            assertNotNull(offer.getPrice());
            assertNotNull(offer.getPrice().getName());
            assertNotNull(offer.getPrice().getSymbol());
            assertNotNull(offer.getPrice().getDecimals());
            assertNotNull(offer.getPrice().getTokenContractAddress());
            assertNotNull(offer.getPrice().getValue());
            assertNotNull(offer.getId());
            assertNotNull(offer.getPrice().getValue());
            assertNotNull(offer.getSender().getAddress());
            assertNotNull(offer.getSender().getName());
            assertNotNull(offer.getSender().getId());
        });
    }

    @Test(testName = "Get NFT active offers not found nft id")
    public void getNftActiveOffersNotFoundNftId() {
        CodeMessageResponse codeMessageResponse = nftService.getNftActiveOffers(faker.internet().uuid(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NOT_FOUND))
                .asClass(CodeMessageResponse.class);
        assertEquals(codeMessageResponse.getCode(), "NFTNotFound");
        assertEquals(codeMessageResponse.getMessage(), "NFT not found");
    }

    @Test(testName = "Get NFT active offers invalid nft id")
    public void getNftActiveOffersInvalidNftId() {
        CodeMessageResponse codeMessageResponse = nftService.getNftActiveOffers("435gvgf", System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/id must match format \"uuid\"");
    }
}
