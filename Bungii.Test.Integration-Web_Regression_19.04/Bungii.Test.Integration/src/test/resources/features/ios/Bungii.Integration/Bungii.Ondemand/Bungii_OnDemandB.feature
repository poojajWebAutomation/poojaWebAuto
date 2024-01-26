@ios
@bungii
Feature: OndemandBungiis
  Background:
	#When I Switch to "customer" application on "same" devices
 
@sanity
@regression
#stable
Scenario: Verify Ondemand Bungii completion As An iOS User
When I Switch to "driver" application on "same" devices
And I login as "valid nashville" driver on "same" device and make driver status as "Online"

And I Switch to "customer" application on "same" devices
Given I am on the "LOG IN" page
When I logged in Customer application using  "valid nashville" user
And I request for  bungii for given pickup and drop location
| Driver | Pickup Location                 | Drop Location                      | geofence  |
| Solo   | Nashville International Airport | Graylynn Drive Nashville Tennessee | nashville |
And I click "Get Estimate" button on "Home" screen
Then I should be navigated to "Estimate" screen
When I confirm trip with following details
| LoadTime | PromoCode | Payment Card | Time | PickUpImage |
| 15       |           |              | Now  | Default     |
Then I should be navigated to "SEARCHING" screen

And I view and accept virtual notification for "Driver" for "on demand trip"
  
  And I Switch to "customer" application on "same" devices
  Then I should be navigated to "BUNGII ACCEPTED" screen
  When I click "Ok" button on "BUNGII ACCEPTED" screen
  Then Customer should be navigated to "EN ROUTE" trip status screen
  
When I Switch to "driver" application on "same" devices
Then I should be navigated to "EN ROUTE TO PICKUP" trip status screen
And I slide update button on "EN ROUTE TO PICKUP" Screen
Then I should be navigated to "ARRIVED AT PICKUP" trip status screen
  And I slide update button on "ARRIVED AT PICKUP" Screen
  And Driver adds photos to the Bungii
  And I slide update button on "ARRIVED AT PICKUP" Screen
  Then I should be navigated to "LOADING ITEMS AT PICKUP" trip status screen
  And I slide update button on "LOADING ITEMS AT PICKUP" Screen
  And Driver adds photos to the Bungii
  And I slide update button on "LOADING ITEMS AT PICKUP" Screen
Then I should be navigated to "DRIVING TO DROP-OFF" trip status screen
And I slide update button on "DRIVING TO DROP-OFF" Screen
Then I should be navigated to "UNLOADING ITEMS AT DROP-OFF" trip status screen
And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
And Driver adds photos to the Bungii
And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
And I click "Skip This Step" button on "Rate customer" screen
Then I should be navigated to "Bungii completed" screen
When I click "On To The Next One" button on "Bungii completed" screen

  #    Core-3098 Verify online/Offline pop up is shown for on demand trip and stay online functionality
  And I check online or offline pop up is displayed
  And I click on "STAY ONLINE" button
  And I check if the status is "ONLINE"

And I Switch to "customer" application on "same" devices
Then I should be navigated to "Bungii Complete" screen
When I click "CLOSE BUTTON" button on "Bungii Complete" screen
Then I should be navigated to "Promotion" screen
When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
Then I should be navigated to "Home" screen
  
  @regression
   #stable
  Scenario:Verify Driver Notification For The Tip Amount Received From Customer
    Given that ondemand bungii is in progress
      | geofence  | Bungii State |
      | nashville3 | UNLOADING ITEM      |
    When I Switch to "customer" application on "same" devices
    
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid nashville3" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_applens_a_kayW Stark_nsOnEW" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    
    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    #And I click on notification for "customer" for "BUNGII FINISHED -RATE DRIVER"
    When I rate Bungii Driver  with following details and Press "OK" Button
      | Ratting | Tip |
      | 5       | 5   |
    And I click on notification for "Driver" for "TIP RECEIVED 5 DOLLAR"
    And I Switch to "driver" application on "same" devices
    And I click "Skip This Step" button on "Rate customer" screen
    And I click "On To The Next One" button on "Bungii completed" screen
  
  @sanity
  @ready
  @ondemand
  Scenario: Verify Ondemand Bungii Flow Till Completion
    When I Switch to "driver" application on "same" devices
    And I login as "valid nashville1" driver on "same" device and make driver status as "Online"
    
    And I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
   # When I am on Customer logged in Home page
    When I logged in Customer application using  "valid nashville" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                      | geofence  |
      | Solo   | Nashville International Airport | Graylynn Drive Nashville Tennessee | nashville |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 15       |           |              | Now  | Default     |
    Then I should be navigated to "SEARCHING" screen
    
    When I Switch to "driver" application on "same" devices
    And I view and accept virtual notification for "Driver" for "on demand trip"
    
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "BUNGII ACCEPTED" screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen
    Then Customer should be navigated to "EN ROUTE" trip status screen
    
    When I Switch to "driver" application on "same" devices
    Then I should be navigated to "EN ROUTE TO PICKUP" trip status screen
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    #Then I should be navigated to "ARRIVED" trip status screen
    Then I should be navigated to "ARRIVED" screen on driverApp
    
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "ARRIVED" trip status screen
    
    When I Switch to "driver" application on "same" devices
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
    #Then I should be navigated to "LOADING ITEM" trip status screen
    Then I should be navigated to "LOADING ITEMS" screen on driverApp
    
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "LOADING ITEMS" trip status screen
    
    When I Switch to "driver" application on "same" devices
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    #Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
    Then I should be navigated to "DRIVING TO DROP-OFF" screen on driverApp
    
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "DRIVING TO DROP-OFF" trip status screen
    
    When I Switch to "driver" application on "same" devices
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    #Then I should be navigated to "UNLOADING ITEM" trip status screen
    Then I should be navigated to "UNLOADING ITEMS" screen on driverApp
    
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "UNLOADING ITEMS" trip status screen
    
    When I Switch to "driver" application on "same" devices
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    When I click "On To The Next One" button on "Bungii completed" screen
    
    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

      #CORE-4122:To verify Arrival Time/ Expected time values on various states of on demand in progress Bungii
    @ready
  Scenario: To verify Arrival Time/ Expected time values on various states of on demand in progress Bungii
    When I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvbm Kansas_bm" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I request "Solo Ondemand" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661138     | Testcustomertywd_appleMarkEI LutherEI |
    And I wait for 1 minutes
    And I view and accept virtual notification for "Driver" for "on demand trip"
    When I Switch to "driver" application on "same" devices
    Then The "Ondemand bungii" should match
    And I swipe to check trip details
    Then The "Ondemand delivery dropOff range" should match
    And I click on "Close" button
    Then The "PICKUP(Arrival time)" "Label" should be displayed
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    Then The "PICKUP(Arrival time)" "at Arrival screen" should be displayed
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
    Then The "PICKUP(Arrival time)" "at Loading Items screen" should be displayed
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    Then The "DROP-OFF(Expected time)" "Label" should be displayed
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    Then The "DROP-OFF(Expected time)" "at Unloading Items screen" should be displayed
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen