Feature: To validate Get satellite details for a specific satellite ID

  Background:
    When Test environment is set

  @Regression
   Scenario Outline: To validate if we can get specific satellite details by ID and also its schema is validated

    And check if health ping is successful

    |path|/satellites|
    |HEADER-Content-Type|application/json          |
    |HEADER-Accept|application/json          |
    |httpCode     |200                     |

     Then check if satellites are returned specific to ID supplied

        |path|/satellites/{ID}|
        |HEADER-Content-Type|application/json          |
        |HEADER-Accept|application/json          |
        |httpCode     |200                      |
      |pathParam       |ID                       |
      |ID              |<ID>                       |

      Examples:

     |Scenario |ID|
     |Happy Flow |25544 |



