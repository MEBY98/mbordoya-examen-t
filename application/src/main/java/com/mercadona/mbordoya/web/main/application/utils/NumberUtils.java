package com.mercadona.mbordoya.web.main.application.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import static com.mercadona.mbordoya.web.main.application.utils.StringUtils.isValidString;

@UtilityClass
@Slf4j
public final class NumberUtils {

    public static Boolean isLong(final String string) {
        if (isValidString(string)) {
            try {
                Long.parseLong(string);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static Double round (final Double value, final int decimals) {
        return Math.round((value * Math.pow(10, decimals))) / Math.pow(10, decimals);
    }
}
