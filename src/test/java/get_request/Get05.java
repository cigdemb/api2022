package get_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get05 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
	    And
	        Among the data there should be someone whose firstname
	        is "Eric" and last name is "Smith"
     */

    @Test
    public void get01(){
        //1.step: Set Url
        //https://restful-booker.herokuapp.com/booking?firstname=Eric&lastname=Smith
        spec.pathParams("first","booking").queryParams("firstname", "Mark",
                "lastname", "Jones");

        //2.step: Setting the expected data

        //3.step: Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4.step: Do Assertion

        response.then().assertThat().statusCode(200);
        assertTrue(response.asString().contains("bookingid"));
        //response.prettyPrint();

    }
}
