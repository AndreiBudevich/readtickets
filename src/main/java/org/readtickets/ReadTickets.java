package org.readtickets;

import org.readtickets.model.Ticket;
import org.readtickets.util.FileUtil;
import org.readtickets.util.JsonUtil;

import java.io.IOException;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.readtickets.util.DateTimeUtil.getAverageFlightTimeBetweenCities;
import static org.readtickets.util.DateTimeUtil.getPercentile;

public class ReadTickets {
    public static void main(String[] args) throws IOException {

        byte[] bytes = FileUtil.readFileAllBytes(args[0]);
        Ticket[] tickets = JsonUtil.getTickets(bytes);

        Double averageFlightTime = getAverageFlightTimeBetweenCities(tickets, MINUTES);

        Double percentile = getPercentile(tickets, MINUTES, 90);

        System.out.printf("AverageFlightTime %s \n", averageFlightTime);
        System.out.printf("Percentile %s", percentile);
    }
}
