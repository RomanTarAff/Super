package api.model.response.social;

import lombok.Data;

import java.util.List;

@Data
public class SearchSuggestionsList {

    private List<SuggestionItemResponse> collections;
    private List<SuggestionItemResponse> nfts;
    private List<SuggestionItemResponse> users;

}
