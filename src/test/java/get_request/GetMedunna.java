package get_request;

import base_urls.MedunnaBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.MedunnaResponseBodyPojo;
import pojos.MedunnaUserCountryInfo;
import pojos.MedunnaUserInfo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class GetMedunna extends MedunnaBaseUrl {
/*
Given
        https://medunna.com/api/patients/3352
    When
        User send GET Request
    Then
        Status code is 200
    And
    "createdBy": "anonymousUser",
    "createdDate": "2021-12-21T22:14:42.623577Z",
    "id": 3352,
    "firstName": "burhanoArron",
    "lastName": "RueckerMcKenzie",
    "birthDate": "1999-12-31T22:12:00Z",
    "phone": "1111111111",
    "gender": "MALE",
    "bloodGroup": "Anegative",
    "adress": "Suite 142 440 Hills Squares, Langoshport, ID 730329601 Wade Shore, Andersontown, KS 45640",
    "email": "filomena.corwin@yahoo.com",
    "description": "Sick",
    "user": {
        "createdBy": "system",
        "createdDate": null,
        "id": 1,
        "login": "systema",
        "firstName": "Systema",
        "lastName": "Systema",
        "email": "haci@gamil.com",
        "activated": true,
        "langKey": "en",
        "imageUrl": null,
        "resetDate": null,
        "ssn": "856-45-6789"
    },
    "appointments": null,
    "inPatients": null,
    "country": {
        "id": 1201,
        "name": "Türkye"
    },
    "cstate": null
}
 */

    @Test
    public void get01() {
        spec.pathParams("first", "patients", "second", 3352);

        MedunnaUserInfo medunnaUserInfo = new MedunnaUserInfo("system", "null", 1, "systema", "Systema", "Systema", "haci@gamil.com", true, "en", "null","null", "856-45-6789");
        MedunnaUserCountryInfo medunnaUserCountryInfo = new MedunnaUserCountryInfo(1201, "Türkye");
        MedunnaResponseBodyPojo expectedData = new MedunnaResponseBodyPojo("anonymousUser",
                    "2021-12-21T22:14:42.623577Z", 3352, "burhanoArron", "RueckerMcKenzie", "1999-12-31T22:12:00Z", "1111111111", "MALE", "Anegative",
                    "Suite 142 440 Hills Squares, Langoshport, ID 730329601 Wade Shore, Andersontown, KS 45640", "filomena.corwin@yahoo.com", "Sick", medunnaUserInfo,
                    "null", "null", medunnaUserCountryInfo, "null");
        Response actualData = given().spec(spec)
                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dXNhbGdhc2ltb3YiLCJhdXRoIjoiUk9MRV9BRE1JTiIsImV4cCI6MTY1NjUyNDA1M30.VLvRIgejqGm_460n_EqBkTYbV4wBEjwZc9gV74T7wAR2o9nSzKtsmRNw-aJScB5cG6B7_ZJpwcaaYZvDlBcKMA")
                .when().get("/{first}/{second}");

        actualData.then().assertThat().statusCode(200);

        MedunnaResponseBodyPojo medunnaResponseBodyPojo = JsonUtil.convertJsonToJavaObject(actualData.asString(), MedunnaResponseBodyPojo.class);
        System.out.println(medunnaResponseBodyPojo);

        //assertEquals(expectedData.getStatus(), medunnaResponseBodyPojo.getStatus );
        assertEquals(expectedData.getAdress(), medunnaResponseBodyPojo.getAdress());
        assertEquals(expectedData.getBirthDate(), medunnaResponseBodyPojo.getBirthDate());
        assertEquals(expectedData.getCountry().getId(), medunnaResponseBodyPojo.getCountry().getId());
    }

}
