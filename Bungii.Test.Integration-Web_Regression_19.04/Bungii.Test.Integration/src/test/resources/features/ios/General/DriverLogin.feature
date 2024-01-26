@ios
Feature: Driver Login
  As a Bungii Driver I should be allowed to login only using valid credential

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp

  @regression
  Scenario Outline: Verify Driver Should Not Be Able To Login To App Using <Scenario>
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message> text should be displayed on driverApp
    And I accept Alert message on driverApp
    Then I should be navigated to "LOG IN" screen on driverApp

    Examples:
      | Scenario                | Username | Password | Expected Message |
      | INVALID PASSWORD        | {VALID}  | Cci1234  | INVALID_PASSWORD |
      | EMPTY PASSWORD          | {VALID}  | <BLANK>  | EMPTY_FIELD      |
      | EMPTY USERNAME PASSWORD | <BLANK>  | <BLANK>  | EMPTY_FIELD      |
      | EMPTY USERNAME          | <BLANK>  | Cci12345 | EMPTY_FIELD      |

  @sanity
  @regression
  Scenario: Verify Driver Should be Able To Login To Application Using Valid Password
    When I enter phoneNumber :{VALID} and  Password :{VALID}
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    Then I should be successfully logged in to the application


  @regression
  Scenario Outline: Verify Driver Cannot Login On Driver App Before Admin Verification
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message> text should be displayed on driverApp
    And I accept Alert message on driverApp
    Then I should be navigated to "LOG IN" screen on driverApp
    Examples:
      | Scenario             | Username   | Password | Expected Message                                  | Login Button Status  |
      | PENDING VERIFICATION | 9823901494 | Cci12345 | Your account registration is still under process. | LOGIN BUTTON ENABLED |

    #Valid failed, driver should be locked
  @regression
  Scenario Outline: Verify Driver Is Locked When He Enters Incorrect Password Five Times
    When I enter phoneNumber :<Username> and  Password :<InCorrectPassword>
    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message> text should be displayed on driverApp
    And I accept Alert message on driverApp

    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message> text should be displayed on driverApp
    And I accept Alert message on driverApp

    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message 3 Times> text should be displayed on driverApp
    And I accept Alert message on driverApp

    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message> text should be displayed on driverApp
    And I accept Alert message on driverApp

    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message 5 Times> text should be displayed on driverApp
    And I accept Alert message on driverApp
#driver should be locked, not allowed to log in
    When I enter phoneNumber :<Username> and  Password :<Correct Password>
    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message 5 Times> text should be displayed on driverApp
    And I accept Alert message on driverApp


    Examples:
      | Username   | InCorrectPassword | Correct Password | Expected Message | Expected Message 3 Times | Expected Message 5 Times |
      | 8888881010 | cci12345          | Cci12345         | INVALID_PASSWORD | INVALID_PASSWORD_3_TIMES | INVALID_PASSWORD_5_TIMES |


  @regression
  Scenario: Verify New Driver With Payment Status As Inactive Or Pending Cannot Go Online
    When I enter phoneNumber :8989890909 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    Then I should be successfully logged in to the application
    When I click "OFFLINE" button on "Home" screen on driverApp
    Then Alert message with HICCUP MESSAGE text should be displayed on driverApp
    And I accept Alert message on driverApp
    When I Select "ACCOUNT > LOGOUT" from driver App menu


