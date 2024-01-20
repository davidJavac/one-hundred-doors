Feature: DoorVisitor acceptance test
  Scenario: One Door to visit
    Given 1 door to visit
    When the visit is performed
    Then I receive a door with number 1 and status OPEN

  Scenario: Doors to visit
    Given an amount of doors to visit as table
      | 1     |
      | 2     |
    When the visit is performed to multiple doors
    Then doors should have the correct number and status
      | doors  | number | status |
      | 1      | 1      | OPEN   |
      | 2      | 1      | OPEN   |
      | 2      | 2      | CLOSED |