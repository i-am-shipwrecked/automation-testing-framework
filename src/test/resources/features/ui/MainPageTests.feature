Feature: Main Page Tests

  Background: https://www.jetbrains.com/ web-page is available
    Given User choose the page where he wants to start SQL injector tests

  @SQL-Injections @Security-Testing @Negative @Automation @Run @rerun @ui
  Scenario: SQL Injections
    Given User is on the page, which you can insert into sql_injector.properties
    When User tries to type in a SQL Injection "<sqlInjection>" into input field
    And User clicks on Enter button
    Then Verify that your database is not broken =)

