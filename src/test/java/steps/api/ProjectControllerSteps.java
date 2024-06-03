package steps.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.automation.testing.my_crud_dto.Project;
import utils.TestContext;
import utils.TestDataGenerator;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;

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

    @When("the User sends a GET request to {string}")
    public void theUserSendsAGETRequestTo(String endpoint) {
        Response response = given()
                .contentType("application/json")
                .when()
                .get(URL + endpoint);

        response.then().log().all();

        testContext.setResponse(response);

        String responseBody = response.getBody().asString();
        assertFalse(responseBody.isEmpty());
    }

    @When("the User sends a POST request to {string} with JSON data to update a project")
    public void theUserSendsAPOSTRequestToWithJSONDataToUpdateAProject(String endpoint) {
        UUID projectId = testContext.getProjectId();
        Project updatedProject = new Project();
        updatedProject.setName("Updated Project Name");
        updatedProject.setDescription("Updated Project Description");
        updatedProject.setBeginning("2024-06-01T00:00:00Z");
        updatedProject.setEnding("2024-12-31T23:59:59Z");

        given()
                .contentType("application/json")
                .body(updatedProject)
                .when()
                .put(URL + "/projects/api/projects/updateProjectById/" + projectId)
                .then()
                .statusCode(200);

    }
}
