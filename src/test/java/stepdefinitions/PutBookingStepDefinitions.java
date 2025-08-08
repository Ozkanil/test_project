package stepdefinitions;

import api.BookingClient;
import contexts.BookingContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.models.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PutBookingStepDefinitions
{
    private final BookingClient bookingClient;
    private  final BookingContext bookingContext;

    public PutBookingStepDefinitions(BookingClient bookingClient, BookingContext bookingContext)
    {
        this.bookingClient = bookingClient;
        this.bookingContext = bookingContext;
    }

    @And("a new session token")
    public void aNewSessionToken()
    {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("admin");
        authRequest.setPassword("password123");

        AuthResponse response = bookingClient.generateToken(authRequest);

        bookingContext.setAuthToken(response.getToken());
    }

    @When("the user requests to update the checkout date and the total price")
    public void theUserRequestsToUpdateTheCheckoutDateAndTheTotalPrice()
    {
        BookingDates dates = new BookingDates();
        dates.setCheckout("2024-01-15");
        dates.setCheckin(bookingContext.getRequest().getBookingdates().getCheckin());

        BookingRequest request = new BookingRequest();
        request.setFirstname(bookingContext.getRequest().getFirstname());
        request.setLastname(bookingContext.getRequest().getLastname());
        request.setDepositpaid(true);
        request.setAdditionalneeds(bookingContext.getRequest().getAdditionalneeds());
        request.setBookingdates(dates);
        request.setTotalprice(150);

        bookingClient.updateBooking(bookingContext.getBookingId(), request, bookingContext.getAuthToken());

        // Get updated booking to compare in the verification step
        BookingGetResponse updatedBooking = bookingClient.getBooking(bookingContext.getBookingId());
        bookingContext.setGetResponse(updatedBooking);
        bookingContext.setRequest(request);
    }

    @Then("the booking is updated successfully")
    public void theBookingIsUpdatedSuccessfully()
    {
        assertEquals(bookingContext.getRequest().getTotalprice(), bookingContext.getGetResponse().getTotalprice(), "The updated price is different from expected!");
        assertEquals(bookingContext.getRequest().getBookingdates().getCheckout(), bookingContext.getGetResponse().getBookingdates().getCheckout(), "The updated checkout date is different from expected!");
    }
}
