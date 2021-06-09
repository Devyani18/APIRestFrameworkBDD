package Resources;
import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;

public class TestData {

	public static AddPlace addPlacePayload(String name, String language, String address) {
		
		List<String> types = new ArrayList<String>();
		types.add("park");
		types.add("public");
		
		Location loc = new Location();
		loc.setLat(-34.567);
		loc.setLng(56.789);
		
		AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setLanguage(language);
		place.setName(name);
		place.setAddress(address);
		place.setPhone_number("1234567890");
		place.setTypes(types);
		place.setWebsite("www.google.com");
		place.setLocation(loc);
		
		return place;
	}

	public static String deletePlacePayload(String entity_id) {
		
		return "{\r\n"
				+ "    \"place_id\":\""+entity_id+"\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	
}
