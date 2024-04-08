package steps.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.automation.testing.my_crud_dto.User;


import static io.restassured.RestAssured.given;

public class UserControllerSteps {
    private static final String URL = "http://localhost:8080";
    private Response response;

    @Given("the User sends an API request")
    public void theUserSendsAnAPIRequest() {
        RestAssured.baseURI = URL;
    }

    @When("the User sends an API request with JSON data")
    public void theUserSendsAnAPIRequestWithJSONData() {
        User userData = new User("user1", "pass1");
        response = given()
                .contentType("application/json")
                .body(userData)
                .when()
                .post("/users/api/register");
    }

//    @When("the User sends an API request with JSON data")
//    public void theUserSendsAnAPIRequestWithJSONData(User userData) {
//        // Отправляем POST запрос с данными пользователя
//        response = given()
//                .contentType("application/json")
//                .body(userData)
//                .when()
//                .post("/users/api/register");
//    }

    @Then("the User receives an HTTP response with status code {int}")
    public void theUserReceivesAnHTTPResponseWithStatusCode(int statusCode) {
        // Проверяем, что получили ожидаемый статус-код
        response.then()
                .statusCode(statusCode);
    }
}
