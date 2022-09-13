package api.social;

import api.BaseApiTests;
import api.enums.Account;
import api.model.response.CodeMessageResponse;
import api.model.response.admin.CollectionListResponse;
import api.model.response.social.CollectionResponse;
import api.util.conditions.Conditions;
import io.qameta.allure.Epic;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.*;

@Epic("Follow unfollow collection")
public class FollowUnfollowCollection extends BaseApiTests {

    private CollectionResponse collection;
    private String followers;

    @BeforeClass
    public void getCollection() {

        CollectionListResponse collections = adminService.getCollections(System.getProperty(Account.ADMIN.getENV()))
                .asClass(CollectionListResponse.class);

        collection = socialService
                .getCollection(collections.getCollections().get(0).getContractAddress(), System.getProperty(Account.MINT.getENV()))
                .asClass(CollectionResponse.class);
        followers = collection.getTotalFollowers();
    }

    @Test(description = "Follow and unfollow collection")
    public void followCollection() {

        //follow
        Object follow = socialService.followCollection(collection.getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        CollectionResponse collectionSearched = socialService
                .getCollection(collection.getContractAddress(), System.getProperty(Account.MINT.getENV()))
                .asClass(CollectionResponse.class);

        soft.assertEquals(Integer.parseInt(collectionSearched.getTotalFollowers()), Integer.parseInt(followers + 1));
        soft.assertTrue(collectionSearched.isFollowed(), "Used collection should be followed");

        //unfollow collection
        Object unfollow = socialService.unfollowCollection(collection.getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        CollectionResponse collectionUnfollow = socialService
                .getCollection(collection.getContractAddress(), System.getProperty(Account.MINT.getENV()))
                .asClass(CollectionResponse.class);

        soft.assertEquals(Integer.parseInt(collectionUnfollow.getTotalFollowers()), Integer.parseInt(followers));
        soft.assertFalse(collectionUnfollow.isFollowed(), "Used collection should not be followed");
        soft.assertAll();
    }

    @Test(description = "Follow collection. Unauthorized")
    public void followUnauthorized() {
        CodeMessageResponse codeMessageResponse = socialService.followCollection(collection.getId(), null)
                .shouldHave(Conditions.statusCode(HTTP_UNAUTHORIZED))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "AuthorizationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Unauthorized");
        soft.assertAll();
    }

    @Test(description = "Unfollow collection. Unauthorized")
    public void unfollowUnauthorized() {
        CodeMessageResponse codeMessageResponse = socialService.unfollowCollection(collection.getId(), null)
                .shouldHave(Conditions.statusCode(HTTP_UNAUTHORIZED))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "AuthorizationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Unauthorized");
        soft.assertAll();
    }

    @Test(description = "Follow collection. Invalid collection id")
    public void followInvalidCollectionId() {
        CodeMessageResponse codeMessageResponse = socialService.followCollection("invalid", System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/id must match format \"uuid\"");
        soft.assertAll();
    }

    @Test(description = "Unfollow collection. Invalid collection id")
    public void unfollowInvalidCollectionId() {
        CodeMessageResponse codeMessageResponse = socialService.unfollowCollection("invalid", System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/id must match format \"uuid\"");
        soft.assertAll();
    }

    @Test(description = "Follow collection. Collection id not found")
    public void followNotExistingCollectionId() {
        CodeMessageResponse codeMessageResponse = socialService.followCollection(faker.internet().uuid(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NOT_FOUND))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "NotFoundError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Collection not found");
        soft.assertAll();
    }

    @Test(description = "Unfollow collection. Collection id not found")
    public void unfollowNotExistingCollectionId() {
        CodeMessageResponse codeMessageResponse = socialService.unfollowCollection(faker.internet().uuid(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NOT_FOUND))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "NotFoundError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Collection not found");
        soft.assertAll();
    }
}
