@android
@general
@bungii
    #These feature will run in Kansas geofence and is STABLE
  # Customer 8805368840 Testcustomertywd_appleRicha Test 8888888881 Testcustomertywd_appleRicha1 Test 8888889916
  #Driver 8888881019
Feature: Delivery Flows
  
  @regression
      #stable
  Scenario: Verify Customer Can Cancel Through SMS To Admin after 2 hour processing is over (Irrespective Of No. Of Required Drivers Have Accepted Or Not)
    When I Switch to "driver" application on "same" devices
    Then As a driver "Testdrivertywd_appleks_ra_four Kent" I log in
    
    And I Switch to "customer" application on "same" devices
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
    
    When I Switch to "customer" application on "same" devices
    Given I login as customer "8805368840" and is on Home Page
    
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
    Then I wait for "2" mins
    And I select already scheduled bungii
    #When I Cancel selected Bungii
    When I tap on "Cancel Bungii" button
    Then correct details should be displayed on the "ADMIN-SMS" app
    And I click on device "Back" button
  
    And I open Admin portal and navigate to "Scheduled Deliveries" page
    
    And I Cancel Bungii with following details
      | Charge | Comments | Reason                         |
      | 0      | TEST     | Outside of delivery scope      |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    And I wait for "2" mins
    And Bungii must be removed from the List
    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    Then Bungii must be removed from "MY BUNGIIS" screen
    
  
  @regression
  #stable
  Scenario:Verify Alert Message Is Displayed When Customer Tries To Contact Driver Who Has A Ongoing Bungii
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time     |
      | Kansas   | Accepted     | 1 hour ahead |
    And I wait for "3" mins
    Given that ondemand bungii is in progress as a second delivery
      | geofence  | Bungii State |
      | Same      | Enroute      |
    
    And I Open "customer" application on "same" devices
    And I wait for "4" mins
    When I am on customer Log in page
    And I wait for "4" mins
    When I am logged in as "valid" customer
    And I wait for "4" mins
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I wait for "2" mins
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select 1st trip from scheduled bungii
    When I wait for 1 hour for Bungii Schedule Time
    When I try to contact driver using "call driver1"
    Then user is alerted for "driver finishing current bungii"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8888889916      | 8805368840      |
  
  
    
  @regression
   #stable
  Scenario: TELET : Verify If Driver receives More Than One Requests He Is Not Able To Accept The Bungii If He Has Already Accepted A Bungii whos TELET Time Overlaps - Case:DUO
    #trip 1
    Given I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time  | Customer Phone | Customer Password | Customer Name                    |
      | 15 min ahead | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test |
     #trip 2
    Given I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                     | Customer label |
      | NEXT_POSSIBLE | 8888888881     | Cci12345          | Testcustomertywd_appleRicha1 Test | 2              |
    
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_appleks_andro_a Kansas_a" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    Then I should able to see "two customer" available trip

    And I Select Trip from available trip
    And I click "ACCEPT" button on Bungii Request screen
    Then I should be navigated to "AVAILABLE BUNGIIS" screen
    Then I should able to see "zero" available trip
    
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8805368840 | 8888888881      |
  
  @regression
    #stable
    #ALWAYS LAST TO BE EXECUTED
  Scenario: Verify Driver Doesnt Receive Scheduled Request If The Request Is Sent Outside Of The Time That Is Set For Trip Alert Settings
    When I clear all notification
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :8888881019 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Count the number of available bungiis

    When I Select "ACCOUNT" from driver App menu
    And The "ACCOUNT" page is opened
    When I Select "ALERT SETTINGS" from ACCOUNT menu
    And I update trip setting of "TODAY" to "12:00 AM" to "12:05 AM"

    #And I update kansas driver todays trip alert setting to outside current time
    When I Switch to "customer" application on "same" devices
    When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8877661162     | Testcustomertywd_appleMarkFG LutherFG | Cci12345          |
    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    Then I should be navigated to "AVAILABLE BUNGIIS" screen
    And I should able to see "old" available trip
    When I Select "ACCOUNT" from driver App menu
    And The "ACCOUNT" page is opened
    When I Select "ALERT SETTINGS" from ACCOUNT menu
    And I update trip setting of "TODAY" to "12:00 AM" to "11:59 PM"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8877661162     |                 |