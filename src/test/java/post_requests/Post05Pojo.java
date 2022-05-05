package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Post05Pojo extends HerOkuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking
            {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
          I send POST Request to the URL
       Then
          Status code is 200
      And
          Response body is like {
                                  "bookingid": 16,
                                  "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }

     */

    @Test
    public void postPojo01(){
        //Set the Url
        spec.pathParam("first", "booking");

        //Set the Expected Data

        BookingDatesPojo bookingDates = new BookingDatesPojo("2021-09-21", "2021-12-21" );
        BookingPojo requestBody = new BookingPojo("Ali", "Can", 999, true, bookingDates, "Breakfast with white tea, Dragon juice" );


        //Send the Post Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");

        //Do the Assertions
        BookingResponseBodyPojo actualBody = response.as(BookingResponseBodyPojo.class);

        assertEquals(200, response.getStatusCode());


        assertEquals(requestBody.getFirstname(),actualBody.getBooking().getFirstname());
        assertEquals(requestBody.getLastname(), actualBody.getBooking().getLastname());
        assertEquals(requestBody.getTotalprice(),actualBody.getBooking().getTotalprice());
        assertEquals(requestBody.getAdditionalneeds(), actualBody.getBooking().getAdditionalneeds());

        //1.way
        assertEquals(requestBody.getBookingdates().getCheckin(),actualBody.getBooking().getBookingdates().getCheckin());
        assertEquals(requestBody.getBookingdates().getCheckout(),actualBody.getBooking().getBookingdates().getCheckout());

        //2.way
        assertEquals(bookingDates.getCheckin(),actualBody.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualBody.getBooking().getBookingdates().getCheckout());
    }
}
