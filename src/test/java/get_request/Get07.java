package get_request;

import base_urls.JsonPlaceBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get07 extends JsonPlaceBaseUrl {
    /*
    Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds less than 5 on the console
			   Assert that maximum userId less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that “delectus aut autem” is one of
			   the titles whose id is less than 5
     */
    @Test
    public void get01(){
        //1.set the Url
        spec.pathParam("first", "todos");

        //2.Set the expected data

        //3.Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}");

        //4.Do Assertions
        response.then().assertThat().statusCode(200);

        // --Print all ids greater than 190 on the console

        JsonPath json = response.jsonPath();

        List<Integer> ids = json.getList("findAll{it.id>190}.id");//Groovy Lang:Java Based Programing Lang
      //  System.out.println(ids);

        // --Assert that there are 10 ids greater than 190

        assertEquals("Number of ids didn't match", 10,ids.size());

        // --Print all userIds whose ids are less than 5 on the console
        List<Integer> userIds = json.getList("findAll{it.id<5}.userId");
      //  System.out.println(userIds);

        // --Assert that the number of userIds whose ids are less than 5 is 4
     //   assertEquals( "The number of userIds whose ids are less than 5 is 4", 4, userIds.size());

        //Print all titles whose ids are less than 5
        List<String> titles = json.getList("findAll{it.id<5}.title");
        System.out.print(titles);

        // --Assert that “delectus aut autem” is one of
        //1.way
        assertTrue("Expected title does not exist",titles.contains("delectus aut autem"));

        //2.way:Lambda
        assertTrue("Expected title does not exist", titles.stream().anyMatch(t ->t.equals("delectus aut autem")));









    }
}
