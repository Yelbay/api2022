package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get10 extends GoRestBaseUrl {

    /*
        Given
            https://gorest.co.in/public/v1/users/13
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            {
                "meta": null,
                "data": {
                    "id": 13,
                    "name": "Fr. Ajit Prajapat",
                    "email": "ajit_fr_prajapat@barrows.org",
                    "gender": "female",
                    "status": "active"
                }
            }
     */
    @Test
    public void get01(){

        //1.Step:Set the URL
        spec.pathParams("first", "users", "second", 13);

        //2.Step: Set the Expected Data
        GoRestTestData dataKey = new GoRestTestData();
        Map<String, String> dataKeyMap = dataKey.dataKeyMap("Aayushmaan Nair", "nair_aayushmaan@spencer-marvin.io", "female", "inactive");

        Map<String, Object> expectedDataMap = dataKey.expectedDataMap(null, dataKeyMap);

        //3.Step: Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        Map<String, Object> actualDataMap = response.as(HashMap.class);

        //4.Step: Do Assertions
        assertEquals(expectedDataMap.get("meta"), actualDataMap.get("meta"));
        assertEquals(dataKeyMap.get("name"), ((Map)actualDataMap.get("data")).get("name"));
        assertEquals(dataKeyMap.get("email"), ((Map)actualDataMap.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"), ((Map)actualDataMap.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"), ((Map)actualDataMap.get("data")).get("status"));


    }
}






