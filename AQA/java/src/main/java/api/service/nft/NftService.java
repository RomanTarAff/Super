package api.service.nft;

import api.model.request.nft.SearchNftRequest;
import api.service.BaseApi;
import api.util.assertions.AssertableResponse;
import io.qameta.allure.Step;

public class NftService extends BaseApi {

    @Step("Call endpoint - search NFTs")
    public AssertableResponse searchNftItems(SearchNftRequest searchNftRequest, String token) {
        return new AssertableResponse(setupNftSpec(token)
                .body(searchNftRequest)
                .post(NftEndPoint.SEARCH_NFT_ITEMS));
    }

    public AssertableResponse likeNft(String nftId, String token) {
        return new AssertableResponse(setupNftSpec(token)
                .pathParam("id", nftId)
                .put(NftEndPoint.LIKE_UNLIKE_NFT));
    }

    public AssertableResponse unlikeNft(String nftId, String token) {
        return new AssertableResponse(setupNftSpec(token)
                .pathParam("id", nftId)
                .delete(NftEndPoint.LIKE_UNLIKE_NFT));
    }

    public AssertableResponse unpinNft(String nftId, String token) {
        return new AssertableResponse(setupNftSpec(token)
                .pathParam("id", nftId)
                .delete(NftEndPoint.PIN_UNPIN_NFT));
    }

    public AssertableResponse pinNft(String nftId, String token) {
        return new AssertableResponse(setupNftSpec(token)
                .pathParam("id", nftId)
                .put(NftEndPoint.PIN_UNPIN_NFT));
    }

    public AssertableResponse getPinnedNft(String userId, String token) {
        return new AssertableResponse(setupNftSpec(token)
                .pathParam("id", userId)
                .get(NftEndPoint.GET_USER_PINNED_NFT));
    }

    public AssertableResponse getNftDetails(String contractAddress, String tokenId, String token) {
        return new AssertableResponse(setupNftSpec(token)
                .pathParam("contractAddress", contractAddress)
                .pathParam("tokenId", tokenId)
                .get(NftEndPoint.GET_NFT_DETAILS));
    }

    @Step("Call endpoint - get active NFT offers")
    public AssertableResponse getNftActiveOffers(String nftId, String token) {
        return new AssertableResponse(setupNftSpec(token)
                .pathParam("id", nftId)
                .get(NftEndPoint.GET_ACTIVE_OFFERS));
    }

    public AssertableResponse getOfferDetails(String offerId, String token) {
        return new AssertableResponse(setupNftSpec(token)
                .pathParam("id", offerId)
                .get(NftEndPoint.GET_OFFER_DETAILS));
    }
}
