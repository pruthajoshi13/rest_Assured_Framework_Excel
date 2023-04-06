package Common_Method;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class Common_Method_API_GET {
	public static int responsestatuscode_extractor(String baseuri, String resource) {
		RestAssured.baseURI=baseuri;
		int response_statuscode = given().when().get(resource).then().extract().statusCode();
		return response_statuscode;
	}
	public static String responsebody_extractor(String baseuri, String resource) {
		RestAssured.baseURI=baseuri;
		String response_body = given().when().get(resource).then().extract().response().asString();
		return response_body;
	}
}
