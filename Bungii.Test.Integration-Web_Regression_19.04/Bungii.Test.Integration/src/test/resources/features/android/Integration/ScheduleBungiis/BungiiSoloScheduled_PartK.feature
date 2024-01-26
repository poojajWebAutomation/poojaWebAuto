@android
Feature: SoloScheduled Part K
  
  Background:
	   
	   
	   #keep this scenario at last
#CMA 1513: delete card once trip is cancel
  #@regression
	   @knownissue
	   Scenario Outline: Verify Customer Cannot Delete Payment Method Linked To Any Ongoing Or Scheduled Trip
	   Given I am on Sign up page
	   When I enter "unique" customer phone number on Signup Page
	   And I enter "valid test" data in mandatory fields on Signup Page
	   And I enter "ValidPercent" promo code on Signup Page
  #  And I enter "Referral" promo code on Signup Page
	   And I tap on the "Sign Up" button on Signup Page
	   And I enter "valid" Verification code
	   And I tap on the "Verification Continue" Link
	   Then The user should be logged in
	   
	   When I tap on "Menu" > "Payment" link
	   And I get the number of cards present
	   And I tap on "Add" on Payment page
	   And I tap on "Credit or Debit Card" on Payment page
	   And I enter "<Card Detail>" on Card Details page
	   And I enter "<Card Expiry>" on Card Details page
	   And I enter "<CVV>" on Card Details page
	   And I enter "<Postal Code>" on Card Details page
	   
	   And I tap on "Add Card" on Payment page
	   Then I should see "the card has been added" on Payment page
	   
	   
	   When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
	   | Bungii Time   | Customer Phone  | Customer Name | Customer Password |
	   | NEXT_POSSIBLE | NEW_USER_NUMBER |               | Cci12345          |
	   
	   Given I am on customer Log in page
	   And I am logged in as "new test customer" customer
	   And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	   And I close "Tutorial" if exist
	   When I tap on "Menu" > "PAYMENT" link
	   Then "Payment" page should be opened
	   When I swipe "default" card on the payment page
	   And I tap on "Delete" on Payment page
  #  Then Alert message with Delete Warning text should be displayed
	   And I should see "Payment Method is already associated to a trip" on Payment page
	   Then I cancel all bungiis of customer
	   | Customer Phone  | Customer2 Phone |
	   | NEW_USER_NUMBER |                 |
	   Given I am on customer Log in page
	   And I am logged in as "new test customer" customer
	   And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	   And I close "Tutorial" if exist
	   When I tap on "Menu" > "PAYMENT" link
	   When I swipe "default" card on the payment page
	   And I tap on "Delete" on Payment page
	   Then I should see "message when no payment methods exist" on Payment page
	   Examples:
	   | Scenario       | Card Detail                | Card Expiry       | CVV       | Postal Code       |
	   | VALID_discover | valid discover card number | valid expiry date | valid cvv | valid postal code |
  
  @knownissue
  Scenario: Verify Customer Can Create A Scheduled Bungii - Also Verify Correct Contact Number Is Displayed On Call And SMS Option [Calling app Not Found on Browserstack]
	Given that solo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time   |
	  | kansas   | Accepted     | NEXT_POSSIBLE |
	When I Switch to "customer" application on "same" devices
	And I am logged in as "valid" customer
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	And I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
   # And I wait for Minimum duration for Bungii Start Time
	And Bungii Driver "Start Schedule Bungii" request
	And I Switch to "customer" application on "same" devices
	Then for a Bungii I should see "Enroute screen"
	
	When I tap "SMS for a solo driver" during a Bungii
	Then correct details should be displayed on "SMS" app
	When I tap "Call for a solo driver" during a Bungii
	Then correct details should be displayed on "Calling" app
	And Trip Information should be correctly displayed on "EN ROUTE" status screen for customer
	
	When I Switch to "driver" application on "same" devices
	Then Bungii driver should see "Enroute screen"
	When Bungii Driver taps "SMS for a customer" during a Bungii
	Then correct details should be displayed to driver on "SMS" app
	When Bungii Driver taps "Call for a customer" during a Bungii
	Then correct details should be displayed to driver on "Calling" app
	When Bungii Driver taps "Contact support" during a Bungii
	Then correct details should be displayed to driver on "Support-SMS" app
	When Bungii Driver taps "View items" during a Bungii
	Then Bungii driver should see "Pickup Item"
	And Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
	
	When Bungii Driver "slides to the next state"
	Then Bungii driver should see "Arrived screen"
	When Bungii Driver "slides to the next state"
	Then Bungii driver should see "Loading Item screen"
	When Bungii Driver "slides to the next state"
	Then Bungii driver should see "Driving to DropOff screen"
	When Bungii Driver "slides to the next state"
	Then Bungii driver should see "Unloading Item screen"
	When Bungii Driver "slides to the next state"
	
	And I Switch to "customer" application on "same" devices
	Then Bungii customer should see "correct details" on Bungii completed page
	And I tap on "OK on complete" on Bungii estimate
	And I tap on "No free money" on Bungii estimate
	
	When I Switch to "driver" application on "same" devices
	Then Bungii driver should see "correct details" on Bungii completed page
	And Bungii Driver "completes Bungii"
	And I Select "HOME" from driver App menu
  
  @knownissue
  Scenario: Verify If TELET Of Re-searched Trip
	Given that solo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time   |
	  | kansas   | Accepted     | NEXT_POSSIBLE |
	And I get TELET time of of the current trip
	Then Telet time of current trip should be correctly calculated
	And I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
	Then I wait for "1" mins
	
	And I open Admin portal and navigate to "Scheduled Deliveries" page
	And I open the trip for "Testcustomertywd_appleyyhGZP Stark" customer
	
	And I remove current driver and researches Bungii
	When I switch to "ORIGINAL" instance
	And I Switch to "driver" application on "same" devices
	Then Telet time of research trip should be not be same as previous trips
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE |                 |
	
  @rework
  Scenario: Verify error message on android When Customer has two Bungiis scheduled, and the 1 hour prior start time of second Bungii overlaps with the TELET of the first Bungii
	When that solo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time  |
	  | Kansas   | Accepted     | 0.75 hour ahead |
	
	And I Open "customer" application on "same" devices
	When I am on customer Log in page
	When I am logged in as "Testcustomertywd_appleand_A Android" customer
	
	And I tap on "Menu" > "Home" link
	And I enter "kansas pickup and dropoff locations" on Bungii estimate
	And I tap on "Get Estimate button" on Bungii estimate
	And I add "1" photos to the Bungii
	And I add loading/unloading time of "30 mins"
	And I select Bungii Time as "next possible scheduled"
	And I tap on "Request Bungii" on Bungii estimate
	And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	And I check if the customer is on success screen
	And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
	And I wait for "2" mins
	
	When I open new "Chrome" browser for "ADMIN PORTAL"
	And I navigate to admin portal
	And I log in to admin portal
	And I Select "Scheduled Trip" from admin sidebar
	And I open the trip for "Testcustomertywd_appleand_A Android" customer
	And I Select "Edit Trip Details" option
	And I assign driver for the "Solo" trip
	And I click on "VERIFY" button
	And The "Your changes are good to be saved." message is displayed
	Then I click on "SAVE CHANGES" button
	And The "Bungii Saved!" message is displayed
	
	When I switch to "ORIGINAL" instance
	When that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
	  | geofence | Bungii State | Bungii Time  |
	  | Kansas   | Scheduled    | 0.5 hour ahead |
	
	And I Open "customer" application on "same" devices
	When I am on customer Log in page
	When I am logged in as "Testcustomertywd_appleand_A Android" customer
	
	When I Switch to "driver" application on "same" devices
	When I Open "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	Then I click "OFFLINE" button on Home screen on driver app
	And I Select "AVAILABLE BUNGIIS" from driver App menu
	And I Select Trip from driver available trip
	And I tap on "ACCEPT" on driver Trip details Page
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
	And Bungii Driver "Start Schedule Bungii" request
	Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8888889916     |                 |
	
	 #@regression
  #need to rework
  @rework
  Scenario: Verify Driver Notification - 30 Mins Before Scheduled Trip
	Given that solo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time   |
	  | kansas   | Scheduled    | NEXT_POSSIBLE |
	
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
	And I tap on "Available Trips link" on Driver Home page
	And I Select Trip from driver available trip
	And I tap on "ACCEPT" on driver Trip details Page
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
	When I Switch to "customer" application on "same" devices
	
	And I wait for Minimum duration for Bungii Start Time
	Then I click on notification for "TAP NOTIFICATION TO ACTIVATE BUNGII"
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE |                 |
  
  
  @rework
  Scenario: Verify Customer Cannot Schedule Bungii For A Time That Is Outside Working Hours :DUO
	Given I login as customer "8805368840" and is on Home Page
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	And I enter "kansas pickup and dropoff locations" on Bungii estimate
	And I tap on "two drivers selector" on Bungii estimate
	Then I should see "two drivers selected" on Bungii estimate
	Then I tap on "Get Estimate button" on Bungii estimate
	When I try to schedule bungii for "today - after working hour" for "DUO"
	Then User should see message "OUTSIDE BUISSNESS HOUR" text on the screen
	When I try to schedule bungii for "tommorow - before working hour" for "DUO"
	Then User should see message "OUTSIDE BUISSNESS HOUR" text on the screen
  
	
  @rework
  Scenario: Verify Customer Cannot Schedule Bungii for A Time That Is Outside Working Hours :SOLO
	And I login as customer "8805368840" and is on Home Page
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	
	And I enter "kansas pickup and dropoff locations" on Bungii estimate
	And I tap on "Get Estimate button" on Bungii estimate
	When I try to schedule bungii for "today - after working hour" for "SOLO"
	Then User should see message "OUTSIDE BUISSNESS HOUR" text on the screen
	When I try to schedule bungii for "tommorow - before working hour" for "SOLO"
	Then User should see message "OUTSIDE BUISSNESS HOUR" text on the screen
	
    #@regression
  @rework
  #need to work on automation cannot wait for 2 hours
  Scenario:Verify Customer Notification - 2 hours before scheduled trip [Not to be executed]
	Given that solo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time    |
	  | kansas   | Accepted     | 1.5 hour ahead |
	And I Switch to "customer" application on "same" devices
	Given I am on customer Log in page
	And I am logged in as "valid" customer
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	And I wait for Minimum duration for current Bungii to be T-2 hours
	And I Switch to "driver" application on "same" devices
	Then I click on notification for "SCHEDULED PICKUP ACCEPTED"
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE |                 |
  
  
  