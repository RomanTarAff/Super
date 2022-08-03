package api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Token {

    private String id;
    private String contractAddress;
    private String name;
    private String symbol;
    private int decimals;
    private String logo;
    private String thumbnail;
    private String createdAt;

    @JsonProperty("default")
    private boolean defaulT;

    private boolean deletable;
}
