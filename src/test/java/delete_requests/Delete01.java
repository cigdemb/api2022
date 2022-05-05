package delete_requests;

import base_urls.JsonPlaceBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


public class Delete01 extends JsonPlaceBaseUrl {
    /*
     Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    /*
          Interview Question:
          How to automate "DELETE Request" in API Testing?
          i)Create new data by using "POST Request"
          ii)Use "DELETE Request" to delete newly created data.

          Note: Do not use "DELETE Request" for the existing data, create your own data then delete it.
       */
    @Test
    public void delete01(){
        //1.Set the Url
        spec.pathParams("first","todos","second",198);

        //2.Set the Request Body
        //response body is empty Json, so we write empty map
        Map<String, Object> expectedMap = new HashMap<>();

        //3.Send the Delete Request and Get the Response
        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        //4.Do Assertion
        //1.way
        Map<String, Object> actualMap = response.as(HashMap.class);

        response.then().assertThat().statusCode(200);
        assertEquals(expectedMap, actualMap);

        //2.way
        response.then().assertThat().statusCode(200);
        assertTrue(actualMap.isEmpty());

    }
}
