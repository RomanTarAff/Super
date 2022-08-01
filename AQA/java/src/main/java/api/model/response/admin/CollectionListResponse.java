package api.model.response.admin;

import lombok.Data;

import java.util.List;

@Data
public class CollectionListResponse {

    private List<Collection> collections;
    private Integer totalCount;
}
