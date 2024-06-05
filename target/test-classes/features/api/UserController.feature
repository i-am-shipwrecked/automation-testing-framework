Feature: User Controller tests

  Background: Api runs correctly

  @api @users @positive
  Scenario: Registering a new user via API endpoint
    Given the User sends an API request
    When the user sends an API request with JSON data to register a new user
    Then the User receives an HTTP response with status code 200
    And the User sends the GET response to this user
    Then the User receives an HTTP response with status code 200

  @api @users @positive
  Scenario: Updating a user's username via API endpoint
    Given the User sends an API request
    When the User sends an API request with JSON data containing the updated username
    Then the User receives an HTTP response with status code 200
    And the User verifies that the user's username is updated by sending a GET request for this user
    Then the User receives an HTTP response with status code 200

  @api @users @positive
  Scenario: Update users password by ID
    Given the User sends an API request
    When the user sends an API request with JSON data to register a new user
    Then the User receives an HTTP response with status code 200
    When the User sends an API request with JSON data containing the updated password
    Then the User receives an HTTP response with status code 200
    And the User verifies that the user's password is updated by sending a GET request for this user
    Then the User receives an HTTP response with status code 200

  @api @users @positive
  Scenario: Deleting a user by ID
    Given the User sends an API request
    When the user sends an API request with JSON data to register a new user
    Then the User receives an HTTP response with status code 200
    And the User sends the GET response to this user
    Then the User receives an HTTP response with status code 200
    And the user sends DELETE response by ID
    Then the User receives an HTTP response with status code 200