Feature: Generate Statistics

  Scenario: TC_6 Verify User provides only a correct Number
    Given The user is on Class Search page
    When User enters correct Number
    Then No Warning should be Displayed for correct Number Scenario

  Scenario: TC_7 Verify a warning is displayed when user enters only number which is incorrect
    Given The user is on Class Search page
    When User enters incorrect Number
    Then A Warning should be Displayed for incorrect number scenario1
