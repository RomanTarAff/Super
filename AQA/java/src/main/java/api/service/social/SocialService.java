package api.service.social;

import api.model.request.social.EditProfileRequest;
import api.model.request.social.SearchSuggestionsRequest;
import api.service.BaseApi;
import api.util.assertions.AssertableResponse;

public class SocialService extends BaseApi {

    public AssertableResponse getMyProfile(String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .get(SocialEndPoint.GET_MY_PROFILE));
    }

    public AssertableResponse getProfile(String ethAddress, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .pathParam("ethAddress", ethAddress)
                .get(SocialEndPoint.GET_PROFILE));
    }

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

    public AssertableResponse followUser(String ethAddress, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .pathParam("ethAddress", ethAddress)
                .post(SocialEndPoint.FOLLOW_UNFOLLOW_USER));
    }

    public AssertableResponse unfollowUser(String ethAddress, String token) {
        return new AssertableResponse(setupSocialSpec(token)
                .pathParam("ethAddress", ethAddress)
                .delete(SocialEndPoint.FOLLOW_UNFOLLOW_USER));
    }



}
