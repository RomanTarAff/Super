package api.model.request.social;

import api.model.response.nft.Royalties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChangeCollectionRoyaltiesRequest {

    private List<Royalties> royalties;
    private String signature;
    private Long signatureTime;
}

