package stepdefinitions;

import api.BookingClient;
import contexts.BookingContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.models.BookingGetResponse;
import utils.models.BookingPatchRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatchBookingStepDefinitions
{
    private final BookingContext bookingContext;
    private final BookingClient bookingClient;

    public PatchBookingStepDefinitions(BookingContext bookingContext,BookingClient bookingClient)
    {
        this.bookingContext = bookingContext;
        this.bookingClient = bookingClient;
    }

    @When("the user requests to update the name and surname in the booking")
    public void theUserRequestsToUpdateTheNameAndSurnameInTheBooking()
    {
        BookingPatchRequest request = new BookingPatchRequest();
        request.setFirstname("Tim");
        request.setLastname("Michael");

        BookingGetResponse response = bookingClient.patchBooking(bookingContext.getBookingId(), request, bookingContext.getAuthToken());

        BookingGetResponse updatedBooking = bookingClient.getBooking(bookingContext.getBookingId());
        bookingContext.setGetResponse(updatedBooking);
        bookingContext.setBookingPatchRequest(request);
    }

    @Then("the booking is patched successfully")
    public void theBookingIsPatchedSuccessfully()
    {
        assertEquals(bookingContext.getBookingPatchRequest().getFirstname(), bookingContext.getGetResponse().getFirstname(), "The updated name is different from expected!");
        assertEquals(bookingContext.getBookingPatchRequest().getLastname(), bookingContext.getGetResponse().getLastname(), "The updated lastname is different from expected!");
    }
}
