/*
Author -> Praveen Kumar
 */

package stepDefinitions;

import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Thens {

    private RequestSpecification thenRequestSpec;
    BaseClass bc = BaseClass.getInstance();

    @Then("API call is success with status code {int}")
    public void apiCallIsSuccessWithStatusCode(int statusCode) {
        assertEquals("Status code did not match", bc.getResponse().getStatusCode(), statusCode);
    }

    @Then("{string} in response body is {string}")
    public void inResponseBodyIs(String attribute, String expectedValue) {

        String actualValue= bc.getUtil().getJsonpath(bc.getResponse(), attribute).toString();
        assertEquals(attribute +" not matched", actualValue, expectedValue);
    }

    @Then("verify {string} created maps to {string} using {string}")
    public void verifyPlace_idCreatedMapsToUsingGetPlaceAPI(String attribute, String expected_Value, String resource) throws IOException {

        String place_id= bc.getUtil().getJsonpath(bc.getResponse(), attribute).toString();
        bc.setPlace_id(place_id);

        thenRequestSpec = given().spec(bc.getUtil().requestSpecification()).queryParam(attribute, place_id);
        bc.setFullSpec(thenRequestSpec);

        new Whens().userCallsUsingHttpRequest(resource, "get");
        String actualName = bc.getUtil().getJsonpath(bc.getResponse(), "name").toString();
        assertEquals("User name not matched", expected_Value, actualName);
    }
}
