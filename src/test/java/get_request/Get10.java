package get_request;

import base_urls.GorestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GorestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get10 extends GorestBaseUrl {
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
                “meta”: null,
                “data”: {
                    “id”: 13,
                    “name”: “Fr. Ajit Prajapat”,
                    “email”: “ajit_fr_prajapat@barrows.org”,
                    “gender”: “female”,
                    “status”: “active”
                }
            }
     */



    @Test
    public void get02(){
        //1.step:Set the Url
        spec.pathParams("first","users","second",11);

        //2.step:Set the Expected Data
        GorestTestData dataKey = new GorestTestData();
        Map<String, String> dataKeyMap = dataKey.dataKeyMap("Prema Kaul", "ajit_fr_prajapat@barrows.org", "female","active");
        Map<String, Object> expectedDataMap = dataKey.expectedDataMap(null, dataKeyMap);
        System.out.println(dataKeyMap);


        //3.step: Send the Request and Get the Response

        Response response = given().spec(spec).when().get("/{first}/{second}");

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        //4.step:Do assertion
        assertEquals(dataKeyMap.get("meta"),actualData.get("meta"));
        assertEquals(dataKeyMap.get("name"),actualData.get("name"));

    }
}
