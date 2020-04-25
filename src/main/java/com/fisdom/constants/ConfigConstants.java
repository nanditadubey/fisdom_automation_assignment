package com.fisdom.constants;

/**
 * Created by nandita.dubey on 24/04/20.
 */
public class ConfigConstants {
    public static final String RootPath = System.getProperty("user.dir").replace("\\", "/");
    public static final String baseurl="https://restful-booker.herokuapp.com/";
    public static String auth =baseurl+"auth";
    public static String bookingcreation =baseurl+"booking";

    public static final String excelloc=RootPath+"/src/main/resources/TestData.xlsx";

}
