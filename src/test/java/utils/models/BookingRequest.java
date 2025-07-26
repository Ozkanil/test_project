package utils.models;

import java.util.Map;

public class BookingRequest {
    public String firstname;
    public String lastname;
    public int totalprice;
    public boolean depositpaid;
    public Map<String, String> bookingdates;
    public String additionalneeds;
}
