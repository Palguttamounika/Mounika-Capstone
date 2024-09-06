Feature: Grocery Store Management API Testing

  Scenario: User Registration
    Given the API is running
    When I register a new user with username "Siri" and email "Siri@example.com"
    Then the user should be successfully registered with status code 201

  Scenario: User Login
    Given the API is running
    When I log in with username "Mounika" and password "Mounika@123"
    Then the login should be successful with status code 200

  Scenario: Add a new product
    Given the API is running
    When I add a product with name "Apple", category "Fruits", price 10.0, and stock quantity 100
    Then the product should be successfully added with status code 201

  Scenario: Search for a product
    Given the API is running
    When I search for products with name "Mango"
    Then I should get the product details with status code 200

  Scenario: Place an order
    Given the API is running
    When I place an order with userId 1, productId 1, quantity 5, and totalPrice 500.0
    Then the order should be successfully created with status code 201

  Scenario: Delete a user by user ID
    Given the API is running
    When I delete the user with ID 2
    Then the user should be successfully deleted with status code 204
    
  Scenario: Filter products by category
    Given the API is running
    When I filter the products by category "Food"
    Then I should receive a list of products in the "Food" category with status code 200
  
  Scenario:Update user profile by user ID
  Given the API is running
  When I update the user with ID 4
  Then the user should be successfully deleted with status code 200
  
  Scenario:Get order details by order ID
  Given the API is running
  When I get the orders by ID 5
  Then I should get the order details with the status code 200