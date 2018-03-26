Feature: Class Search verify Advanced Search

	Background:
		Given The user is on Class Search page Advanced Search
	
	Scenario: TC_22 Verify user provides correct Subject and Instructor
		When  User performs a search using the correct Subject and Instructor
		Then The Results with correct subject info and Instructor should be Displayed
		
	Scenario: TC_23 Verify user selects College/School Only the college or school chosen should return
		When User performs a search using a college/school
		Then The results should contain records with the selected college/school
		
	Scenario: TC_24 Verify when user searches with # of Units only classes with number of units selected return
		When User selects # of Units and subject and performs search
		Then The results should contain only classes with the given number of units

	Scenario: TC_25 Verify when user searches with Class # only classes with that class number should return
		When User selects Class # and subject and performs search
		Then The results should contain only classes with that class number 
		
	Scenario: TC_26 Verify when user searches with Start Date only classes starting on or after the date should return
		When User enters Start Date and subject and performs search
		Then The results should contain only classes only classes starting on or after the date should return
		
	Scenario: TC_27 Verify user provides correct Subject and End Date and only classes ending on or after the date should return
		When  User performs a search using the correct Subject and End Date
		Then The Results must display only classes ending on or after the date
			
	Scenario: TC_28 Verify user provides correct Subject and selects honors and only honors classes should return
		When  User performs a search using the correct Subject and checks Honors check box
		Then The Results must display only honors classes or classes that offer honors enrichment contracts
			
	Scenario: TC_29 Verify user provides correct Subject and selects Project-based only Promod block classes should return
		When  User performs a search using the correct Subject and checks Project-based check box
		Then The Results must display only Promod block classes or individual classes designated as project based with a special note 0018
		
	Scenario: TC_30 Verify user searches using General Studies option only classes with the chosen GS category should return
		When  User performs a search using General Studies option
		Then The Results must display only classes with the chosen GS category		
		
	Scenario: TC_31 Verify user searches using subject and level options only classes in the level or division chosen should return
		When  User performs a search using Subject and Level
		Then The Results must display only classes in the level or division chosen
		
	