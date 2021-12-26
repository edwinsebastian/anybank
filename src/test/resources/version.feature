Feature: the version can be retrieved
  Scenario: client makes call to GET /v1/version
    When the client calls /v1/version
    Then the client receives status code of 200
    And the client receives server version 1.0