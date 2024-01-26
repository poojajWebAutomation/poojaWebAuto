@ios
@scheduled
@bungii
@critical
    # this will run in miami and goa
Feature: Promocode_Manually_End_Bungii
  
  Background:
	#When I clear all notification
	When I Switch to "customer" application on "same" devices
	
@regression
#stable
	@authfailure
Scenario: Verify that Promoter Type Promocode Is Correctly Applied for bungii and Manually Ending link is dsiable
When I Switch to "customer" application on "same" devices
And I am on the "LOG IN" page
And I logged in as "valid miami 2" customer
  And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  And I close "Tutorial" if exist
  
And I Switch to "driver" application on "same" devices
And I am on the "LOG IN" page on driverApp
And I am logged in as "valid miami" driver
  And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  And I change driver status to "Online"

When I Switch to "customer" application on "same" devices
And I request for  bungii for given pickup and drop location
| Driver | Pickup Location             | Drop Location            | Geofence |
| Solo   | 7346 coldstream drive miami | 2400 S Bayshore Dr Miami | miami    |
Then I click "Get Estimate" button on "Home" screen
When I select load time as "30" mins

And I click "PROMO CODE LINE" button on "Estimate" screen
And I add "PROMOTER TYPE MULTIPLE PROMO" PromoCode
And I click "ADD" button on "PROMOS" screen
And I tap "Back" on Promos screen

When I enter following details on "Estimate" screen
| LoadTime | PromoCode | Payment Card | Time          | PickUpImage |
|          |           |              | NEXT_SECOND_POSSIBLE | Default     |

And I should be navigated to "Estimate" screen
Then I save bungii trip time details
And I request for bungii using Request Bungii Button
Then I click "Done" button on "Success" screen
  When I Select "Home" from Customer App menu
  Then I should be navigated to "Home" screen
  
  And I view and accept virtual notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
  When I Select "SCHEDULED BUNGIIS" from driver App menu
  Then I should be navigated to "SCHEDULED BUNGII" screen
  
And I Select Trip from scheduled trip
And I start selected Bungii
Then I should be navigated to "EN ROUTE" screen on driverApp
#Then I should be navigated to "EN ROUTE" screen
And I slide update button on "EN ROUTE TO PICKUP" Screen
And I slide update button on "ARRIVED AT PICKUP" Screen
And I slide update button on "LOADING ITEMS AT PICKUP" Screen
And I slide update button on "DRIVING TO DROP-OFF" Screen
  When I Switch to "customer" application on "same" devices

And I wait for "2" mins
And I open Admin portal and navigate to "Live Deliveries" page
Then I should be able to see the respective bungii with the below status
| Status          |
| Unloading Items |
#When I view the trip details
And I select trip from live trips

When I switch to "ORIGINAL" instance
And I open "customer" application on "same" devices
And I switch to "ADMIN" instance
Then manually end bungii should be "disabled"
When I switch to "ORIGINAL" instance
When I Switch to "driver" application on "same" devices
And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
#And I click on "Manually End Bungii" link
#And Enter the End Date and Time
#And Click on "Calculate Cost" button
#And Click on "Confirm" button

When I switch to "ORIGINAL" instance
When I Switch to "customer" application on "same" devices
  
Then I should be navigated to "Bungii Complete" screen
And Bungii customer should see "correct details with delivery promo" on Bungii completed page
When I click "CLOSE BUTTON" button on "Bungii Complete" screen
Then I should be navigated to "Promotion" screen
When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
Then I should be navigated to "Home" screen

When I Switch to "driver" application on "same" devices
And I click "Skip This Step" button on "Rate customer" screen
Then Bungii driver should see "correct details" on Bungii completed page
And I click "On To The Next One" button on "Bungii completed" screen

  
  @regression
#stable
  Scenario: Verify Promo Type Promocode Is Correctly Applied and Manually Ending link is disable
	
	And I am on the "LOG IN" page
    And I logged in as "valid miami" customer
	And I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid miami" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I change driver status to "Online"
	
	When I Switch to "customer" application on "same" devices
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	And I request for  bungii for given pickup and drop location
	  | Driver | Pickup Location             | Drop Location            | Geofence |
	  | Solo   | 7346 coldstream drive miami | 2400 S Bayshore Dr Miami | miami    |
	Then I click "Get Estimate" button on "Home" screen
	When I select load time as "30" mins
	And I click "PROMO CODE LINE" button on "Estimate" screen
	And I add "PROMO PERCENT OFF" PromoCode
	And I click "ADD" button on "PROMOS" screen
	And I tap "Back" on Promos screen
	
	When I enter following details on "Estimate" screen
	  | LoadTime | PromoCode | Payment Card | Time          | PickUpImage |
	  |        |           |              | NEXT_POSSIBLE | Default     |
	
	And I should be navigated to "Estimate" screen
	Then I save bungii trip time details
	And I request for bungii using Request Bungii Button
	Then I click "Done" button on "Success" screen
	When I Select "Home" from Customer App menu
	Then I should be navigated to "Home" screen
	
	And I view and accept virtual notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
	When I Select "SCHEDULED BUNGIIS" from driver App menu
	Then I should be navigated to "SCHEDULED BUNGII" screen
	
	And I Select Trip from scheduled trip
	And I start selected Bungii
	#Then I should be navigated to "EN ROUTE" screen
	Then I should be navigated to "EN ROUTE" screen on driverApp
	And I slide update button on "EN ROUTE TO PICKUP" Screen
	And I slide update button on "ARRIVED AT PICKUP" Screen
	And I slide update button on "LOADING ITEMS AT PICKUP" Screen
	And I slide update button on "DRIVING TO DROP-OFF" Screen
	When I Switch to "customer" application on "same" devices
	Then Customer should be navigated to "UNLOADING ITEM" trip status screen
	And I wait for "2" mins
	And I open Admin portal and navigate to "Live Deliveries" page
	Then I should be able to see the respective bungii with the below status
	  | Status        |
	  | Unloading Items |
	#When I view the trip details
	And I select trip from live trips
	  Then manually end bungii should be "disabled"
	  When I switch to "ORIGINAL" instance
	  When I Switch to "driver" application on "same" devices
	  And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen

	#And I click on "Manually End Bungii" link
	#And Enter the End Date and Time
	#And Click on "Calculate Cost" button
	#And Click on "Confirm" button

	#When I switch to "ORIGINAL" instance
	When I Switch to "customer" application on "same" devices
	Then I should be navigated to "Bungii Complete" screen
	#And Bungii customer should see "correct details with promo" on Bungii completed page
	#When I click "CLOSE BUTTON" button on "Bungii Complete" screen
	#Then I should be navigated to "Promotion" screen
	#When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
	#Then I should be navigated to "Home" screen
	
	When I Switch to "driver" application on "same" devices
	And I click "Skip This Step" button on "Rate customer" screen
	Then Bungii driver should see "correct details" on Bungii completed page
	And I click "On To The Next One" button on "Bungii completed" screen

  @regression
#stable
  Scenario: Verify One Off Type Promocode Is Correctly Applied and Manually Ending link is disable
	
	When I Switch to "customer" application on "same" devices
	And I am on the "LOG IN" page
    And I logged in as "valid miami" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	
	And I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid miami" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I change driver status to "Online"
	
	When I Switch to "customer" application on "same" devices
	And I request for  bungii for given pickup and drop location
	  | Driver | Pickup Location             | Drop Location            | Geofence |
	  | Solo   | 7346 coldstream drive miami | 2400 S Bayshore Dr Miami | miami    |
	Then I click "Get Estimate" button on "Home" screen
	When I select load time as "30" mins
	And I click "PROMO CODE LINE" button on "Estimate" screen
	And I add "ONE OFF VALID 2" PromoCode
	And I click "ADD" button on "PROMOS" screen
	And I tap "Back" on Promos screen
	
	When I enter following details on "Estimate" screen
	  | LoadTime | PromoCode | Payment Card | Time          | PickUpImage |
	  |          |           |              | NEXT_POSSIBLE | Default     |
	
	
	And I should be navigated to "Estimate" screen
	Then I save bungii trip time details
	
	And I request for bungii using Request Bungii Button
	Then I click "Done" button on "Success" screen
	When I Select "Home" from Customer App menu
	Then I should be navigated to "Home" screen
	
	And I view and accept virtual notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
	And I Switch to "driver" application on "same" devices
	When I Select "SCHEDULED BUNGIIS" from driver App menu
	Then I should be navigated to "SCHEDULED BUNGII" screen
	
	And I Select Trip from scheduled trip
	And I start selected Bungii
	#Then I should be navigated to "EN ROUTE" screen
	Then I should be navigated to "EN ROUTE" screen on driverApp
	And I slide update button on "EN ROUTE TO PICKUP" Screen
	And I slide update button on "ARRIVED AT PICKUP" Screen
	And I slide update button on "LOADING ITEMS AT PICKUP" Screen
	And I slide update button on "DRIVING TO DROP-OFF" Screen
	When I Switch to "customer" application on "same" devices
	
	And I wait for "2" mins
	And I open Admin portal and navigate to "Live Deliveries" page
	Then I should be able to see the respective bungii with the below status
	  | Status             |
	  | Unloading Items |
	#When I view the trip details
	And I select trip from live trips
	Then manually end bungii should be "disabled"
	When I switch to "ORIGINAL" instance
	When I Switch to "driver" application on "same" devices
	And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
	#And I click on "Manually End Bungii" link
	#And Enter the End Date and Time
	#And Click on "Calculate Cost" button
	#And Click on "Confirm" button
	
	When I switch to "ORIGINAL" instance
	When I Switch to "customer" application on "same" devices
	Then I should be navigated to "Bungii Complete" screen
	And Bungii customer should see "correct details with promo" on Bungii completed page
	
	When I Switch to "driver" application on "same" devices
	And I click "Skip This Step" button on "Rate customer" screen
	Then Bungii driver should see "correct details" on Bungii completed page

