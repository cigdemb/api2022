package get_request;

import base_urls.DummyRestApiBaseUrl;
import com.google.gson.JsonParseException;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiPojo;
import pojos.DummyResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyRestApiBaseUrl {
    /*
    Given
        https://dummy.restapiexample.com/api/v1/employee/1
    When
        User send GET Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
     */

    @Test
    public void get01(){
        spec.pathParams("first", "employee", "second", 1);


        DummyApiPojo responseBodyPojo = new DummyApiPojo(1,"Tiger Nixon",
                320800, 61, "" );
        DummyResponseBodyPojo  expectedData = new DummyResponseBodyPojo("success",
                responseBodyPojo, "Successfully! Record has been fetched.");

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        DummyResponseBodyPojo actualData = JsonUtil.convertJsonToJavaObject(response.asString(), DummyResponseBodyPojo.class);
        System.out.println(actualData);

        JsonPath json = response.jsonPath();

        assertEquals(expectedData.getStatus(), actualData.getStatus());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
        assertEquals(expectedData.getData().getEmployeeName(),json.getString("data.employee_name"));
        assertEquals(expectedData.getData().getEmployeeAge(), (Integer) json.getInt("data.employee_age"));
        assertEquals(expectedData.getData().getEmployeeSalary(),(Integer) json.getInt("data.employee_salary"));
        assertEquals(expectedData.getData().getProfileImage(), json.getString("data.profile_image"));


    }
}
