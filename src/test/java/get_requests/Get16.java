package get_requests;
import base_urls.DummyRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyRestApiBaseUrl {

    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */

    /*
        Given
           https://dummy.restapiexample.com/api/v1/employees
        When
            User send GET Request to the URL
        Then
            Status code is 200
        And
            There are 24 employees
        And
            "Tiger Nixon" and "Garrett Winters" are among the employees
        And
            The greatest age is 66
        And
           The name of the lowest age is "[Tatyana Fitzpatrick]"
        And
           Total salary of all employees is 6,644,770
     */
    @Test
    public void get01(){
        spec.pathParam("first", "employees");
        Response response = given().spec(spec).when().get("/{first}");
        response.
                then().
                assertThat().
                statusCode(200).
                body("data.id", hasSize(24), "data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"));
        JsonPath json = response.jsonPath();
        List<Integer> ageList = json.getList("data.findAll{it.id>0}.employee_age");
        Collections.sort(ageList);
        assertEquals(66, (int)ageList.get(ageList.size()-1));
        String groovyString = "data.findAll{it.employee_age==" + ageList.get(0) + "}.employee_name";
        assertEquals("[Tatyana Fitzpatrick]", json.getString(groovyString));
        List<Integer> salaryList = json.getList("data.findAll{it.id>0}.employee_salary");
        //1.Way to calculate the sum: Good
//        int sum = 0;
//        for(Integer w : salaryList){
//            sum = sum + w;
//        }

        //2.Way to calculate the sum: Better
//        int sum = salaryList.stream().reduce(0, (x,y)->(x+y));

        //3.Way to calculate the sum: The best
        int sum = salaryList.stream().reduce(0, Math::addExact);

        assertEquals(6644770, sum);
    }
}
