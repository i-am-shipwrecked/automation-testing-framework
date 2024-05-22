package steps.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.automation.testing.my_crud_dto.Project;
import utils.TestContext;
import utils.TestDataGenerator;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class ProjectControllerSteps {
    private static final String URL = "http://localhost:8080";
    private Response response;
    private TestContext testContext = TestContext.getInstance();
    private TestDataGenerator testDataGenerator = new TestDataGenerator();
    @When("the User sends a POST request to {string} with JSON data to create a new project")
    public void theUserSendsAPOSTRequestToWithJSONDataToCreateANewProject(String endpoint) {
        Project newProject = TestDataGenerator.generateRandomProject();

        Response response = given()
                .contentType("application/json")
                .body(newProject)
                .when()
                .post(URL + endpoint);

        String projectIdString = response.getBody().asString().replace("\"", "");
        UUID projectId = UUID.fromString(projectIdString);

        testContext.setResponse(response);
        testContext.setProjectId(projectId);
    }

    @Then("the User receives an HTTP response with status code {int} or {int}")
    public void theUserReceivesAnHTTPResponseWithStatusCode(int statusCode1, int statusCode2) {
        Response response = TestContext.getInstance().getResponse();
        int actualStatusCode = response.getStatusCode();

        if (actualStatusCode != statusCode1 && actualStatusCode != statusCode2) {
            throw new AssertionError("Expected status code " + statusCode1 + " or " + statusCode2 + " but got " + actualStatusCode);
        }
    }

    @And("the User sends a GET request to {string} for this project")
    public void theUserSendsAGETRequestToForThisProject(String endpoint) {
        UUID projectId = testContext.getProjectId();
        String fullEndpoint = endpoint.replace("{id}", projectId.toString());

        Response response = given()
                .contentType("application/json")
                .when()
                .get(fullEndpoint);

        testContext.setResponse(response);
    }
}
