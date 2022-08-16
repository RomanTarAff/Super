package api.social;

import api.BaseApiTests;
import api.enums.Account;
import api.model.response.CodeMessageResponse;
import api.model.response.social.CollectionResponse;
import api.util.conditions.Conditions;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.*;
import static org.testng.Assert.*;

public class GetCollectionsTests extends BaseApiTests {

    @Test(testName = "Get collection")
    public void getMyCollection() {

        CollectionResponse collection = socialService.getCollection(getTestCollectionData().getContractAddress(), System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(CollectionResponse.class);

        assertNotNull(collection.getId());
        assertNotNull(collection.getContractAddress());
        assertNotNull(collection.getName());
        assertNotNull(collection.getAvatarUrl());
        assertNotNull(collection.getBannerUrl());
        assertNotNull(collection.getTotalFollowers());
        assertNotNull(collection.getTotalNFTItems());
        assertNotNull(collection.getTotalOwners());
        assertNotNull(collection.getTotalSaleCount());
        assertNotNull(collection.getTotalSaleAmount());
        assertNotNull(collection.getTokens());
        assertNotNull(collection.getFloorPrice());
    }

    @Test(testName = "Get collections not found")
    public void getCollectionsNotFound() {

        CodeMessageResponse codeMessageResponse = socialService.getCollection(faker.internet().uuid(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NOT_FOUND))
                .asClass(CodeMessageResponse.class);
        assertEquals(codeMessageResponse.getCode(), "NotFoundError");
        assertEquals(codeMessageResponse.getMessage(), "Collection not found");
    }

    @Test(testName = "Get collections invalid token address")
    public void getCollectionsInvalidTokenAddress() {

        CodeMessageResponse codeMessageResponse = socialService.getCollection("gfgf", System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NOT_FOUND))
                .asClass(CodeMessageResponse.class);
        assertEquals(codeMessageResponse.getCode(), "NotFoundError");
        assertEquals(codeMessageResponse.getMessage(), "Collection not found");
    }
}
