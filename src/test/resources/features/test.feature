Feature: Generate Statistics


    Scenario: TC_9 Verify that search with only incorrect keyword produces proper warning message
        Given The user is on Class Search page
        When  User performs a search using only the incorrect keyword or keyword with less than 3 letters
        Then  An Appropriate warning message is displayed for incorrect keyword
