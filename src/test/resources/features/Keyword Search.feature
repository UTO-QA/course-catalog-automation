Feature: Class Search verify Keyword Search criteria
	
	Scenario: TC_33 Verify User performs any 3 character search that matches subject table
		Given The user is on Class Search page to search using Keyword
		When User performs a search using Search by Keyword
		Then The Results must contain records with matching subject

	Scenario: TC_34 Verify User performs any 3 character search that does not match subject table but matches class topic or instructor
		Given The user is on Class Search page to search using Keyword
		When User performs a search using Search by Keyword
		Then The Results must contain records with matching class title/topic title or instructor
		
	Scenario: TC_35 Verify User performs any 3 character search that does not match subject table but matches class topic or instructor
		Given The user is on Class Search page to search using Keyword
		When User performs a search using Search by Keyword
		Then The Results must contain records with matching class title/topic title or instructor
						
	Scenario: TC_36 Verify User performs search in the format 'ENG 101'
		Given The user is on Class Search page to search using Keyword
		When User performs a search using Search by Keyword
		Then The Results must contain records with correct subject and category number
						
	Scenario: TC_37 Verify User performs keyword search with exactly 5 digits
		Given The user is on Class Search page to search using Keyword
		When User performs a search using Search by Keyword
		Then The Results must contain records that match the class number
	
	Scenario: TC_38 Verify User performs keyword search that includes 5 digits 
		Given The user is on Class Search page to search using Keyword
		When User performs a search using Search by Keyword
		Then The Results must contain records that match the class number and ignores all other keywords		
		
	Scenario: TC_39 Verify User performs keyword search with a keyword that matches formal subject description  
		Given The user is on Class Search page to search using Keyword
		When User performs a search using Search by Keyword
		Then The Results must contain subjects that match the formal description
		
	Scenario: TC_40 Verify User performs keyword search with a keyword that does not match formal subject description  
		Given The user is on Class Search page to search using Keyword
		When User performs a search using Search by Keyword
		Then The Results must contain records with matching class title/topic title or instructor		
		
	Scenario: TC_41 Verify User performs keyword search with a keyword that has more than two words that match formal subject description
		Given The user is on Class Search page to search using Keyword
		When User performs a search using Search by Keyword
		Then The Results must contain subjects that match the formal description
				
	Scenario: TC_42 Verify User performs keyword search with a keyword that has more than two words does match formal subject description
		Given The user is on Class Search page to search using Keyword
		When User performs a search using Search by Keyword
		Then The Results must contain records with matching class title/topic title or instructor

		
		
		