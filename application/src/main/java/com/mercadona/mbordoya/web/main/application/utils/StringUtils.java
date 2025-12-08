package com.mercadona.mbordoya.web.main.application.utils;

import lombok.experimental.UtilityClass;

import java.text.Normalizer;
import java.util.regex.Pattern;

@UtilityClass
public final class StringUtils {

    private static final String UNACCENTED_REGEX = "\\p{M}";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidString(final String string) {
        return string != null && !string.isBlank();
    }

    public static boolean isValidEmail(final String string) {
        return isValidString(string) && EMAIL_PATTERN.matcher(string).matches();
    }

    public static String removeAccents(final String string) {
        return isValidString(string)
                ? Normalizer.normalize(string, Normalizer.Form.NFKD).replaceAll(UNACCENTED_REGEX, "")
                : null;
    }

}
