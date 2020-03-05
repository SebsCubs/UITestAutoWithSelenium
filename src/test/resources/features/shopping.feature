Feature: Shopping and Cart

  Scenario: Price filter
    Given A search for "portatiles" is made
    When A price filter between "60000" and "100000"
    Then The results show only items within the price range

  Scenario: Add a product to the shopping cart
    Given The "nevera" item is open
    And An user is logged in
    When The item is added to the cart
    Then A pop-up is displayed with the confirmation

  Scenario: Delete an item from the cart
    Given At least one item is in the cart
    And An user is logged in
    And The cart page is open
    When The first item is deleted from the cart
    Then The cart has one less item
