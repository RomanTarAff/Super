package api.nft;

import api.BaseApiTests;
import api.enums.Account;
import api.enums.Group;
import api.enums.Sort;
import api.enums.Status;
import api.model.request.nft.SearchNftRequest;
import api.model.response.CodeMessageResponse;
import api.model.response.nft.OfferDetails;
import api.model.response.nft.SearchNftResponse;
import api.model.response.nft.SearchNftResponseList;
import api.util.conditions.Conditions;
import io.qameta.allure.Epic;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static java.net.HttpURLConnection.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Epic("Get offers details")
public class GetOfferDetailsTests extends BaseApiTests {

    private SearchNftResponse nft;

    @BeforeClass
    public void getOffer() {
        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .statuses(List.of(Status.HAS_OFFERS.getValue()))
                .limit(1)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        //search nft
        nft = nftService.searchNftItems(search, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class).getNfts().get(0);
    }

    @Test(description = "Get offer details")
    public void getOfferDetails() {

        OfferDetails offerDetails = nftService.getOfferDetails(nft.getHighestOffer().getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(OfferDetails.class);

        assertEquals(offerDetails.getCollection().getContractAddress(), getTestCollectionData().getContractAddress());
        assertNotNull(offerDetails.getCollection());
        assertNotNull(offerDetails.getId());
        assertNotNull(offerDetails.getHighestOffer());
        assertNotNull(offerDetails.getNft());
        assertNotNull(offerDetails.getSender());
    }

    @Test(description = "Get offer details - not found")
    public void getOfferDetailsNotFound() {
        CodeMessageResponse codeMessageResponse = nftService.getOfferDetails(nft.getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NOT_FOUND))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "OfferNotFound");
        assertEquals(codeMessageResponse.getMessage(), "Offer not found");
    }

    @Test(description = "Get offer details - unauthorized")
    public void getOfferDetailsUnauthorized() {
        CodeMessageResponse codeMessageResponse = nftService.getOfferDetails(nft.getHighestOffer().getId(), null)
                .shouldHave(Conditions.statusCode(HTTP_UNAUTHORIZED))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "AuthorizationError");
        assertEquals(codeMessageResponse.getMessage(), "Unauthorized");
    }
}
