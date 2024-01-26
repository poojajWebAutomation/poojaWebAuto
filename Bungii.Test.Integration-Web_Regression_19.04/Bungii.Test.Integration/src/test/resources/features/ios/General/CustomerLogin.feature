@ios
Feature: Customer Login
  As a Bungii Customer I should be allowed to login only using valid credential

  Background:
    Given I am on the "LOG IN" page
    And I accept Alert message if exist

  @regression
  Scenario Outline: Verify Customer Cannot Login To Application Using <Scenario>
    When I enter Username :<Username> and  Password :<Password>
    And I click "Log In" button on "Log In" screen
    Then Alert message with <Expected Message> text should be displayed
    When I accept Alert message
    Then I should be navigated to "LOG IN" screen

    Examples:
      | Scenario                | Username | Password | Expected Message |
      | INVALID PASSWORD        | {VALID1}  | Cci1234  | INVALID_PASSWORD |
      | EMPTY PASSWORD          | {VALID1}  | <BLANK>  | EMPTY_FIELD      |
      | EMPTY USERNAME PASSWORD | <BLANK>  | <BLANK>  | EMPTY_FIELD      |
      | EMPTY USERNAME          | <BLANK>  | Cci12345 | EMPTY_FIELD      |
    
  @sanity
  @regression
  Scenario: Verify Customer Can Login Using Valid Credentials
    When I enter Username :{VALID1} and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then User should be successfully logged in to the application

  @onetime
  Scenario: Verify Customer Is Shown Terms And Condition Screen On First Time Login
    Given I install Bungii App again
    When I am on the "LOG IN" page
    And I enter Username :{VALID1} and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    Then I should be navigated to "TERMS AND CONDITION" screen
    And I should see "all details" on Term and Condition agreement
    When I accept Term and Condition agreement
    Then I should be navigated to "ALLOW NOTIFICATIONS" screen
    And I should see "all details" on allow notifications screen
    When I verify and allow access of Notification from Bungii application
    Then I should be navigated to "ALLOW LOCATION" screen
    And I should see "all details" on allow location screen
    When I verify and allow access of Location from Bungii application
    And I close tutorial Page using close button
    Then User should be successfully logged in to the application
    And I Select "LOGOUT" from Customer App menu
    When I am on the "LOG IN" page
    And I enter Username :{VALID1} and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    Then I should be navigated to "Home" screen
    
 