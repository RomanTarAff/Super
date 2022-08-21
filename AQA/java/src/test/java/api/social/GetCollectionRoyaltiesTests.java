package api.social;

import api.BaseApiTests;
import api.enums.Account;
import api.model.response.CodeMessageResponse;
import api.util.conditions.Conditions;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.*;
import static org.testng.Assert.assertEquals;

public class GetCollectionRoyaltiesTests extends BaseApiTests {

    @Test(testName = "Get collection royalties")
    public void getCollectionRoyalties() {

        Object emptyRoyalties = socialService.getCollectionRoyalties(getTestCollectionData().getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .getResponse().asString();
        assertEquals(emptyRoyalties, "{\"royalties\":[]}");
    }

    @Test(testName = "Get collection royalties unauthorized")
    public void getCollectionRoyaltiesUnauthorized() {

        Object emptyRoyalties = socialService.getCollectionRoyalties(getTestCollectionData().getId(), null)
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .getResponse().asString();
        assertEquals(emptyRoyalties, "{\"royalties\":[]}");
    }

    @Test(testName = "Get collection royalties invalid id")
    public void getCollectionRoyaltiesInvalidId() {

        CodeMessageResponse codeMessageResponse = socialService.getCollectionRoyalties(getTestCollectionData().getContractAddress(),
                        System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/id must match format \"uuid\"");
    }
}
