package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Account {
    MINT("6eed43a0c5c052903158f0440d15b3b8dba7138d77f0a1c9cc28f67b96a7cfaf", "0x2ACd471C427c7BEB720Cad6bDD2Fb50c2cFbC093", "MINT"),
    BUY("a552403371a8987927061c90abece84022c85c39996f1682c9fbe03e18d47019", "0x7125A305FDEff17B5Da64AeD2fC12Ff190A9E6D7", "BUY"),
    ADMIN("6eed43a0c5c052903158f0440d15b3b8dba7138d77f0a1c9cc28f67b96a7cfaf", "0x2ACd471C427c7BEB720Cad6bDD2Fb50c2cFbC093", "ADMIN");

    private String privateKey;
    private String address;
    private String ENV;
}
