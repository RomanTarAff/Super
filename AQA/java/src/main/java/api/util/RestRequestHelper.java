package api.util;

import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

import java.util.Map;

import static io.restassured.RestAssured.given;

@UtilityClass
public class RestRequestHelper {

    public static Response getRequest(String url, Map<String, String> params) {
        return given().queryParams(params).when().get(url).then().extract().response();
    }

    public static Response getRequest(String url, Map<String, String> params, String token) {
        return given().auth().oauth2(token).queryParams(params).when().get(url).then().extract().response();
    }

    public static Response getRequest(String url) {
        return given().when().get(url).then().extract().response();
    }

    public static Response getRequest(String url, String token) {
        return given().auth().oauth2(token).when().get(url).then().extract().response();
    }

    public static Response postRequest(Map<String, Object> headers, String url, Object object) {
        return given().body(object).when().headers(headers).post(url).then().extract().response();
    }

    public static Response postRequest(String url, Object object, String token) {
        return given().auth().oauth2(token).body(object).when().post(url).then().extract().response();
    }

    public static Response putRequest(String url, Object object) {
        return given().body(object).when().put(url).then().extract().response();
    }

    public static Response putRequest(String url, Object object, String token) {
        return given().auth().oauth2(token).body(object).when().put(url).then().extract().response();
    }

    public static Response deleteRequest(String url) {
        return given().when().delete(url).andReturn();
    }

    public static Response deleteRequest(String url, String token) {
        return given().auth().oauth2(token).when().delete(url).andReturn();
    }

    public static Response deleteRequest(String url, Object object) {
        return given().body(object).when().delete(url).andReturn();
    }

    public static Response deleteRequest(String url, Object object, String token) {
        return given().body(object).auth().oauth2(token).when().delete(url).andReturn();
    }
}
