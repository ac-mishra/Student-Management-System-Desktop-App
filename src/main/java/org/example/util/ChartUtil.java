package org.example.util;

import java.util.Map;

public class ChartUtil {

    private ChartUtil() {
    }

    public static int[] toValues(
            Map<String, Integer> map
    ) {

        return map.values()

                .stream()

                .mapToInt(Integer::intValue)

                .toArray();

    }

    public static String[] toLabels(
            Map<String, Integer> map
    ) {

        return map.keySet()

                .toArray(new String[0]);

    }

}