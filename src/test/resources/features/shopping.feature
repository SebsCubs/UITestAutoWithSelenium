Feature: Shopping and Cart

  Scenario: Add a product to the shopping cart
    Given an user account is signed in
    And an item page is opened
    When the item is added to the cart
    Then a pop-up is displayed with the confirmation

  Scenario: Price filter
    Given a search for "portatiles" is made
    When a price filter between "60000" and "100000"
    Then the results show only items within the price range

  Scenario: Delete an item from the cart
    Given an user account is signed in
    And at least one item is in the cart
    And the cart page is open
    When the first item is deleted from the cart
    Then the cart has one less item
