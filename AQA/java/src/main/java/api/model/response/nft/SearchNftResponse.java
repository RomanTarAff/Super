package api.model.response.nft;

import api.model.Blockchain;
import api.model.response.HighestOffer;
import api.model.response.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SearchNftResponse {

    private String lastSoldAt;
    private String createdAt;
    private Integer trendingScore;
    private int likesCount;
    private boolean acceptOffers;
    private String contractType;
    private Blockchain blockchain;
    private Collection collection;
    private String name;
    private String ownerAddress;
    private String ownerId;
    private String tokenId;
    private String id;
    private String previewUrl;
    private HighestOffer highestOffer;
    private String pinnedAt;
    private Price price;
    private String mediaUrl;

    @JsonProperty("isLiked")
    private boolean isLiked;

    @JsonProperty("isPinned")
    private boolean isPinned;

    @JsonProperty("isWatched")
    private boolean isWatched;

    private List<String> statuses;
    private Object lastPrice;
    private Object lowestPrice;
    private Object minBid;
    private Object topBid;
    private ListingResponse listing;

    private Object offer;
}
