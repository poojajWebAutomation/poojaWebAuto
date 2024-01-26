@android
Feature: Trip Alert Settings Menu
  In Bungii Driver
  As a logged in driver
  I want to check Trip Alert Settings
  
  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    

  @regression
   #stable
  Scenario Outline: Verify Trip Alert Settings On Trip Alerts Tab
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    When I Select "ACCOUNT" from driver App menu
    And The "ACCOUNT" page is opened
    And I Select "ALERT SETTINGS" from ACCOUNT menu
    #And I Select "ALERT SETTINGS" from driver App menu
    And The "ALERT SETTINGS" page is opened
    And I click on "Delivery Alerts" tab
    Then I should be able to see "Delivery Alerts" Text and Time
    Then I click on "Navigate Back" button on the "ALERT SETTINGS" page
    #And I Select "LOGOUT" from driver App menu
    When I Select "ACCOUNT" from driver App menu
    And The "ACCOUNT" page is opened
    When I Select "LOGOUT" from ACCOUNT menu
    
    Examples:
      | Username   | Password   |
      | 8888882020 | Cci12345   |

  #@regression
  @regression
  Scenario Outline: Verify Trip Alert Settings On SMS Alerts Tab [Default7.00AM-9.00PM]
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    When I Select "ACCOUNT" from driver App menu
    And The "ACCOUNT" page is opened
    And I Select "ALERT SETTINGS" from ACCOUNT menu
    #And I Select "ALERT SETTINGS" from driver App menu
    And The "ALERT SETTINGS" page is opened
    And I click on "SMS Alerts" tab
    Then I should be able to see "SMS Alerts" Text and Time
    Then I click on "Navigate Back" button on the "ALERT SETTINGS" page
    #And I Select "LOGOUT" from driver App menu
    When I Select "ACCOUNT" from driver App menu
    And The "ACCOUNT" page is opened
    When I Select "LOGOUT" from ACCOUNT menu
  
    Examples:
      | Username   | Password   |
      | 8989890909 | Cci12345   |
  
  @regression
    Scenario Outline: Verify Correct Data Is Displayed In Trip And Sms Alert Settings Upon Switching Between Trip And SMS Alerts Tabs
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    When I Select "ACCOUNT" from driver App menu
    And The "ACCOUNT" page is opened
    And I Select "ALERT SETTINGS" from ACCOUNT menu
    #And I Select "ALERT SETTINGS" from driver App menu
    And The "ALERT SETTINGS" page is opened
      And I click on "Delivery Alerts" tab
      And I click on time and change "From" time
      And I click on "SAVE TIME" button
      And I click on "SMS Alerts" tab
      And I click on "Delivery Alerts" tab
      Then I verify that changes in time are saved
      Examples:
        | Username   | Password   |
        | 8888881001 | Cci12345   |

    @ready
      #CORE-2965 Sprint 47: Total earning on stats screen with default dropdown are incorrect
    Scenario: Verify Driver Can Access Total Earnings Upon Clicking Earnings Page
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 8877661181     | Testcustomertywd_BppleMarkFZ LutherFZ|
    And As a driver "Testdrivertywd_appledc_a_drvag Washingtonag" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute       |
      | Arrived       |
      | Loading Item   |
      | Driving To Dropoff |
      | Unloading Item     |
      | Bungii Completed     |

    #login to driver
      And I am logged in as "Testdrivertywd_appledc_a_drvag Washingtonag" driver
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      When I Select "EARNINGS" from driver App menu
      And The "EARNINGS" page is opened
      Then I should be able to see data on "EARNINGS" page
      And I click on "year" dropdown
      Then I click on "first value" dropdown
      And I click on "year" dropdown
      Then I click on "second value" dropdown
      And I click on "year" dropdown
      Then I click on "third value" dropdown
      Then I check "total earnings"

 #CORE-4749: The 'i' icon should be displayed on itemized earning screen on android device when delivery is completed with same day configuration
  @ready
  Scenario:The 'i' icon should be displayed on itemized earning screen on android device when delivery is completed with same day configuration
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 8877661181     | Testcustomertywd_BppleMarkFZ LutherFZ|
    And As a driver "Testdrivertywd_appledc_a_drvap Washingtonap" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute       |
      | Arrived       |
      | Loading Item   |
      | Driving To Dropoff |
      | Unloading Item     |
      | Bungii Completed     |
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_appledc_a_drvap Washingtonap" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    When I Select "EARNINGS" from driver App menu
    When I click on "Itemized Earnings" button
  #CORE-4749:The 'i' icon should be displayed on itemized earning screen on android device when delivery is completed with same day configuration
    Then The "i" icon should be displayed for the completed delivery