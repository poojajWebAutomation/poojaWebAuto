@ios

Feature: Bungiis
  Background:
	
@ready
  @fi
Scenario: Verify Requesting Of Ondemand Bungii With Referral Code
#Given I have customer with referral code
  When I Switch to "customer" application on "same" devices
  Given I am on Customer logged in Home page
  When I Select "Home" from Customer App menu
  And I click "Invite referrals" button on "HOME" screen
  Then I should be navigated to "Invite" screen
  When I get Invite Code

  When I Switch to "driver" application on "same" devices
And I login as "valid nashville" driver on "same" device and make driver status as "Online"

When I Switch to "customer" application on "same" devices
When I am on the "LOG IN" page
And I logged in Customer application using  "newly created user" user
And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
And I close "Tutorial" if exist
And I request for  bungii for given pickup and drop location
| Driver | Pickup Location                 | Drop Location                                        | Geofence  |
| Solo   | Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | nashville |
And I click "Get Estimate" button on "Home" screen
Then I should be navigated to "Estimate" screen
When I select load time as "15" mins
  And I tap "Promo code" on Estimate screen
  And I should be navigated to "PROMOS" screen
  And I add "Referral" PromoCode
  And I click "ADD" button on "PROMOS" screen
Then I should able to see expected promo code in available promo code
    #When I tap "Back" on Promos screen
And I enter following details on "Estimate" screen
| LoadTime | PromoCode | Payment Card | Time | PickUpImage |
|          |           |              | Now  | Default     |
And I request for bungii using Request Bungii Button
Then I should be navigated to "SEARCHING" screen

And I view and accept virtual notification for "Driver" for "on demand trip"

And I slide update button on "EN ROUTE TO PICKUP" Screen
And I slide update button on "ARRIVED AT PICKUP" Screen
And I slide update button on "LOADING ITEMS AT PICKUP" Screen
And I slide update button on "DRIVING TO DROP OFF" Screen
And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen

And I should be navigated to "Bungii Completed" screen
And I Switch to "customer" application on "same" devices
Then I should be navigated to "Bungii Complete" screen
And Bungii customer should see "correct details with promo" on Bungii completed page
And I click "CLOSE BUTTON" button on "Bungii Complete" screen
Then I should be navigated to "Promotion" screen
When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
Then I should be navigated to "Home" screen

When I Switch to "driver" application on "same" devices
And I click "Skip This Step" button on "Rate customer" screen
Then Bungii driver should see "correct details" on Bungii completed page
And I click "On To The Next One" button on "Bungii completed" screen

@ready
Scenario: Verify Requesting Of Ondemand Bungii With Received Referred Code
Given I have customer with referral code received
And I login as "valid nashville" driver on "same" device and make driver status as "Online"

When I Switch to "customer" application on "same" devices
When I am on the "LOG IN" page
And I logged in Customer application using  "valid nashville" user
And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
And I close "Tutorial" if exist
And I request for  bungii for given pickup and drop location
| Driver | Pickup Location                 | Drop Location                                        | Geofence  |
| Solo   | Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | nashville |
And I click "Get Estimate" button on "Home" screen
Then I should be navigated to "Estimate" screen
When I select load time as "15" mins
And I tap "Promo code" on Estimate screen
Then I should see "referral code received with out first time tag" on Promos page
Then I should able to see expected promo code in available promo code
    #When I tap "Back" on Promos screen
And I enter following details on "Estimate" screen
| LoadTime | PromoCode | Payment Card | Time | PickUpImage |
|          |           |              | Now  | Default     |
And I request for bungii using Request Bungii Button
Then I should be navigated to "SEARCHING" screen

And I view and accept virtual notification for "Driver" for "on demand trip"

And I slide update button on "EN ROUTE TO PICKUP" Screen
And I slide update button on "ARRIVED AT PICKUP" Screen
And I slide update button on "LOADING ITEMS AT PICKUP" Screen
And I slide update button on "DRIVING TO DROP OFF" Screen
And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
And I should be navigated to "Bungii Completed" screen

And I Switch to "customer" application on "same" devices
Then I should be navigated to "Bungii Complete" screen
And Bungii customer should see "correct details with promo" on Bungii completed page
And I click "CLOSE BUTTON" button on "Bungii Complete" screen
Then I should be navigated to "Promotion" screen
When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
Then I should be navigated to "Home" screen

When I Switch to "driver" application on "same" devices
And I click "Skip This Step" button on "Rate customer" screen
Then Bungii driver should see "correct details" on Bungii completed page
And I click "On To The Next One" button on "Bungii completed" screen