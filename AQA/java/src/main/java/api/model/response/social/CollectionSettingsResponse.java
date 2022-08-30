package api.model.response.social;

import api.model.request.social.SocialLinks;
import api.model.response.Token;
import lombok.Data;

import java.util.List;

@Data
public class CollectionSettingsResponse {

    private String avatarUrl;
    private String name;
    private String slug;
    private String bannerUrl;
    private String contractAddress;
    private String id;
    private List<Token> tokens;
    private List<Collaborator> collaborators;
    private String description;
    private String websiteUrl;
    private SocialLinks socialLinks;
    private Object royalties;
}
