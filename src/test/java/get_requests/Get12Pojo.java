package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get12Pojo extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/2
        When
          I send GET Request to the URL
       Then
          Status code is 200
      And
          Response body is like {
                                    "firstname": "Mary",
                                    "lastname": "Brown",
                                    "totalprice": 227,
                                    "depositpaid": true,
                                    "bookingdates": {
                                        "checkin": "2015-10-21",
                                        "checkout": "2021-08-28"
                                    },
                                    "additionalneeds": "Breakfast"
                                 }
     */
    @Test
    public void get01Pojo(){

        //1.Step:Set the URL
        spec.pathParams("first", "booking", "second", 2);

        //2.Step: Set the Expected Data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2015-08-07", "2016-06-12");
        BookingPojo bookingPojo = new BookingPojo("Mary", "Brown", 227, true, bookingDatesPojo, "Breakfast");

        //3.Step: Send the GET Request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //4.Step: Do Assertions
        BookingPojo actualPojo = response.as(BookingPojo.class);

        //assertEquals(bookingPojo.getFirstname(), actualPojo.getFirstname());
        //assertEquals(bookingPojo.getLastname(), actualPojo.getLastname());
        //assertEquals(bookingPojo.getTotalprice(), actualPojo.getTotalprice());
        //assertEquals(bookingPojo.getDepositpaid(), actualPojo.getDepositpaid());
        //1.Way:
        //assertEquals(bookingPojo.getBookingdates().getCheckin(), actualPojo.getBookingdates().getCheckin());
        //assertEquals(bookingPojo.getBookingdates().getCheckout(), actualPojo.getBookingdates().getCheckout());
        //2.Way:Recommended
        assertEquals(bookingDatesPojo.getCheckin(), actualPojo.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualPojo.getBookingdates().getCheckout());

    }

}