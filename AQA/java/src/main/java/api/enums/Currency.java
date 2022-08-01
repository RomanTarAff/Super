package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Currency {

    FOUR_KB("4KB"),
    ETH("ETH");

    private final String value;
}
