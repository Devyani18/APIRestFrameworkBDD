package stepDefinitions;

import Resources.APIResources;
import Resources.TestData;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;

import java.io.IOException;

import Utilities.ReusableMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PlaceValidation {
	
	RequestSpecification request;
	Response response;
	static String entity_id;
	
	@Given("Place payload with {string} {string} {string}")
	public void place_payload_with(String name, String language, String address) throws IOException {
		request = given().spec(ReusableMethods.getRequestSpec()).body(TestData.addPlacePayload(name, language, address));
	}
	
	@When("User calls {string} with {string} http request")
	public Response user_calls_with_http_request(String resource, String method) {
		APIResources res = APIResources.valueOf(resource);
		
		if(method.equalsIgnoreCase("POST")) {
				response = request.when().post(res.getResource());
				System.out.println("POST request sent");
		}
		if(method.equalsIgnoreCase("GET")) {
			response = request.when().get(res.getResource());
			System.out.println("GET request sent");
		}
		if(method.equalsIgnoreCase("DELETE")) {
			response = request.when().delete(res.getResource());
			System.out.println("DELETE request sent");
		}
//				.then().spec(ReusableMethods.getResponseSpec()).extract().response();
		return response;
	}
	
	@Then("API responds with {string} as {string}")
	public void api_responds_with_as(String responseKey, String expectedValue) {
	    assertEquals(ReusableMethods.getJsonValue(response,responseKey).toString(),expectedValue);
	    System.out.println(responseKey+" equals "+expectedValue);
	}
	
	@Then("API responds with status code {int}")
	public void api_responds_with_status_code(int statusCode) {
	    assertEquals(response.getStatusCode(),statusCode);
	    System.out.println("Status code is :"+statusCode);
	}
	
	@Then("Place is added successfully")
	public void place_is_added_successfully() {
	    
	}
	
		@Then("Verify {string} contains expected {string} {string} using {string}")
	public void verify_contains_expected_using(String entity, String attribute_key, String attribute_value, String resource) throws IOException {
		//get request spec
		entity_id = ReusableMethods.getJsonValue(response,entity);
		request = given().spec(ReusableMethods.getRequestSpec()).queryParam(entity, entity_id);
		user_calls_with_http_request(resource,"GET");
		assertEquals(ReusableMethods.getJsonValue(response, attribute_key),attribute_value);
		System.out.println("Actual "+attribute_key+" value matches the expected one..!");
	}
		
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
		request = given().spec(ReusableMethods.getRequestSpec()).body(TestData.deletePlacePayload(entity_id));
	}

}
