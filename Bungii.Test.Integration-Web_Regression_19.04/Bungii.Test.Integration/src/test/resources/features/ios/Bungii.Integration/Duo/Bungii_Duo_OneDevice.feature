@ios
@DUO
@scheduled
@bungii
Feature: Scheduled Bungii on one device
  I want  request Scheduled Bungii with Duo type

  Background:
  When I Switch to "customer" application on "same" devices
	
	
  @sanity
  @regression
	#Stable
  Scenario: Verify Scheduled Duo Bungii Can Be Requested As An iOS Customer in Goa Geofence [1 Device]
    And I logged in Customer application using  "customer-duo" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Duo    | Margao Railway Overbridge  | Panjim bus stand |
    And I click "Get Estimate" button on "Home" screen
    
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage | Save Trip Info |
      | 30       |           |              | NEXT_POSSIBLE | Default | Yes            |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
  
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 9403960188      |                 |
    
	
  @regression
  Scenario: Verify Customer Can View Ongoing Bungii Progress Screens When Trip Is Started By Control Driver [1 Device]
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | Accepted     | 0.5 hour ahead | customer-duo | valid duo driver 1 | valid driver 2 |
    
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I login as "valid duo driver 1" driver on "same" device and make driver status as "Online"
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select delivery "1" from scheduled deliveries
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE" screen
    Then I check ETA of "control driver"

    And I Switch to "customer" application on "same" devices
    When I logged in Customer application using  "customer-duo" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then I should be navigated to "EN ROUTE" screen
    Then "control driver" eta should be displayed to customer
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    Then I should be navigated to "ARRIVED" screen
    And I slide update button on "ARRIVED AT PICKUP" Screen
    Then I should be navigated to "LOADING ITEMS" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "LOADING ITEMS" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    Then I should be navigated to "DRIVING TO DROP-OFF" screen
    Then I check ETA of "control driver"

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "DRIVING TO DROP-OFF" screen
    Then "control driver" eta should be displayed to customer
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    Then I should be navigated to "UNLOADING ITEMS" screen
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I select "4" Ratting star for solo Driver 1
    And I click "Submit" button on "Rate duo teammate" screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii completed" screen

    When I Switch to "customer" application on "same" devices
    Then I wait for "2" mins
    Then I should be navigated to "BUNGII COMPLETE" screen
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen


  @regression
    #stable
  Scenario: Verify Non Control Driver Doesnt Receive Long Stack Request If Started Before The Control Driver [1 Device]
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
      | goa      | Accepted     | 0.5 hour ahead | valid    | valid   | valid driver 2 |
    
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select delivery "1" from scheduled deliveries
    And I start selected Bungii
    
    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
      | now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |
    And I should not get notification for "driver" for "stack trip"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 9403960183 |


  @regression
	#stable
  Scenario: Verify Non Control Driver Cannot Cancel Bungii once started If Control Driver Has Not Started The Bungii [1 Device]
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
      | goa      | Accepted     | 0.5 hour ahead | valid    | valid   | valid driver 2 |
    
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select delivery "1" from scheduled deliveries
    And I start selected Bungii
    And I click "Cancel" button on "update" screen
    When I click "YES" on alert message
    Then Alert message with TRIP CANNOT BE CANCELED AS CONTROL DRIVER NOT STARTED text should be displayed
    Then Alert should have "cancel,proceed" button
    When I click "Cancel" on alert message
    Then I should be navigated to "EN ROUTE" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |



  @regression
    #stable
  Scenario: Verify Scheduled Duo Bungii can be accepted by drivers and they are shown under displayed under Scheduled List upon accepting [1 Device]
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | Requested     | 0.5 hour ahead | customer-duo | valid duo driver 1 | valid driver 2 |
    When I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid duo driver 1" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I click "Offline" button on "Home" screen on driverApp
	#Given I Switch to "driver" application on "same" devices
    #And I am on the "Home" page on driverApp
	#And I am on the "LOG IN" page on driverApp
	#And I am logged in as "valid duo driver 1" driver
	
	And I Select "AVAILABLE BUNGIIS" from driver App menu
	And I Select Trip from available trip
	Then I should be navigated to "BUNGII DETAILS" screen
	And Driver Bungii Information should be correctly displayed on BUNGII DETAILS screen
    #Core - 2569 Verify ~ sign under earnings is shown on Driver app for Customer Deliveries
    And I check if variable sign is shown under "available bungii details"
	When I accept selected Bungii
    #CORE-4581: Confirmation of acceptance of delivery in driver's app
    Then I should see "Delivery Accepted" popup displayed
    And I click "OK" button on alert message

	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select delivery "1" from scheduled deliveries
	Then I should be navigated to "BUNGII DETAILS" screen
    And I check if variable sign is shown under "schedule bungii details"
	
	When I Select "ACCOUNT > LOGOUT" from driver App menu
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid driver 2" driver
	
	And I Select "AVAILABLE BUNGIIS" from driver App menu
	And I Select Trip from available trip
    Then I should be navigated to "BUNGII DETAILS" screen
	Then Driver Bungii Information should be correctly displayed on BUNGII DETAILS screen
	When I accept selected Bungii
    Then I should see "Delivery Accepted" popup displayed
    And I click "OK" button on alert message

	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select delivery "1" from scheduled deliveries
	Then I should be navigated to "BUNGII DETAILS" screen
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | 9403960188      |                 |
  

  @ready
  Scenario: Verify When Customer Cancel A Scheduled Duo Trip Accepted By One Driver Then Driver Gets Notification When App Is In Foreground [1 Device]
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
	  | goa      | Scheduled    | 0.5 hour ahead | customer-duo | valid duo driver 1 | valid driver 2 |
	
	When I Switch to "customer" application on "same" devices
	Given I am on the "LOG IN" page
	When I logged in Customer application using  "customer-duo" user
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
    
    #And I connect to "extra1" using "Driver1" instance
	When I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid duo driver 1" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
	And I Select "AVAILABLE BUNGIIS" from driver App menu
	And I Select Trip from available trip
	When I accept selected Bungii
    #CORE-4581: Confirmation of acceptance of delivery in driver's app
    Then I should see "Delivery Accepted" popup displayed
    And I click "OK" button on alert message

	When I Switch to "customer" application on "same" devices
	And I Select "MY BUNGIIS" from Customer App menu
	And I select already scheduled bungii
	When I Cancel selected Bungii
   
   # When I switch to "Driver1" instance
	When I Switch to "driver" application on "same" devices
	Then Alert message with CUSTOMER CANCELLED SCHEDULED BUNGII text should be displayed
	When I click "OK" on alert message
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE |                 |
  
  