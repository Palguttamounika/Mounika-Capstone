Feature: Place a new order

  Scenario: Successfully place a new order
    Given I am logged in as a user
    When I add items to the cart
    Then the order is placed successfully
