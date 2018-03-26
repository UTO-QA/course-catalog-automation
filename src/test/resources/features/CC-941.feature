Feature: CC-941 Automated testing for Jan 2018

    Scenario: TC_52_CC-742 Increase Class Search keyword search to continue even after results are found
        Given The user is on Class Search page to search using Keyword
        When User performs a search using Search by Keyword
        Then The search returns classes with subject name matching the keyword and searches class title/topic title/instructor name with "like" logic.

    Scenario: TC_53_CC-742 Increase Class Search keyword search to continue even after results are found
        Given The user is on Class Search page to search using Keyword
        When User performs a search using Search by Keyword
        Then The search returns classes with subject name matching the keyword and searches class title/topic title/instructor name with "like" logic.

    Scenario: TC_54_CC-742 Class Search keyword search is case insensitive
        Given The user is on Class Search page to search using Keyword
        When User performs a search using Search by Keyword
        Then The keyword search results are case insensitive

    Scenario: TC_55_CC-921 Revert catalog number logic
        Given The user is on Class Search page
        Then User is able to search using a wildcard in number field

    Scenario: TC_52 Verify Filter Search Criteria with Filter By Location to test for Cochise location
        Given The user is on Class Search page Filter Search prod
        When User performs a search with subject and applies Location filter current
        Then Results should return for locations matching the filter current

    Scenario: TC_53 Verify Filter Search Criteria with Filter By Location to test for Central AZ College location
        Given The user is on Class Search page Filter Search prod
        When User performs a search with subject and applies Location filter current
        Then Results should return for locations matching the filter current

    Scenario: TC_54 Verify Filter Search Criteria with Filter By Location to test for Lake Havasu location
        Given The user is on Class Search page Filter Search prod
        When User performs a search with subject and applies Location filter current
        Then Results should return for locations matching the filter current

    Scenario: TC_59_CC-935 VIP classes do not return the drawer or details page experiences
        Given The user is on Class Search page
        When User enters correct Subject and Number
        Then Clicking on Class record must return the drawer or details page experiences