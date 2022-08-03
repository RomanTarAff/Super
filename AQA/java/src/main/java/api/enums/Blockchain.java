package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Blockchain {

    ETH("eth", "rinkeby");

    private final String name;
    private final String network;
}
