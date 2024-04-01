Feature: Testing API

  @Api
  Scenario: Get users from API
    Given User sends API request
    When User sends API request with JSON
    Then User gets HTTP request with 200