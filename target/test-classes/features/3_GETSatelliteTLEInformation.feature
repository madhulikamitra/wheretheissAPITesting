Feature: To validate we can get satellite TLE information

  Background:
    When Test environment is set

  @Regression
   Scenario Outline: To validate we can get satellite TLE information by satellite ID
    And check if health ping is successful

    |path|/satellites|
    |HEADER-Content-Type|application/json          |
    |HEADER-Accept|application/json          |
    |httpCode     |200                     |

     Then check if tle information is returned for a satellite

       |path|/satellites/{ID}/tles|
       |HEADER-Content-Type|application/json          |
       |HEADER-Accept|application/json          |
       |httpCode     |<httpCode>                    |
       |pathParam       |ID                       |
       |ID              |<ID>                       |
       |ErrorScenario      |<ErrorScenario>     |
       |ErrorMessage      |<ErrorMessage>     |
     Examples:

       |Scenario |ID |ErrorScenario|ErrorMessage|httpCode|
       |Happy Flow |25544  |false|        |200|
       |Invalid Satellite ID |25545 |      true |satellite not found|404|
       |Invalid Satellite ID |25544* |      true |satellite not found|404|

