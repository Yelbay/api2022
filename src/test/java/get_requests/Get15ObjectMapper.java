package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15ObjectMapper extends HerOkuAppBaseUrl {
    /*
    Given
               https://restful-booker.herokuapp.com/booking/2
        When
            I send GET Request to the URL
      Then
            Status code is 200
                    {
                    "firstname": "Jim",
                    "lastname": "Smith",
                    "totalprice": 649,
                    "depositpaid": false,
                    "bookingdates":
                             {
                        "checkin": "2015-09-16",
                        "checkout": "2018-04-09"
                              },
                     "additionalneeds": "Breakfast"
                    }
     */


    @Test
    public void get01() {

        //1.set the url
        spec.pathParams("first", "booking", "second", 2);

        //2. set the expected data
        String expectedData = "{\n" +
                "\"firstname\": \"Jim\",\n" +
                "\"lastname\": \"Ericsson\",\n" +
                "\"totalprice\": 249,\n" +
                "\"depositpaid\": false,\n" +
                "\"bookingdates\":\n" +
                "{\n" +
                "\"checkin\": \"2015-06-28\",\n" +
                "\"checkout\": \"2016-06-15\"\n" +
                "},\n" +
                "\"additionalneeds\": \"Breakfast\"\n" +
                "}";
        BookingPojo expectedDataPojo = JsonUtil.convertJsonToJavaObject(expectedData, BookingPojo.class);

        //3. send get request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //4. Do assertions
        BookingPojo actualDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(), BookingPojo.class);

        assertEquals(200, response.getStatusCode());

        //hard assertion
//        assertEquals(expectedDataPojo.getFirstname(), actualDataPojo.getFirstname());
//        assertEquals(expectedDataPojo.getLastname(), actualDataPojo.getLastname());
//        assertEquals(expectedDataPojo.getTotalprice(), actualDataPojo.getTotalprice());
//        assertEquals(expectedDataPojo.getDepositpaid(), actualDataPojo.getDepositpaid());
//        assertEquals(expectedDataPojo.getBookingdates().getCheckin(), actualDataPojo.getBookingdates().getCheckin());
//        assertEquals(expectedDataPojo.getBookingdates().getCheckout(), actualDataPojo.getBookingdates().getCheckout());

        //soft assertion steps
        //i) create sof assert object
        SoftAssert softAssert = new SoftAssert();
        //ii)Do assertion
        softAssert.assertEquals(actualDataPojo.getFirstname(), expectedDataPojo.getFirstname(), "FIrst name did not match");
        softAssert.assertEquals(actualDataPojo.getLastname(), expectedDataPojo.getLastname(), "Last name did not match");
        softAssert.assertEquals(actualDataPojo.getTotalprice(), expectedDataPojo.getTotalprice(), "Totalprice did not match");
        softAssert.assertEquals(actualDataPojo.getDepositpaid(), expectedDataPojo.getDepositpaid(), "Deposited did not match");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckin(), expectedDataPojo.getBookingdates().getCheckin(), "Checkin did not match");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckout(), expectedDataPojo.getBookingdates().getCheckout(), "Checkout did not match");

        //iii) assertall
        softAssert.assertAll();


    }


}




