package api.model.response.nft;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SearchNftResponseList {

    @JsonProperty("nfts")
    private List<SearchNftResponse> nfts;

    @JsonProperty("totalCount")
    private int totalCount;

    @JsonProperty("cursorNext")
    private String cursorNext;
}
