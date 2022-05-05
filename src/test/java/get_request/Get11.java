package get_request;

import base_urls.GorestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Get11 extends GorestBaseUrl {
    /*
    Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 20
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 20
        And
            We have at least one "active" status
        And
            "Indra Ganaka", "Sarada Mehrotra", "Jagathi Chopra" are among the users
        And
            The female users are more than male users
     */

    @Test
    public void get01(){
        //1.step:Set the Url
        spec.pathParam("first", "users");

        //2.step: Set the Expected Data

        //3.step:Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}");
      //  response.prettyPrint();

        //4.step:Do Assertion
        response.then().assertThat().statusCode(200).body("meta.pagination.limit",equalTo(20));
        response.then().assertThat().body("meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data.id",hasSize(20),
                "data.status", hasItem("active"),
                "data.name",hasItems("Mrs. Susheel Jain", "Kartik Kocchar", "Vidya Talwar"));


        //1.way
        JsonPath json = response.jsonPath();
        List<String> gender = json.getList("data.gender");
        System.out.println(gender);
        int numOfFemales = 0;
        for (String w: gender) {
            if(w.equals("female")){
                numOfFemales++;
            }
        }
        //System.out.println(numOfFemales);
        assertTrue(numOfFemales<(gender.size()/2));

        //2.waw: Groovy Lang
        List<String> femaleList = json.getList("data.findAll{it.gender='female'}.gender");
        List<String> maleList = json.getList("data.findAll{it.gender='male'}.gender");
        assertTrue(femaleList.size()>=maleList.size());



    }
}
