Feature: Testing Trainee Registration
  Scenario: Register Trainee Successfully
    Given guest calls endpoint "/api/trainees" with the following JSON request body:
    """
    {
      "firstName" : "Chelovek",
      "lastName" : "Adamov",
      "dateOfBirth" : "1999-09-06",
      "address" : "Saryarka str. 6"
    }
    """
    When user sends a POST request in order to register
    Then the response code should be 200

  Scenario: Register Trainee Failure
    Given guest calls endpoint "/api/trainees" with the following JSON request body:
    """
    {
    }
    """
    When user sends a POST request in order to register
    Then the response code should be 400

  Scenario: Register Trainer Successfully
    Given guest calls endpoint "/api/trainers" with the following JSON request body:
    """
    {
      "firstName" : "Chelovekus",
      "lastName" : "Adamovus",
      "specialization" : 1
    }
    """
    When user sends a POST request in order to register
    Then the response code should be 200

  Scenario: Register Trainer Failure
    Given guest calls endpoint "/api/trainers" with the following JSON request body:
    """
    {
    }
    """
    When user sends a POST request in order to register
    Then the response code should be 400