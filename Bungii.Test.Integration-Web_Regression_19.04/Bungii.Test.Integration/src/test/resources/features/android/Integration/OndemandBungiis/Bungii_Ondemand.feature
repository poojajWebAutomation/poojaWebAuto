@android
@bungii
  @general
  @ondemand
  #These feature will run in baltimore geofence
Feature: On Demand Bungii
  
  @regression
  Scenario: Verify Customer Receives Notification Upon Bungii Completion
    Given that ondemand bungii is in progress
      | geofence  | Bungii State |
      | baltimore | Enroute      |
    
    And I Switch to "customer" application on "same" devices
    And I am logged in as "valid baltimore" customer
    
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid baltimore" driver
    And I Switch to "driver" application on "same" devices
    Then Bungii driver should see "General Instructions"
    And I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And Bungii driver uploads "1" image
    And I slide update button on "ARRIVED" Screen
    And I slide update button on "LOADING ITEM" Screen
    And Bungii driver uploads "1" image
    And I slide update button on "LOADING ITEMS" Screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I slide update button on "UNLOADING ITEM" Screen
    And Bungii driver uploads "1" image
    And I slide update button on "UNLOADING ITEM" Screen
       #  Core - 3113 Verify that Rate customer page UI and elements are displayed correctly to driver
    And I check the elements displayed on rate customer screen
    When Bungii Driver "rates customer"
    And I click on "SUBMIT RATING" button
    And I click "Next Bungii" button on the "Bungii Completed" screen

#    Core-3098 Verify online/Offline pop up is shown for on demand trip and check go offline functionality
    And I check online or offline pop up is displayed
    And I click on "GO OFFLINE" button
    And I check if the status is "OFFLINE"
    Then I click on notification for "CUSTOMER-JUST FINISHED BUNGII"
  
  @regression
#  Scenario:Verify Manually End Bungii Option Is Available In The Last Three States Only
  Scenario:Verify Manually End Bungii Option Is Removed For Live Delivery
    Given that ondemand bungii is in progress
      | geofence  | Bungii State |
      | baltimore | Enroute      |
    
    And I Switch to "customer" application on "same" devices
    And I am logged in as "valid baltimore" customer
    
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid baltimore" driver
    And I wait for "2" mins
    And I open Admin portal and navigate to "Live Deliveries" page
    And I select trip from live trips
    Then I wait for trip status to be "Trip Started"
    Then manually end bungii should be "disabled"
    
    When I switch to "ORIGINAL" instance
    And I slide update button on "EN ROUTE" Screen
    When I switch to "ADMIN" instance
    Then I wait for trip status to be "Driver(s) Arrived"
    Then manually end bungii should be "disabled"
    
    When I switch to "ORIGINAL" instance
    And I slide update button on "ARRIVED" Screen
    When I switch to "ADMIN" instance
    Then I wait for trip status to be "Loading Items"
    Then manually end bungii should be "disabled"
    
    When I switch to "ORIGINAL" instance
    And I slide update button on "LOADING ITEM" Screen
    When I switch to "ADMIN" instance
    Then I wait for trip status to be "Driving To Dropoff"
    Then manually end bungii should be "disabled"
    
    When I switch to "ORIGINAL" instance
    And I slide update button on "DRIVING TO DROP OFF" Screen
    When I switch to "ADMIN" instance
    Then I wait for trip status to be "Unloading Items"
    Then manually end bungii should be "disabled"
    
    When I switch to "ORIGINAL" instance
    And I slide update button on "UNLOADING ITEM" Screen
    And I click "On To The Next One" button on the "Bungii Completed" screen
    
    And I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    When I click "I DON'T LIKE FREE MONEY" button on the "Promotion" screen
    

  @sanity
  @regression
  Scenario: Verify Customer Can An Create Ondemand Bungii
    Given I am logged in as "valid baltimore" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    
    When I Switch to "driver" application on "same" devices
    And I am logged in as "valid baltimore" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    And I Select "HOME" from driver App menu
    And I tap on "Go Online button" on Driver Home page
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "HOME" link
    And I enter "new baltimore pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"

    #When I Open "driver" application on "same" devices
    And Bungii Driver "accepts On Demand Bungii" request
    Then Bungii driver should see "General Instructions"
    Then Bungii driver should see "Enroute screen"

    When I Switch to "customer" application on "same" devices
    And I tap "OK on Driver Accepted screen" during a Bungii
    Then for a Bungii I should see "Enroute screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Arrived screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    And Driver adds photos to the Bungii
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Loading Items screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Loading Item screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    And Driver adds photos to the Bungii
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to Drop-Off screen"
    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Driving to DropOff screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Items screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"
    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    And Driver adds photos to the Bungii
    And Bungii Driver "slides to the next state"
    And I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    And I Switch to "driver" application on "same" devices
    Then Bungii Driver "completes Bungii"
    #And Customer should receive "Your Bungii Receipt" email
  
    
  @regression
  #stable
  Scenario:Verify ondemand Trip is displayed in past trips after completion
    Given that ondemand bungii is in progress
      | geofence  | Bungii State   |
      | baltimore | COMPLETED|
    
    And I Switch to "customer" application on "same" devices
    And I am logged in as "valid baltimore" customer
    And I tap on "Menu" > "My Bungiis" link
    Then "MY BUNGIIS" page should be opened
    And I click on "Past" tab
    And I open the trip for "Testdrivertywd_applemd_a_bill Stark_bltOnE" driver
    Then Driver names and trip cost is displayed correctly
    
    
  @ready
    #stable
  Scenario:Verify Driver Receives Notification For Tip When Customer Gives A Tip Amount and Poor driver rating email
    Given that ondemand bungii is in progress
      | geofence  | Bungii State   |
      | baltimore | UNLOADING ITEM |
    
    And I Switch to "customer" application on "same" devices
    And I am logged in as "valid baltimore" customer
    
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid baltimore" driver
    And I slide update button on "UNLOADING ITEM" Screen
    
    Then I click on notification for "CUSTOMER-JUST FINISHED BUNGII"
    And I Switch to "customer" application on "same" devices
    When I select "2" Ratting star for duo "Driver 1"
    When I give tip to Bungii Driver with following tip and Press "OK" Button
      | Tip |
      | 5   |
    And I click on notification for "Driver" for "TIP RECEIVED 5 DOLLAR"
    And I click "On To The Next One" button on the "Bungii Completed" screen
    Then poor driver ratting should be sent to customer

  @ready
  #CORE-4732
  Scenario:Verify Transform pickup for the ondemand trip
    Given I am logged in as "valid baltimore" customer
    When I tap on "Menu" > "HOME" link
    And I enter "new baltimore pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"
    And I wait for "3" mins
    Then for a Bungii I should see "SET PICKUP TIME SCREEN"
    When I tap "Schedule Bungii" button on DRIVER NOT AVAILABLE screen
    Then I should be navigated to "Success!" screen
    And I click "Done" button on "Success" screen
    And I tap on "Menu" > "MY BUNGIIS" link
    Then The status on "MY BUNGIIS" should be displayed as "Contacting Drivers"