package api.service;

import api.util.RequestSpecHelper;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;

import static core.config.ConfigurationManager.configuration;

public class BaseApi {

    protected static String authHeader = "__sf_at=%s; Path=/; SameSite=Lax; Max-Age=2592000; Secure; HttpOnly";

    public BaseApi() {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.requestSpecification = RequestSpecHelper.getDefaultRequestSpec();
    }

    protected RequestSpecification setupAuthSpec() {
        var spec = RestAssured.given()
                .baseUri(configuration().authBaseUrl())
                .log().all();
        return spec;
    }

    protected RequestSpecification setupSocialSpec(String accessToken) {
        var spec = RestAssured.given()
                .baseUri(configuration().socialBaseUrl())
                .header(new Header("Cookie", String.format(authHeader, accessToken)))
                .log().all();
        return spec;
    }

    protected RequestSpecification setupAdminSpec(String accessToken) {
        var spec = RestAssured.given()
                .baseUri(configuration().adminBaseUrl())
                .log().all();
        return spec;
    }

    protected RequestSpecification setupNftSpec(String accessToken) {
        var spec = RestAssured.given()
                .baseUri(configuration().nftBaseUrl())
                .header(new Header("Cookie", String.format(authHeader, accessToken)))
                .log().all();
        return spec;
    }

    protected RequestSpecification setupRinkebySpec(String accessToken) {
        var spec = RestAssured.given()
                .baseUri(configuration().rinkeby())
                .log().all();
        return spec;
    }

}
