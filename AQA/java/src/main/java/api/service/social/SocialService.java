package api.service.social;

import api.model.request.social.ChangeCollectionRoyaltiesRequest;
import api.model.request.social.CollectionSettingsRequest;
import api.model.request.social.EditProfileRequest;
import api.model.request.social.SearchSuggestionsRequest;
import api.service.BaseApi;
import api.util.assertions.AssertableResponse;
import io.qameta.allure.Step;

import java.util.Map;

public class SocialService extends BaseApi {

    @Step("Get my profile")
    public AssertableResponse getMyProfile(String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .get(SocialEndPoint.GET_MY_PROFILE));
    }

    public AssertableResponse getProfile(String ethAddress, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .pathParam("ethAddress", ethAddress)
                .get(SocialEndPoint.GET_PROFILE));
    }

    @Step("Edit my profile")
    public AssertableResponse editProfile(EditProfileRequest request, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .body(request)
                .patch(SocialEndPoint.EDIT_MY_PROFILE));
    }

    public AssertableResponse searchSuggestions(SearchSuggestionsRequest searchSuggestionsRequest, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .body(searchSuggestionsRequest)
                .post(SocialEndPoint.SEARCH_SUGGESTIONS));
    }

    public AssertableResponse getCollection(String contractAddress, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .pathParam("id", contractAddress)
                .get(SocialEndPoint.GET_COLLECTION));
    }

    public AssertableResponse getCollectionRoyalties(String contractAddress, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .pathParam("id", contractAddress)
                .get(SocialEndPoint.GET_COLLECTION_ROYALTIES));
    }

    public AssertableResponse changeCollectionRoyalties(ChangeCollectionRoyaltiesRequest request, String contractAddress, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .pathParam("id", contractAddress)
                .body(request)
                .post(SocialEndPoint.CHANGE_COLLECTION_ROYALTIES));
    }

    public AssertableResponse getCollectionOwnersCount(String id, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .pathParam("id", id)
                .get(SocialEndPoint.GET_COLLECTION_OWNERS_COUNT));
    }

    public AssertableResponse followCollection(String collectionId, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .pathParam("collectionId", collectionId)
                .post(SocialEndPoint.FOLLOW_UNFOLLOW_COLLECTION));
    }

    public AssertableResponse unfollowCollection(String collectionId, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .pathParam("collectionId", collectionId)
                .delete(SocialEndPoint.FOLLOW_UNFOLLOW_COLLECTION));
    }

    public AssertableResponse followUser(String id, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .pathParam("id", id)
                .put(SocialEndPoint.FOLLOW_UNFOLLOW_USER));
    }

    public AssertableResponse unfollowUser(String id, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .pathParam("id", id)
                .delete(SocialEndPoint.FOLLOW_UNFOLLOW_USER));
    }

    public AssertableResponse getMyCollections(Map<String, Object> params, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .params(params)
                .get(SocialEndPoint.GET_MY_COLLECTIONS));
    }

    public AssertableResponse getCollectionSettings(String collectionId, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .pathParam("id", collectionId)
                .get(SocialEndPoint.GET_COLLECTION_SETTINGS));
    }

    public AssertableResponse updateCollectionSettings(String collectionId, CollectionSettingsRequest request, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .pathParam("id", collectionId)
                .body(request)
                .patch(SocialEndPoint.UPDATE_COLLECTION_SETTINGS));
    }
}
