package api.model.response.nft;

import lombok.Data;

@Data
public class Offer {

    private Long expiresAt;
    private OfferPrice price;
    private String id;
    private Sender sender;
}
