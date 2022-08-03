package api.model.response;

import lombok.Data;

@Data
public class Creator {

    private String type;
    private String name;
    private String address;
    private String avatar;
    private String id;
    private boolean verified;
    private String slug;
}
