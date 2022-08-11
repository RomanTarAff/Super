package api.model.response.nft;

import lombok.Data;

@Data
public class OfferPrice {

    private String symbol;
    private String decimals;
    private String tokenContractAddress;
    private String name;
    private String value;
}
