@ios
@bungii
 
  # this will run in 	nashville 13 Scenarios
Feature: Ondemand Bungii Scenarios - Nashville Geofence
  
  Background:
    #When I clear all notification
    When I Switch to "customer" application on "same" devices


  @regression
    @testing
    #CORE-3412 changes added
    #Stable
    #move to end
  Scenario: Verify Manually End Bungii Option Is not Available In The any States of bungii
    Given that ondemand bungii is in progress
      | geofence  | Bungii State |
      | nashville | Enroute      |

	When I Switch to "customer" application on "same" devices
	When I am on the "LOG IN" page
#    And I logged in Customer application using  "valid nashville" user
    And I logged in as "valid nashville" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist

    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    #And I am logged in as "valid nashville" driver
    And I login as "valid nashville" driver on "same" device and make driver status as "Online"
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I wait for "2" mins
    And I open Admin portal and navigate to "Live Deliveries" page
    And I select trip from live trips
    Then I wait for trip status to be "Trip Started"
    Then manually end bungii should be "disabled"

    When I switch to "ORIGINAL" instance
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    When I switch to "ADMIN" instance
    Then I wait for trip status to be "Driver(s) Arrived"
    Then manually end bungii should be "disabled"

    When I switch to "ORIGINAL" instance
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
    When I switch to "ADMIN" instance
    Then I wait for trip status to be "Loading Items"
    Then manually end bungii should be "disabled"

    When I switch to "ORIGINAL" instance
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    When I switch to "ADMIN" instance
    Then I wait for trip status to be "Driving To Dropoff"
    Then manually end bungii should be "disabled"

    When I switch to "ORIGINAL" instance
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    When I switch to "ADMIN" instance
    Then I wait for trip status to be "Unloading Items"
    Then manually end bungii should be "disabled"

    When I switch to "ORIGINAL" instance
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    Then I see "Rate customer" screen
    And I select "4" customer rating
    #And I click "Submit" button on Rate customer screen
    And I click "Submit" button on "Rate customer" screen
    And I click "On To The Next One" button on "Bungii completed" screen

    And I Switch to "customer" application on "same" devices
    And I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    

  @regression
    #CORE-3412 changes added
  #stable
  Scenario: Verify Delivery information & Bungii completed screen For Ondemand Delivery
    Given that ondemand bungii is in progress
      | geofence  | Bungii State |
      | nashville2 | Enroute      |
	When I Switch to "customer" application on "same" devices
    When I am on the "LOG IN" page
    And I logged in as "valid nashville2" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    #And I am logged in as "valid nashville" driver
    And I login as "valid nashville2" driver on "same" device and make driver status as "Online"
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
    When I Switch to "customer" application on "same" devices
    Then Trip Information should be correctly displayed on "EN ROUTE" status screen for customer

    When I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    Then Trip Information should be correctly displayed on "ARRIVED" status screen for driver

    When I Switch to "customer" application on "same" devices
    Then Trip Information should be correctly displayed on "ARRIVED" status screen for customer

    When I Switch to "driver" application on "same" devices
    And I click "More Options" button on "update" screen
    And I click "Take Photo" button on "update" screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
    Then Trip Information should be correctly displayed on "LOADING ITEMS" status screen for driver

    When I Switch to "customer" application on "same" devices
    Then Trip Information should be correctly displayed on "LOADING ITEMS" status screen for customer

    When I Switch to "driver" application on "same" devices
    And I click "More Options" button on "update" screen
    And I click "Take Photo" button on "update" screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    Then Trip Information should be correctly displayed on "DRIVING TO DROP-OFF" status screen for driver

    When I Switch to "customer" application on "same" devices
    Then Trip Information should be correctly displayed on "DRIVING TO DROP-OFF" status screen for customer

    When I Switch to "driver" application on "same" devices
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    Then Trip Information should be correctly displayed on "UNLOADING ITEMS" status screen for driver

    When I Switch to "customer" application on "same" devices
    Then Trip Information should be correctly displayed on "UNLOADING ITEMS" status screen for customer

    When I Switch to "driver" application on "same" devices
    And I click "More Options" button on "update" screen
    And I click "Take Photo" button on "update" screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct details" on Bungii completed page
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "driver" application on "same" devices
    #And I click "Skip This Step" button on "Rate customer" screen
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii completed" screen
    And I Select "ACCOUNT > LOGOUT" from driver App menu
  
  @regression
  Scenario: Verify Driver Rating Details Is Correctly Shown On Customer App When Bungii Is In Progress
    Given that ondemand bungii is in progress
      | geofence  | Bungii State   |
      | nashville | UNLOADING ITEM |
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid nashville" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Switch to "customer" application on "same" devices
    When I am on the "LOG IN" page
#   And I logged in Customer application using  "valid nashville" user
    And I logged in as "valid nashville" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then ratting should be correctly displayed on Bungii progress page
    
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
  
  @knownissue
    #stable
  Scenario: Email : Verify Poor rating email for Driver For Solo Trip
    Given that ondemand bungii is in progress
      | geofence  | Bungii State |
      | nashville | UNLOADING ITEM      |
	When I Switch to "customer" application on "same" devices
	When I am on the "LOG IN" page
    And I logged in as "valid nashville" customer
    
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid nashville" driver

    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    When I click "On To The Next One" button on "Bungii completed" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct rating detail for solo" on Bungii completed page
    When I select "1" Ratting star for solo Driver 1
    Then "1" stars should be highlighted for solo Driver1
    When I click "OK" button on "BUNGII COMPLETE" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    
    Then poor driver ratting should be sent to customer
    
 
  @knownissue
  Scenario: Verify Requesting An Ondemand Bungii With FB Share Code
    Given that ondemand bungii is in progress
      | geofence  | Bungii State   |
      | nashville | UNLOADING ITEM |
	When I Switch to "customer" application on "same" devices
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid nashville" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid nashville" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I click "More Options" button on "update" screen
    And I click "Take Photo" button on "update" screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    And I should be navigated to "Bungii completed" screen
    And I click "On To The Next One" button on "Bungii completed" screen

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "YES, I'LL TAKE $5" button on "Promotion" screen
    When I enter "valid data" on Overlay Facebook screen
  #  And I tap "Next" button on Overlay Facebook screen
  #  When I tap "Share" button on Overlay Facebook screen
    When I tap "Post" button on Overlay Facebook screen

    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                                        | Geofence  |
      | Solo   | Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | nashville |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I select load time as "15" mins
    And I tap "Promo code" on Estimate screen
    And I should be navigated to "PROMOS" screen
    Then I should able to see facebook promo code in available promo code
    #When I tap "Back" on Promos screen
    And I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      |          |           |              | Now  | Default     |
    And I request for bungii using Request Bungii Button
    Then I should be navigated to "SEARCHING" screen
    
    And I view and accept virtual notification for "Driver" for "on demand trip"
    
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    And I click "More Options" button on "update" screen
    And I click "Take Photo" button on "update" screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And I click "More Options" button on "update" screen
    And I click "Take Photo" button on "update" screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I click "More Options" button on "update" screen
    And I click "Take Photo" button on "update" screen
    And Driver adds photos to the Bungii
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


  #this scenario is moved from signup to ondemand feature as we can use test data generated in this test case
  #this scenario is move to knownissue from regression due to SSL error while adding the Credit card.
  #@regression
    @regression
    #stable
  Scenario Outline: Verify Sign up of Customer With Referral Code
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid nashville" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I click "Invite referrals" button on "HOME" screen
    Then I should be navigated to "Invite" screen
    When I get Invite Code
    When I Select "ACCOUNT > LOGOUT" from Customer App menu
    Given I am on the "SIGN UP" page
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Enter "<Promo Code>" value in "Referral code" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then I should be navigated to "VERIFICATION" screen
    When I Get SMS CODE for new "Customer"
    And I enter "valid" Verification code
    Then I should be navigated to "Home" screen
    When I Select "ACCOUNT > PROMOS" from Customer App menu
    And I should able to see expected promo code in available promo code
    Then I should see "first time code subtext" on Promos page
    When I Select "ACCOUNT > LOGOUT" from Customer App menu
    Then I save customer phone and referral code in feature context

    Examples:
      | First Name                 | Last Name       | Email ID                        | Phone Number       | Password | Promo Code    | Source   | CardNo        | Expiry | Postal Code       | Cvv       |
      | Testcustomertywd_appleREFC | {RANDOM_STRING} | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 | REFERRAL CODE | facebook | DISCOVER CARD | 12/22  | VALID POSTAL CODE | VALID CVV |
  
  
  @regression
  #stable
  Scenario: Verify Call View Item Details For Ongoing Ondemand Bungii
    Given that ondemand bungii is in progress
      | geofence  | Bungii State |
      | nashville | Enroute      |
    
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid nashville" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    Then correct details should be displayed to driver on "Call" app
    And correct details should be displayed to driver for "VIEW ITEMS"
    
    When I Switch to "customer" application on "same" devices
    When I am on the "LOG IN" page
    And I logged in as "valid nashville" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then correct details should be displayed to customer on "Call" app
    
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
  
  @regression
  Scenario: Verify SMS Details For Ongoing Ondemand Bungii
    Given that ondemand bungii is in progress
      | geofence  | Bungii State |
      | nashville | Enroute      |
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid nashville" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    Then correct details should be displayed to driver on "SMS" app
	And correct details should be displayed to driver for "SMS FOR SUPPORT"
 
	When I Switch to "customer" application on "same" devices
    When I am on the "LOG IN" page
    And I logged in as "valid nashville" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then correct details should be displayed to customer on "SMS" app
    
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |