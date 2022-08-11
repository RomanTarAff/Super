package api.model.response.nft;

import lombok.Data;

import java.util.List;

@Data
public class OffersListResponse {

    private List<Offer> offers;
    private Integer totalCount;
}
