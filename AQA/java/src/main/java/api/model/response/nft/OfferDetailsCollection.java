package api.model.response.nft;

import api.model.FloorPrice;
import lombok.Data;

@Data
public class OfferDetailsCollection {

    private String name;
    private String slug;
    private boolean verified;
    private String contractAddress;
    private String id;
    private FloorPrice floorPrice;
}
