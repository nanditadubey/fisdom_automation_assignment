package com.fisdom.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

/**
 * Created by nandita.dubey on 25/04/20.
 */
public class RestUtillocal {

    public static Response postCall(Object body, String url,int statuscode) {
        Response response = null;
        try {

            response = given().
                    contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .post(url)
                    .then()
                    .assertThat()
                    .statusCode(statuscode)
                    .extract().response();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    public static Response putCall(Object body, String url,String token,int statuscode) {
        Response response = null;
        try {

            response = given().
                    contentType(ContentType.JSON)
                    .header("Cookie","token="+ token )
                    .body(body)
                    .when()
                    .put(url)
                    .then()
                    .assertThat()
                    .statusCode(statuscode)
                    .extract().response();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static Response patchCall(Object body, String url,String token,int statuscode) {
        Response response = null;
        try {

            response = given().
                    contentType(ContentType.JSON)
                    .header("Cookie","token="+ token )
                    .body(body)
                    .when()
                    .patch(url)
                    .then()
                    .assertThat()
                    .statusCode(statuscode)
                    .extract().response();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
