package patch_requests;

import base_urls.JsonPlaceBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Patch01 extends JsonPlaceBaseUrl {
    /*
    Given
	        https://jsonplaceholder.typicode.com/todos/198
	        {
                "title": "Wash the dishes"
            }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									   }
     */

    @Test
    public void patch01(){
        //1.step:Set the Url
        spec.pathParams("first", "todos", "second", 198);

        //2.Set the Patch Request
        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String, Object> requestBodyMap = requestBody.expectedDataWithMissingKeys(null,"Wash the dishes", null);

        //3.Send the Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().patch("/{first}/{second}");
        response.prettyPrint();

        //4.Do Assertion
        //1.way:
        response.then().assertThat().statusCode(200).body("title", equalTo(requestBodyMap.get("title")));


        //When you do PATCH Assertion, just the data you updated should be asserted. But if someone insists on assert all parts do the following
        Map<String, Object> mapAssertAll = requestBody.expectedDataWithAllKeys(10,"Wash the dishes", true);
        response.then().assertThat().statusCode(200).body("title", equalTo(mapAssertAll.get("title")),
                "userId", equalTo(mapAssertAll.get("userId")),
                "completed", equalTo(mapAssertAll.get("completed")));

    }
}
