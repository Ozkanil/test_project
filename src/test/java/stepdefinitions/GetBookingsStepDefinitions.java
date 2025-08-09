package stepdefinitions;

import api.BookingClient;
import contexts.BookingContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.models.BookingsResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetBookingsStepDefinitions
{
    private final BookingClient bookingClient;
    private final BookingContext bookingContext;

    public GetBookingsStepDefinitions(BookingClient bookingClient, BookingContext bookingContext)
    {
        this.bookingClient = bookingClient;
        this.bookingContext =bookingContext;
    }
    @When("the user requests all existing booking")
    public void theUserRequestsAllExistingBooking()
    {
        List<BookingsResponse> bookings = bookingClient.getAllBookings();

        this.bookingContext.setBookings(bookings);
    }

    @Then("all booking are fetched successfully")
    public void allBookingAreFetchedSuccessfully()
    {
        assertNotNull(this.bookingContext.getBookings(), "Booking list should not be null");
        assertFalse(this.bookingContext.getBookings().isEmpty(), "Booking list should not be empty");
    }
}
