package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Patch01 extends JsonPlaceHolderBaseUrl {

    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "Wash the dishes"
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									   }
     */
    @Test
    public void patch01(){

        //1.Step: Set the URL
        spec.pathParams("first", "todos", "second", 198);

        //2.Step: Set the Request Body
        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String, Object> requestBodyMap = requestBody.expectedDataWithMissingKeys(null, "Wash the dishes", null);

        //3.Step: Send the PATCH Request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().patch("/{first}/{second}");
        response.prettyPrint();

        //4.Step: Do Assertions
        //1.Way:
        response.then().assertThat().statusCode(200).body("title", equalTo(requestBodyMap.get("title")));

        //When you do PATCH Assertion, just the data you updated should be asserted. But if someone insists on assert all parts do the following
        Map<String, Object> MapToAssertAllDetails =  requestBody.expectedDataWithAllKeys(10, "Wash the dishes", true);
        response.then().assertThat().statusCode(200).body("title", equalTo(MapToAssertAllDetails.get("title")),
                "userId", equalTo(MapToAssertAllDetails.get("userId")),
                "completed", equalTo(MapToAssertAllDetails.get("completed")));

    }

}
