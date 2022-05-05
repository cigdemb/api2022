package get_request;

import base_urls.GorestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GorestResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get13Pojo extends GorestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users/13
    When
        User send GET Request to the URL
    Then
        Status Code should be 200
    And
        Response body should be like
        {
            "meta": null,
                "data": {
                    "id": 13,
                    "name": "Archan Adiga II",
                    "email": "archan_adiga_ii@murray.org",
                    "gender": "male",
                    "status": "inactive"
                }
        }
*/
     /*
        To do that task do the followings;
        1)Check the response body on Postman
        2)Create Pojo Classes
        3)Follow the 4 steps in API automation
     */

    @Test
    public void getPojo01(){
        //1.Set the Url
        spec.pathParams("first","users", "second", 13);

        //2.Set the Get Data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo(13, "Archan Adiga II", "saini_purushottam_lld@mayer.info", "male", "inactive");
        GorestResponseBodyPojo requestBody = new GorestResponseBodyPojo(null, goRestDataPojo);

        //3.Send the Get Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");



        //4.Do Assertions
        GorestResponseBodyPojo actualBody = response.as(GorestResponseBodyPojo.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(requestBody.getMeta(),actualBody.getMeta());
        assertEquals(requestBody.getData().getId(), actualBody.getData().getId());
        assertEquals(goRestDataPojo.getEmail(), actualBody.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(), actualBody.getData().getGender());
    }
}
