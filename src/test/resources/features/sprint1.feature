Feature: Class Search verify Subject,Number and keyword

	Scenario: TC_1 Verify User provides correct Subject 
		Given The user is on Class Search page 
		When User enters Correct Subject
		Then The Results with correct subject info should be Displayed
		
	 Scenario: TC_2 Verify User provides Incorrect Subject 
		Given The user is on Class Search page 
		When User enters Incorrect Subject
		Then A Warning should be Displayed for incorrect subject
				
	Scenario: TC_3 Verify User provides Correct Subject and Number
		Given The user is on Class Search page 
		When User enters correct Subject and Number
		Then The Results should be Displayed for Correct Subject and Number scenario
		
	Scenario: TC_4 Verify User provides Incorrect Subject and Number
		Given The user is on Class Search page 
		When User enters incorrect Subject and incorrect Number
		Then A Warning should be Displayed for incorrect subject and number scenario
	
	Scenario: TC_5 Verify User provides Incorrect Subject and Number
		Given The user is on Class Search page 
		When User enters swapped values for Subject and Number
		Then A Warning should be Displayed for swapped scenario
		
    Scenario: TC_6 Verify User provides only a correct Number
		Given The user is on Class Search page 
		When User enters correct Number
		Then  A Warning should be Displayed for correct Number Scenario
		
	Scenario: TC_7 Verify a warning is displayed when user enters only number which is incorrect 
		Given The user is on Class Search page 
		When User enters incorrect Number
		Then  A Warning should be Displayed for incorrect number scenario

#	Outdated scenario. Covered in Keyword search part.		
#	Scenario: TC_8 Verify the user is able to perform the search successfully using a correct  keyword 
#		Given The user is on Class Search page 
#		When  User performs a search using only the correct keyword
#		Then  The search results page is displayed for correct keyword scenario
		
		
	Scenario: TC_9 Verify that search with only incorrect keyword produces proper warning message
		Given The user is on Class Search page 
		When  User performs a search using only the incorrect keyword or keyword with less than 3 letters
		Then  An Appropriate warning message is displayed for incorrect keyword
		
	Scenario: TC_10 Verify that search with correct Subject and keyword produces Search Results
		Given The user is on Class Search page 
		When  User performs a search using the correct keyword and correct Subject
		Then  The Search Results page is Displayed for correct keyword and subject scenario
		
	Scenario: TC_11 Verify that search with incorrect Subject or keyword produces a warning
		Given The user is on Class Search page 
		When  User performs a search using the incorrect keyword or Subject
		Then  The Warning is Displayed for incorrect keyword or subject scenario
	
