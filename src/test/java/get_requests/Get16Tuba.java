package get_requests;

import base_urls.DummyRestApiBaseUrl;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.Collections;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
public class Get16Tuba extends DummyRestApiBaseUrl {
        /*
              URL: https://dummy.restapiexample.com/api/v1/employees
              HTTP Request Method: GET Request
              Test Case: Type by using Gherkin Language
              Assert:  i) Status code is 200
                      ii) There are 24 employees
                     iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                      iv) The greatest age is 66
                       v) The name of the lowest age is "[Tatyana Fitzpatrick]"
                      vi) Total salary of all employees is 6,644,770
       */
    /*
    Given
       https://dummy.restapiexample.com/api/v1/employees
    When
       User sends GET request to the URL
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
            //1.Step: Set the URL
            spec.pathParam("first","employees");
            //2.Step: Set the expected data
            //3.Step: Send the Get request and get the response
            Response response = given().spec(spec).when().get("/{first}");
            response.prettyPrint();
            //4.Step: Do assertions
            //Assert that status code is 200
            //Assert that there are 24 employees
            //Assert that "Tiger Nixon" and "Garrett Winters" are among the employees
            response.
                    then().
                    assertThat().
                    statusCode(200).
                    body("data.id",hasSize(24),"data.employee_name", hasItems("Tiger Nixon","Garrett Winters"));

            //Assert that The greatest age is 66
            JsonPath json = response.jsonPath();
            List<Integer> ageList = json.getList("data.findAll{it.id>0}.employee_age");
            System.out.println(ageList);
            Collections.sort(ageList);
            System.out.println(ageList);
            assertEquals(66, (int) ageList.get(ageList.size()-1));
            //Assert that The name of the lowest age is "Tatyana Fitzpatrick"
//        String minAgeEmployeeName = json.getString("data.findAll{it.employee_age==19}.employee_name");
//        System.out.println(minAgeEmployeeName);
            //"data.findAll{it.employee_age==19}.employee_name"
            String groovyString = "data.findAll{it.employee_age==" + ageList.get(0) + "}.employee_name";
//        String minAgeEmployeeName = json.getString(groovyString);
//        System.out.println(minAgeEmployeeName);
            assertEquals("[Tatyana Fitzpatrick]",json.getString(groovyString));
            //Assert that Total salary of all employees is 6,644,770
            List<Integer> salaryList = json.getList("data.findAll{it.id>0}.employee_salary");
            //1.way to calculate the salary: good
//        int sum = 0;
//        for(Integer w : salaryList){
//            sum = sum + w;
//        }
//        System.out.println(sum);
            //2.Way to calculate salary: better
//        int sum = salaryList.stream().reduce(0,(x,y) -> x+y);
            //3.Way to calculate salary: best
            int sum = salaryList.stream().reduce(0,Math::addExact);
            assertEquals(6644770,sum);
        }
    }


