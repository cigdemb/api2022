package get_request;

import base_urls.JsonPlaceBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper extends JsonPlaceBaseUrl {
    /*
    Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */


    @Test
    public void get01ObjMap() {
        //1.Set the Url
        spec.pathParams("first", "todos", "second", 198);

        //2.Set the Expected Data
//        String expectedData = "{\n" +
//                "\"userId\": 10,\n" +
//                "\"id\": 198,\n" +
//                "\"title\": \"quis eius est sint explicabo\",\n" +
//                "\"completed\": true\n" +
//                "}";
        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();
        String expectedData = jsonPlaceHolderTestData.expectedDataInString(10, "quis eius est sint explicabo", true);

        HashMap<String, Object> expectedDataMap = JsonUtil.convertJsonToJavaObject(expectedData, HashMap.class);

        //3.Send the Get Request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //4.Do Assertions
        HashMap<String, Object> actualDataMap = JsonUtil.convertJsonToJavaObject(response.asString(), HashMap.class);

        assertEquals(200, response.getStatusCode());

        assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));
    }

    @Test // This is the best way
    public void get02ObjMap() {
        //1.Set the Url
        spec.pathParams("first", "todos", "second", 198);

        //2.Set the Expected Data
        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();
        String expectedData = jsonPlaceHolderTestData.expectedDataInString(10, "quis eius est sint explicabo", true);
        JsonPlaceHolderPojo expectedDataPojo = JsonUtil.convertJsonToJavaObject(expectedData, JsonPlaceHolderPojo.class);

        //3.Send the Get Request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //4.Do Assertions
        JsonPlaceHolderPojo actualDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(), JsonPlaceHolderPojo.class);

        assertEquals(200, response.getStatusCode());

        assertEquals(expectedDataPojo.getUserId(), actualDataPojo.getUserId());
        assertEquals(expectedDataPojo.getTitle(), actualDataPojo.getTitle());
        assertEquals(expectedDataPojo.getCompleted(), actualDataPojo.getCompleted());


    }
}
