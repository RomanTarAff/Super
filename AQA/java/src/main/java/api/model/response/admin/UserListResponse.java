package api.model.response.admin;

import lombok.Data;

import java.util.List;

@Data
public class UserListResponse {

    private List<User> users;
    private Integer totalCount;
}
