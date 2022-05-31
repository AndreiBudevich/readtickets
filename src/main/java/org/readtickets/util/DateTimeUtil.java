package org.readtickets.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class DateTimeUtil {

    private static final Map<String, String> zoneIdCity;

    static {
        zoneIdCity = new HashMap<>();
        zoneIdCity.put("VVO", "Asia/Vladivostok");
        zoneIdCity.put("TLV", "Asia/Tel_Aviv");
    }

    private DateTimeUtil() {
    }

    public static ZonedDateTime get(LocalDate localDate, LocalTime localTime, String city) {
        return ZonedDateTime.of(localDate, localTime, ZoneId.of(zoneIdCity.get(city)));
    }
}
