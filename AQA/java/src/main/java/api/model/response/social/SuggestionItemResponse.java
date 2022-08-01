package api.model.response.social;

import lombok.Data;

@Data
public class SuggestionItemResponse {

    private String id;
    private String avatarUrl;
    private String name;
    private String address;
    private String tokenId;
    private String slug;
    private boolean verified;
}
