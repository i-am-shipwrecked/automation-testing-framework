package steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import managers.DriverManager;
import utils.TestContext;

import java.util.UUID;

public class HooksApi {
    private TestContext testContext = TestContext.getInstance();
    @After("@api")
    public void tearDownAPI(Scenario scenario) {
        UUID userIdToDelete = testContext.getUserId();
        if (userIdToDelete != null && !scenario.isFailed()) {
            deleteUser(userIdToDelete);
        }
    }

    private void deleteUser(UUID userId) {
        Response response = RestAssured
                .given()
                .pathParam("id", userId.toString())
                .when()
                .delete("/users/api/user/{id}");

        if (response.getStatusCode() == 404) {
            System.out.println("User with ID " + userId + " not found.");
        } else if (response.getStatusCode() == 200) {
            System.out.println("User with ID " + userId + " deleted successfully.");
        } else {
            System.out.println("Failed to delete user with ID " + userId);
        }
    }
}
