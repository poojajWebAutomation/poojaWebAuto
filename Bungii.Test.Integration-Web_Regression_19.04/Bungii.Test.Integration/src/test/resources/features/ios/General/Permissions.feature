@ios
Feature: Permissions
  
  @setapppermission
  Scenario: PreStep - Accept Customer App Permissions - Device 1
	#Given I install Bungii App again
	When I Switch to "customer" application on "same" devices
	When I am on the "LOG IN" page
	And I enter Username :{VALID1} and  Password :{VALID}
	And I click "Log In" button on "Log In" screen
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	Then User should be successfully logged in to the application
  
  @setapppermission
  Scenario: PreStep - Accept Driver App Permissions - Device 1
	#Given I install Bungii Driver App again
	Given I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	When I enter phoneNumber :{VALID} and  Password :Cci12345
	And I click "Log In" button on "Log In" screen on driverApp
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
  @setapppermission2
  Scenario: PreStep - Accept Customer App Permissions - Device 2
	#Given I install Bungii App again
	When I Switch to "customer" application on "customer2" devices
	When I am on the "LOG IN" page
	And I enter Username :{VALID1} and  Password :{VALID}
	And I click "Log In" button on "Log In" screen
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	Then User should be successfully logged in to the application
  
  @setapppermission2
  Scenario: PreStep - Accept Driver App Permissions - Device 2
	#Given I install Bungii Driver App again
	And I Switch to "driver" application on "Driver2" devices
	When I enter phoneNumber :{VALID} and  Password :Cci12345
	And I click "Log In" button on "Log In" screen on driverApp
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
  @onetime
  Scenario: Verify Driver Location Permission Displayed When Driver Permission Is Set Off
	#Given I install Bungii Driver App again
	And I Switch to "driver" application on "same" devices
	When I enter phoneNumber :{VALID} and  Password :Cci12345
	And I click "Log In" button on "Log In" screen on driverApp
	Then I should be navigated to "ALLOW NOTIFICATIONS" screen
	And I should see "all details" on allow notifications driver screen
	When I verify and allow access of Notification from Bungii driver application
	Then I should be navigated to "ALLOW LOCATION" screen
	And I should see "all details" on allow location driver screen
	When I verify and deny access of Location from Bungii driver application
	And I Switch to "driver" application on "same" devices
	Then user is alerted for "PLEASE ENABLE LOCATION SERVICES"
	Given I install Bungii Driver App again
  
  @onetime
  Scenario: Verify Driver Location Permission Displayed Upon First Time Installation
	Given I install Bungii Driver App again
	And I Switch to "driver" application on "same" devices
	When I enter phoneNumber :{VALID} and  Password :Cci12345
	And I click "Log In" button on "Log In" screen on driverApp
	Then I should be navigated to "ALLOW NOTIFICATIONS" screen
	And I should see "all details" on allow notifications driver screen
	When I verify and allow access of Notification from Bungii driver application
	Then I should be navigated to "ALLOW LOCATION" screen
	And I should see "all details" on allow location driver screen
	When I verify and allow access of Location from Bungii driver application
	When I Select "ACCOUNT > LOGOUT" from driver App menu
	When I enter phoneNumber :{VALID} and  Password :Cci12345
	And I click "Log In" button on "Log In" screen on driverApp
	Then I should be navigated to "Home" screen
  
  @onetime
  Scenario: Verify Dismissal Of Tutorials By Tapping On Start
	Given I install Bungii App again
	When I am on the "LOG IN" page
	And I enter Username :{VALID1} and  Password :{VALID}
	And I click "Log In" button on "Log In" screen
	Then I should be navigated to "TERMS AND CONDITION" screen
	When I accept Term and Condition agreement
	Then I should be navigated to "ALLOW NOTIFICATIONS" screen
	When I verify and allow access of Notification from Bungii application
	Then I should be navigated to "ALLOW LOCATION" screen
	When I verify and allow access of Location from Bungii application
	Then I should able to see "tutorials page 1" on Tutorials screen
	Then I "left" swipe on tutorials page
	Then I should able to see "tutorials page 2" on Tutorials screen
	Then I "left" swipe on tutorials page
	Then I should able to see "tutorials page 3" on Tutorials screen
	Then I "left" swipe on tutorials page
	Then I should able to see "tutorials page 4" on Tutorials screen
	Then I "left" swipe on tutorials page
	Then I should able to see "tutorials page 5" on Tutorials screen
	And I close tutorial Page by using Start button
	Then User should be successfully logged in to the application
	When I Select "ACCOUNT > LOGOUT" from Customer App menu
	When I am on the "LOG IN" page
	And I enter Username :{VALID1} and  Password :{VALID}
	And I click "Log In" button on "Log In" screen
	Then User should be successfully logged in to the application
  
  @onetime
  Scenario: Verify Swiping Back And Forth Between Tutorials Screen To View Tutorials
	Given I install Bungii App again
	When I am on the "LOG IN" page
	And I enter Username :{VALID1} and  Password :{VALID}
	And I click "Log In" button on "Log In" screen
	Then I should be navigated to "TERMS AND CONDITION" screen
	When I accept Term and Condition agreement
	Then I should be navigated to "ALLOW NOTIFICATIONS" screen
	When I verify and allow access of Notification from Bungii application
	Then I should be navigated to "ALLOW LOCATION" screen
	When I verify and allow access of Location from Bungii application
	Then I should able to see "tutorials page 1" on Tutorials screen
	Then I "left" swipe on tutorials page
	Then I should able to see "tutorials page 2" on Tutorials screen
	Then I "right" swipe on tutorials page
	Then I should able to see "tutorials page 1" on Tutorials screen
	Then I "left" swipe on tutorials page
	Then I "left" swipe on tutorials page
	Then I "left" swipe on tutorials page
	Then I "left" swipe on tutorials page
	Then I should able to see "tutorials page 5" on Tutorials screen
	Then I "right" swipe on tutorials page
	Then I should able to see "tutorials page 4" on Tutorials screen
	Then I "right" swipe on tutorials page
	Then I should able to see "tutorials page 3" on Tutorials screen
	And I close tutorial Page using close button
	Then User should be successfully logged in to the application