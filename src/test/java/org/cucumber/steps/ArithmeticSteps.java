package org.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ArithmeticSteps {
    private List<Integer> numbers;
    private int sum;
    private Map<String, Integer> priceList;
    private int totalSum;

    @Given("^a list of numbers$")
    public void a_list_of_numbers(List<Integer> numbers) throws Throwable {
        this.numbers = numbers;
    }

    @When("^I summarize them$")
    public void i_summarize_them() throws Throwable {
        for (Integer number : numbers) {
            sum += number;
        }
    }

    @Then("^should I get (\\d+)$")
    public void should_I_get(int expectedSum) throws Throwable {
        assertThat(sum, is(expectedSum));
    }
    


    @Given("^the price list for a coffee shop$")
    public void the_price_list_for_a_coffee_shop(Map<String, Integer> priceList) throws Throwable {
        this.priceList = priceList;
    }

    @When("^I order (\\d+) (.*) and (\\d+) (.*)$")
    public void i_order_coffee_and_donut(int numberOfFirstItems, String firstItem,
                                         int numberOfSecondItems, String secondItem) throws Throwable {
        int firstPrice = priceList.get(firstItem);
        int secondPrice = priceList.get(secondItem);

        totalSum += firstPrice * numberOfFirstItems;
        totalSum += secondPrice * numberOfSecondItems;
    }

    @Then("^should I pay (\\d+)$")
    public void should_I_pay(int expectedCost) throws Throwable {
        assertThat(totalSum, is(expectedCost));
    }
    
    
}
