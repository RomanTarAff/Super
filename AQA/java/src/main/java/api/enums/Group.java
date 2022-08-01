package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Group {

    IN_WALLET("in_wallet"),
    FAVORITES("favorites");

    private final String value;
}
