Feature: feature to test search result page and filter functionality

  Scenario: Validate user is able to search a product and navigate to search result page and apply filter
    Given User is in home page "https://www.amazon.com/"
    When User enters search keyword "phone case" in search box
    And Clicks search submit button
    Then Validate expected search keyword "phone case" is displayed in Search result page
    When User selects "Carbon Fiber" filter option under "Material" filter type
    Then Validate all the products in list displays price
    And Print all the names and price of products, ordered from low to high price

  # These steps uses DataTable to control Test Data
    #Given User is in home page <url>
      #| https://www.amazon.com/ |
    #When User enters search keyword <searchKeyword> in search box
      #| phone case |
    #And Clicks search submit button
    #Then Validate expected search keyword <expectedText> is displayed in Search result page
      #| phone case |
    #When User selects <filterOption> filter option under <filterType> filter type
      #| Carbon Fiber |
      #| Material     |
    #Then Validate all the products in list displays price
    #And Print all the names and price of products, ordered from low to high price
