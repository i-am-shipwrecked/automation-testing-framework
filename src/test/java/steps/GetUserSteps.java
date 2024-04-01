package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.automation.testing.dto.Users;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetUserSteps {
    private static final String URL = "https://reqres.in";
    private List<Users> users;
    @Given("User sends API request")
    public List<Users> userSendsApiRequest() {
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/api/users?page=2")
                .then().log().all()
                .extract().response();

        assertEquals(200, response.getStatusCode(), "Unexpected HTTP status code");

        users = response.jsonPath().getList("data", Users.class);
        return users;
    }

    @When("User sends API request with JSON")
    public Response userSendsRequest() {
        return given().get(URL + "/api/users?page=2");
    }

    @Then("User gets HTTP request with 200")
    public void userGetsRequest() {
        Response response = userSendsRequest();
        assertNotNull(response, "API response is null");
        assertEquals(200, response.getStatusCode(), "Unexpected HTTP status code");
    }
}
