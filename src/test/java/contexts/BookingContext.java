package contexts;

import io.restassured.response.Response;
import utils.models.BookingGetResponse;
import utils.models.BookingPatchRequest;
import utils.models.BookingRequest;

public class BookingContext
{
    private int bookingId;
    private BookingRequest requestData;
    private Response response;
    private BookingRequest request;
    private BookingGetResponse getResponse;
    private String authToken;
    private BookingPatchRequest bookingPatchRequest;

    public BookingPatchRequest getBookingPatchRequest() {
        return bookingPatchRequest;
    }

    public void setBookingPatchRequest(BookingPatchRequest bookingPatchRequest) {
        this.bookingPatchRequest = bookingPatchRequest;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public BookingGetResponse getGetResponse() {
        return getResponse;
    }

    public void setGetResponse(BookingGetResponse getResponse) {
        this.getResponse = getResponse;
    }

    public int getBookingId() {
        return bookingId;
    }

    public BookingRequest getRequest() {
        return request;
    }

    public void setRequest(BookingRequest request) {
        this.request = request;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public BookingRequest getRequestData() {
        return requestData;
    }

    public void setRequestData(BookingRequest requestData) {
        this.requestData = requestData;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
