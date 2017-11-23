package org.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InternationalPriceList {
    private Map<String, Price> priceList;
    private int sekSum;
    private int euroSum;

    @Given("^the price list for an international coffee shop$")
    public void the_price_list_for_an_international_coffee_shop(List<Price> prices) throws Throwable {
        priceList = new HashMap<String, Price>();

        for (Price price : prices) {
            String key = price.getProduct();
            priceList.put(key, price);
        }
    }

    @When("^I buy (\\d+) (.*) and (\\d+) (.*)$")
    public void i_order_coffee_and_donut(int numberOfFirstItems, String firstItem,
                                         int numberOfSecondItems, String secondItem) throws Throwable {
        Price firstPrice = priceList.get(firstItem);
        calculate(numberOfFirstItems, firstPrice);
        Price secondPrice = priceList.get(secondItem);
        calculate(numberOfSecondItems, secondPrice);
    }

    private void calculate(int numberOfItems, Price price) {
        if (price.getCurrency().equals("SEK")) {
            sekSum += numberOfItems * price.getPrice();
            return;
        }
        if (price.getCurrency().equals("EUR")) {
            euroSum += numberOfItems * price.getPrice();
            return;
        }
        throw new IllegalArgumentException("The currency is unknown");
    }

    @Then("^should I pay (\\d+) EUR and (\\d+) SEK$")
    public void should_I_pay_EUR_and_SEK(int expectedEuroSum, int expectedSekSum) throws Throwable {
        assertThat(euroSum, is(expectedEuroSum));
        assertThat(sekSum, is(expectedSekSum));
    }
    
    
    
    /* Inner class */
    public class Price {
        private String product;
        private Integer price;
        private String currency;

        public Price(String product, Integer price, String currency) {
            this.product = product;
            this.price = price;
            this.currency = currency;
        }

        public String getProduct() {
            return product;
        }

        public Integer getPrice() {
            return price;
        }

        public String getCurrency() {
            return currency;
        }
    }
}