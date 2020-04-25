package com.fisdom.constants;

/**
 * Created by nandita.dubey on 25/04/20.
 */
public class ConfigConstants {
    public static final String RootPath = System.getProperty("user.dir").replace("\\", "/");
    public static final String baseurl="https://restful-booker.herokuapp.com/";
    public static String auth =baseurl+"auth";
    public static String bookingcreation =baseurl+"booking";
    public static String firstname ="Anand";
    public static String lastname ="Brown";
    public static String totalprice ="500";
    public static String depositpaid ="true";
    public static String checkin ="2018-01-01";
    public static String checkout ="2019-01-01";
    public static String additionalneeds ="Breakfast";

    public static final String excelloc=RootPath+"/src/main/resources/TestData.xlsx";

}
