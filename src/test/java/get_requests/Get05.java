package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get05 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
      And
         Among the data there should be someone whose firstname is "Eric" and last name is "Smith"
     */
    @Test
    public void get01(){

        //1.Step:Set the Url
        //https://restful-booker.herokuapp.com/booking?firstname=Eric&lastname=Smith
        spec.pathParam("first", "booking").
                queryParams("firstname", "Eric", "lastname", "Jackson");

        //2.Step: Set the Expected Data

        //3.Step: Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4.Step: Do Assertion
        response.then().assertThat().statusCode(200);

        assertTrue(response.asString().contains("bookingid"));
       //Query parameters ==>are for filtering data ===> Firstname  Mary Lastname Ericson
        //pathParams==> Making resource small you are focusing just small resource/www.amazon.com/book
    }
}



