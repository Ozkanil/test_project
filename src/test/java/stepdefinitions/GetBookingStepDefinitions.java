package stepdefinitions;

import api.BookingClient;
import contexts.BookingContext;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.When;
import utils.models.BookingGetResponse;

public class GetBookingStepDefinitions
{
    private final BookingClient bookingClient;
    private final BookingContext context;

    public GetBookingStepDefinitions(BookingClient bookingClient, BookingContext context)
    {
        this.bookingClient = bookingClient;
        this.context = context;
    }

    @When("the user requests an existing booking")
    public void TheUserRequestsAnExistingBooking() throws JsonProcessingException
    {
        BookingGetResponse booking = bookingClient.getBooking(context.getBookingId());
        context.setGetResponse(booking);
    }
}
