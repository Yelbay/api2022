package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post01 extends HerOkuAppBaseUrl {

    /*
        Given
           1) https://restful-booker.herokuapp.com/booking
           2) {
                 "firstname": "Selim",
                 "lastname": "Ak",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
         I send POST Request to the Url
      Then
         Status code is 200
         And response body should be like {
                                     "bookingid": 11,
                                     "booking": {
                                         "firstname": "Selim",
                                         "lastname": "Ak",
                                         "totalprice": 11111,
                                         "depositpaid": true,
                                         "bookingdates": {
                                             "checkin": "2020-09-09",
                                             "checkout": "2020-09-21"
                                         }
                                     }
                                  }
     Note 1: The data you send in the request is called "Request Body" or "Payload"
     Note 2: The data you get in response is called "Response Body"
     */
    @Test
    public void post01(){
        //1.Step: Set the URL
        spec.pathParam("first", "booking");
        //2.Step: Set the Expected Data
        HerOkuAppTestData herOkuApp = new HerOkuAppTestData();
        Map<String, String> bookingDatesMap = herOkuApp.bookingDateSetUp("2020-09-09", "2020-09-21");
        Map<String, Object> expectedDataMap = herOkuApp.expectedDataSetUp("Selim", "Ak", 11111, true, bookingDatesMap);
        //3.Step: Send POST Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");
        response.prettyPrint();
        //4.Step: Do Assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);

        assertEquals(expectedDataMap.get("firstname"), ((Map)actualDataMap.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), ((Map)actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), ((Map)actualDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), ((Map)actualDataMap.get("booking")).get("depositpaid"));

        assertEquals(bookingDatesMap.get("checkin"), ((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"), ((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkout"));

    }
}