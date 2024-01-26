@ios

Feature: FORGOT PASSWORD
  As a Bungii driver
  When I click Forgot password button
  I Should be able to Reset my password

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp

  @regression
  Scenario Outline: Verify Driver Is Alerted If <Scenario> Is Added During Forgot Password Functionality
    When I click "Forgot Password" button on "LOG IN" screen on driverApp
    Then "FORGOT PASSWORD INFORMATION" message should be displayed on "FORGOT PASSWORD" page on driverApp
    And I Enter "<Value>" value in "Phone Number" field in "FORGOT PASSWORD" Page on driverApp
    When I click "SEND" button on "FORGOT PASSWORD" screen on driverApp
    Then user is alerted for "<Expected Message>" on driverApp

    Examples:
      | Scenario                  | Value        | Expected Message     |
      | Phone Number less than 10 | 940396       | FAILED TO SEND TOKEN |
      | Invalid Phone Number      | 212121212121 | FAILED TO SEND TOKEN |

  @regression
  Scenario Outline: Verify Driver Is Alerted If Enters <Scenario> During Forgot Password Functionality
    #CORE-4572 Verify hyperlink redirection to correct url in external browser on ios
    When I click "START AN APPLICATION HERE" button on "LOG IN" screen on driverApp
    Then The "Bungii: The Ultimate Side Hustle" "heading" should be displayed
    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    When I click "Forgot Password" button on "LOG IN" screen on driverApp
    Then "FORGOT PASSWORD INFORMATION" message should be displayed on "FORGOT PASSWORD" page on driverApp
    And I Enter "<Value>" value in "Phone Number" field in "FORGOT PASSWORD" Page on driverApp
    When I click "SEND" button on "FORGOT PASSWORD" screen on driverApp
    And I Get SMS CODE for "<Value>" number on driverApp
    And I Enter "<SMS CODE>" value in "sms code" field in "FORGOT PASSWORD" Page on driverApp
    And I Enter "<New Password>" value in "new password" field in "FORGOT PASSWORD" Page on driverApp
    And I Enter "<New Password>" value in "confirm password" field in "FORGOT PASSWORD" Page on driverApp
    When I click "Continue" button on "Forgot Password" screen on driverApp
    Then user is alerted for "<Expected Message>" on driverApp
    And I should be navigated to "FORGOT PASSWORD" screen on driverApp

    Examples:
      | Scenario         | Value        | New Password | SMS CODE | Expected Message             |
      | Invalid password | {VALID USER} | Cci1         | valid    | INVALID PASSWORD WHILE RESET |
      | Invalid SMS code | {VALID USER} | Cci12345     | invalid  | INVALID SMS CODE |

  @regression
  Scenario Outline: Verify Driver Is Able To Change Password Using Forgot Password Functionality
    When I click "Forgot Password" button on "LOG IN" screen on driverApp
    Then "FORGOT PASSWORD INFORMATION" message should be displayed on "FORGOT PASSWORD" page on driverApp
    And I Enter "<Value>" value in "Phone Number" field in "FORGOT PASSWORD" Page on driverApp
    When I click "SEND" button on "FORGOT PASSWORD" screen on driverApp
    And I Get SMS CODE for "<Value>" number on driverApp
    And I Enter "valid" value in "sms code" field in "FORGOT PASSWORD" Page on driverApp
    And I Enter "<New Password>" value in "new password" field in "FORGOT PASSWORD" Page on driverApp
    And I Enter "<Confirm Password>" value in "confirm password" field in "FORGOT PASSWORD" Page on driverApp
    When I click "Continue" button on "FORGOT PASSWORD" screen on driverApp
    Then user is alerted for "<Expected Message>" on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I should be navigated to "Home" screen on driverApp

    Examples:
      | Scenario    | Value         | New Password | Confirm Password  | Expected Message        |
      | VALID PHONE | {VALID USER1} | Cci12345     | Cci12345          | PASSWORD CHANGE SUCCESS |
