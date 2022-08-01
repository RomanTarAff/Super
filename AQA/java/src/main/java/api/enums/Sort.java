package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Sort {

    MOST_RECENT("most_recent"),
    RECENTLY_LISTED("recently_listed"),
    MOST_POPULAR("most_popular"),
    TRENDING("trending"),
    PRICE_DESC("price_desc"),
    PRICE_ASC("price_asc");

    private final String value;
}
