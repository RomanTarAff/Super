package api.social;

import api.BaseApiTests;
import api.enums.Account;
import api.enums.Sort;
import api.model.response.CodeMessageResponse;
import api.model.response.social.CollectionsListResponse;
import api.util.conditions.Conditions;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;


import java.util.HashMap;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("Get my collections")
public class GetMyCollectionsTests extends BaseApiTests {
    
    @Test(description = "Get my collections - have no collections")
    public void getMyCollections() {

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 10);
        params.put("sort", Sort.TRENDING.getValue());

        CollectionsListResponse collections = socialService.getMyCollections(params, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(CollectionsListResponse.class);

        assertEquals(collections.getTotalCount(), 0);
        assertTrue(collections.getCollections().isEmpty());
    }

    @Test(description = "Get my collections. Unauthorized")
    public void getMyCollectionsUnauthorized() {

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 10);
        params.put("sort", Sort.TRENDING.getValue());

        CodeMessageResponse codeMessageResponse = socialService.getMyCollections(params, null)
                .shouldHave(Conditions.statusCode(HTTP_UNAUTHORIZED))
                .asClass(CodeMessageResponse.class);
        assertEquals(codeMessageResponse.getCode(), "AuthorizationError");
        assertEquals(codeMessageResponse.getMessage(), "Unauthorized");
    }

    @Test(description = "Get my collections without required fields")
    public void getMyCollectionsWithoutRequiredFields() {

        Map<String, Object> params = new HashMap<>();

        CodeMessageResponse codeMessageResponse = socialService.getMyCollections(params, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data must have required property 'limit'");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(1), "data must have required property 'sort'");
    }

    @Test(description = "Get my collections. Invalid sort")
    public void getMyCollectionsInvalidSort() {

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 10);
        params.put("sort", Sort.MOST_RECENT.getValue());

        CodeMessageResponse codeMessageResponse = socialService.getMyCollections(params, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/sort must be equal to one of the allowed values");
    }
}
