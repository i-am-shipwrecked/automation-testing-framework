Feature: User Controller tests

  @api @users
  Scenario: Registering a new user via API endpoint
    Given the User sends an API request
    When the User sends an API request with JSON data
    Then the User receives an HTTP response with status code 200
    And the User sends the GET response to this user
    Then the User receives an HTTP response with status code 200