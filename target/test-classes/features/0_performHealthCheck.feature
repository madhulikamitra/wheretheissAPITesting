Feature: To perform health check of the application

  Background:
   When Test environment is set
  @Regression
  Scenario: To perform health check of the application

    And check if health ping is successful

    |path|/satellites|
    |HEADER-Content-Type|application/json          |
    |HEADER-Accept|application/json          |
    |httpCode     |200                      |


