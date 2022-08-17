package api.model.response.nft;

import lombok.Data;

@Data
public class OfferDetailsNft {

    private String id;
    private String name;
    private String mediaUrl;
    private String previewUrl;
    private String tokenId;
    private String contractAddress;
    private String contractType;
    private String nftStatus;
}
