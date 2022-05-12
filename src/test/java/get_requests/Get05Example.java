package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class Get05Example extends HerOkuAppBaseUrl {
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
//1.Step Set the Url    https://restful-booker.herokuapp.com/booking?firstname=Eric&lastname=Smith
        spec.pathParam("first","booking").queryParams("firstname","Eric","lastname","Jackson");//.queryParams("firstname","Eric","lastname","Smith");

//2.Step Set the expected data
        //3.step Send the request and get the response
       Response response= given().spec(spec).when().get("/{first}");
    response.prettyPrint();

response.then().assertThat().statusCode(200);

    }
}
