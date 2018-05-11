Feature: Generate Statistics


  Scenario: TC_39 Verify User performs keyword search with a keyword that matches formal subject description
    Given The user is on Class Search page to search using Keyword
    When User performs a search using Search by Keyword
    Then The Results must contain subjects that match the formal description

  Scenario: TC_41 Verify User performs keyword search with a keyword that has more than two words that match formal subject description
    Given The user is on Class Search page to search using Keyword
    When User performs a search using Search by Keyword
    Then The Results must contain subjects that match the formal description

  Scenario: TC_2 Verify User provides Incorrect Subject
    Given The user is on Class Search page
    When User enters Incorrect Subject
    Then A Warning should be Displayed for incorrect subject