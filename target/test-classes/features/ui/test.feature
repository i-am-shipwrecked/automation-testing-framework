Feature: SQL injector

  Background: Your web-page is available
    Given User choose the page where he wants to start SQL injector tests

  @SQL-Injections @Security-Testing @Negative @Automation @Run @rerun @ui
  Scenario: SQL Injections
    Given User is on the page, which you can insert into sql_injector.properties
    When User tries to type in a SQL Injection "<sqlInjection>" into input field
    And User clicks on Enter button
    Then Verify that your database is not broken =)


#  @SQL-Injections @Security-Testing @Negative @Automation @Run @rerun @ui
  Scenario: This test should be failed
    Given User is on the page, which you can insert into sql_injector.properties
    When User clicks on null button
    Then Test should be failed and returned

#  @SQL-Injections @Security-Testing @Negative @Automation @Run @rerun @ui
  Scenario: This test should be failed
    Given User is on the page, which you can insert into sql_injector.properties
    When User clicks on null button
    Then Test should be failed and returned