Feature: Project Controller tests

  @api @projects @positive
  Scenario: Successfully retrieving tasks for a valid project ID
    Given the User sends an API request
    When the User sends a valid GET request to Get all tasks by project ID
    Then the User receives an HTTP response with status code 200

  @api @projects @negative
  Scenario: Successfully retrieving tasks for a valid project ID
    Given the User sends an API request
    When the User sends a GET request with invalid id
    Then the User receives an HTTP response with status code 400