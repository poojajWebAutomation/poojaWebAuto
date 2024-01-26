@android
Feature: DriverMenu
  In Bungii Driver
  As a logged in driver
  I want to check all menu links

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    Given I am on Driver logged in Home page
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
  @regression
  Scenario: Verify Driver Menus - Leaderboard | Scheduled And Available Bungiis | Earnigs | Account |  Alert Settings | Privacy Policy | Store | Logout
    When I Select "LEADERBOARD" from driver App menu
    And The "LEADERBOARD" page is opened
    Then I should be able to see data on "LEADERBOARD" page
    When I Select "SCHEDULED BUNGIIS" from driver App menu
    And The "SCHEDULED BUNGIIS" page is opened
    Then I should be able to see data on "SCHEDULED BUNGIIS" page
    When I Select "AVAILABLE BUNGIIS" from driver App menu
    And The "AVAILABLE BUNGIIS" page is opened
    Then I should be able to see data on "AVAILABLE BUNGIIS" page
    When I Select "EARNINGS" from driver App menu
    And The "EARNINGS" page is opened
    Then I should be able to see data on "EARNINGS" page
    When I Select "ACCOUNT" from driver App menu
    And The "ACCOUNT" page is opened
    When I Select "ACCOUNT INFO" from ACCOUNT menu
    Then I should be able to see data on "ACCOUNT INFO" page
    Then I click on "Navigate Back" button on the "ACCOUNT INFO" page
    And I Select "ALERT SETTINGS" from ACCOUNT menu
    And The "ALERT SETTINGS" page is opened
    Then I should be able to see data on "ALERT SETTINGS" page
    Then I click on "Navigate Back" button on the "ALERT SETTINGS" page
    And I Select "PRIVACY POLICY" from ACCOUNT menu
    And The "PRIVACY POLICY" page is opened
    Then I should be able to see data on "PRIVACY POLICY" page
    Then I click on "Navigate Back" button on the "PRIVACY POLICY" page
    When I Select "BUNGII STORE" from driver App menu
    And The "STORE" page is opened
    Then I should be able to see data on "BUNGII STORE" page
    When I Select "ACCOUNT" from driver App menu
    And The "ACCOUNT" page is opened
    When I Select "LOGOUT" from ACCOUNT menu
    Then I should be able to see data on "LOGOUT" page
  
  @regression
    #stable
  Scenario: Verify Driver Menus - FAQ
    When I Select "FAQ" from driver App menu
    And The "FAQ" page is opened
    And I wait for "2" mins
    Then I should be able to see data on "FAQ" page
    When I Select "ACCOUNT" from driver App menu
    And The "ACCOUNT" page is opened
    When I Select "LOGOUT" from ACCOUNT menu
    Then I should be able to see data on "LOGOUT" page

  @ready
  #stable
  Scenario: Verify Driver Can Access Trip Histoy Page Upon Clicking Itemised Earnings Hyperlink
    When I Select "EARNINGS" from driver App menu
    And The "EARNINGS" page is opened
    Then I should be able to see data on "EARNINGS" page
    When I click on "Itemized Earnings" button
    Then I am redirected to "Itemized Earnings page"
  
      #failing due to BCKD-1103
  #@regression
  Scenario: Verify Device Token De-registration Upon Driver Logout
    Then Driver active flag should be "1"
    When I Select "ACCOUNT" from driver App menu
    And The "ACCOUNT" page is opened
    When I Select "LOGOUT" from ACCOUNT menu
    #When I Select "LOGOUT" from driver App menu
    Then I should be able to see data on "LOGOUT" page
    Then Driver active flag should be "0"