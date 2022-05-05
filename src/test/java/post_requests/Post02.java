package post_requests;

import base_urls.JsonPlaceBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Post02 extends JsonPlaceBaseUrl {
    /*
         Given
         1)  https://jsonplaceholder.typicode.com/todos
         2)  {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post01(){
        //1.step: Set the Url
        spec.pathParam("first","todos");

        //2.step: Set the Expected Data(Request Body - Payload)
        JsonPlaceHolderTestData jsonPlaceHolderApp = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData = jsonPlaceHolderApp.expectedDataWithAllKeys(55,"Tidy your room", false);
        System.out.println(expectedData);

        //3.step: Send POST Request and Get the Response

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
       // response.prettyPrint();


        //4.step: Do Assertions
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);
        response.then().assertThat().statusCode(201);
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    }
}
