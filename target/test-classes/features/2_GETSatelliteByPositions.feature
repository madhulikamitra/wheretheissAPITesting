Feature: To validate Get satellite position  details for a specific satellite ID

  Background:
    When Test environment is set

  @Regression
   Scenario Outline: To validate if we can get specific satellite position details by timestamps and units and also its schema is validated

    And check if health ping is successful

    |path|/satellites|
    |HEADER-Content-Type|application/json          |
    |HEADER-Accept|application/json          |
    |httpCode     |200                     |

     Then check if satellites are returned based on units and timestamps

        |path|/satellites/{ID}/positions|
        |HEADER-Content-Type|application/json          |
        |HEADER-Accept|application/json          |
        |httpCode     |200                      |
      |pathParam       |ID                       |
      |ID              |<ID>                       |
    |timestamps      |<timestamps>     |
        |units      |<units>     |
        |ErrorScenario      |<ErrorScenario>     |
        |ErrorMessage      |<ErrorMessage>     |
    Examples:

     |Scenario |ID |timestamps      |units|ErrorScenario|ErrorMessage|
     |Happy Flow -miles |25544 |1627322480,1627328617|       miles         |false||
     |Happy Flow - kilometers |25544 |1627322480,1627328617|       kilometers         |false||
     |Happy Flow - future position |25544 |1648670400000,1743364800000|                |          false||
     |Happy Flow - no units |25544 |1627322480,1627328617|                |          false||
     |no timestamp sent |25544 ||          miles      |                         true |invalid timestamp in list: |
     |Happy Flow - future position |25544 |1617134400000,1617220800000,1617307200000,1617393600000,1617480000000,1617566400000,1617652800000,1617739200000,1617825600000,1617912000000,1617998400000|                |          false||
