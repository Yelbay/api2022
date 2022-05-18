package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get08 extends JsonPlaceHolderBaseUrl {

    /*
        Serialization: To convert Java Object to JSON Data
        De-Serialization: To convert JSON Data to Java Object

        To do De-Serialization and Serialization we can use the followings;
        1)Gson: Google Created
        2)Object Mapper: More popular
     */

    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
         I send GET Request to the URL
       Then
         Status code is 200
         And "completed" is false
         And "userId" is 1
         And "title" is "quis ut nam facilis et officia qui"
         And header "Via" is "1.1 Vegur"
         And header "Server" is "cloudflare"
         {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */
    @Test
    public void get01(){
        //1.Step: Set the URL
        spec.pathParams("first", "todos", "second", 2);

        //2.Step:Set the Expected Data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        expectedData.put("StatusCode", 200);
        expectedData.put("Via", "1.1 Vegur");
        expectedData.put("Server", "cloudflare");

        //3.Step: Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        //4.Step: Do Assertions
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedData.get("Via"), response.getHeader("Via"));
        assertEquals(expectedData.get("Server"), response.getHeader("Server"));
    }

    @Test
    public void get02(){

        //1.Step: Set the URL
        spec.pathParams("first", "todos", "second", 2);

        //2.Step:Set the Expected Data
        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
        Map<String, Object> expectedDataMap = expectedData.expectedDataWithAllKeys(1, "quis ut nam facilis et officia qui", false);
        expectedDataMap.put("StatusCode", 200);
        expectedDataMap.put("Via", "1.1 vegur");
        expectedDataMap.put("Server", "cloudflare");

        //3.Step: Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        //4.Step: Do Assertions
        assertEquals(expectedDataMap.get("userId"), actualData.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualData.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualData.get("completed"));
        assertEquals(expectedDataMap.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedDataMap.get("Via"), response.getHeader("Via"));
        assertEquals(expectedDataMap.get("Server"), response.getHeader("Server"));

    }




}
