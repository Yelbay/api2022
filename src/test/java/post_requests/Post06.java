package post_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.ResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post06 extends DummyRestApiBaseUrl {

    /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body: {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }
     */
    /*
        Given
            https://dummy.restapiexample.com/api/v1/create
            {
                "employee_name": "Tom Hanks",
                "employee_salary": 111111,
                "employee_age": 23,
                "profile_image": "Perfect image"
             }
         When
            User send the POST Request
         Then
            Status code is 200
         And
            Response body should be like the following
            {
                "status": "success",
                "data": {
                    "employee_name": "Ali Can",
                    "employee_salary": 111111,
                    "employee_age": 23,
                    "profile_image": "Perfect image",
                    "id": 6344
                },
                "message": "Successfully! Record has been added."
            }
     */
    @Test
    public void post01(){
        spec.pathParam("first", "create");
        DummyApiDataPojo dummyApiDataPojo = new DummyApiDataPojo("Tom Hanks", 111111, 23, "Perfect image");
        ResponseBodyPojo expectedData = new ResponseBodyPojo("success", dummyApiDataPojo, "Successfully! Record has been added.");
        Response response = given().spec(spec).contentType(ContentType.JSON).body(dummyApiDataPojo).when().post("/{first}");
        ResponseBodyPojo actualData = JsonUtil.convertJsonToJavaObject(response.asString(), ResponseBodyPojo.class);
        assertEquals(expectedData.getStatus(), actualData.getStatus());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
        assertEquals(expectedData.getData().getEmployeeName(), actualData.getData().getEmployeeName());
        assertEquals(expectedData.getData().getEmployeeSalary(), actualData.getData().getEmployeeSalary());
        assertEquals(expectedData.getData().getEmployeeAge(), actualData.getData().getEmployeeAge());
        assertEquals(expectedData.getData().getProfileImage(), actualData.getData().getProfileImage());
    }
}