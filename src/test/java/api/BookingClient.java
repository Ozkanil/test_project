package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.Configuration;
import utils.models.BookingResponse;

import static io.restassured.RestAssured.*;

public class BookingClient {

    private String baseUrl = Configuration.get("base.url");

    public Response createBooking(String body) {
        return given()
                .baseUri(baseUrl)
                .basePath("/booking")
                .header("Content-Type", "application/json")
                .body(body)
                .post();
    }

    public BookingResponse getBooking(int bookingId) {
        return given()
                .baseUri(baseUrl)
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .as(BookingResponse.class); // <-- automatic deserialization
    }

    public Response updateBooking(int id, String body, String token) {
        return given()
                .baseUri(baseUrl)
                .basePath("/booking/" + id)
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token)
                .body(body)
                .put();
    }

    public Response deleteBooking(int id, String token) {
        return given()
                .baseUri(baseUrl)
                .basePath("/booking/" + id)
                .header("Cookie", "token=" + token)
                .delete();
    }
}
