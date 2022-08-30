package api.model.response.social;

import api.model.FloorPrice;
import api.model.response.Token;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CollectionResponse {

    private String id;
    private String contractAddress;
    private String name;
    private String description;
    private String avatarUrl;
    private String bannerUrl;
    private String totalNFTItems;
    private String totalFollowers;
    private String totalOwners;
    private String avgSaleAmount;
    private String totalSaleAmount;
    private String websiteUrl;
    private FloorPrice floorPrice;
    private Object socialLinks;
    private List<Token> tokens;
    private Object royalties;

    @JsonProperty("isFollowed")
    private boolean isFollowed;

    private boolean editable;
    private String totalSaleCount;
    private boolean verified;
    private String slug;
}
