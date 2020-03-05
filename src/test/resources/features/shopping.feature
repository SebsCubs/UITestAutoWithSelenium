Feature: Shopping and Cart

  Scenario: Add a product to the shopping cart
    Given An user is logged in
    And An item page is open
    When The item is added to the cart
    Then A pop-up is displayed with the confirmation

  Scenario: Price filter
    Given A search for "portatiles" is made
    When A price filter between "60000" and "100000"
    Then The results show only items within the price range

  Scenario: Delete an item from the cart
    Given An user is logged in
    And At least one item is in the cart
    And The cart page is open
    When The first item is deleted from the cart
    Then The cart has one less item
