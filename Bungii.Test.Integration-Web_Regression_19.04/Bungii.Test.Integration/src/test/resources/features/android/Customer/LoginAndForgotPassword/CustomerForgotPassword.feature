@android
Feature: CustomerForgotPassword
  Forgot password functionality in Customer app

  Background:
    Given I am on customer Log in page
    And I tap on the "Forgot Password" Link

  #@regression
  #known issue, the snackbar message disappears quickly.
  Scenario: Verify Customer Forgot Password With Valid Phone Number
    When I enter "valid" Phone Number
    And I tap on the "Send" Link
    And I enter "valid" SMS code
    And I enter customers new "valid" Password
#    And I tap on the "Continue" Link
    Then The user should see "snackbar validation message for success once I click continue" on forgot password page
    And The user should be logged in
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    #And I tap on "Menu" > "Logout" link
    And I tap on the "ACCOUNT>LOGOUT" link

  @regression
  Scenario: Verify Customer Forgot Password With Incorrect Phone Number
    When I enter "invalid" Phone Number
    And I tap on the "Send" Link
    Then The user should see "snackbar validation message for invalid number" on forgot password page

  @regression
  Scenario: Verify Customer Forgot Password With Phone Number Less Than 10 Characters
    When I enter "less than 10 digit" Phone Number
    Then The user should see "validation for incorrect number" on forgot password page
    And The user should see "Send button disabled" on forgot password page
    
  @regression
  Scenario: Verify Customer Forgot Password With Password Less Than 6 Characters
    When I enter "valid" Phone Number
    And I tap on the "Send" Link
    And I enter "valid" SMS code
    And I enter customers new "invalid" Password
    Then The user should see "validation for incorrect password" on forgot password page

  @regression
    #Stable
  Scenario: Verify Customer Forgot Password With Resend SMS Code Functionality
    When I enter "valid" Phone Number
    And I tap on the "Send" Link
    #And I tap on the "Resend Code" Link
    And I enter "valid" SMS code
    And I enter customers new "valid" Password
    And I tap on the "Continue" Link
    Then The user should be logged in

  @regression
  Scenario: Verify Customer Forgot Password Functionality With Expired SMS Code
    When I enter "valid" Phone Number
    And I tap on the "Send" Link
    And I record the SMS Code
    And I tap on the "Resend Code" Link
    And I enter "previous" SMS code
    And I enter customers new "valid" Password
    And I tap on the "Continue" Link
    Then The user should see "snackbar validation message for invalid sms code" on forgot password page
  
  @regression
    #stable
  Scenario: Verify Customer Forgot Password Functionality With Incorrect SMS Code
    When I enter "valid" Phone Number
    And I tap on the "Send" Link
    And I enter "invalid" SMS code
    And I enter customers new "valid" Password
    And I tap on the "Continue" Link
    Then The user should see "snackbar validation message for invalid sms code" on forgot password page