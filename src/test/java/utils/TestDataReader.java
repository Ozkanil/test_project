package utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.models.BookingRequest;

import java.io.File;
import java.io.IOException;

    public class TestDataReader
    {
        public static BookingRequest getBookingRequest(String filePath) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.readValue(new File(filePath), BookingRequest.class);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load test data from " + filePath, e);
            }
        }
    }
