package api.service.social;

public class SocialEndPoint {

    public static final String GET_MY_PROFILE = "/v1/me";
    public static final String EDIT_MY_PROFILE = "/v1/me";
    public static final String GET_MY_PLAYLISTS = "/v1/me/playlists";
    public static final String GET_MY_BADGES = "/v1/me/badges";
    public static final String GET_MY_COLLECTIONS = "/v1/me/collections";
    public static final String GET_NONCE = "/v1/me/nonce";
    public static final String GET_PROFILE = "/v1/{ethAddress}";
    public static final String FOLLOW_UNFOLLOW_USER = "/v1/users/{id}/followers";
    public static final String FOLLOW_UNFOLLOW_COLLECTION = "/v1/collections/{collectionId}/followers";
    public static final String REPORTS = "/v1/users/{id}/reports";
    public static final String GET_USER_PLAYLISTS = "/v1/users/{id}/playlists";

    public static final String GET_COLLECTIONS_INFO = "/v1/collections/{id}/info";
    public static final String GET_COLLECTION = "/v2/collections/{id}";
    public static final String SEARCH_SUGGESTIONS = "/v1/search/suggestions";

}
