package stepDefinitions;

import java.io.IOException;
import Utilities.ReusableMethods;
import io.cucumber.java.Before;
import io.restassured.response.Response;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		if(PlaceValidation.entity_id==null) {
			Response response;
			PlaceValidation p = new PlaceValidation();
			p.place_payload_with("Hook", "German", "Bandstand");
			response = p.user_calls_with_http_request("AddPlaceAPI", "POST");
			PlaceValidation.entity_id = ReusableMethods.getJsonValue(response,"place_id");
		}
	}

}
