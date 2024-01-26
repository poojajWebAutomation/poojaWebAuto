@android
Feature: Customer Home screen

  Background:
    #Given I am on Customer logged in Home page
    Given I am logged in as "Testcustomertywd_appleand_B Android" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
  

  @regression
  Scenario: Verify Clear Text Button On Pickup And Dropoff Location
    When I tap on "Menu" > "Home" link
    And I enter "Goa pickup and dropoff locations" on Bungii estimate screen
    When I tap "Drop Clear Text" on Home page
    Then "Drop" address should be empty
    When I tap "Pick Up Clear Text" on Home page
    Then "PICK UP" address should be empty

  @regression
  Scenario: Verify That Dropoff Field Is Displayed Only When Pickup Address Is Selected
    When I tap on "Menu" > "Home" link
    And I select "Pick up" location
    Then "Drop Off" address text box should be displayed on app screen

  @regression
  Scenario: Verify If ETA Bar Remains On Map When Pickup Address Is Cleared
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I Select "HOME" from driver App menu
    Then I Switch to "customer" application on "same" devices
    When I tap on "Menu" > "Home" link
    And I select "Pick up" location
    Then The ETA bar is seen on screen
    When I clear "Pick up" location
    Then The ETA bar is not seen on screen

  @regression
  Scenario: Verify If Driver ETA Is Displayed When Drivers Within 30 min Of Radius From Pickup Location Is Available
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I Select "HOME" from driver App menu
    Then I Switch to "customer" application on "same" devices
    When I tap on "Menu" > "Home" link
    And I select "Pick up" location to check driver within 30mins
    Then The ETA bar is seen on screen with less then "30" mins

    
  @regression
  Scenario: Verify ETA Box When Geofence Is Not Active
    When I tap on "Menu" > "Home" link
    And I enter "Non Geofence pickup location" on Bungii estimate screen
    Then I get the error popup message for "Non Geofence Location"

  @regression
  Scenario: Verify that location picker appears on home screen when customer login.
    When I tap on "Menu" > "Home" link
    Then I verify that "Location picker" is displayed

  @regression
  Scenario: Verify that 'SET PICKUP LOCATION' button appears when customer moves the location picker for pickup field.
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Goa driver_1" driver
    Then I Switch to "customer" application on "same" devices
    When I tap on "Menu" > "Home" link
    And I enter "Goa pickup location" on Bungii estimate screen
    Then I verify that "SET PICKUP LOCATION BUTTON" is displayed
    When I click on "SET PICKUP LOCATION" button
    Then I verify that "SET DROP OFF LOCATION BUTTON" is displayed
    
  @regression
  Scenario: Verify That Customer Is Allowed To Set Pickup And Dropoff Locations When No Driver ETA Is Found Within Geofence
    When I tap on "Menu" > "Home" link
    And I enter "Goa pickup and dropoff locations" on Bungii estimate screen
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"
    And I tap on "Cancel during search" on Bungii estimate
  
  @ready
    @nonstable
  Scenario: Verify Long Haul Alert Is Shown When Distance Between Pickup And Dropoff Is greated than 150 Miles
    When I tap on "Menu" > "Home" link
    And I enter "Atlanta pickup and Indiana dropoff location" on Bungii estimate screen
    Then I get the error popup message for "More than 150 miles trip"
  
  @ready
  @nonstable
  Scenario: Verify that ETA bar appears when customer selects pickup and drop-off address
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Goa driver_1" driver
    When I click "OFFLINE" button on Home screen on driver app
    
    Then I Switch to "customer" application on "same" devices
    When I tap on "Menu" > "Home" link
    And I enter "Goa Geofence pickup location" on Bungii estimate screen
    Then I verify that "ETA bar" is displayed
    When I click on "SET PICKUP LOCATION" button
    And I enter "Goa Geofence dropoff location" on Bungii estimate screen
    Then I verify that "ETA bar" is displayed
    And I verify that "GET ESTIMATE" is displayed
    