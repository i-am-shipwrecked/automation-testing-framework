Feature: Project Controller tests

  @api @projects @positive
  Scenario: Registering a new Project via API endpoint
    Given the User sends an API request
    When the User sends a POST request to "/projects/api/createProject" with JSON data to create a new project
    Then the User receives an HTTP response with status code 200
    And the User sends a GET request to "/api/projects/getProjectById/{id}" for this project
    Then the User receives an HTTP response with status code 200cv
