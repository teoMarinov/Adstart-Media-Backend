package com.teodor.backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum QuoteRequestPriceRangeEnum {
    LOW("$5.000-$10.000"),
    MEDIUM("$10.000-$20.000"),
    HIGH("$20.000-$50.000"),
    HIGHEST("$50.000+");

    private final String displayValue;

    QuoteRequestPriceRangeEnum(String displayValue) {
        this.displayValue = displayValue;
    }

    @JsonValue
    public String getDisplayValue() {
        return displayValue;
    }

    @JsonCreator
    public static QuoteRequestPriceRangeEnum fromValue(String value) {
        for (QuoteRequestPriceRangeEnum range : QuoteRequestPriceRangeEnum.values()) {
            if (range.displayValue.equals(value)) {
                return range;
            }
        }
        throw new IllegalArgumentException("Invalid quote range: " + value);
    }
}
