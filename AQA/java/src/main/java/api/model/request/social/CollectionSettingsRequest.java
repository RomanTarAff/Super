package api.model.request.social;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CollectionSettingsRequest {

    private String name;
    private String description;
    private String avatarUrl;
    private String bannerUrl;
    private String websiteUrl;
    private SocialLinks socialLinks;
    private String slug;
}
