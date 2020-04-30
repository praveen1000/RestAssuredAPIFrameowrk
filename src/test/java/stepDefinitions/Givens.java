/*
Author -> Praveen Kumar
 */

package stepDefinitions;

import helpers.HelpersInitialization;
import io.cucumber.java.en.Given;
import io.restassured.specification.RequestSpecification;
import pojo.DeletePlace;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class Givens implements HelpersInitialization {

    private RequestSpecification givenRequestSpec;

    @Given("Add place payload with {string} {string} {string} {double} {double} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address, double latitude,
                                    double longitude, String phoneNumber, String types) throws IOException {

        givenRequestSpec = given().spec(util.requestSpecification())
                .body(data.addPlacePayload(name, language, address, latitude, longitude, phoneNumber, types));

        store.setFullSpec(givenRequestSpec);
    }

    @Given("DeletePlace payload")
    public void deleteplacePayload() throws IOException {

        DeletePlace body = data.deletePlacePayload(store.getPlace_id());
        givenRequestSpec = given().spec(util.requestSpecification()).body(body);
        store.setFullSpec(givenRequestSpec);
    }

    @Given("Update payload {string} {string}" )
    public void updatePayload(String address, String key) throws IOException {
        String placeId = store.getPlace_id();
        givenRequestSpec = given().spec(util.requestSpecification())
                .body(data.UpdatePlacePayload(placeId, address, key));

        store.setFullSpec(givenRequestSpec);
    }
}
