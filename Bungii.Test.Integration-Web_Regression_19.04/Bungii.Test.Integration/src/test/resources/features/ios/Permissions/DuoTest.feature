@ios
Feature: Duo Test
  
  
  @lambda
  Scenario: Verify DUO Stacking
  
	When I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid duo driver 1" driver
	
	And I connect to "extra1" using "Driver2" instance
	And I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid driver 2" driver
	And I Switch to "customer" application on "ORIGINAL" devices
	
	
	And I logged in Customer application using  "customer-duo" user
	And I request for  bungii for given pickup and drop location
	  | Driver | Pickup Location | Drop Location                |
	  | Duo    | Margao Railway Overbridge  | Panjim bus stand |
	And I click "Get Estimate" button on "Home" screen
	Then I should be navigated to "Estimate" screen
 
	When I confirm trip with following details
	  | LoadTime | PromoCode | Payment Card | Time          | PickUpImage | Save Trip Info |
	  | 30       |           |              | NEXT_POSSIBLE | large image | Yes            |
	Then I should be navigated to "Success" screen
	When I click "Done" button on "Success" screen
	And I Select "Home" from Customer App menu
	And I Switch to "driver" application on "same" devices
	And I Select "AVAILABLE BUNGIIS" from driver App menu
	And I Select Trip from available trip
	Then I should be navigated to "BUNGII DETAILS" screen
	#And Trip Information should be correctly displayed on BUNGII DETAILS screen
    And Driver Bungii Information should be correctly displayed on BUNGII DETAILS screen
	
	When I accept selected Bungii
	And I click "OK" button on alert message
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Switch to "driver" application on "Driver2" devices
	And I Select "AVAILABLE BUNGIIS" from driver App menu
	And I Select Trip from available trip
	#Then Trip Information should be correctly displayed on BUNGII DETAILS screen
	Then Driver Bungii Information should be correctly displayed on BUNGII DETAILS screen
	When I accept selected Bungii
	And I click "OK" button on alert message
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from scheduled trip
	Then I should be navigated to "BUNGII DETAILS" screen
  #  When I wait for Minimum duration for Bungii Start Time
	And I start selected Bungii
 #   Then I should be navigated to "EN ROUTE" trip status screen
	Then I should be navigated to "EN ROUTE" screen
 
	When I Switch to "driver" application on "ORIGINAL" devices
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from scheduled trip
	And I start selected Bungii
  #  Then I should be navigated to "EN ROUTE" trip status screen
	Then I should be navigated to "EN ROUTE" screen
 
	When I Switch to "customer" application on "same" devices
  #  Then Customer should be navigated to "EN ROUTE" trip status screen
	Then I should be navigated to "EN ROUTE" screen
 
	When I Switch to "driver" application on "same" devices
	And I slide update button on "EN ROUTE TO PICKUP" Screen
 #   Then I should be navigated to "ARRIVED" trip status screen
	Then I should be navigated to "ARRIVED" screen
 
	When I Switch to "driver" application on "Driver2" devices
	And I slide update button on "EN ROUTE TO PICKUP" Screen
 #   Then I should be navigated to "ARRIVED" trip status screen
	Then I should be navigated to "ARRIVED" screen
 
	When I Switch to "customer" application on "ORIGINAL" devices
#    Then Customer should be navigated to "ARRIVED" trip status screen
	Then I should be navigated to "ARRIVED" screen
 
	When I Switch to "driver" application on "same" devices
	And I slide update button on "ARRIVED AT PICKUP" Screen
	Then I accept Alert message for "Reminder: both driver at pickup"
 #   And I should be navigated to "LOADING ITEM" trip status screen
	Then I should be navigated to "LOADING ITEM" screen
 
	When I Switch to "driver" application on "Driver2" devices
	And I slide update button on "ARRIVED AT PICKUP" Screen
	Then I accept Alert message for "Reminder: both driver at pickup"
  #  And I should be navigated to "LOADING ITEM" trip status screen
	Then I should be navigated to "LOADING ITEMS" screen
 
	When I Switch to "customer" application on "ORIGINAL" devices
 #   Then Customer should be navigated to "LOADING ITEM" trip status screen
	Then I should be navigated to "LOADING ITEMS" screen
 
	When I Switch to "driver" application on "same" devices
	And I slide update button on "LOADING ITEMS AT PICKUP" Screen
  #  Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
	Then I should be navigated to "DRIVING TO DROP-OFF" screen
 
	When I Switch to "driver" application on "Driver2" devices
	And I slide update button on "LOADING ITEMS AT PICKUP" Screen
  #  Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
	Then I should be navigated to "DRIVING TO DROP-OFF" screen
 
	When I Switch to "customer" application on "ORIGINAL" devices
  #  Then Customer should be navigated to "DRIVING TO DROP OFF" trip status screen
	Then I should be navigated to "DRIVING TO DROP-OFF" screen
 
	When I Switch to "driver" application on "same" devices
	And I slide update button on "DRIVING TO DROP-OFF" Screen
#    Then I should be navigated to "UNLOADING ITEM" trip status screen
	Then I should be navigated to "UNLOADING ITEMS" screen
 
	When I Switch to "driver" application on "Driver2" devices
	And I slide update button on "DRIVING TO DROP-OFF" Screen
  #  Then I should be navigated to "UNLOADING ITEM" trip status screen
	Then I should be navigated to "UNLOADING ITEMS" screen
 
	When I Switch to "customer" application on "ORIGINAL" devices
#    Then Customer should be navigated to "UNLOADING ITEM" trip status screen
	Then I should be navigated to "UNLOADING ITEMS" screen
 
	When I Switch to "driver" application on "same" devices
	And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
	Then I accept Alert message for "Reminder: both driver at drop off"
 
	When I Switch to "driver" application on "Driver2" devices
	And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
	Then I accept Alert message for "Reminder: both driver at drop off"
 
 
	And I Switch to "customer" application on "ORIGINAL" devices
	Then I should be navigated to "Bungii Complete" screen
	And Bungii customer should see "correct details" on Bungii completed page
	When I rate Bungii Driver  with following details and Press "CLOSE" Button
	  | Ratting | Tip |
	  | 5       | 5   |
	And I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
	Then I should be navigated to "Home" screen
 
	And I Switch to "driver" application on "ORIGINAL" devices
	And I click "Skip This Step" button on "Rate customer" screen
	Then Bungii driver should see "correct details" on Bungii completed page
	And I click "On To The Next One" button on "Bungii completed" screen

 
	When I Switch to "driver" application on "Driver2" devices
	And I click "Skip This Step" button on "Rate customer" screen
	Then Bungii driver should see "correct details" on Bungii completed page
	When I click "On To The Next One" button on "Bungii completed" screen
	And I Select "HOME" from driver App menu
