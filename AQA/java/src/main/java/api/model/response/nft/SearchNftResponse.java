package api.model.response.nft;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SearchNftResponse {
    private String id;
    private String name;
    private String previewUrl;
    private List<String> statuses;
    private Collection collection;
    private int likesCount;
    private Integer trendingScore;
    private String ownerAddress;
    private String ownerId;
    private String tokenId;
    private String pinnedAt;
    private String mediaUrl;

    @JsonProperty("isLiked")
    private boolean isLiked;

    @JsonProperty("isPinned")
    private boolean isPinned;

    @JsonProperty("isWatched")
    private boolean isWatched;

    private boolean acceptOffers;
    private Object blockchain;
    private Object price;
    private Object lastPrice;
    private Object lowestPrice;
    private Object minBid;
    private Object topBid;
    private Object highestOffer;
    private ListingResponse listing;
    private Object offer;
    private String contractType;
    private String lastSoldAt;
    private String createdAt;
}
