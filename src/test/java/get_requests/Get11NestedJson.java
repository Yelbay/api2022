package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get11NestedJson extends GoRestBaseUrl {

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
            "Indra Ganaka", "Sarada Mehrotra", "Jagathi Chopra" are among the users
        And
            The female users are more than male users
     */
    @Test
    public void get01(){

        //1.Step: Set the URL
        spec.pathParam("first", "users");

        //2.Step: Set the Expected Data

        //3.Step: Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4.Step: Do Assertions

        //1.Way: By using body()
        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit", equalTo(20),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id", hasSize(20),
                        "data.status", hasItem("active"),
                        "data.name", hasItems("Brajendra Chaturvedi", "Lila Pothuvaal"));

        //I will compare the number of female and male users in 2 ways
        //i)I will get all genders then I will count the females then I will calculate males then I will check which one is more
        JsonPath json = response.jsonPath();
        List<String> genders = json.getList("data.gender");
        int numOfFemales = 0;
        for(String w : genders){
            if(w.equals("female")){
                numOfFemales++;
            }
        }
        System.out.println(numOfFemales);//14
        assertTrue(numOfFemales<(genders.size()-numOfFemales));

        //ii)I will get all females by using Groovy, I will get all males by using Groovy then compare them
        List<String> femaleList = json.getList("data.findAll{it.gender='female'}.gender");
        System.out.println(femaleList);//[female, female, ... , female]

        List<String> maleList = json.getList("data.findAll{it.gender='male'}.gender");
        System.out.println(maleList);//[male, male, ... , male]

        assertTrue(femaleList.size()<=maleList.size());

    }

}

