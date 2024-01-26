@android
Feature: Login
  In order to login to bungii
  As a customer and perform functions

  Background:
    Given I am on customer Log in page
    
  @regression
  Scenario: Verify Terms And Condition Screen Is Displayed To Bungii Customer On First Time Login (Resetting data Of App To Simulate Fresh Install)
    Given I newly installed "Bungii Customer" app
    When I tap on the "Log in" button on Signup Page
    And I enter customers "valid" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    Then "Terms and Conditions" page should be opened
    And I should see "all details" on Term and Condition agreement
    When I accept Term and Condition agreement
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    Then "Tutorial" page should be opened
    When I close tutorial Page
    Then The user should be logged in
    And I tap on the "ACCOUNT>LOGOUT" link
    #And I tap on "Menu" > "Logout" link
    
  @sanity
  @regression
  Scenario: Verify Customer Login With Valid Credentials
    When I enter customers "valid" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then The user should be logged in
    And I tap on the "ACCOUNT>LOGOUT" link
    #And I tap on "Menu" > "Logout" link

  @ready
    #CORE-3685(Android)
  Scenario: Verify account deletion for existing Customer
    When I enter customers "existing valid" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then The user should be logged in
    When I tap on "Menu" > "ACCOUNT" link
    Then "ACCOUNT INFO" page should be opened
    And I tap on the "Delete account" Link
    Then "Delete account" page should be opened
    And I enter customers "invalid1" Password
    Then The user should see "snackbar validation message invalid password for account deletion" on log in page
    And I enter customers "valid1" Password
    And I click on "Delete" button
    Then User should see message "Account deleted successfully" text on the screen
    And I am on customer Log in page
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "customers" from admin sidebar
    And I Search for customer with phone number
    Then I should see "No Customers found." message
    And I Select "completed deliveries" from admin sidebar
    And I Search for customer with phone number
    Then I should see "No Deliveries found." message



  @regression
  Scenario: Verify Customer Login With Invalid Credentials
    When I enter customers "invalid" Phone Number
    And I enter customers "invalid" Password
    And I tap on the "Log in" Button on Login screen
    Then The user should see "snackbar validation message invalid password" on log in page

  @regression
  Scenario: Verify Customer Login With Blank Credentials
    When I enter customers "blank" Phone Number
    And I enter customers "blank" Password
    Then The user should see "field validations for phone number" on log in page
    And The user should see "field validations for password" on log in page
    And The user should see "login button disabled" on log in page

  @regression
  Scenario: Verify Customer Login With Blank Phone Number
    When I enter customers "blank" Phone Number
    And I enter customers "valid" Password
    Then The user should see "field validations for phone number" on log in page
    And The user should see "login button disabled" on log in page

  @regression
  Scenario: Verify Customer Login With Blank Password
    When I enter customers "blank" Password
    And I enter customers "valid" Phone Number
    Then The user should see "field validations for password" on log in page
    And The user should see "login button disabled" on log in page


  @regression
  Scenario: Verify Customer Login Functionality With Invalid Password Attempt Five Times
    When I enter customers "Valid_ToBeLocked" Phone Number
    And I enter customers "invalid" Password
    And I tap on the "Log in" Button on Login screen
    Then The user should see "snackbar validation message invalid password" on log in page
    When I enter customers "invalid" Password
    And I tap on the "Log in" Button on Login screen
    Then The user should see "snackbar validation message invalid password" on log in page
    When I enter customers "invalid" Password
    And I tap on the "Log in" Button on Login screen
    Then The user should see "Invalid login credentials. 3 out of 5 attempts exhausted message" on log in page
    When I enter customers "invalid" Password
    And I tap on the "Log in" Button on Login screen
    Then The user should see "snackbar validation message invalid password" on log in page
    When I enter customers "invalid" Password
    And I tap on the "Log in" Button on Login screen
    Then The user should see "Invalid login credentials. Your account has been locked message" on log in page
    And I tap on the "Forgot Password" Link
    When I enter "Valid_ToBeLocked" Phone Number
    And I tap on the "Send" Link
    And I enter "valid code for locked" SMS code
    And I enter customers new "valid" Password
    And I tap on the "Continue" Link
 #   Then The user should see "snackbar validation message for success once I click continue" on forgot password page
    And The user should be logged in
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened

  #@regression
  Scenario: Verify Permissions Are Only Displayed On App Upon First Time Installation
    Given I have device which has location permission
    Given I install Bungii App again
    And I am on customer Log in page
    And I enter customers "valid" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    Then "Terms and Conditions" page should be opened
    When I accept Term and Condition agreement
    Then "LOCATION" page should be opened
    And I should see "all details" on allow location screen
    When I verify and allow access of Location from Bungii application
    When I close tutorial Page
    Then The user should be logged in
    And I tap on the "ACCOUNT>LOGOUT" link
    #And I tap on "Menu" > "Logout" link
    And I am on customer Log in page
    And I enter customers "valid" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    Then "Home" page should be opened

  #@regression
  Scenario: Verify If Location Permissions Are Denied Then Alert Is Shown Again On Home page
    Given I have device which has location permission
    Given I install Bungii App again
    And I am on customer Log in page
    And I enter customers "valid" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    Then "Terms and Conditions" page should be opened
    When I accept Term and Condition agreement
    Then "LOCATION" page should be opened
    When I verify and deny access of Location from Bungii application
    When I close tutorial Page
    Then I verify and allow access of Location upon reasking from Bungii application
    Then "Home" page should be opened
