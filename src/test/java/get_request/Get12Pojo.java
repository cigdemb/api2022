package get_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12Pojo extends HerOkuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/4
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                                    "firstname": "Mark",
                                    "lastname": "Jones",
                                    "totalprice": 537,
                                    "depositpaid": false,
                                    "bookingdates": {
                                        "checkin": "2020-06-25",
                                        "checkout": "2021-01-15"
                                    },
                                    "additionalneeds": "Breakfast"
                                }
                              }
     */

    @Test
    public void getPojo01(){
        //Set the Url
        spec.pathParams("first", "booking", "second", 4);

        //Set the Get Data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2017-08-17", "2019-04-10");
        BookingPojo requestBody = new BookingPojo("Mark", "Jones", 843, true, bookingDates, "Breakfast");

        //3.Send tge Get Request and Get the Response

        Response response = given().spec(spec).when().get("/{first}/{second}");

        //Do the Assertion
        BookingPojo actualData = response.as(BookingPojo.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(requestBody.getFirstname(), actualData.getFirstname());
        assertEquals(requestBody.getLastname(),actualData.getLastname());
        assertEquals(requestBody.getTotalprice(),actualData.getTotalprice());
        assertEquals(requestBody.getDepositpaid(),actualData.getDepositpaid());

        //1.way
        assertEquals(requestBody.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(requestBody.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());

        //2.way
        assertEquals(bookingDates.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBookingdates().getCheckout());




    }

}
