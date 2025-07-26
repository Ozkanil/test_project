package stepdefinitions;

import api.BookingClient;
import contexts.BookingContext;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.TestDataReader;
import utils.models.BookingRequest;
import utils.models.BookingResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateBookingStepDefinitions {

    private final BookingClient bookingClient = new BookingClient();
    private final BookingContext context;

    public CreateBookingStepDefinitions(BookingContext context) {
        this.context = context;
    }

    @When("a user requests a new booking")
    public void AUserRequestsANewBooking() throws JsonProcessingException
    {
        BookingRequest request = TestDataReader.getBookingRequest("src/test/resources/data/BookingRequest.json");
        // save request data to the context to use in verification step
        context.setRequest(request);

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(request);

        Response response = bookingClient.createBooking(requestBody);
        context.setBookingId(response.jsonPath().getInt("bookingid"));

        System.out.println("Booking ID: " + response.jsonPath().getInt("bookingid"));
    }

    @Then("the booking request is saved successfully")
    public void theBookingRequestIsSavedSuccessfully()
    {
        BookingResponse response = bookingClient.getBooking(context.getBookingId());

        assertEquals(context.getRequest().firstname, response.getFirstname(), "The customer name is different from expected!");
    }
}
