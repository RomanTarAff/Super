package api.model.response.nft;

import lombok.Data;

@Data
public class Royalties {

    private String walletAddress;
    private Integer percentage;
}
