package api.service.nft;

public class NftEndPoint {

    public static final String SEARCH_NFT_ITEMS = "/v1/search/nfts";
    public static final String LIKE_UNLIKE_NFT = "/v1/nfts/{id}/likes";
    public static final String PIN_UNPIN_NFT = "/v1/nfts/{id}/pin";
    public static final String GET_USER_PINNED_NFT = "/v1/users/{id}/nfts/pinned";

}
