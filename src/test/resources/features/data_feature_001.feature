Feature: Home Page
  In order to test Home Page of application
  As a Registered user
  I want to specify the features of home page

  Scenario: Home Page Default content - DataDriven -001
    Given "vgantena" user is on Github home page
    Then user gets a GitHub bootcamp section
    And username is also displayed on right corner
  Scenario: Home Page Default content - DataDriven -002
    Given <UserName> user is on Github home screen
    |UserName|
    |plokeswar|
    |ppagolu|
    Then user gets a GitHub bootcamp section
    And username is also displayed on right corner

  Scenario Outline: GitHub Bootcamp Section - DataDriven -003
    Given user <UserName> is on GitHub home screen
    When user focuses on GitHub Bootcamp Section
    Then user gets an option to setup git
    And user gets an option to create repository 
    Examples:
    |UserName|
    |vgantena|
    |rjampani|

