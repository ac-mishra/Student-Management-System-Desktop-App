package org.example.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private DateUtil() {
    }

    public static String format(
            LocalDate date
    ) {

        if (date == null) {

            return "";

        }

        return FORMATTER.format(date);

    }

    public static LocalDate parse(
            String value
    ) {

        return LocalDate.parse(
                value,
                FORMATTER
        );

    }

}