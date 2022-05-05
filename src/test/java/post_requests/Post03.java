package post_requests;

import base_urls.AgromonitoringBaseUrl;
import com.google.gson.annotations.JsonAdapter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.AgromonitoringApiTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Post03 extends AgromonitoringBaseUrl {
    /*
    Given
		"http://api.agromonitoring.com/agro/1.0/polygons?appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0&duplicated=true"
            {
               "name":"Polygon Sample",
               "geo_json":{
                  "type":"Feature",
                  "properties":{},
                  "geometry":{
                     "type":"Polygon",
                     "coordinates":[
                        [
                           [-121.1958,37.6683],
                           [-121.1779,37.6687],
                           [-121.1773,37.6792],
                           [-121.1958,37.6792],
                           [-121.1958,37.6683]
                        ]
                     ]
                  }
               }
            }
	When
		 I send POST Request to the Url
	Then
		Assert Status Code (201)
		And Response Body should be like {
										    "id": "5fd8c383714b523b2ce1f154",
										    "geo_json": {
										        "geometry": {
										            "coordinates": [
										                [
										                    [
										                        -121.1958,
										                        37.6683
										                    ],
										                    [
										                        -121.1779,
										                        37.6687
										                    ],
										                    [
										                        -121.1773,
										                        37.6792
										                    ],
										                    [
										                        -121.1958,
										                        37.6792
										                    ],
										                    [
										                        -121.1958,
										                        37.6683
										                    ]
										                ]
										            ],
										            "type": "Polygon"
										        },
										        "type": "Feature",
										        "properties": {
										        }
										    },
										    "name": "Polygon Sample",
										    "center": [
										        -121.1867,
										        37.67385
										    ],
										    "area": 190.9484,
										    "user_id": "5fd8c02a3da20c000759e0f8",
										    "created_at": 1608041347
										}
     */

    @Test
    public void post01(){
        //1.step: Set the Url
        spec.pathParams("first","agro","second",1.0, "three","polygons")
            .queryParams("appid","f4ffe3b2ef1fcb3600ab1d7fbc88c2f0","duplicated", true);

        //2.step: Set the Payload Data
        AgromonitoringApiTestData requestBody = new AgromonitoringApiTestData();
        Map<String, Object> expectedDataMap = requestBody.requestBody();
        System.out.println(expectedDataMap);

        //3.step: Send the Post Request and Get The Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).post("/{first}/{second}/{three}");
        response.prettyPrint();

        //4.step: Do Assertion
        //1.way
        Map<String,Object> actualDataMap = response.as(HashMap.class);

       // assertEquals(expectedDataMap.get("name"), actualDataMap.get("name"));
       // assertEquals(expectedDataMap.get("center"), actualDataMap.get("center"));
//        assertEquals(expectedDataMap.get("area"), actualDataMap.get("area"));
//        assertEquals(String.valueOf(requestBody.coordinates[0][0][0]), ((Map)((Map)actualDataMap.get("geo_json")).get("geometry")).get("coordinates").toString().substring(3,12));
//        assertEquals(requestBody.geometrySetUp().get("type"), ((Map)((Map)actualDataMap.get("geo_json")).get("geometry")).get("type"));

        //System.out.println(actualDataMap.get("geo_json.geometry.coordinates"));

        //2.way
        JsonPath json = response.jsonPath();

        assertTrue(json.getFloat("geo_json.geometry.coordinates[0][0][0]")==
                requestBody.coordinates[0][0][0]);
        assertTrue(json.getString("geo_json.geometry.type").equals(requestBody.geometrySetUp().get("type")));
        assertTrue(json.getString("geo_json.type").equals(requestBody.geo_jsonSetUp().get("type")));
        assertTrue(json.getJsonObject("geo_json.properties").equals(requestBody.geo_jsonSetUp().get("properties")));
        assertTrue(json.getString("name").equals(expectedDataMap.get("name")));
        assertTrue(json.getFloat("center[0]")==requestBody.center[0]);
        assertTrue(json.getFloat("center[1]")==requestBody.center[1]);
        //To asert "area" value you can use both of the followings
        assertTrue(json.get("area").toString().equals(expectedDataMap.get("area").toString()));
        assertTrue(json.getDouble("area")==(Double)(expectedDataMap.get("area")));
    }
}
