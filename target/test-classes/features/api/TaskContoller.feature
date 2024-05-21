Feature: Task Controller Controller tests

#  @api @tasks @positive
  Scenario: Successfully retrieving tasks for a valid project ID
    Given the User sends an API request
    When the User sends a valid GET request to Get all tasks by project ID
    Then the User receives an HTTP response with status code 200
    Then the User receives all tasks about project

  @api @tasks @negative
  Scenario: Successfully creating a new task
    Given the User sends an API request
    When the User sends a POST request with valid JSON
    Then the User receives an HTTP response with status code 200