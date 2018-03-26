Feature: Class Search verify Filter Search
	

	Scenario: TC_43 Verify Filter Search Criteria with In-Person & iCourses / ASU Online Classes Toggle
		Given The user is on Class Search page Filter Search
		When User toggles the In-Person & iCourses/ASU Online Classes toggle to ASU Online Classes
		Then The Results should contain Only ASU Online Courses
		And User toggles back In-Person & iCourses 
		And In-Person and iCourses return again
		
	Scenario: TC_44 Verify Filter Search Criteria with Open Classes/All Classes Toggle	
		Given The user is on Class Search page Filter Search
		When User performs a search with default filters(Open Classes)
		Then The Results should contain only classes with seats available 
		And User clicks All Classes toggle
		And All classes full and open should display
		
	Scenario: TC_45 Verify Filter Search Criteria with Filter By Session: A, B, C, Dynamic
		Given The user is on Class Search page Filter Search
		When User performs a search with different combination of session filters
		Then The Results should contain only the classes of the session type(s) in the filter
						
	Scenario: TC_46 Verify Filter Search Criteria with Filter By Location to test for Polytechnic, Tempe, West, iCourse and Off-Campus locations
		Given The user is on Class Search page Filter Search
		When User performs a search with School and Level and applies Filters
		Then The Results should return for filters Polytechnic, Tempe, West, iCourse and Off-Campus and combinations matching filters, other locations do not return results	
	
	Scenario: TC_47 Verify Filter Search Criteria with Filter By Location to test for Thunderbird location
		Given The user is on Class Search page Filter Search
		When User performs a search with subject and applies Location filter
		Then Results should return for locations matching the filter other locations do not return results
		
	Scenario: TC_48 Verify Filter Search Criteria with Filter By Location to test for Downtown and Eastern AZ locations
		Given The user is on Class Search page Filter Search
		When User performs a search with subject and applies Location filter
		Then Results should return for locations matching the filter other locations do not return results
	
	Scenario: TC_49 Verify Filter Search Criteria with Filter By Location to test for Tuscon and AZ Western locations
		Given The user is on Class Search page Filter Search
		When User performs a search with college/school and applies Location filter
		Then Results should return for locations matching the filter other locations do not return results		
		
	Scenario: TC_50 Verify Filter Search Criteria with Filter by Days of Week
		Given The user is on Class Search page Filter Search
		When User performs a search with School and applies Days filter
		Then Results should return any class with any day in the filter set
	
	Scenario: TC_51 Verify Filter Search Criteria with Filter by Start and End Times
		Given The user is on Class Search page Filter Search
		When User performs a search with Subject and applies Time Filter
		Then All classes returned should start after the start time and end before end time