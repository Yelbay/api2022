package delete_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Delete01 extends JsonPlaceHolderBaseUrl {
   /* Given
    https://jsonplaceholder.typicode.com/todos/198
    When
    I send DELETE Request to the Url
            Then
    Status code is 200
    And Response body is { }
*/

    @Test
    public void delete01(){
   //1.Step;Set the Url
        spec.pathParams("first","todos","second",198);


        //2.Step: Set the Expected data( it is empty Json
        Map<String,Object> expectedMap=new HashMap<>();


        //3.Step: Send delete Request and get rthe response


       Response response= given().spec(spec).when().delete("/{first}/{second}");
       response.prettyPrint();


       //4.Step:Do Assertion
//1.way:
        Map<String,Object> actualMap= response.as(HashMap.class);
        response.then().assertThat().statusCode(200);
        assertEquals(expectedMap,actualMap);
//2.way;//
assertTrue(actualMap.isEmpty());//recommended one
assertTrue(actualMap.size()==0);//Or sam with below..
        /*

            Interview Question:
            How to automate "DELETE Request" in API Testing?
            i)Create new data by using "POST Request"
            ii)Use "DELETE Request" to delete newly created data.

            Note: Do not use "DELETE Request" for the existing data, create your own data then delete it.
         */


    }

}
