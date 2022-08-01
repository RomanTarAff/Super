package api.model.request.nft;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchNftRequest {

    private String search;
    private List<String> statuses;
    private String group;
    private Integer limit;
    private String sort;
    private List<String> collections;

}
