package stepdefinitions;

import api.BookingClient;
import contexts.BookingContext;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.helpers.Helpers;
import utils.models.BookingRequest;
import utils.models.BookingGetResponse;
import utils.models.BookingResponseWrapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateBookingStepDefinitions {

    private final BookingClient bookingClient;
    private final BookingContext context;

    public CreateBookingStepDefinitions(BookingContext context, BookingClient bookingClient)
    {
        this.context = context;
        this.bookingClient = bookingClient;
    }
    @Given("an existing booking")
    @When("a user requests a new booking")
    public void AUserRequestsANewBooking() throws JsonProcessingException
    {
        BookingRequest request = Helpers.GetBookingRequest();
        // save request data to the context to use in verification step
        context.setRequest(request);

        BookingResponseWrapper response = bookingClient.createBooking(request);
        context.setBookingId(response.getBookingid());

        //Fetch booking from DB to compare with the test data in the post request
        BookingGetResponse booking = bookingClient.getBooking(response.getBookingid());
        context.setGetResponse(booking);
    }

    @Then("the booking is fetched successfully")
    @Then("the booking request is saved successfully")
    public void theBookingRequestIsSavedSuccessfully()
    {
        assertEquals(context.getRequest().getFirstname(), context.getGetResponse().getFirstname(), "The customer name is different from expected!");
        assertEquals(context.getRequest().getLastname(), context.getGetResponse().getLastname(), "The customer surname is different from expected!");
        assertEquals(context.getRequest().getTotalprice(), context.getGetResponse().getTotalprice(), "The total price is different from expected!");
        assertEquals(context.getRequest().getBookingdates().getCheckin(), context.getGetResponse().getBookingdates().getCheckin(), "The checkin date is different from expected!");
        assertEquals(context.getRequest().getBookingdates().getCheckout(), context.getGetResponse().getBookingdates().getCheckout(), "The checkout date is different from expected!");
        assertEquals(context.getRequest().getAdditionalneeds(), context.getGetResponse().getAdditionalneeds(), "The additional needs are different from expected!");
    }
}
