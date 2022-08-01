package api.social;

import api.BaseApiTests;
import api.model.response.CodeMessageResponse;
import api.model.response.admin.CollectionListResponse;
import api.model.response.social.CollectionResponse;
import api.util.conditions.Conditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.*;

public class FollowUnfollowCollection extends BaseApiTests {

    private CollectionResponse collection;

    @BeforeClass
    public void getCollection() {

        CollectionListResponse collections = adminService.getCollections(MINT_TOKEN)
                .asClass(CollectionListResponse.class);

        for (int i = 0; i < collections.getCollections().size(); i++) {
            CollectionResponse collectionSearched = socialService
                    .getCollection(collections.getCollections().get(i).getContractAddress(), MINT_TOKEN)
                    .asClass(CollectionResponse.class);
            if (collectionSearched.getTotalFollowers().equals("0")) {
                collection = collectionSearched;
                return;
            }
        }
        soft.assertEquals(collection.getTotalFollowers(), "0");
        soft.assertFalse(collection.isFollowed(), "Used collection should not be followed");
        soft.assertAll();
    }

    @Test(testName = "Follow and unfollow collection")
    public void followCollection() {

        //follow
        CollectionResponse follow = socialService.followCollection(collection.getId(), MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT))
                .asClass(CollectionResponse.class);

        CollectionResponse collectionSearched = socialService
                .getCollection(collection.getContractAddress(), MINT_TOKEN)
                .asClass(CollectionResponse.class);

        soft.assertEquals(collectionSearched.getTotalFollowers(), "1");
        soft.assertTrue(collectionSearched.isFollowed(), "Used collection should be followed");

        //unfollow collection
        CollectionResponse unfollow = socialService.unfollowCollection(collection.getId(), MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT))
                .asClass(CollectionResponse.class);

        CollectionResponse collectionUnfollow = socialService
                .getCollection(collection.getContractAddress(), MINT_TOKEN)
                .asClass(CollectionResponse.class);

        soft.assertEquals(collectionUnfollow.getTotalFollowers(), "0");
        soft.assertFalse(collectionUnfollow.isFollowed(), "Used collection should not be followed");
        soft.assertAll();
    }

    @Test(testName = "Follow unauthorized")
    public void followUnauthorized() {
        CodeMessageResponse codeMessageResponse = socialService.followCollection(collection.getId(), "invalid")
                .shouldHave(Conditions.statusCode(HTTP_UNAUTHORIZED))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "AuthorizationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Unauthorized");
        soft.assertAll();
    }

    @Test(testName = "Unfollow unauthorized")
    public void unfollowUnauthorized() {
        CodeMessageResponse codeMessageResponse = socialService.unfollowCollection(collection.getId(), "invalid")
                .shouldHave(Conditions.statusCode(HTTP_UNAUTHORIZED))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "AuthorizationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Unauthorized");
        soft.assertAll();
    }

    @Test(testName = "Follow collection invalid collection id")
    public void followInvalidCollectionId() {
        CodeMessageResponse codeMessageResponse = socialService.followCollection("invalid", MINT_TOKEN)
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/id must match format \"uuid\"");
        soft.assertAll();
    }

    @Test(testName = "Unfollow collection invalid collection id")
    public void unfollowInvalidCollectionId() {
        CodeMessageResponse codeMessageResponse = socialService.unfollowCollection("invalid", MINT_TOKEN)
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/id must match format \"uuid\"");
        soft.assertAll();
    }

    @Test(testName = "Follow collection not existing collection id")
    public void followNotExistingCollectionId() {
        CodeMessageResponse codeMessageResponse = socialService.followCollection(faker.internet().uuid(), MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_NOT_FOUND))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "");
        soft.assertEquals(codeMessageResponse.getMessage(), "");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "");
        soft.assertAll();
    }

    @Test(testName = "Unfollow collection not existing collection id")
    public void unfollowNotExistingCollectionId() {
        CodeMessageResponse codeMessageResponse = socialService.unfollowCollection(faker.internet().uuid(), MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_NOT_FOUND))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "");
        soft.assertEquals(codeMessageResponse.getMessage(), "");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "");
        soft.assertAll();
    }

    @Test(testName = "Follow and unfollow already")
    public void followUnFollowCollectionAlready() {

        //follow
        CollectionResponse follow = socialService.followCollection(collection.getId(), MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT))
                .asClass(CollectionResponse.class);

        //follow again
        CodeMessageResponse codeMessageResponse = socialService.followCollection(collection.getId(), MINT_TOKEN)
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "");
        soft.assertEquals(codeMessageResponse.getMessage(), "");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "");


        //unfollow collection
        CollectionResponse unfollow = socialService.unfollowCollection(collection.getId(), MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT))
                .asClass(CollectionResponse.class);

        //unfollow again
        CodeMessageResponse codeMessageResponseUnFollow = socialService.followCollection(collection.getId(), MINT_TOKEN)
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponseUnFollow.toString());
        soft.assertEquals(codeMessageResponseUnFollow.getCode(), "");
        soft.assertEquals(codeMessageResponseUnFollow.getMessage(), "");
        soft.assertEquals(codeMessageResponseUnFollow.getPayload().getErrors().get(0), "");
        soft.assertAll();
    }
}
