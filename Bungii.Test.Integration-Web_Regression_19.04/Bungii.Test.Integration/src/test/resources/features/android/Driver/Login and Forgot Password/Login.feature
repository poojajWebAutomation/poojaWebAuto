@android
Feature: Log In
  As a Bungii Driver I should be allowed to login only using valid credential

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app


  @regression
    #stable
  Scenario Outline: Verify Driver Should Not Be Able To Login To App Using Invalid Details - Case:<Scenario>
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    Then I should see "<Expected Message>" on Log In screen on driver app
    And I should see "<Login Button Status>" on Log In screen on driver app

  #  Then I should be navigated to "LOG IN" screen on driverApp

    Examples:
      | Scenario                | Username | Password | Expected Message                          |Login Button Status  |
      | EMPTY USERNAME PASSWORD | <BLANK>  | <BLANK>  | Empty Phone and Password Error            |LOGIN BUTTON DISABLED|
      | EMPTY USERNAME          | <BLANK>  | Cci12345 | Empty Phone Error                         |LOGIN BUTTON DISABLED|
      | INVALID PASSWORD        | {VALID}  | Cc1234   | SNACK BAR VALIDATION FOR INVALID PASSWORD |LOGIN BUTTON ENABLED |
      | EMPTY PASSWORD          | {VALID}  | <BLANK>  | Empty Password Error                      |LOGIN BUTTON DISABLED|

  @sanity
  @regression
  Scenario Outline: Verify Driver Should be Able To Login To Application Using Valid Password
    #CORE-4572 Verify hyperlink redirection to correct url in external browser on Android
    When I click "START AN APPLICATION HERE" button on Log In screen on driver app
    Then The "Bungii: The Ultimate Side Hustle" "heading" should be displayed
    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    Then I should be navigated to Home screen on driver app

    Examples:
      | Username   | Password   |
      | 9049840008 | Cci12345   |

  @regression
#stable
   Scenario Outline: Verify Driver Cannot Login On Driver App Before Admin Verification
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    Then I should see "<Expected Message>" on Log In screen on driver app
    And I should see "<Login Button Status>" on Log In screen on driver app
    Examples:
      | Scenario        | Username   | Password | Expected Message                                  | Login Button Status  |
      | PENDING PAYMENT | 9999991009 | Cci12345 | Your account registration is still under process. | LOGIN BUTTON ENABLED |
    
  @regression
  Scenario Outline:  Verify New Driver With Application Status As Pending Cannot Login To Application Using Valid Credentials
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    Then I should see "<Expected Message>" on Log In screen on driver app
    And I should see "<Login Button Status>" on Log In screen on driver app
    Examples:
      | Scenario        | Username   | Password | Expected Message                                  | Login Button Status  |
      | PENDING PAYMENT | 9999991009 | Cci12345 | Your account registration is still under process. | LOGIN BUTTON ENABLED |
  
  @regression
    #stable
  Scenario Outline: Verify New Driver With Payment Status As Inactive Or Pending Cannot Go Online
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
	And I wait for "1" mins
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I tap on "Go Online button" on Driver Home page
    Then I should see "<Expected Message>" on Log In screen on driver app
    Examples:
      | Scenario        | Username   | Password | Expected Message                                  |
      | PENDING PAYMENT | 8989890909 | Cci12345 | It looks like we ran into a hiccup. Please contact support@bungii.com for more information. |
  
  @regression
  Scenario Outline: Verify Driver Is Locked When He Enters Incorrect Password Five Times
    When I enter phoneNumber
    And I enter invalid password and click on "Log In" button for "3" times on Log In screen on driver app
    Then I should see "<Expected Message 3>" on Log In screen on driver app
    And I enter invalid password and click on "Log In" button for "2" times on Log In screen on driver app
    Then I should see "<Expected Message 5>" on Log In screen on driver app
    Examples:
      | Expected Message 5                                                                                                    |   Expected Message 3                                                                                    |
      | Invalid login credentials. Your account has been locked. Please use the Forgot Password option to reset your account. | Invalid login credentials. You have exhausted 3 out of 5 attempts of entering the correct credentials.  |
  
  @regression
  Scenario Outline: Verify Driver Is Locked When He Enters Incorrect Password Five Times Second Case
    When I enter phoneNumber
    And I enter invalid password and click on "Log In" button for "5" times on Log In screen on driver app
    Then I should see "<Expected Message>" on Log In screen on driver app
    Examples:
      | Expected Message                                                                                                      |
      | Invalid login credentials. Your account has been locked. Please use the Forgot Password option to reset your account. |
    
  #@regression
  Scenario: Verify Driver Location Permission Displayed Upon First Time Installation
    Given I have device which has location permission
    Given I install Bungii Driver App again
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    When I enter phoneNumber :{VALID} and  Password :{VALID}
    And I click "Log In" button on Log In screen on driver app
    Then "DRIVER's LOCATION" page should be opened
    And I should see "all details" on allow location driver screen
    When I verify and allow access of Location from Bungii driver application
    Then I should be navigated to Home screen on driver app
    When I Select "ACCOUNT" from driver App menu
    And The "ACCOUNT" page is opened
    When I Select "LOGOUT" from driver App menu
    When I enter phoneNumber :{VALID} and  Password :{VALID}
    And I click "Log In" button on Log In screen on driver app
    Then I should be navigated to Home screen on driver app
