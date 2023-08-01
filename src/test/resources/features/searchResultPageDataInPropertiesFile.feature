@DataInPropertiesFile
Feature: feature to test search result page and filter functionality

  Scenario: Validate user is able to search a product and navigate to search result page and apply filter
    Given User is in home page
    When User enters search keyword in search box
    And Clicks search submit button
    Then Validate expected search keyword is displayed in Search result page
    When User selects filter option under filter type
    Then Validate all the products in list displays price
    And Print all the names and price of products, ordered from low to high price