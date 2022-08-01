package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DatePeriod {

    DAY_1("1 day"),
    DAYS_3("3 days"),
    DAYS_5("5 days"),
    DAYS_7("7 days"),
    DAYS_10("10 days"),
    MONTHS_6("6 months");

    private final String value;
    }
