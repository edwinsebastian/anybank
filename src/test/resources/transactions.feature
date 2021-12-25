Feature: check transactions status

  Scenario: A - check a not stored transaction from any channel
    Given A transaction that is not stored in our system
    When I check the status from any channel
    Then The system returns the status INVALID

  Scenario: B - check a stored transaction dated earlier than today from CLIENT or ATM channel
    Given A transaction that is stored in our system
    When I check the status from CLIENT or ATM channel
    And the transaction date is before today
    Then The system returns the status SETTLED
    And the amount subtracting the fee

  Scenario: C - check a stored transaction dated earlier than today from INTERNAL channel
    Given A transaction that is stored in our system
    When I check the status from INTERNAL channel
    And the transaction date is before today
    Then The system returns the status SETTLED
    And the amount
    And the fee

  Scenario: D - check a stored transaction dated today from CLIENT or ATM channel
    Given A transaction that is stored in our system
    When I check the status from CLIENT or ATM channel
    And the transaction date is equals to today
    Then The system returns the status PENDING
    And the amount subtracting the fee

  Scenario: E - check a stored transaction dated today from INTERNAL channel
    Given A transaction that is stored in our system
    When I check the status from INTERNAL channel
    And the transaction date is equals to today
    Then The system returns the status PENDING
    And the amount
    And the fee

  Scenario: F - check a stored transaction date greater than today from CLIENT channel
    Given A transaction that is stored in our system
    When I check the status from CLIENT channel
    And the transaction date is greater than today
    Then The system returns the status FUTURE
    And the amount subtracting the fee

  Scenario: G - check a stored transaction date greater than today from ATM channel
    Given A transaction that is stored in our system
    When I check the status from ATM channel
    And the transaction date is greater than today
    Then The system returns the status PENDING
    And the amount subtracting the fee

  Scenario: H - check a stored transaction date greater than today from INTERNAL channel
    Given A transaction that is stored in our system
    When I check the status from INTERNAL channel
    And the transaction date is greater than today
    Then The system returns the status FUTURE
    And the amount
    And the fee