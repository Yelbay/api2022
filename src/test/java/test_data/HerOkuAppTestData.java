package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {

        /*
        {
          "firstname": "Selim",
           "lastname": "Ak",
           "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                     "checkin": "2021-09-09",
                      "checkout": "2021-09-21"
                      }
                   }
         */

    public Map<String ,String> bookingDateSetUp(String checkin,String checkout){
        Map<String,String> bookingDateMap = new HashMap<>();
        bookingDateMap.put("checkin",checkin);     /// inner Json
        bookingDateMap.put("checkout",checkout);
        return bookingDateMap;
    }

    public Map<String,Object> expectedDataSetUp(String firstname, String lastname, int totalprice, boolean depositpaid, Map<String,String> bookingdates ){
        Map<String,Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname",firstname );
        expectedDataMap.put("lastname", lastname);
        expectedDataMap.put("totalprice", totalprice);             // For Outer Json
        expectedDataMap.put("depositpaid",depositpaid);
        expectedDataMap.put("bookingdates", bookingdates);

        return expectedDataMap;


    }


}
