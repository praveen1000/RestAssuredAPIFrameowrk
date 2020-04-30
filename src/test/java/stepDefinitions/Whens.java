/*
Author -> Praveen Kumar
 */

package stepDefinitions;

import helpers.HelpersInitialization;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class Whens implements HelpersInitialization {

    private Response response;

    @When("User calls {string} using http {string} request")
    public void userCallsUsingHttpRequest(String resource, String method) {
        try {
            if (method.equalsIgnoreCase("post")) {
                response = store.getFullSpec().when().post(util.getResourceURL(resource));
            } else if (method.equalsIgnoreCase("get")) {
                response = store.getFullSpec().when().get(util.getResourceURL(resource));
            } else if (method.equalsIgnoreCase("delete")) {
                response = store.getFullSpec().when().delete(util.getResourceURL(resource));
            } else if (method.equalsIgnoreCase("put")) {
                response = store.getFullSpec().when().put(util.getResourceURL(resource));
            } else {
                throw new NullPointerException();
            }

            store.setResponse(response);
        }
        catch(NullPointerException e ){
            System.out.println(method +"  method not found. Please check spelling and provide correct method.");
        }
    }

}
