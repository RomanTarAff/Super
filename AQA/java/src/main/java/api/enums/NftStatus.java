package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NftStatus {

    ACTIVE("active");

    private final String value;
}
