package get_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class Get06 extends HerOkuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/5
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
            {
                "firstname": "Mary",
                "lastname": "Jackson",
                "totalprice": 111,
                "depositpaid": false,
                "bookingdates": { "checkin": "2017-05-23",
                                  "checkout":"2019-07-02" }
            }
     */
    @Test
    public void get01(){
        //1.step: Set the Url
        spec.pathParams("first","booking", "second", 5);

        //2.step: Set the Expected Data

        //3.step: Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.step: Do Assertion
        //1.way
//        response.then().assertThat().statusCode(200)
//                .contentType(ContentType.JSON)
//                .body("firstname", equalTo("Susan"),
//                        "lastname", equalTo("Jackson"),
//                        "totalprice", equalTo(764),
//                        "depositpaid", equalTo(false),
//                        "bookingdates.checkin", equalTo("2019-05-07"),
//                        "bookingdates.checkout", equalTo("2019-06-05"));

        //2.way: We will use JsonPath Class

          JsonPath json = response.jsonPath();//we convert response obj to json obj
//        assertEquals("Mark",json.getString("firstname"));
//        assertEquals("Jones",json.getString("lastname"));
//        assertEquals(156,json.getInt("totalprice"));
//        assertEquals(false,json.getBoolean("depositpaid"));
//        assertEquals("2016-12-08",json.getString("bookingdates.checkin"));
//        assertEquals("2018-07-08",json.getString("bookingdates.checkout"));

        //3.way: Use Soft Assertion
        //To use Soft Assertion follow given 3 steps;
        //1)Create SoftAssert Object

        SoftAssert softAssert = new SoftAssert();

        //2)By using softAssert object do assertion

        softAssert.assertEquals(json.getString("firstname"), "Jim", "First name didn't match");
        softAssert.assertTrue(json.getString("lastname").equals("Jackson"), "Last name didn't match");
        softAssert.assertEquals(json.getInt("totalprice"), 156, "Total price didn't match");
        softAssert.assertFalse(json.getBoolean("depositpaid"),  "Deposit paid is not false");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2016-12-08", "Check in date didn't match");
        softAssert.assertEquals(json.getString("bookingdates.checkout"), "2018-07-08","Check out date didn't match" );
        //3.Step: Use assertAll() method,
        // otherwise you will get pass everytime, but it will not be meaningful

        softAssert.assertAll();



    }
}
