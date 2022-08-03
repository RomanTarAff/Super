package api.model.response;

import lombok.Data;

@Data
public class HighestOffer {

    private String buyerAddress;
    private Price price;
    private String id;
}
