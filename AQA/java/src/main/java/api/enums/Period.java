package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Period {

    ONE_HOUR("one_hour"),
    ONE_DAY("one_day"),
    ONE_WEEK("one_week"),
    ONE_MONTH("one_month"),
    ONE_QUARTER("one_quarter"),
    ONE_YEAR("one_year");

    private final String value;
}
