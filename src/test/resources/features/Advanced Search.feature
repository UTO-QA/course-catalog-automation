Feature: Course Search verify Advanced Search

	Background:
		Given The user is on Class Search page Advanced Search

	Scenario: TC_28 Verify user provides correct Subject and selects honors and only honors classes should return
		When  User performs a search using the correct Subject and checks Honors check box
		Then The Results must display only honors classes or classes that offer honors enrichment contracts

	Scenario: TC_23 Verify user selects College/School Only the college or school chosen should return
		When User performs a search using a college/school
		Then The results should contain records with the selected college/school
		
	Scenario: TC_30 Verify user searches using General Studies option only classes with the chosen GS category should return
		When  User performs a search using General Studies option
		Then The Results must display only classes with the chosen GS category		
		
	Scenario: TC_31 Verify user searches using subject and level options only classes in the level or division chosen should return
		When  User performs a search using Subject and Level
		Then The Results must display only classes in the level or division chosen
		
	