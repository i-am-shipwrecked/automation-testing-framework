package steps.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.automation.testing.my_crud_dto.User;
import utils.TestContext;
import utils.TestDataGenerator;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class UserControllerSteps {
    private static final String URL = "http://localhost:8080";
    private Response response;
    private TestContext testContext = TestContext.getInstance();
    private TestDataGenerator testDataGenerator = new TestDataGenerator();

    @Given("the User sends an API request")
    public void theUserSendsAnAPIRequest() {
        RestAssured.baseURI = URL;
    }

    @When("the User sends an API request with JSON data")
    public void theUserSendsAnAPIRequestWithJSONData() {
        User userData = new User(testDataGenerator.generateTestData(6), testDataGenerator.generateTestData(6));

        testContext.setUserData(userData);

        response = given()
                .contentType("application/json")
                .body(userData)
                .when()
                .post("/users/api/register");
    }

    @Then("the User receives an HTTP response with status code {int}")
    public void theUserReceivesAnHTTPResponseWithStatusCode(int statusCode) {
        response.then()
                .statusCode(statusCode);
    }

    @And("the User sends the GET response to this user")
    public void theUserSendsTheGETResponseToThisUser() {
        String userId = response.getBody().asString().replaceAll("\"", "").trim();
        System.out.println(userId + " userId пользователя");
        UUID uuid;

        try {
            uuid = UUID.fromString(userId);
            testContext.setUserId(UUID.fromString(userId));
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid UUID string: " + userId);
        }

        System.out.println(userId + " UUID пользователя");
        System.out.println("URL: " + URL + "/users/api/user/" + userId);


        response = given()
                .pathParam("userId", userId)
                .when()
                .get("/users/api/user/{userId}");

        response.then()
                .statusCode(200);
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
    }

    @When("the User sends an API request with JSON data containing the updated username")
    public void theUserSendsAnAPIRequestWithJSONDataContainingTheUpdatedUsername() {
        User updatedUserData = new User(testDataGenerator.generateTestData(6), testDataGenerator.generateTestData(6));
        System.out.println(updatedUserData);

        response = given()
                .contentType("application/json")
                .body(updatedUserData)
                .when()
                .post("/users/api/register");

        String userId = response.getBody().asString().replaceAll("\"", "").trim();
        testContext.setUserId(UUID.fromString(userId));
        testContext.setUserData(updatedUserData);
        UUID uuid;

        try {
            uuid = UUID.fromString(userId);
            testContext.setUserId(UUID.fromString(userId));
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid UUID string: " + userId);
        }

        response = given()
                .contentType("application/json")
                .body(updatedUserData)
                .when()
                .put("/users/api/user/" + userId + "/profile");
    }

    @And("the User verifies that the user's username is updated by sending a GET request for this user")
    public void theUserVerifiesThatTheUserSUsernameIsUpdatedBySendingAGETRequestForThisUser() {
        UUID userId = testContext.getUserId();

        response = given()
                .pathParam("id", userId)
                .when()
                .get("/users/api/user/{id}");

        response.then().statusCode(200);

        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains(testContext.getUserData().getUsername()));
    }
}
