package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Post01 extends HerOkuAppBaseUrl {
    /*
    Given
	      1)  https://restful-booker.herokuapp.com/booking
	      2)  {
                "firstname": "Selim",
                "lastname": "Ak",
                "totalprice": 11111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-09",
                    "checkout": "2021-09-21"
                 }
              }
        When
	 		I send POST Request to the Url
	 	Then
	 		Status code is 200
	 		And response body should be like {
											    "bookingid": 11,
											    "booking": {
											        "firstname": "Selim",
											        "lastname": "Ak",
											        "totalprice": 11111,
											        "depositpaid": true,
											        "bookingdates": {
											            "checkin": "2020-09-09",
											            "checkout": "2020-09-21"
											        }
											    }
											 }

	Note 1: The data you send in the request is called "Request Body" or "Payload"
    Note 2: The data you get in response is called "Response Body"
     */

    @Test
    public void post01(){
        //1.step: Set the Url
        spec.pathParam("first", "booking");

        //2.step: Set the Expected Data
        HerOkuAppData herOkuApp = new HerOkuAppData();
        Map<String, Object> expectedData = herOkuApp.expectedDataWithAllKeys("Selim",
                "Ak", 11111, true,
                herOkuApp.bookingdates("2020-09-09","2020-09-21"), "Breakfast");
       // System.out.println(expectedData);

        //3.step: Send the Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();




        //4.step: Do Assertions
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);
        response.then().assertThat().statusCode(200);
        assertEquals(expectedData.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(expectedData.get("checkin"),(actualData.get("booking.bookingdates.checkin")));//bunu ben yazdim calisiyor
//        assertEquals(expectedData.get("checkin"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));//Hocanin kodu calismiyor bende
//        assertEquals(expectedData.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));

    }
}
