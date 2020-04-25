package com.fisdom.testcases;

import com.fisdom.constants.ConfigConstants;
import com.fisdom.dataReader.ExcelReader;
import com.fisdom.pojo.*;
import com.fisdom.utils.RestUtillocal;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;

import java.io.IOException;

/**
 * Created by nandita.dubey on 24/04/20.
 */
public class TokenCreation {
    public static Authtoken_Pojo req;
    private static Response response=null;
    public static String token;

    @DataProvider(name = "requestdata")
    public Object[][] getxldata() throws IOException {
        Object[][] obj=null;
        ExcelReader er=new ExcelReader();
        obj=er.getAddressSearchData("Sheet1");
        return obj;

    }

    @org.testng.annotations.Test(dataProvider="requestdata")
    public static void authtest(String a,String b) {
        try {
            req= new Authtoken_Pojo();
            req.setUsername(a);
            req.setPassword(b);
            String auh=req.getUsername();
            response = RestUtillocal.postCall(req,ConfigConstants.auth);
            String strr=response.asString();
            JsonPath js = new JsonPath(strr);
            token = js.getString("token");
            System.out.println("token is-------------"+token);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
