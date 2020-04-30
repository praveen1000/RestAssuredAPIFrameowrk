/*
Author -> Praveen Kumar
 */

package cucumber.Options;

import helpers.HelpersInitializzation;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import stepDefinitions.BaseClass;

import java.io.*;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",
        glue = {"stepDefinitions"},
        tags = {"@smoke or @regression"},
        plugin="json:target/jsonReports/cucumber-report.json",
        monochrome = true)
public class TestRunner implements HelpersInitializzation {

    @BeforeClass
    public static void setUp() throws IOException {
        System.out.println("Jenkins Build Number - " + util.getGlobalProperties("jenkins.buildNumber"));
    }

}
