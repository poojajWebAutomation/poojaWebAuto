@ios
  Feature: AdditionalFlow
	
	#failing due to BCKD-1103
	@onetime
	Scenario: Verify Device Token Deregistration Upon Driver Logout
	Given I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid nashville" driver
	Then I driver active flag should be "1"
	When I Select "ACCOUNT > LOGOUT" from driver App menu
	Then I driver active flag should be "0"
 
	@failed
  #failing due to BCKD-1103
	@onetime
	Scenario: Verify Device Token Deregistration Upon Customer Logout
	  Given I am on Customer logged in Home page
	  Then I customers active flag should be "1"
	  When I Select "ACCOUNT > LOGOUT" from Customer App menu
	  Then I customers active flag should be "0"

    #enable restriction on iphone , disable safari. Not to be run with normal regression
	@safaridisabled
	Scenario: Verify DRIVE WITH BUNGII Menu Link With No Safari Browser
	  Given I am on Customer logged in Home page
	  When I Select "DRIVE WITH BUNGII" from Customer App menu
	  Then user is alerted for "PLEASE INSTALL A BROWSER"
