package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import utils.Configuration;
import utils.models.*;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

public class BookingClient {

    private String baseUrl = Configuration.get("base.url");

    public BookingResponseWrapper createBooking(BookingRequest request)
    {
        return given()
                .baseUri(baseUrl)
                .basePath("/booking")
                .header("Content-Type", "application/json")
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .as(BookingResponseWrapper.class);
    }

    public BookingGetResponse getBooking(int bookingId) {
        var response = given()
                .baseUri(baseUrl)
                .when()
                .get("/booking/" + bookingId)
                .then()
                .extract()
                .response();

        int statusCode = response.statusCode();

        if (statusCode == 200) {
            return response.as(BookingGetResponse.class);
        } else if (statusCode == 404) {
            return null;
        } else {
            throw new RuntimeException("Internal server error: " + statusCode);
        }
    }

    public List<BookingsResponse> getAllBookings() {
        BookingsResponse[] bookings = RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .extract()
                .as(BookingsResponse[].class);

        return Arrays.asList(bookings);
    }

    public BookingGetResponse updateBooking(int bookingId, BookingRequest request, String token) {
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .basePath("/booking/" + bookingId)
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .body(request)
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .as(BookingGetResponse.class);
    }

    public AuthResponse generateToken(AuthRequest request) {
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .basePath("/auth")
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .as(AuthResponse.class);
    }

    public BookingGetResponse patchBooking(int bookingId, BookingPatchRequest request, String token)
    {
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .basePath("/booking/" + bookingId)
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .body(request)
                .when()
                .patch()
                .then()
                .statusCode(200)
                .extract()
                .as(BookingGetResponse.class);
    }

    public void deleteBooking(int bookingId, String token) {
        RestAssured
                .given()
                .baseUri(baseUrl)
                .basePath("/booking/" + bookingId)
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .when()
                .delete()
                .then()
                .statusCode(201);
    }
}
