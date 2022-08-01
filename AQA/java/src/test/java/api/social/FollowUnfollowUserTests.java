package api.social;

import api.BaseApiTests;
import api.model.response.CodeMessageResponse;
import api.model.response.admin.UserListResponse;
import api.model.response.social.CollectionResponse;
import api.model.response.social.ProfileResponse;
import api.util.conditions.Conditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.*;

public class FollowUnfollowUserTests extends BaseApiTests {

    private ProfileResponse user;

    @BeforeClass
    public void getUser() {

        UserListResponse users = adminService.getUsers(MINT_TOKEN)
                .asClass(UserListResponse.class);

        for (int i = 0; i < users.getUsers().size(); i++) {
            ProfileResponse userSearched = socialService
                    .getProfile(users.getUsers().get(i).getEthAddress(), MINT_TOKEN)
                    .asClass(ProfileResponse.class);
            if (userSearched.getTotalFollowers().equals("0")) {
                user = userSearched;
                return;
            }
        }
        soft.assertEquals(user.getTotalFollowers(), "0");
        soft.assertFalse(user.isFollowed(), "User should not be followed");
        soft.assertAll();
    }

    @Test(testName = "Follow and unfollow user")
    public void followUnfollowUser() {

        //follow
        ProfileResponse follow = socialService.followUser(user.getEthAddress(), MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT))
                .asClass(ProfileResponse.class);

        ProfileResponse userSearched = socialService
                .getProfile(user.getEthAddress(), MINT_TOKEN)
                .asClass(ProfileResponse.class);

        soft.assertEquals(userSearched.getTotalFollowers(), "1");
        soft.assertTrue(userSearched.isFollowed(), "User should be followed");

        //unfollow user
        ProfileResponse unfollow = socialService.unfollowUser(user.getEthAddress(), MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT))
                .asClass(ProfileResponse.class);

        ProfileResponse userUnfollow = socialService
                .getCollection(user.getEthAddress(), MINT_TOKEN)
                .asClass(ProfileResponse.class);

        soft.assertEquals(userUnfollow.getTotalFollowers(), "0");
        soft.assertFalse(userUnfollow.isFollowed(), "User should not be followed");
        soft.assertAll();
    }

    @Test(testName = "Follow unauthorized")
    public void followUnauthorized() {
        CodeMessageResponse codeMessageResponse = socialService.followUser(user.getEthAddress(), "invalid")
                .shouldHave(Conditions.statusCode(HTTP_UNAUTHORIZED))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "AuthorizationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Unauthorized");
        soft.assertAll();
    }

    @Test(testName = "Unfollow unauthorized")
    public void unfollowUnauthorized() {
        CodeMessageResponse codeMessageResponse = socialService.unfollowUser(user.getEthAddress(), "invalid")
                .shouldHave(Conditions.statusCode(HTTP_UNAUTHORIZED))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "AuthorizationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Unauthorized");
        soft.assertAll();
    }

    @Test(testName = "Follow user invalid user ethAddress")
    public void followUserInvalidEthAddress() {
        CodeMessageResponse codeMessageResponse = socialService.followUser("invalid", MINT_TOKEN)
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/id must match format \"uuid\"");
        soft.assertAll();
    }

    @Test(testName = "Unfollow user invalid user ethAddress")
    public void unfollowInvalidEthAddress() {
        CodeMessageResponse codeMessageResponse = socialService.unfollowUser("invalid", MINT_TOKEN)
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/id must match format \"uuid\"");
        soft.assertAll();
    }

    @Test(testName = "Follow user not existing user ethAddress")
    public void followNotExistingEthAddress() {
        CodeMessageResponse codeMessageResponse = socialService.followUser(faker.internet().uuid(), MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_NOT_FOUND))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "");
        soft.assertEquals(codeMessageResponse.getMessage(), "");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "");
        soft.assertAll();
    }

    @Test(testName = "Unfollow user not existing ethAddress")
    public void unfollowNotExistingEthAddress() {
        CodeMessageResponse codeMessageResponse = socialService.unfollowUser(faker.internet().uuid(), MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_NOT_FOUND))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "");
        soft.assertEquals(codeMessageResponse.getMessage(), "");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "");
        soft.assertAll();
    }

    @Test(testName = "Follow and unfollow user already")
    public void followUnFollowUserAlready() {

        //follow
        CollectionResponse follow = socialService.followUser(user.getEthAddress(), MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT))
                .asClass(CollectionResponse.class);

        //follow again
        CodeMessageResponse codeMessageResponse = socialService.followUser(user.getEthAddress(), MINT_TOKEN)
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "");
        soft.assertEquals(codeMessageResponse.getMessage(), "");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "");


        //unfollow collection
        CollectionResponse unfollow = socialService.unfollowUser(user.getEthAddress(), MINT_TOKEN)
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT))
                .asClass(CollectionResponse.class);

        //unfollow again
        CodeMessageResponse codeMessageResponseUnFollow = socialService.unfollowUser(user.getEthAddress(), MINT_TOKEN)
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponseUnFollow.toString());
        soft.assertEquals(codeMessageResponseUnFollow.getCode(), "");
        soft.assertEquals(codeMessageResponseUnFollow.getMessage(), "");
        soft.assertEquals(codeMessageResponseUnFollow.getPayload().getErrors().get(0), "");
        soft.assertAll();
    }
}
