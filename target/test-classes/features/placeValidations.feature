Feature: Validate Place API

	@AddPlace @Regression
	Scenario Outline: Place is added successfully using AddPlaceAPI
		Given Place payload with "<name>" "<language>" "<address>"
		When User calls "AddPlaceAPI" with "post" http request
		Then API responds with "status" as "OK"
		And API responds with status code 200
		And Place is added successfully
		And Verify "place_id" contains expected "name" "<name>" using "GetPlaceAPI"
				
	Examples:
		|name	|language	|address	|
		|Saaj	|Marathi	|Pune		|
#		|Naad	|Hindi		|Mumbai		|

	@DeletePlace @Regression
	Scenario: Place is deleted successfully using DeletePlaceAPI
		Given Delete place payload
		When User calls "DeletePlaceAPI" with "delete" http request
		Then API responds with "status" as "OK"
		And API responds with status code 200