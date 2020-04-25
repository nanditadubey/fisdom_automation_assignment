package com.fisdom.testcases;

import com.fisdom.constants.ConfigConstants;
import com.fisdom.dataReader.ExcelReader;
import com.fisdom.pojo.BookingCreation_Pojo;
import com.fisdom.pojo.BookingDates_Pojo;
import com.fisdom.pojo.PartialUpdate_Pojo;
import com.fisdom.utils.RestUtillocal;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
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
            response = RestUtillocal.postCall(req, ConfigConstants.bookingcreation);
            String actualresponse=response.asString();
            JsonPath js = new JsonPath(actualresponse);
            bookingid= js.getString("bookingid");
            Assert.assertNotNull(bookingid,"Booking is null");
            System.out.println("bookingid is-------------"+bookingid);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @org.testng.annotations.Test(dataProvider="requestdata")
    public static void updateBooking(String firstname,String lastname,String totalprice,String depositpaid,String checkin,String checkout,String additionalneeds) {
        try {
          String bookingupdateurl =ConfigConstants.baseurl+"booking"+"/"+bookingid;

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
            response = RestUtillocal.putCall(req, bookingupdateurl,TokenCreation.token);
            String actualresponse=response.asString();

            System.out.println("actualresponse is-------------"+actualresponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.testng.annotations.Test(dataProvider="requestdata")
    public static void partialUpdateBooking(String firstname,String lastname,String totalprice,String depositpaid,String checkin,String checkout,String additionalneeds) {
        try {
            String bookingpartialupdateurl =ConfigConstants.baseurl+"booking"+"/"+bookingid;

            reqnew1= new PartialUpdate_Pojo();
            reqnew1.setFirstname(firstname);
            reqnew1.setLastname(lastname);
            response = RestUtillocal.patchCall(reqnew1, bookingpartialupdateurl,TokenCreation.token);
            String actualresponse=response.asString();

            System.out.println("actualresponse is-------------"+actualresponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
