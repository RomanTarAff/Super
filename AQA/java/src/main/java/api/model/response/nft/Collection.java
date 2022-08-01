package api.model.response.nft;

import lombok.Data;

@Data
public class Collection {

    private String avatarUrl;
    private boolean verified;
    private String slug;
    private String name;
    private String contractAddress;
    private String id;
}
