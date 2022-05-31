package org.readtickets.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.readtickets.model.Ticket;

import java.io.IOException;
import java.io.StringReader;

public class JsonUtil {

    private static final ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    private JsonUtil() {
    }

    public static Ticket[] getTickets(byte[] array) throws IOException {
        StringReader reader = new StringReader(getJsonString(array));
        return mapper.readValue(reader, Ticket[].class);
    }

    private static String getJsonString(byte[] array) {
        String jsonString = new String(array);
        int beginIndex = jsonString.indexOf("{\n" + "  \"tickets\": ") + 15;
        int endIndex = jsonString.indexOf("]", beginIndex) + 1;
        return ReplaceAll(jsonString.substring(beginIndex, endIndex));


    }

    private static String ReplaceAll(String string) {
        return string.replaceAll("origin_name", "originName")
                .replaceAll("departure_date", "departureDate")
                .replaceAll("arrival_date", "arrivalDate")
                .replaceAll("destination_name", "destinationName")
                .replaceAll("departure_time", "departureTime")
                .replaceAll("arrival_time", "arrivalTime");
    }
}
