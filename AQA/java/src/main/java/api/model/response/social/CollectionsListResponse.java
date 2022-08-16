package api.model.response.social;

import lombok.Data;

import java.util.List;

@Data
public class CollectionsListResponse {

    private List<CollectionResponse> collections;
    private String cursorNext;
    private Integer totalCount;
}
