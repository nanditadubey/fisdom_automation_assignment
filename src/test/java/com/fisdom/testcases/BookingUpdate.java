package com.fisdom.testcases;

import com.fisdom.constants.ConfigConstants;
import com.fisdom.dataReader.ExcelReader;
import com.fisdom.pojo.BookingCreation_Pojo;
import com.fisdom.pojo.BookingDates_Pojo;
import com.fisdom.pojo.PartialUpdate_Pojo;
import com.fisdom.utils.RestUtillocal;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.IOException;

/**
 * Created by nandita.dubey on 25/04/20.
 */
public class BookingUpdate {
    public static BookingCreation_Pojo req;
    public static BookingDates_Pojo reqnew;
    public static PartialUpdate_Pojo reqnew1;
    private static Response response=null;
    public static String bookingid=null;


    @DataProvider(name = "requestdata")
    public Object[][] getxldata() throws IOException {
        Object[][] obj=null;
        ExcelReader er=new ExcelReader();
        obj=er.getAddressSearchData("Sheet2");
        return obj;

    }

    @org.testng.annotations.Test(dataProvider="requestdata")
    public static void bookingCreation(String firstname,String lastname,String totalprice,String depositpaid,String checkin,String checkout,String additionalneeds) {
        try {

            //reading data from excel file and mapped to pojo class
            req= new BookingCreation_Pojo();
            reqnew=new BookingDates_Pojo();
            reqnew.setCheckin(checkin);
            reqnew.setCheckout(checkout);
            req.setBookingdates(reqnew);
            req.setFirstname(firstname);
            req.setLastname(lastname);
            req.setTotalprice(totalprice);
            req.setDepositpaid(depositpaid);
            req.setAdditionalneeds(additionalneeds);

            //service call
            response = RestUtillocal.postCall(req, ConfigConstants.bookingcreation, HttpStatus.SC_OK);

            String actualresponse=response.asString();
            JsonPath js = new JsonPath(actualresponse);
            bookingid= js.getString("bookingid");

            //Assertion that booking id is not null and request data passed in api
            Assert.assertNotNull(bookingid,"Booking is null");
            Assert.assertEquals(js.getString("booking.firstname"),ConfigConstants.firstname);
            Assert.assertEquals(js.getString("booking.lastname"),ConfigConstants.lastname);
            Assert.assertEquals(js.getString("booking.totalprice"),ConfigConstants.totalprice);
            Assert.assertEquals(js.getString("booking.depositpaid"),ConfigConstants.depositpaid);
            Assert.assertEquals(js.getString("booking.bookingdates.checkin"),ConfigConstants.checkin);
            Assert.assertEquals(js.getString("booking.bookingdates.checkout"),ConfigConstants.checkout);
            Assert.assertEquals(js.getString("booking.additionalneeds"),ConfigConstants.additionalneeds);

            System.out.println("bookingid is-------------"+bookingid);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @org.testng.annotations.Test(dataProvider="requestdata")
    public static void updateBooking(String firstname,String lastname,String totalprice,String depositpaid,String checkin,String checkout,String additionalneeds) {
        try {
            String bookingupdateurl =ConfigConstants.baseurl+"booking"+"/"+bookingid;
            String bookingupdateurlneg =ConfigConstants.baseurl+"booking"+"/";

            req= new BookingCreation_Pojo();
            reqnew=new BookingDates_Pojo();
            reqnew.setCheckin(checkin);
            reqnew.setCheckout(checkout);
            req.setBookingdates(reqnew);
            req.setFirstname(firstname);
            req.setLastname(lastname);
            req.setTotalprice(totalprice);
            req.setDepositpaid(depositpaid);
            req.setAdditionalneeds(additionalneeds);

            response = RestUtillocal.putCall(req, bookingupdateurl,TokenCreation.token,HttpStatus.SC_OK);
            String actualresponse=response.asString();
            System.out.println("actualresponse is-------------"+actualresponse);

            JsonPath js = new JsonPath(actualresponse);

            //Assertion of request passed data
            Assert.assertEquals(js.getString("firstname"),ConfigConstants.firstname);
            Assert.assertEquals(js.getString("lastname"),ConfigConstants.lastname);
            Assert.assertEquals(js.getString("totalprice"),ConfigConstants.totalprice);
            Assert.assertEquals(js.getString("depositpaid"),ConfigConstants.depositpaid);
            Assert.assertEquals(js.getString("bookingdates.checkin"),ConfigConstants.checkin);
            Assert.assertEquals(js.getString("bookingdates.checkout"),ConfigConstants.checkout);
            Assert.assertEquals(js.getString("additionalneeds"),ConfigConstants.additionalneeds);

            //Negative test case when token is null should get 403 for updatebooking
            response = RestUtillocal.putCall(req, bookingupdateurl,"null",HttpStatus.SC_FORBIDDEN);

            //Negative test case when booking id is not passed in url for updatebooking
            response = RestUtillocal.putCall(req, bookingupdateurlneg,TokenCreation.token,HttpStatus.SC_NOT_FOUND);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.testng.annotations.Test(dataProvider="requestdata")
    public static void partialUpdateBooking(String firstname,String lastname,String totalprice,String depositpaid,String checkin,String checkout,String additionalneeds) {
        try {
            String bookingpartialupdateurl =ConfigConstants.baseurl+"booking"+"/"+bookingid;
            String bookingpartialupdateurlneg =ConfigConstants.baseurl+"booking"+"/";

            reqnew1= new PartialUpdate_Pojo();
            reqnew1.setFirstname(firstname);
            reqnew1.setLastname(lastname);

            response = RestUtillocal.patchCall(reqnew1, bookingpartialupdateurl,TokenCreation.token,HttpStatus.SC_OK);
            String actualresponse=response.asString();
            System.out.println("actualresponse is-------------"+actualresponse);

            JsonPath js = new JsonPath(actualresponse);

            //Assertion of request passed data
            Assert.assertEquals(js.getString("firstname"),ConfigConstants.firstname);
            Assert.assertEquals(js.getString("lastname"),ConfigConstants.lastname);
            Assert.assertEquals(js.getString("totalprice"),ConfigConstants.totalprice);
            Assert.assertEquals(js.getString("depositpaid"),ConfigConstants.depositpaid);
            Assert.assertEquals(js.getString("bookingdates.checkin"),ConfigConstants.checkin);
            Assert.assertEquals(js.getString("bookingdates.checkout"),ConfigConstants.checkout);
            Assert.assertEquals(js.getString("additionalneeds"),ConfigConstants.additionalneeds);

            //Negative test case when token is null should get 403 for partialupdatebooking
            response = RestUtillocal.patchCall(reqnew1, bookingpartialupdateurl,"null",HttpStatus.SC_FORBIDDEN);

            //Negative test case when booking id is not passed in url for partialupdatebooking
            response = RestUtillocal.patchCall(reqnew1, bookingpartialupdateurlneg,TokenCreation.token,HttpStatus.SC_NOT_FOUND);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
