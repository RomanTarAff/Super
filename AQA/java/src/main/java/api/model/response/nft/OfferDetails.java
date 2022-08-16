package api.model.response.nft;

import api.model.response.HighestOffer;
import lombok.Data;

import java.util.List;

@Data
public class OfferDetails {

    private String id;
    private HighestOffer highestOffer;
    private String expiresAt;
    private String status;
    private List<Royalties> royalties;
    private OfferPrice price;
    private OfferDetailsSender sender;
    private OfferDetailsNft nft;
    private OfferDetailsCollection collection;
}
