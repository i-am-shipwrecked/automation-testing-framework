Feature: User Controller tests

  @apii @users
  Scenario: Registering a new user via API endpoint
    Given the User sends an API request
    When the User sends an API request with JSON data
    Then the User receives an HTTP response with status code 200
    And the User sends the GET response to this user
    Then the User receives an HTTP response with status code 200

  @api @users
  Scenario: Updating a user's username via API endpoint
    Given the User sends an API request
    When the User sends an API request with JSON data containing the updated username
    Then the User receives an HTTP response with status code 200
    And the User verifies that the user's username is updated by sending a GET request for this user
    Then the User receives an HTTP response with status code 200
