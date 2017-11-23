package org.cucumber.steps;

import static org.junit.Assert.assertTrue;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class CommonSteps {

	
	Scenario scenario;
	
	/*@After   //Cucumber-JVM reporting in jenkins
	public void afterScenarioReporting(Scenario scenario) {
	    //byte[] screenshot = ((RemoteWebDriver) webDriverInstance).getScreenshotAs(OutputType.BYTES);
	    scenario.write("Sample log from user.....!");
	} */
	
	
	
	@Before
	public void intilScenario(Scenario scenario){
		this.scenario = scenario;
	}
	
	@Given("^\"([^\"]*)\" user is on Github home page")
	public void user_is_on_Github_home_page(String usrerName) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("Its Given-1 :" + usrerName);
	    //assertTrue("This is user defined error message to cucumber-jvm reports", true);
	    scenario.write("This is user defined error message to cucumber-jvm reports-1");
	}
	
	@Given("^([^\"]*) user is on Github home screen$")
	public void user_is_on_Github_home_page_mode(DataTable table) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("Its Given-1-2 :" + table);
	    scenario.write("This is user defined error message to cucumber-jvm reports-2");
	}

	@Then("^user gets a GitHub bootcamp section$")
	public void user_gets_a_GitHub_bootcamp_section() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Its When-1");
		scenario.write("This is user defined error message to cucumber-jvm reports-3");
	}

	@Then("^username is also displayed on right corner$")
	public void username_is_also_displayed_on_right_corner() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Its Then-1");
		scenario.write("This is user defined error message to cucumber-jvm reports-4");
	}

	@Given("^user is on GitHub home page$")
	public void user_UserName_is_on_GitHub() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Its Given-2 :*************: ");
		scenario.write("This is user defined error message to cucumber-jvm reports-5");
	}
	
	@Given("^user ([^\"]*) is on GitHub home screen$")
	public void user_UserName_is_on_GitHub_home(String UserName) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Its Given-2 :*************: " + UserName);
		scenario.write("This is user defined error message to cucumber-jvm reports-6");
	}

	@When("^user focuses on GitHub Bootcamp Section$")
	public void user_focuses_on_GitHub_Bootcamp_Section() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Its When-2");
		scenario.write("This is user defined error message to cucumber-jvm reports-7");
	}

	@Then("^user gets an option to setup git$")
	public void user_gets_an_option_to_setup_git() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Its Then-2");
		scenario.write("This is user defined error message to cucumber-jvm reports-8");
	}

	@Then("^user gets an option to create repository$")
	public void user_gets_an_option_to_create_repository() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Its Then-2/1");
		scenario.write("This is user defined error message to cucumber-jvm reports-9");
	}
	
	
}
