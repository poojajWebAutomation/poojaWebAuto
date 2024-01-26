@web
Feature: Admin_Logview
  
  Background:
	  Given I am logged in to Query Panel
	
	@regression
	  #stable
	  #CORE-2627
	Scenario Outline: Verify admin can fetch and download the data from <table> table in logview
	  When I Select Database "<database>"
	  And I enter "<sql statement>" query with limit "<limit>"
	  And I click on "Execute" button
	  Then the "<limit>" records from the "<table>" should be displayed
	  When I click on "Download Query Result(comma)" Link
	  Then the file should be downloaded having "comma" separated values
	  When I click on "Download Query Result(pipe)" Link
	  Then the file should be downloaded having "pipe" separated values
	  
	  Examples:
	 | database  |table | sql statement | limit |
	     | Bungii/Admin | pickupdetails |  select pickupid,pickupref from pickupdetails order by pickupid desc    |       10|
	     | Bungii Reporting | factpickup |   select id, pickupref from factpickup order by id desc  |       10|