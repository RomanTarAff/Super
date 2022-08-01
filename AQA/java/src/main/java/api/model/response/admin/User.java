package api.model.response.admin;

import lombok.Data;

@Data
public class User {
    private String id;
    private String username;
    private String avatarUrl;
    private String ethAddress;
    private String userStatus;
    private String registeredAt;
}
