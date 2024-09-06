package stepdefinition;

import io.cucumber.java.en.*;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class GroceryManagementSteps {

    private Response response;
    private String baseUrl = "http://localhost:5000";

    @Given("the API is running")
    public void the_api_is_running() {
        // Optional health check or just setting up RestAssured
        baseURI = baseUrl;
    }

    @When("I register a new user with username {string} and email {string}")
    public void i_register_a_new_user_with_username_and_email(String username, String email) {
        String body = "{ \"username\": \"" + username + "\", \"password\": \"Password123\", \"email\": \"" + email + "\" }";

        response = 
            given()
                .header("Content-Type", "application/json")
                .body(body)
            .when()
                .post("/users/register");
    }

    @Then("the user should be successfully registered with status code {int}")
    public void the_user_should_be_successfully_registered_with_status_code(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @When("I log in with username {string} and password {string}")
    public void i_log_in_with_username_and_password(String username, String password) {
        String body = "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }";

        response = 
            given()
                .header("Content-Type", "application/json")
                .body(body)
            .when()
                .post("/users/login");
    }

    @Then("the login should be successful with status code {int}")
    public void the_login_should_be_successful_with_status_code(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @When("I add a product with name {string}, category {string}, price {double}, and stock quantity {int}")
    public void i_add_a_product_with_name_category_price_and_stock_quantity(String name, String category, Double price, Integer stockQuantity) {
        String body = "{ \"name\": \"" + name + "\", \"category\": \"" + category + "\", \"price\": " + price + ", \"stockQuantity\": " + stockQuantity + " }";

        response = 
            given()
                .header("Content-Type", "application/json")
                .body(body)
            .when()
                .post("/products");
    }

    @Then("the product should be successfully added with status code {int}")
    public void the_product_should_be_successfully_added_with_status_code(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @When("I search for products with name {string}")
    public void i_search_for_products_with_name(String name) {
        response = 
            given()
                .queryParam("name", name)
            .when()
                .get("/products/search");
    }

    @Then("I should get the product details with status code {int}")
    public void i_should_get_the_product_details_with_status_code(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @When("I place an order with userId {int}, productId {int}, quantity {int}, and totalPrice {double}")
    public void i_place_an_order_with_userId_productId_quantity_and_totalPrice(Integer userId, Integer productId, Integer quantity, Double totalPrice) {
        String body = "{ \"userId\": " + userId + ", \"productId\": " + productId + ", \"quantity\": " + quantity + ", \"totalPrice\": " + totalPrice + " }";

        response = 
            given()
                .header("Content-Type", "application/json")
                .body(body)
            .when()
                .post("/orders");
    }

    @Then("the order should be successfully created with status code {int}")
    public void the_order_should_be_successfully_created_with_status_code(Integer expectedStatusCode) {
        // Assert that the status code from the response matches the expected status code
        Assert.assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
    }

    @When("I delete the user with ID {int}")
    public void i_delete_the_user_with_ID(Integer userId) {
        response = 
            RestAssured.given()
                .when()
                .delete("/users/" + userId);
    }

    @Then("the user should be successfully deleted with status code {int}")
    public void the_user_should_be_successfully_deleted_with_status_code(Integer expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
    }

    @When("I filter the products by category {string}")
    public void i_filter_the_products_by_category(String category) {
        response = given()
            .queryParam("category", category)
        .when()
            .get("/products/filter");
    }

    @Then("I should receive a list of products in the {string} category with status code {int}")
    public void i_should_receive_a_list_of_products_in_the_category_with_status_code(String category, Integer expectedStatusCode) {
        // Assert the status code
        Assert.assertEquals(expectedStatusCode.intValue(), response.getStatusCode());

        // Check that all returned products belong to the specified category
        response.jsonPath().getList("category").forEach(cat -> {
            Assert.assertEquals(category, cat);
        });
    }

    @When("I update the user with ID {int}")
    public void i_update_the_user_with_ID(Integer userId) {
        String body = "{ \"username\": \"UpdatedUser\", \"email\": \"updated@example.com\" }";

        response = 
            given()
                .header("Content-Type", "application/json")
                .body(body)
            .when()
                .put("/users/" + userId);
    }

    @Then("the user should be successfully updated with status code {int}")
    public void the_user_should_be_successfully_updated_with_status_code(Integer expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
    }
    
    @When("I get the orders by ID {int}")
    public void i_get_the_orders_by_ID(Integer orderId) {
        response = 
            given()
                .when()
                .get("/orders/" + orderId);
    }

    @Then("I should get the order details with the status code {int}")
    public void i_should_get_the_order_details_with_the_status_code(Integer expectedStatusCode) {
        // Assert the status code
        Assert.assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
        
        // Optionally, you might want to add further assertions to validate the order details
        // Example:
        // Assert.assertNotNull(response.jsonPath().get("id"));
        // Assert.assertEquals(orderId.intValue(), response.jsonPath().getInt("id"));
    }
}
