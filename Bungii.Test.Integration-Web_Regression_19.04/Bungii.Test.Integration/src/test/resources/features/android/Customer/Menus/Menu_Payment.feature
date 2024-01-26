@android
Feature: Menu_Payment
Scenarios on Payment Methods

  @regression
#stable
   Scenario: Verify Customer Can Change Default Payment Card Added To New One
    Given I am on customer Log in page
    And I am logged in as "Testcustomertywd_appleNwBBB CustBBB" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I tap on "Menu" > "Payment" link
    And I tap on "the 2nd payment method" on Payment page
    And I tap on "Set as default payment mode" on Payment page
    And I tap on "Save" on Payment page
    Then I should see "default payment set" on Payment page
    And I tap on the "ACCOUNT>LOGOUT" link
    #And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Verify Customer Payment Screen With No Payment Method Exists
    Given I am on customer Log in page
    And I am logged in as "newly registered" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I tap on "Menu" > "Payment" link
    Then I should see "message when no payment methods exist" on Payment page
    And I tap on the "ACCOUNT>LOGOUT" link
    #And I tap on "Menu" > "Logout" link

  #commented this due to base to auto data issue
  @BrowserstackDoesNotSupport
    #donotexecute
  Scenario Outline: Verify Customer Payment Method Deletion
    Given I am on Sign up page
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
   # And I enter "ValidPercent" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
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

    When I swipe "default" card on the payment page
    And I tap on "Delete" on Payment page
    Then I should see "message when no payment methods exist" on Payment page
    #And I tap on "Menu" > "Logout" link
    And I tap on the "ACCOUNT>LOGOUT" link
    Examples:
      | Scenario       | Card Detail                | Card Expiry       |CVV|Postal Code|
      | VALID_discover | valid discover card number | valid expiry date |valid cvv|valid postal code|
  
  @BrowserstackDoesNotSupport
    #donotexecute
  Scenario Outline: Verify Customer Payment Method Addition With Valid Card Details
    Given I am logged in as "valid" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I tap on "Menu" > "Payment" link
    And I get the number of cards present
    And I tap on "Add New" on Payment page
    And I tap on "Credit or Debit Card" on Payment page
    And I enter "<Card Detail>" on Card Details page
    And I enter "<Card Expiry>" on Card Details page
    And I enter "<CVV>" on Card Details page
    And I enter "<Postal Code>" on Card Details page

    And I tap on "Add Card" on Payment page
    Then I should see "the card has been added" on Payment page
    #And I tap on "Menu" > "Logout" link
    And I tap on the "ACCOUNT>LOGOUT" link
    Examples:
      | Scenario       | Card Detail                | Card Expiry       |CVV|Postal Code|
      | VALID_discover | valid discover card number | valid expiry date |valid cvv|valid postal code|
      | VALID_visa     | valid visa card number     | valid expiry date |valid cvv|valid postal code|
  
  @BrowserstackDoesNotSupport
    #donotexecute
  Scenario: Verify Customer Payment Method Addition With Invalid Card Number
    Given I am logged in as "valid" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I tap on "Menu" > "Payment" link
    And I tap on "Add New" on Payment page
    And I tap on "Credit or Debit Card" on Payment page
    And I enter "invalid card number" on Card Details page
    Then I should see "invalid card error" on Payment page
  
  
  @BrowserstackDoesNotSupport
    #donotexecute
  Scenario:  Verify Customer Payment Method Addition With Invalid Card Expiry
    Given I am logged in as "valid" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I tap on "Menu" > "Payment" link
    And I tap on "Add New" on Payment page
    And I tap on "Credit or Debit Card" on Payment page
    And I enter "valid card number" on Card Details page
    Then I should see "no option to add previous year" on Payment page
  
  @BrowserstackDoesNotSupport
    #donotexecute
  Scenario: Verify Customer Payment Method Addition With Fraud Card Number
    Given I am logged in as "valid" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I tap on "Menu" > "Payment" link
    And I tap on "Add New" on Payment page
    And I tap on "Credit or Debit Card" on Payment page
    And I enter "fraud card number" on Card Details page
    Then I should see "fraud card error" on Payment page