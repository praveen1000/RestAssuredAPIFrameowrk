Feature: Validating place API's

  As a user
  I want to add and delete a place
  In order to verify place API calls

  @smoke
  Scenario Outline: Verify new place is successfully added using AddPlaceAPI
    Given Add place payload with "<name>" "<language>" "<address>" <latitude> <longitude> "<phoneNumber>" "<types>"
    When User calls "AddPlaceAPI" using http "post" request
    Then API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify "place_id" created maps to "<name>" using "GetPlaceAPI"

    Examples:
      | name    | language | address   | latitude|longitude| phoneNumber| types       |
      | Praveen | English  | Rochester | 50.31   |30.567   | 9999999999 | first,second|

  @smoke
  Scenario Outline: Verify address is successfully using PutPlaceAPI
    Given Update payload "<address>" "<key>"
    When User calls "PutPlaceAPI" using http "put" request
    Then API call is success with status code 200
    And "msg" in response body is "Address successfully updated"
    Examples:
      | address     | key        |
      | New address | qaclick123 |

  @smoke
  Scenario: Verify place is deleted successfully using DeletePlaceAPI
    Given DeletePlace payload
    When User calls "DeletePlaceAPI" using http "post" request
    Then API call is success with status code 200
    And "status" in response body is "OK"

