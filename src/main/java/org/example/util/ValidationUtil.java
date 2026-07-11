package org.example.util;

import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern EMAIL = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    private static final Pattern PHONE = Pattern.compile(
            "\\d{10}"
    );

    private ValidationUtil() {
    }

    public static boolean isEmail(
            String value
    ) {

        return EMAIL.matcher(value).matches();

    }

    public static boolean isPhone(
            String value
    ) {

        return PHONE.matcher(value).matches();

    }

    public static boolean isEmpty(
            String value
    ) {

        return value == null
                || value.isBlank();

    }

}