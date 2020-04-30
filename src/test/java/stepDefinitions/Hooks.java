/*
Author -> Praveen Kumar
 */

package stepDefinitions;

import io.cucumber.java.*;
import java.io.IOException;
import java.util.Arrays;

public class Hooks {


    @Before
    public void start(Scenario scenario){

       System.out.println("Scenario: "+scenario.getName() + " -- STARTED");
       System.out.println("---- Tags: "+ Arrays.toString(scenario.getSourceTagNames().toArray()));
    }

    @After
    public void finish(Scenario scenario) throws IOException {

        System.out.println("Scenario: "+scenario.getName() + " -- "+scenario.getStatus());
    }
}
