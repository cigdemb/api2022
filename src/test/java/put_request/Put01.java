package put_request;

import base_urls.JsonPlaceBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Put01 extends JsonPlaceBaseUrl {
    /*
    Given
	       1)https://jsonplaceholder.typicode.com/todos/198
	       2) {
                "userId": 21,
                "title": "Wash the dishes",
                "completed": false
             }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */
    @Test
    public void put01(){
        //1.step:Set the Url
        spec.pathParams("first", "todos", "second", 198);

        //2.step:Set the Expected Data (Payload)
        JsonPlaceHolderTestData jsonPlaceHolderApp = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = jsonPlaceHolderApp.expectedDataWithAllKeys(21, "Wash the dishes", false);

        //3.step:Send the PUT Request and Get the Response

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).put("/{first}/{second}");
        response.prettyPrint();

        //4.step:Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);
        response.then().assertThat().statusCode(200);
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    }
}
