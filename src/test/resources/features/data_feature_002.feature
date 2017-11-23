Feature: Cucumber can convert Gherkin data tables to a list of a type you specify.

  Scenario: The sum of a list of numbers should be calculated
    Given a list of numbers
      | 17   |
      | 42   |
      | 4711 |
    When I summarize them
    Then should I get 4770
  
  #Cucumber can convert a Gherkin table to a map. This an example of a simple price list. 
  Scenario: A price list can be represented as price per item
    Given the price list for a coffee shop
      | coffee | 1 |
      | donut  | 2 |
    When I order 1 coffee and 1 donut
    Then should I pay 3

