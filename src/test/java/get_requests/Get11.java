package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.AssertJUnit.assertTrue;

public class Get11 extends GoRestBaseUrl {


/*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 20
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 20
    And
        We have at least one "active" status
    And
        "Chandani Mehrotra", "Dakshayani Trivedi" are among the users
    And
        The male users are more than female users

 */

    @Test
    public void get01(){
      //1.Step:Set the Url
       spec.pathParam("first","users");
       //2.Step:Set the expected data

        //3.Step: Send the request and get the response
       Response response= given().spec(spec).when().get("/{first}");
       response.prettyPrint();
//3.Step:Do Assertions
        //By using response body
//        response.then().assertThat().statusCode(200).body("meta.pagination.limit",equalTo(20),
//                "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
//                "data.id",hasSize(20),
//                "data.status",hasItem("active"),
//                "data.name",hasItems( "Raj Acharya,  Dhanalakshmi Sethi"));
//

        //I will compare the number of female and male users in 2 ways
        // i)I will get all genders then I will count the females then i will calculate males then i will check which one is more

        JsonPath json= response.jsonPath();
        List<String> genders=json.getList("data.gender");
        System.out.println(genders);
        //[male, male, female, female, male, male, female, male, male, male, male, female, male, female, male, male, female, female, male, male]
        int numOfMales=0;
        // male,male female, female,female, male
        for (String w:genders) {
            if(w.equals("male")){
                numOfMales++;
            }

        }
        System.out.println(numOfMales);
                             //13               //number of Female
        assertTrue(numOfMales>genders.size()-numOfMales);
//ii)I will get all females by using Groovy, I will get all males by using Groovy then compare them
        List<String> femaleList = json.getList("data.findAll{it.gender='female'}.gender");
        System.out.println(femaleList);//[female, female, ... , female]

        List<String> maleList = json.getList("data.findAll{it.gender='male'}.gender");
        System.out.println(maleList);//[male, male, ... , male]

        Assert.assertTrue(femaleList.size()<=maleList.size());





    }



}