package api.model.request.social;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EditProfileRequest {

    private String username;
    private String email;
    private String description;
    private String websiteUrl;
    private String avatarUrl;
    private String bannerUrl;
    private SocialLinks socialLinks;
    private boolean hideAverageSaleAmount;
    private boolean hideTradedAmount;
}
