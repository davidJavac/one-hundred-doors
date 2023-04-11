Feature: DoorVisitor
  Scenario: Doors to visit
    Given 1 door to visit
    When the visit is performed
    Then I receive a door with number 1 and status OPEN