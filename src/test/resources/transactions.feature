Feature: Check transactions status

  Scenario Outline: A - check a not stored transaction from any channel
    Given A transaction that is not stored in our system
    When I check the status from <channel> channel
    Then The system returns the status INVALID
    Examples:
      |channel  |
      |CLIENT   |
      |ATM      |
      |INTERNAL |

  Scenario Outline: B - check a stored transaction dated earlier than today from CLIENT or ATM channel
    Given A transaction that is stored in our system with transaction date before than today
    When I check the status from <channel> channel
    Then The system returns the status SETTLED
    And the amount subtracting the fee
    Examples:
      |channel  |
      |CLIENT   |
      |ATM      |

  Scenario: C - check a stored transaction dated earlier than today from INTERNAL channel
    Given A transaction that is stored in our system with transaction date before than today
    When I check the status from INTERNAL channel
    Then The system returns the status SETTLED
    And the amount
    And the fee

  Scenario Outline: D - check a stored transaction dated today from CLIENT or ATM channel
    Given A transaction that is stored in our system with transaction date equals than today
    When I check the status from <channel> channel
    Then The system returns the status PENDING
    And the amount subtracting the fee
    Examples:
      |channel  |
      |CLIENT   |
      |ATM      |

  Scenario: E - check a stored transaction dated today from INTERNAL channel
    Given A transaction that is stored in our system with transaction date equals than today
    When I check the status from INTERNAL channel
    Then The system returns the status PENDING
    And the amount
    And the fee

  Scenario: F - check a stored transaction date greater than today from CLIENT channel
    Given A transaction that is stored in our system with transaction date greater than today
    When I check the status from CLIENT channel
    Then The system returns the status FUTURE
    And the amount subtracting the fee

  Scenario: G - check a stored transaction date greater than today from ATM channel
    Given A transaction that is stored in our system with transaction date greater than today
    When I check the status from ATM channel
    Then The system returns the status PENDING
    And the amount subtracting the fee

  Scenario: H - check a stored transaction date greater than today from INTERNAL channel
    Given A transaction that is stored in our system with transaction date greater than today
    When I check the status from INTERNAL channel
    Then The system returns the status FUTURE
    And the amount
    And the fee