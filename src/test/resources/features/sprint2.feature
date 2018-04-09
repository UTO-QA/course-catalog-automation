Feature: Class Search verify Radio buttons open-all,inperson-online and session checkbox
		
	Scenario: TC_13 Verify that the results displayed for open and closed classes only when all is selected
		Given The user is on Class Search page 
		When User selects for all classes and performs a search
		Then The Results should contain open and closed values
		
	Scenario: TC_14 Verify that the results displayed contain only campus classes when in-Person is selected
		Given The user is on Class Search page 
		When User selects for in-person and performs a search
		Then The Results should contain only On-Campus Classes
		
	Scenario: TC_15 Verify that the results displayed contain only ASUONLINE classes when online is selected
		Given The user is on Class Search page 
		When User selects for online and performs a search
		Then The Results should contain only online Classes
		
	Scenario: TC_16 Verify that the results displayed contain only Session A classes when session A is selected 
		Given The user is on Class Search page 
		When User selects Session A and performs a search
		Then The Results should contain only Session A classes
		
	Scenario: TC_17 Verify that the results displayed contain only Session B classes when session B is selected 
		Given The user is on Class Search page 
		When User selects Session B and performs a search
		Then The Results should contain only Session A classes
		
	Scenario: TC_18 Verify that the results displayed contain only Session C classes when session C is selected 
		Given The user is on Class Search page 
		When User selects Session C and performs a search
		Then The Results should contain only Session A classes
			