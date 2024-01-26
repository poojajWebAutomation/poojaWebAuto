@android
Feature: CustomerSignup
  Sign up as a Customer on Bungii app

  Background:
    Given I am on Sign up page
  
  
  @regression
 #stable
  Scenario: Verify Signup With Existing Phone Number
    When I enter "existing" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    Then the new user should see "snackbar validation message for existing user"
    And the new user should see "Signup page"

  @regression
  Scenario: Verify Referral Source Count Upon Customer Signup
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Referral Source" from admin sidebar
    Then I get Referral Source info for "OTHER"
    When I switch to "ORIGINAL" instance
    And I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I tap on the "Sign Up" button on Signup Page
#    And I tap on the "No, Continue" button on Signup Page
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then The user should be logged in
    When I switch to "ADMIN PORTAL" instance
    And I Select "Referral Source" from admin sidebar
    Then account created info for "OTHER" should be "increase by 1"

  @regression
  Scenario: Verify Customer Signup With All Fields Blank
    When I enter "blank" customer phone number on Signup Page
    And I enter "blank" data in mandatory fields on Signup Page
    Then the new user should see "sign up button disabled"

  @regression

  Scenario: Verify Customer Signup With Invalid Details
    When I enter "invalid" customer phone number on Signup Page
    And I enter "invalid" data in mandatory fields on Signup Page
    Then the new user should see "validations for all fields"



  @regression
  Scenario: Verify Signup With Invalid Referral Code
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I enter "invalid" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I tap on the "Yes" button on Signup Page
    Then the new user should see "Signup page"



  @knownissue
  Scenario: Verify Signup With Promo Code To Be Active In Future
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I enter "FutureActive" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    Then the new user should see "Inactive Promo Code message"

  @regression
  Scenario: Verify Text On Promos Page When First Time Promo Code Is Added
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I enter "ValidPercent" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then The user should be logged in
    #When I tap on "Menu" > "Promos" link
    When I tap on the "ACCOUNT>PROMOS" link
    #Then "ValidPercent" promo code should be displayed
    When I enter "PROMO1" promo code
    And I click on "ADD" button
    Then The "This code is only available for your first Bungii." is displayed
    When I click on "i" icon
    Then The "Info" is displayed
  
  
  @regression
    #web
  #stable

  Scenario: Verify Trip completed Count On Admin Portal Is Updated When Driver Completes A Bungii
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8877661165	     | Testcustomertywd_appleMarkFJ LutherFJ | Cci12345          |
    And As a driver "Testdrivertywd_appleks_a_kay Stark_ksThreE" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | enroute       |
      |Arrived         |
      |Loading Item     |
      |Driving To Dropoff |
      |Unloading item    |
      |Completed         |
    And I wait for 2 minutes
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Customers" from admin sidebar
    And I Search for customer
    Then I verify the trip count
  
  @email
  @ready
    @nonstable
  Scenario: Verify Customer Signup With Valid Promo Code
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I enter "ValidPercent" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    Then The user should be logged in
    #When I tap on "Menu" > "Promos" link
    When I tap on the "ACCOUNT>PROMOS" link
    Then "ValidPercent" promo code should be displayed
    When I click on "i" icon
    Then The "Info Message" is displayed
    And Customer should receive signup email

  @ready
    #CORE-3685 (Android)
  Scenario: Verify deletion of new created customer account and then reuse the same account for new customer creation
    When I enter "new valid" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I enter "ValidPercent" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    Then The user should be logged in
    When I tap on "Menu" > "ACCOUNT" link
    Then "ACCOUNT INFO" page should be opened
    And logged in Customer details should be displayed
    And I tap on the "Delete account" Link
    Then "Delete account" page should be opened
    And I enter customers "valid1" Password
    And I click on "Delete" button
    And I am on customer Log in page
    And I tap on the "Sign up" Button on Login screen
    When I enter "deleted valid" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I enter "ValidPercent" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    Then The user should be logged in

  #used one off
  #Know issue, no alert
  @knownissue
  Scenario: Verify That Validation Message Is Displayed On Signing Up With Invalid Or Used One off Promocode
    When I Switch to "customer" application on "same" devices
    And I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I Enter "Referral Code" value in "Referral code" field in "SIGN UP" Page
      | Referral Code |
      | R1D2INVALID   |
    And I Select Referral source
    And I tap on the "Sign Up" button on Signup Page
    Then the new user should see "Invalid Promo Code message"
    
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then The user should be logged in