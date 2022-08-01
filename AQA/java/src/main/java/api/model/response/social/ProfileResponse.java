package api.model.response.social;

import api.model.request.social.SocialLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProfileResponse {

    private String id;
    private String username;
    private String email;
    private String description;
    private String avatarUrl;
    private String bannerUrl;
    private String ethAddress;
    private String totalNFTItems;
    private String totalFollowers;
    private String avgSaleAmount;
    private String totalSaleAmount;
    private String traded;
    private String websiteUrl;

    private SocialLinks socialLinks;
    private String linkedServices;
    private boolean hideAverageSaleAmount;
    private boolean hideTradedAmount;
    private String userStatus;
    private String registeredAt;

    @JsonProperty("isFollowed")
    private boolean isFollowed;
}
