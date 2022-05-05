package get_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get09 extends HerOkuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/1
        When
	 		I send GET Request to the url
	 	Then
	 		Response body should be like that;
	 		{
			    "firstname": "Eric",
			    "lastname": "Smith",
			    "totalprice": 555,
			    "depositpaid": false,
			    "bookingdates": {
			        "checkin": "2016-09-09",
			        "checkout": "2017-09-21"
			     }
			     "additionalneeds": "Breakfast"
			}
     */
    @Test
    public void get01(){

        //1.Step: Set the URL
        spec.pathParams("first", "booking", "second", 1);

        //2.Step: Set the Expected Data
        Map<String, String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", "2020-10-28");
        bookingDatesMap.put("checkout", "2021-04-30");

        Map<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname", "Eric");
        expectedDataMap.put("lastname", "Smith");
        expectedDataMap.put("totalprice", 456);
        expectedDataMap.put("depositpaid", false);
        expectedDataMap.put("bookingdates", bookingDatesMap);
        System.out.println(expectedDataMap);

        //3.Step: Send the Request and Get the Response
        Response response  = given().spec(spec).when().get("/{first}/{second}");

        Map<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        //Do Assertions
        //assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        //assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        //assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));
        //assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));

        assertEquals(bookingDatesMap.get("checkin"), ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"), ((Map)actualDataMap.get("bookingdates")).get("checkout"));

    }



        @Test
    public void get02(){
        //1.step: Set the Url
        spec.pathParams("first", "booking", "second", 1);

        //2.step:Set the Expected Data
        HerOkuAppData expectedDataMap = new HerOkuAppData();
        Map<String, Object> expectedData = expectedDataMap.expectedDataWithAllKeys("Susan","Smith",555,
                false, expectedDataMap.bookingdates("2016-09-09", "2017-09-21"), "Breakfast");

        //3.step: Send the Data and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        Map<String,Object> actualData = response.as(HashMap.class);

        //4.step:Do the Assertion
            assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
            System.out.println(expectedData);
            System.out.println(actualData);

    }

}
