Feature: feature to test search result page and filter functionality

  Scenario: Validate user is able to search a product and navigate to search result page and apply filter
    Given user is in home page
    When user enters a <searchKeyword> in search box
    And clicks search submit button
    And user is navigated to search results page
    Then Validate the results for <searchKeyword> are showing in SRP
    When user selects <filterOption> filter option under <filterType> filter
    Then Validate all the product list displays price
    And Print all the names and price of products, ordered from low to high price
