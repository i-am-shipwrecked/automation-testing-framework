package steps.api;

import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.automation.testing.my_crud_dto.Task;
import utils.TestContext;
import utils.TestDataGenerator;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class TasksControllerSteps {
    private static final String URL = "http://localhost:8080";
    private Response response;
    private TestContext testContext = TestContext.getInstance();
    private TestDataGenerator testDataGenerator = new TestDataGenerator();
    private UUID projectId;
    @When("the User sends a POST request with valid JSON")
    public void theUserSendsAPOSTRequestWithValidJSON() {
        Task newTask = testDataGenerator.generateNewTask(projectId); // Создаем новую задачу
        Response response = given()
                .contentType("application/json")
                .body(newTask)
                .when()
                .post("/tasks/api/projects/" + projectId + "/tasks");

        testContext.setResponse(response);
    }
}
