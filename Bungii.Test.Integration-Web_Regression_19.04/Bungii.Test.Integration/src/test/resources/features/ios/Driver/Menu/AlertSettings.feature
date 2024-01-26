@ios
Feature: AlertSettings
  In Bungii Driver
  As a logged in driver
  I want to check Alert Settings
  
  Background:
	Given I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "new driver" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
@regression
Scenario: Verify Trip Alert Settings On Trip Alerts Tab
When I Select "ACCOUNT > ALERT SETTINGS" from driver App menu
When I click "SMS ALERT" button on "ALERT SETTINGS" screen on driverApp
Then I should be able to see default data on "SMS ALERT" page


