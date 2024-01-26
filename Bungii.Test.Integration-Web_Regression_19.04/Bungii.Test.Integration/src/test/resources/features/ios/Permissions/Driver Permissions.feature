@ios
Feature: Driver App Permissions
  As a Bungii Driver I should be allowed to accept permissions
  
  Background:
	Given I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	
@permission
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

@permission
@browserstack
Scenario: Verify Driver Location Permission Displayed Upon First Time Installation
#Given I install Bungii Driver App again
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
