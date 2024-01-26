@android
Feature: SoloScheduled Part L
  
  Background:

@knownissue
Scenario: Verify Customer Can Create Ondemand Bungii With Received Referred Code
Given I have customer with referral code
And I Switch to "driver" application on "same" devices
And I am logged in as "valid baltimore" driver
And I Select "HOME" from driver App menu
Then I tap on "Go Online button" on Driver Home page
When I Switch to "customer" application on "same" devices
And I am on customer Log in page
And I am logged in as "valid baltimore" customer

And I enter "baltimore pickup and dropoff locations" on Bungii estimate
And I tap on "Get Estimate button" on Bungii estimate
And I add loading/unloading time of "15 mins"
And I tap on "Promo code value" on Estimate screen
Then I should able to see expected promo code in available promo code
Then The "referral code received with out first time tag" is displayed
When I tap on "Back" button of android mobile
And I add "1" photos to the Bungii
And I tap on "Request Bungii" on Bungii estimate
And I tap on "Yes on HeadsUp pop up" on Bungii estimate
Then for a Bungii I should see "Bungii search screen"

Then I click on notification for "on demand trip"
Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
When I click "YES" button on alert message
And I click "ACCEPT" button on the "Bungii Request" screen

And I Switch to "customer" application on "same" devices
Then I click "Ok" button on the "Bungii Request" screen

And I Switch to "driver" application on "same" devices
And Bungii Driver "slides to the next state"
And Bungii Driver "slides to the next state"
And Bungii Driver "slides to the next state"
And Bungii Driver "slides to the next state"
And Bungii Driver "slides to the next state"

And I Switch to "customer" application on "same" devices
Then Bungii customer should see "correct details with promo" on Bungii completed page
And I tap on "OK on complete" on Bungii estimate
And I tap on "No free money" on Bungii estimate

When I Switch to "driver" application on "same" devices
Then Bungii driver should see "correct details" on Bungii completed page
And Bungii Driver "completes Bungii"
    
     #this scenario is moved from signup to ondemand feature as we can use test data generated in this test case
@knownissue
Scenario Outline: Verify Customer Signup With Referral code
Given I Switch to "customer" application on "same" devices
When I am on customer Log in page
And I am logged in as "valid baltimore" customer
And I tap "Referral Invite link" on Home page
Then I should see "Referral Code" on Invite Page
When I get Invite Code
And I tap on "Back" icon of page
Then I tap on the "ACCOUNT>LOGOUT" link
    #Then I tap on "Menu" > "Logout" link
And I Switch to "customer" application on "same" devices
Given I am on Sign up page
When I enter "unique" customer phone number on Signup Page
And I enter "valid" data in mandatory fields on Signup Page
 #   And I enter "ValidPercent" promo code on Signup Page
And I enter "Code" promo code on Signup Page
And I tap on the "Sign Up" button on Signup Page
And I enter "valid" Verification code
And I tap on the "Verification Continue" Link
Then The user should be logged in
    #When I tap on "Menu" > "Promos" link
When I tap on the "ACCOUNT>PROMOS" link
And I should able to see expected promo code in available promo code
Then The "This code is only available for your first Bungii." is displayed
Then I save customer phone and referral code in feature context
Examples:
| Scenario       | Card Detail                | Card Expiry       |CVV      |Postal Code      |
| VALID_discover | valid discover card number | valid expiry date |valid cvv|valid postal code|


@knownissue
Scenario: Verify Customer Can Create Ondemand Bungii With Referral Code
Given I have customer with referral code
And I Switch to "driver" application on "same" devices
And I am logged in as "valid baltimore" driver
And I Select "HOME" from driver App menu
Then I tap on "Go Online button" on Driver Home page
When I Switch to "customer" application on "same" devices
And I am on customer Log in page
And I am logged in as "newly created user" customer

And I enter "baltimore pickup and dropoff locations" on Bungii estimate
And I tap on "Get Estimate button" on Bungii estimate
And I add "1" photos to the Bungii
And I add loading/unloading time of "15 mins"
And I tap on "Promo code value" on Estimate screen
Then I should able to see expected promo code in available promo code
When I tap on "Back" button of android mobile
And I tap on "Request Bungii" on Bungii estimate
And I tap on "Yes on HeadsUp pop up" on Bungii estimate
Then for a Bungii I should see "Bungii search screen"

Then I click on notification for "on demand trip"
Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
When I click "YES" button on alert message
And I click "ACCEPT" button on the "Bungii Request" screen

And I Switch to "customer" application on "same" devices
When I click "Ok" button on the "BUNGII ACCEPTED" screen

And I Switch to "driver" application on "same" devices
And Bungii Driver "slides to the next state"
And Bungii Driver "slides to the next state"
And Bungii Driver "slides to the next state"
And Bungii Driver "slides to the next state"
And Bungii Driver "slides to the next state"

And I Switch to "customer" application on "same" devices
Then Bungii customer should see "correct details with promo" on Bungii completed page
And I tap on "OK on complete" on Bungii estimate
And I tap on "No free money" on Bungii estimate

When I Switch to "driver" application on "same" devices
Then Bungii driver should see "correct details" on Bungii completed page
And I click "On To The Next One" button on the "Bungii Completed" screen

@knownissue
Scenario: Verify Customer Can Create An Ondemand Bungii And Correct Contact Number Is Displayed On Call And SMS Option
Given that ondemand bungii is in progress
| geofence | Bungii State |
| baltimore   | Enroute      |
When I Switch to "driver" application on "same" devices
And I am on the LOG IN page on driver app
And I am logged in as "valid baltimore" driver
And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

And I Switch to "customer" application on "same" devices
And I am logged in as "valid baltimore" customer
And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
And I close "Tutorial" if exist
Then for a Bungii I should see "Enroute screen"
When I tap "SMS for a solo driver" during a Bungii
Then correct details should be displayed on "SMS" app
When I tap "Call for a solo driver" during a Bungii
    #Gopal:--Commenting the validation steps since on browserstack app close when click on Call for a solo driver
    #Then correct details should be displayed on "Calling" app
    #And Trip Information should be correctly displayed on "EN ROUTE" status screen for customer

When I Switch to "driver" application on "same" devices
Then Bungii driver should see "Enroute screen"
When Bungii Driver taps "SMS for a customer" during a Bungii
Then correct details should be displayed to driver on "SMS" app
Then Bungii Driver taps "Call for a customer" during a Bungii
When I Switch to "driver" application on "same" devices
    #Then correct details should be displayed to driver on "Calling" app
Then Bungii Driver taps "Contact support" during a Bungii
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
When I tap on "OK on complete" on Bungii estimate
And I tap on "No free money" on Bungii estimate

And I Switch to "driver" application on "same" devices
And Bungii Driver "completes Bungii"
And Customer should receive "bungii" receipt email