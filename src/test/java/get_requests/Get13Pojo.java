package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
 /*
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
        "name": "Sanya Pandey",
        "email": "sanya_pandey@collier.io",
        "gender": "female",
        "status": "inactive"
    }
}
*/


    /*
    To do that tak do the folllowings;
    1) Check the response body  on postman
    2) Create Pojo classes you need to create 2 pojos
    3) Follow the 4 steps in Api automation
     */
@Test
    public void get01Pojo(){


    spec.pathParams("first", "users","second",13);

    GoRestDataPojo goRestDataPojo=new GoRestDataPojo(13,"Sanya Pandey","sanya_pandey@collier.io","female","inactive");
    GoRestResponseBodyPojo goRestResponseBodyPojo=new GoRestResponseBodyPojo(null,goRestDataPojo);
      Response response=given().spec(spec).when().get("/{first}/{second}");
      response.prettyPrint();

      GoRestResponseBodyPojo actualPojo=response.as(GoRestResponseBodyPojo.class);
      assertEquals(goRestResponseBodyPojo.getMeta(),actualPojo.getMeta()) ;
    assertEquals(goRestDataPojo.getId(),actualPojo.getData().getId());

    assertEquals(goRestDataPojo.getName(),actualPojo.getData().getName());
    assertEquals(goRestDataPojo.getEmail(),actualPojo.getData().getEmail());
    assertEquals(goRestDataPojo.getGender(),actualPojo.getData().getGender());
    assertEquals(goRestDataPojo.getStatus(),actualPojo.getData().getStatus());


}


}
