package org.readtickets.util;

import org.readtickets.model.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
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

    public static double getAverageFlightTimeBetweenCities(Ticket[] tickets, ChronoUnit unit) {
        return Arrays.stream(getFlightTimeBetweenCities(tickets, unit)).average().orElseThrow();
    }

    public static double getPercentile(Ticket[] tickets, ChronoUnit unit, int p) {
        long[] longs = getFlightTimeBetweenCities(tickets, unit);
        double serialNumber = p * 0.01 * (tickets.length - 1) + 1;
        int number = (int) serialNumber;
        return longs[number - 1] + (serialNumber - number) * (longs[number] - longs[number - 1]);
    }

    private static ZonedDateTime get(LocalDate localDate, LocalTime localTime, String city) {
        return ZonedDateTime.of(localDate, localTime, ZoneId.of(zoneIdCity.get(city)));
    }

    private static long zonedDateTimeDifference(Ticket ticket, ChronoUnit unit) {
        ZonedDateTime departure = get(ticket.getDepartureDate(), ticket.getDepartureTime(), ticket.getOrigin());
        ZonedDateTime arrival = get(ticket.getArrivalDate(), ticket.getArrivalTime(), ticket.getDestination());
        return unit.between(departure, arrival);
    }

    private static long[] getFlightTimeBetweenCities(Ticket[] tickets, ChronoUnit unit) {
        return Arrays.stream(tickets)
                .mapToLong(ticket -> zonedDateTimeDifference(ticket, unit))
                .sorted()
                .toArray();
    }


}
