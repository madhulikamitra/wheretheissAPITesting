Feature: To validate we are blocked when we try to use incorrect methods or wrong pathnames

  Background:
    When Test environment is set

  @Regression
   Scenario Outline: To validate if we can get all matching satellites tle information
    And check if health ping is successful

    |path|/satellites|
    |HEADER-Content-Type|application/json          |
    |HEADER-Accept|application/json          |
    |httpCode     |200                     |

    Then check for not allowed methods satellite

      |path|<path>|
      |HEADER-Content-Type|application/json          |
      |HEADER-Accept|application/json          |
      |httpCode     |<httpCode>                    |
      |Method       |<Method>                      |
      |pathParam       |ID                       |
      |ID              |<ID>                       |
      |ErrorScenario      |<ErrorScenario>     |
      |ErrorMessage      |<ErrorMessage>     |
     Examples:

       |Scenario |ID |ErrorScenario|ErrorMessage|httpCode|path|Method|
       |Trying to use wrong path |25544  |true|  Invalid controller specified (satellites_tless)      |404|satellites/25544/tless              |   GET   |
       |Trying to update satellite data |25544 |      true |authorization required|401|satellites/25544|PUT|
       |Trying to delete satellite data |25544 |      true |authorization required|401|satellites/25544|DELETE|
       |Trying to post new satellite data  |25544 |      true |authorization required|401|satellites/25544/positions?timestamps=1627322480,1627328617&units=miles|POST|

