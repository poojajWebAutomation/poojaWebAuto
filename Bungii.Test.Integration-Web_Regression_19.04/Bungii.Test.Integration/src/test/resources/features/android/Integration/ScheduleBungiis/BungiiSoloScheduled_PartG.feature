
@android
@SoloScheduled
@bungii
#These feature will run in kansas geofence
Feature: SoloScheduled Part G
  
  Background:
    
    ############################################################################################
   @regression
      #Stable
  Scenario: Verify If Customer Receives Notification Once Required Number Of Drivers Accepts The Scheduled Trip - Solo
    And I Switch to "customer" application on "same" devices
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I login as customer "8805368840" and is on Home Page
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And As a driver "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "Solo Scheduled" trip
      | driver1 state |
      | Accepted      |
    Then I should get "SCHEDULED PICKUP ACCEPTED" notification for customer
    
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
     
  
  @regression
    #Stable
  Scenario:Verify Driver Receives Scheduled Bungii Request While In Offline State
    When I clear all notification
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_appleks_a_drvd Kansas_d" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    Then I click "ONLINE" button on Home screen on driver app
    When I Switch to "customer" application on "same" devices
    And I request "Solo Scheduled" Bungii as a customer in "Kansas" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                    |
      | now         | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test |
    Then I click on notification for the "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then I should be navigated to "SCHEDULED BUNGII" screen
    When I click "REJECT" button on SCHEDULED BUNGII screen
    When I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
    And I Switch to "customer" application on "same" devices
    And I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                    |
      | now         | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test |
    And I wait for "2" mins
    And I click on notification for the "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then I should be navigated to "SCHEDULED BUNGII" screen
    When I click "REJECT" button on SCHEDULED BUNGII screen
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
    
  @ready
 #stable
  Scenario: Verify If Driver receives More Than One Requests He Is Not Able To Accept The Bungii If He Has Already Accepted A Bungii whos TELET Time Overlaps:Solo
    Given I Switch to "customer" application on "same" devices
    #trip 1
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | kansas   | Scheduled    | 15 min ahead |
     #trip 2
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    | Customer label |
      | NEXT_POSSIBLE | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test | 2              |
    
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    Then I should able to see "two" available trip
    And I wait for "1" mins
    And I Select second Trip from available trip
    And I click "ACCEPT" button on Bungii Request screen
    Then I should be navigated to "AVAILABLE BUNGIIS" screen
    Then I should able to see "zero" available trip
    
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8805368840      |
  
  @regression
    #stable
  Scenario: Verify Scheduled Bungii Notification Info Is Correct [Estimated earnings and Date]
    When I clear all notification
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_appleks_a_drvd Kansas_d" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    Then I click "OFFLINE" button on Home screen on driver app
    When I Switch to "customer" application on "same" devices
    
    And I request "Solo Scheduled" Bungii as a customer in "Kansas" geofence
      | Bungii Time | Customer Phone | Customer Name                    | Customer Password |
      | now         | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I wait for "2" mins
    Then I click on notification for the "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then I should be navigated to "SCHEDULED BUNGII" screen
    And "correct scheduled trip details" should be displayed on Bungii request screen
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
  
    