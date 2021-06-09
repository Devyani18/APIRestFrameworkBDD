package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReusableMethods {

	static RequestSpecification requestSpec;
	
	public static JsonPath rawToJson(String raw) {
		JsonPath json = new JsonPath(raw);
		return json;
	}
	
	public static Properties getGlobalProperties() throws IOException {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(
				"D:\\Softwares\\eclipse\\Workspace\\APIRestFramework\\src\\test\\java\\Resources\\framework.properties");
		properties.load(fis);
		properties.getProperty("baseURI");
		return properties;
	}
	
	public static RequestSpecification getRequestSpec() throws IOException {

		if(requestSpec==null) {
			PrintStream logger = new PrintStream(new FileOutputStream("RestAPI.log"));
			requestSpec = new RequestSpecBuilder()
					.setBaseUri(getGlobalProperties().getProperty("baseURI"))
					.addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(logger))
					.addFilter(ResponseLoggingFilter.logResponseTo(logger))
					.build();
			return requestSpec;
		}
		return requestSpec;
	}

	public static ResponseSpecification getResponseSpec() {
		ResponseSpecification responseSpec = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.build();
		return responseSpec;
	}
	
	public static String getJsonValue(Response response, String key) {
		JsonPath js = new JsonPath(response.asString());
		return js.get(key);
		
	}
}