Feature: DoorVisitor acceptance test
  Scenario: Doors to visit
    Given 1 doors to visit
    When the visit is performed
    Then I receive a door with number 1 and status OPEN

  Scenario: Doors to visit
    Given 2 doors to visit
    When the visit is performed
    Then I receive 2 doors: one with number 1 and status OPEN, and the other with number 2 and status CLOSED