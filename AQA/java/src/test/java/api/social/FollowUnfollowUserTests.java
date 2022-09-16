package api.social;

import api.BaseApiTests;
import api.enums.Account;
import api.model.response.CodeMessageResponse;
import api.model.response.admin.UserListResponse;
import api.model.response.social.ProfileResponse;
import api.util.conditions.Conditions;
import io.qameta.allure.Epic;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static java.net.HttpURLConnection.*;

@Epic("Follow unfollow user")
public class FollowUnfollowUserTests extends BaseApiTests {

    private ProfileResponse user;

    @BeforeClass
    public void getUser() {

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 100);
        params.put("offset", 1);

        UserListResponse users = adminService.getUsers(params, System.getProperty(Account.ADMIN.getENV()))
                .asClass(UserListResponse.class);

        for (int i = 0; i < users.getUsers().size(); i++) {
            ProfileResponse userSearched = socialService
                    .getProfile(users.getUsers().get(i).getEthAddress(), System.getProperty(Account.MINT.getENV()))
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


    @Test(description = "Follow and unfollow user")
    public void followUnfollowUser() {

        //follow
        Object follow = socialService.followUser(user.getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse userSearched = socialService
                .getProfile(user.getEthAddress(), System.getProperty(Account.MINT.getENV()))
                .asClass(ProfileResponse.class);

        soft.assertEquals(userSearched.getTotalFollowers(), "1");
        soft.assertTrue(userSearched.isFollowed(), "User should be followed");

        //unfollow user
        Object unfollow = socialService.unfollowUser(user.getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse userUnfollow = socialService
                .getProfile(user.getEthAddress(), System.getProperty(Account.MINT.getENV()))
                .asClass(ProfileResponse.class);

        soft.assertEquals(userUnfollow.getTotalFollowers(), "0");
        soft.assertFalse(userUnfollow.isFollowed(), "User should not be followed");
        soft.assertAll();
    }

    @Test(description = "Follow user. Unauthorized")
    public void followUnauthorized() {
        CodeMessageResponse codeMessageResponse = socialService.followUser(user.getId(), null)
                .shouldHave(Conditions.statusCode(HTTP_UNAUTHORIZED))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "AuthorizationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Unauthorized");
        soft.assertAll();
    }

    @Test(description = "Unfollow user. Unauthorized")
    public void unfollowUnauthorized() {
        CodeMessageResponse codeMessageResponse = socialService.unfollowUser(user.getId(), null)
                .shouldHave(Conditions.statusCode(HTTP_UNAUTHORIZED))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "AuthorizationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Unauthorized");
        soft.assertAll();
    }

    @Test(description = "Follow user. Invalid user id")
    public void followUserInvalidId() {
        CodeMessageResponse codeMessageResponse = socialService.followUser("invalid", System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/followedUserId must match format \"uuid\"");
        soft.assertAll();
    }

    @Test(description = "Unfollow user. Invalid user id")
    public void unfollowInvalidId() {
        CodeMessageResponse codeMessageResponse = socialService.unfollowUser("invalid", System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/followedUserId must match format \"uuid\"");
        soft.assertAll();
    }

}
