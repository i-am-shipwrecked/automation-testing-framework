package steps.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.automation.testing.my_crud_dto.User;
import utils.TestContext;


import static io.restassured.RestAssured.given;

public class UserControllerSteps {
    private static final String URL = "http://localhost:8080";
    private Response response;
    private TestContext testContext = TestContext.getInstance();

    @Given("the User sends an API request")
    public void theUserSendsAnAPIRequest() {
        RestAssured.baseURI = URL;
    }

    @When("the User sends an API request with JSON data")
    public void theUserSendsAnAPIRequestWithJSONData() {
        User userData = new User("user2", "pass2");

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
        User userData = testContext.getUserData();

        response = given()
                .queryParam("username", userData.getUsername())
                .queryParam("password", userData.getPassword())
                .when()
                .get("/users/api/user/profile");

        response.then()
                .statusCode(200);
        System.out.println(response.getBody().asString());
    }
}
