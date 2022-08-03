package api.model.response.nft;

import api.model.Blockchain;
import api.model.response.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NftDetails {

    private String contractType;
    private List<String> metadata;
    private String nftStatus;
    private String externalLink;
    private String name;
    private Blockchain blockchain;
    private boolean acceptOffers;
    private String previewUrl;
    private String tokenId;
    private long createdAt;
    private int views;
    private int likesCount;
    private int watchers;
    private String contractAddress; //owner
    private String description;
    private String id;
    private Price price;
    private String mediaUrl;
    private List<Token> tokens;
    private Owner owner;
    private List<String> statuses;
    private Creator creator;
    private CollectionNftDetails collection;
    private ListingResponse listing;
    private Object offer;
    private Object offers;
    private String iframeUrl;

    @JsonProperty("isLiked")
    private boolean isLiked;

    @JsonProperty("isPinned")
    private boolean isPinned;

    @JsonProperty("isWatched")
    private boolean isWatched;

    private HighestOffer highestOffer;
    private Object lastPrice;
    private String bannedAt;
}
