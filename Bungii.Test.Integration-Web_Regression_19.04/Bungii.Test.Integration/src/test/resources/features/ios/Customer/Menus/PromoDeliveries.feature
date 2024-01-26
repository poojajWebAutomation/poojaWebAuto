@ios
@bungii
 
  # this will run in 	nashville 13 Scenarios
Feature: Promo Deliveries - Nashville Geofence

@failed
@ready
Scenario Outline: Verify Requesting of Ondemand Bungii Requests With Promo code :<Scenario>
  When I Switch to "customer" application on "same" devices
  Given I am on the "LOG IN" page
  When I logged in Customer application using  "<User>" user
  And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  And I close "Tutorial" if exist
  
  When I Switch to "driver" application on "same" devices
  And I login as "valid nashville" driver on "same" device and make driver status as "Online"
  
  When I Switch to "customer" application on "same" devices
And I request for  bungii for given pickup and drop location
| Driver | Pickup Location                 | Drop Location                                        | Geofence  |
| Solo   | Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | nashville |
And I click "Get Estimate" button on "Home" screen
Then I should be navigated to "Estimate" screen
When I select load time as "15" mins
And I tap "Promo code" on Estimate screen
And I should be navigated to "PROMOS" screen
And I add "<Promo Code>" PromoCode
And I click "ADD" button on "PROMOS" screen
Then I should able to see expected promo code in available promo code
   # When I tap "Back" on Promos screen
And I enter following details on "Estimate" screen
| LoadTime | PromoCode | Payment Card | Time | PickUpImage |
|          |           |              | Now  | Default     |
And I request for bungii using Request Bungii Button
Then I should be navigated to "SEARCHING" screen
And I view and accept virtual notification for "Driver" for "on demand trip"
And I slide update button on "EN ROUTE TO PICKUP" Screen

And I Switch to "customer" application on "same" devices
When I click "Ok" button on "BUNGII ACCEPTED" screen

When I Switch to "driver" application on "same" devices
And I slide update button on "ARRIVED AT PICKUP" Screen
And I slide update button on "LOADING ITEMS AT PICKUP" Screen
And I slide update button on "DRIVING TO DROP OFF" Screen
And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
And I should be navigated to "Bungii Completed" screen

And I Switch to "customer" application on "same" devices
Then I should be navigated to "Bungii Complete" screen
And Bungii customer should see "<Expected Details>" on Bungii completed page
And I click "CLOSE BUTTON" button on "Bungii Complete" screen
Then I should be navigated to "Promotion" screen
When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
Then I should be navigated to "Home" screen

When I Switch to "driver" application on "same" devices
And I click "Skip This Step" button on "Rate customer" screen
Then Bungii driver should see "correct details" on Bungii completed page
And I click "On To The Next One" button on "Bungii completed" screen

And I open new "Chrome" browser for "ADMIN PORTAL"
And I navigate to admin portal
And I log in to admin portal
And I Select "trips" from admin sidebar
  And I select trip from all deliveries
Then On admin trip details page "<Expected value in admin>" should be displayed
Examples:
| Scenario         | Promo Code        | User                       | Expected Details           | Expected value in admin |
| fixed valid      | PROMO DOLLAR OFF  | valid nashville            | correct details with promo | promo                   |
| Promo percentage | PROMO PERCENT OFF | valid nashville            | correct details with promo | promo                   |
| valid one off    | ONE OFF2           | valid nashville            | correct details with promo | oneoff                  |
| First time       | FIRST TIME        | valid nashville first time | correct details with promo | promo                   |


@regression
  @authfailure
Scenario Outline: Verify Requesting of Ondemand Bungii Requests With Promoter Type Promocode
When I Switch to "customer" application on "same" devices
Given I am on the "LOG IN" page
When I logged in Customer application using  "<User>" user
  And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  And I close "Tutorial" if exist
  
  When I Switch to "driver" application on "same" devices
  And I login as "valid nashville" driver on "same" device and make driver status as "Online"
  
  When I Switch to "customer" application on "same" devices
  And I request for  bungii for given pickup and drop location
  | Driver | Pickup Location                 | Drop Location                                        | Geofence  |
  | Solo   | Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | nashville |
  And I click "Get Estimate" button on "Home" screen
  Then I should be navigated to "Estimate" screen
  When I select load time as "15" mins
  And I tap "Promo code" on Estimate screen
  And I should be navigated to "PROMOS" screen
  And I add "<Promo Code>" PromoCode
  And I click "ADD" button on "PROMOS" screen
  Then I should able to see expected promo code in available promo code
    #When I tap "Back" on Promos screen
  And I enter following details on "Estimate" screen
  | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
  |          |           |              | Now  | Default     |
  And I request for bungii using Request Bungii Button
  Then I should be navigated to "SEARCHING" screen

  And I view and accept virtual notification for "Driver" for "on demand trip"
  And I Switch to "customer" application on "same" devices
  When I click "Ok" button on "BUNGII ACCEPTED" screen
  
  When I Switch to "driver" application on "same" devices
  And I slide update button on "EN ROUTE TO PICKUP" Screen
  #And Driver adds photos to the Bungii
  #And I slide update button on "EN ROUTE" Screen
  And I slide update button on "ARRIVED AT PICKUP" Screen
  And Driver adds photos to the Bungii
  And I slide update button on "ARRIVED AT PICKUP" Screen
  And I slide update button on "LOADING ITEMS AT PICKUP" Screen
  And Driver adds photos to the Bungii
  And I slide update button on "LOADING ITEMS AT PICKUP" Screen
  And I slide update button on "DRIVING TO DROP OFF" Screen
  And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
  And Driver adds photos to the Bungii
  And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
  And I click "Skip This Step" button on "Rate customer" screen
  And I should be navigated to "Bungii Completed" screen

  And I Switch to "customer" application on "same" devices
  Then I should be navigated to "Bungii Complete" screen
  And Bungii customer should see "<Expected Details>" on Bungii completed page
  And I click "CLOSE BUTTON" button on "Bungii Complete" screen
  Then I should be navigated to "Promotion" screen
  When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
  Then I should be navigated to "Home" screen

  When I Switch to "driver" application on "same" devices
  Then Bungii driver should see "correct details" on Bungii completed page
  And I click "On To The Next One" button on "Bungii Completed" screen

  And I open new "Chrome" browser for "ADMIN PORTAL"
  And I navigate to admin portal
  And I log in to admin portal
  And I Select "live trips" from admin sidebar
  And I select trip from trips
  Then On admin trip details page "<Expected value in admin>" should be displayed
  Examples:
  | Scenario         | Promo Code        | User                       | Expected Details           | Expected value in admin |
  | PROMOTER_TYPE_PROMO | PROMOTER TYPE PROMO | valid nashville | correct details with delivery promo | promoter |

