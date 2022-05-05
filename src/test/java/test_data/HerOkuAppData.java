package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppData {

    public Map<String, Object> expectedDataWithAllKeys(String firstname, String lastname, int totalprice, boolean depositpaid, Map<String,String> bookingdates, String additionalneeds){
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("additionalneeds", additionalneeds);

        expectedData.put("bookingdates", bookingdates);

        return expectedData;

    }

    public Map<String, String> bookingdates(String checkin, String checkout){
        Map<String, String> expectedDataBookingDates = new HashMap<>();
        expectedDataBookingDates.put("checkin", checkin);
        expectedDataBookingDates.put("checkout", checkout);

        return expectedDataBookingDates;
    }
    public String expectedBookingDataInString(String checkin, String checkout){
        String expectedBookingData = "{" + "\"checkin\":" + "\"" + checkin + "\"" + "," + "\"checkout\":"  + "\"" + checkout + "\""  + "}" ;
        return expectedBookingData;

    }
    public String expectedDataInString(String firstname, String lastname, int totalprice, boolean depositpaid, String expectedBookingData, String additionalneeds) {

        String expectedData = "{" + "\"firstname\":" + "\"" + firstname + "\"" + "," + "\"lastname\":" + "\"" + lastname + "\""+ ","
                + "\"totalprice\":" + totalprice +   "," + "\"depositpaid\":" + depositpaid +  ","
                + "\"bookingdates\":" + expectedBookingData + "," +"\"additionalneeds\":"  + "\"" +additionalneeds + "\"" + "}";

        return expectedData;
    }

    public static void main(String[] args) {
        HerOkuAppData dates = new HerOkuAppData();

        HerOkuAppData str = new HerOkuAppData();
        String s = str.expectedDataInString("Eric","Smith", 989 , true, dates.expectedBookingDataInString("2020-01-22", "2021-10-15"), "Breakfast");
        System.out.println(s);
    }
}
