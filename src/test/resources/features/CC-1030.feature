Feature: Course Search filter criteria check

  Scenario: TC_222 Verify User provides nothing with no filter criteria
    Given The user is on Class Search page
    When User enters nothing and performs search
    Then A Warning should be Displayed for no filter criteria

  Scenario: TC_23 Verify user selects College/School Only the college or school chosen should return
    Given The user is on Class Search page Advanced Search
    When User performs a search using a college/school
    Then The results should contain records with the selected college/school


  Scenario: TC_30 Verify user searches using General Studies option only classes with the chosen GS category should return
    Given The user is on Class Search page Advanced Search
    When  User performs a search using General Studies option
    Then The Results must display only classes with the chosen GS category