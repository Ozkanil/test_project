package utils.helpers;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import utils.models.BookingRequest;

import java.io.File;
import java.io.IOException;

public class Helpers
{
    public static BookingRequest GetBookingRequest() {

        String filePath = "src/test/resources/data/BookingRequest.json";

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filePath), BookingRequest.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read test data from: " + filePath, e);
        }
    }
}
