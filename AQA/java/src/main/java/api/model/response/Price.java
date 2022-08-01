package api.model.response;

import lombok.Data;

@Data
public class Price {

    private String symbol;
    private String thumbnail;
    private String decimals;
    private String tokenContractAddress;
    private String name;
    private String logo;
    private String value;
}
