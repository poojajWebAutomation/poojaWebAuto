@ios
@bungii
 
  # Without Email Steps
Feature: Ondemand Bungii Scenarios - Nashville Geofence(Without Email)
  
  Background:
    #When I clear all notification
    When I Switch to "customer" application on "same" devices

  @regression
  Scenario: Verify Customer Is Allowed To Rate Driver For Solo Trip
    Given that ondemand bungii is in progress
      | geofence  | Bungii State |
      | nashville | UNLOADING ITEM      |
	When I Switch to "customer" application on "same" devices
	When I am on the "LOG IN" page
    And I logged in Customer application using  "valid nashville" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid nashville" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    When I click "On To The Next One" button on "Bungii completed" screen
    
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct rating detail for solo" on Bungii completed page
    When I select "3" Ratting star for solo Driver 1
    Then "3" stars should be highlighted for solo Driver1
    When I click "OK" button on "BUNGII COMPLETE" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
