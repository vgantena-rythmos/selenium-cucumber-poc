/**
 * 
 */
package org.cucumber.core;



import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;

/**
 * @author v-gantena
 *
 */
@RunWith(Cucumber.class) 
@CucumberOptions(
		plugin ={"pretty" ,"html:target/cucumber" , "json:target/cucumber.json", "rerun:target/rerun.txt"},
		features={"src/test/resources/features"},
		glue={"org.cucumber.steps"},tags={"~@ignore"}
		)

public class Runner {
	

}
