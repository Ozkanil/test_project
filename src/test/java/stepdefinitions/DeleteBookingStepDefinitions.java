package stepdefinitions;

import api.BookingClient;
import contexts.BookingContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.models.BookingGetResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DeleteBookingStepDefinitions
{
    private final BookingClient bookingClient;
    private  final BookingContext bookingContext;

    public DeleteBookingStepDefinitions(BookingClient bookingClient, BookingContext bookingContext)
    {
        this.bookingClient = bookingClient;
        this.bookingContext = bookingContext;
    }

    @When("the user requests to delete the booking")
    public void theUserRequestsToDeleteTheBooking()
    {
        this.bookingClient.deleteBooking(bookingContext.getBookingId(), bookingContext.getAuthToken());
    }

    @Then("the booking is deleted successfully")
    public void theBookingIsDeletedSuccessfully()
    {
        BookingGetResponse updatedBooking = bookingClient.getBooking(bookingContext.getBookingId());

        assertNull(updatedBooking, "A booking was returned while it should return null!");
    }
}
