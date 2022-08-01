package api.model.response.admin;

import lombok.Data;

@Data
public class Collection {

    private String id;
    private String name;
    private String avatarUrl;
    private String bannerUrl;
    private String collectionStatus;
    private String createdAt;
    private String contractAddress;
    private String isVerified;
}
