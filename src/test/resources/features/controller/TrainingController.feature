Feature: Testing Training Controller

  Scenario: Add training successfully
    Given trainee calls endpoint "/login" with the following JSON request body:
      """
      {
        "username" : "asd.aytkazin",
        "password" : "password"
      }
      """
    When the trainee sends a POST request in order to authenticate
    Then the authentication response status code should be 200

    Given user calls endpoint "/api/trainings" with the following JSON request body:
      """
      {
        "traineeUsername" : "askar.aytkazin",
        "trainerUsername" : "david.martinez",
        "trainingName" : "Some Training123123",
        "trainingDate" : "2025-10-14",
        "trainingDuration" : 60,
        "trainingTypeName" : "training2"
      }
      """
    When the user sends a POST request
    Then the response status code should be 200

  Scenario: Add training failure
    Given trainee calls endpoint "/login" with the following JSON request body:
      """
      {
        "username" : "asd.aytkazin",
        "password" : "password"
      }
      """
    When the trainee sends a POST request in order to authenticate
    Then the authentication response status code should be 200
    Given user calls endpoint "/api/trainings" with the following JSON request body:
      """
      {
      }
      """
    When the user sends a POST request
    Then the response status code should be 400