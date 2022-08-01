package api.model.response.nft;

import api.model.response.Price;
import lombok.Data;

@Data
public class ListingResponse {

    private String listingId;
    private String startsAt;
    private String endsAt;
    private Price price;
}
