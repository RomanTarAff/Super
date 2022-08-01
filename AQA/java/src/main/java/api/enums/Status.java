package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {

    NOT_LISTED("not_listed"),
    ON_SALE("on_sale"),
    HAS_OFFERS("has_offers"),
    NO_OFFERS("no_offers");

    private final String value;
}
