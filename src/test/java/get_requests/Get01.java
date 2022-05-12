package get_requests;

import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Get01 {

   /*
    1) Postman is used for manuel API testing.
    2) We use Rest-Assured Library for API Automation Testing.
    3) To type Automation scripts follow the given steps
          a) Understand the requirement
          b) Type our test cases
               i) To type our test cases we will use 'Gherkin Language'
                  'Gherkin Language' has some keywords to type test cases
                   The keywords are xx
                                    y)When: It is for actions.
                                    z)Then: It is for outputs.
                                    t)And: It is for multiple given or when or then.

         c) Start to type Automation Scripts
            i) Set the URL
            ii)Set the expected the data(POST-PUT-PATCH)
            iii) Type the code to send request
            iv) Do Assertions
     */
    /*
    Given
        https://restful-booker.herokuapp.com/booking/3
    When
        User send a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK

 */
   @Test
    public void get01(){
//       i) Set the URL
       String url="https://restful-booker.herokuapp.com/booking/3";

//       ii)Set the expected the data(POST-PUT-PATCH)

//       iii) Type the code to send request
      Response response= given().when().get(url);
       response.prettyPrint();
//       iv) Do Assertions
       response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");
       //How to print status code on the console?
       System.out.println("Status Code:" + response.getStatusCode());

       // How to print Content Type o n the console?
       System.out.println("Content Type:"+ response.getContentType());


       //How to print "Status line" on the console
       System.out.println("Status Code:"+ response.getStatusCode());
       System.out.println(response.statusLine());
       //How to print "Header" on the console
       System.out.println(response.getHeader("Connection"));

       //How to print ""Headers" on the console?
       System.out.println(response.getHeaders());

       //How to print "Time" on the console?
       System.out.println("Time:"+ response.getTime());

       Assert.assertEquals(200,response.statusCode());
       Assert.assertEquals("application/json",response.contentType());
       Assert.assertEquals("HTTP/1.1 200 OK",response.statusLine());
    }

}
