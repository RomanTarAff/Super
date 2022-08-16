package api.model.response.social;

import lombok.Data;

@Data
public class Period {

    private String name;
    private Integer diffByValue;
    private Integer diffByPercent;
}
