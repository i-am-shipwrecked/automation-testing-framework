package steps.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.automation.testing.my_crud_dto.Project;
import utils.TestContext;
import utils.TestDataGenerator;

public class ProjectControllerSteps {
    private static final String URL = "http://localhost:8080";
    private Response response;
    private TestContext testContext = TestContext.getInstance();
    private TestDataGenerator testDataGenerator = new TestDataGenerator();
    @When("the User sends a POST request to {string} with JSON data to create a new project")
    public void theUserSendsAPOSTRequestToWithJSONDataToCreateANewProject(String endpoint) {
        Project newProject = TestDataGenerator.generateRandomProject();

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(newProject)
                .when()
                .post(URL + endpoint);

        TestContext.getInstance().setResponse(response);
    }

    @And("the User sends a GET request to {string} for this project")
    public void theUserSendsAGETRequestToForThisProject(String endpoint) {
    }
}
