@ios
@scheduled
@DUO
@bungii
    # this will run in denver
  # Denver | Customer 8888889917, Valid denver - 9999993015
  # Denver | Driver Testdrivertywd_appledv_b_matt Stark_dvOnE -9999998086  , Testdrivertywd_appledv_b_seni Stark_dvThree -9955112208
  # Denver | Customer denver customer Valid denver & denver customer - 9999993015
  # Denver | Driver valid denver & denver driver 1 - 8888884321 | denver driver 2 - 9955112208
  
Feature: Scheduled DUO Bungii Cancellation in Denver Geofence
  I want to use request Scheduling Bungii with duo type
    #All Cases are stable in this feature

  Background:
    When I Switch to "customer" application on "same" devices

  @regression
    #stable
  Scenario:Verify Driver Cannot Cancel Scheduled Bungii From App When Bungii Is Not Started And He Should Send SMS To Cancel Duo Scheduled Bungii
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | denver   | Accepted     | NEXT_POSSIBLE | denver customer | denver driver 1 | denver driver 2 |
    
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I try to cancel selected Bungii
    Then user is alerted for "FOR EMERGENCY CONTACT SUPPORT LINE"
    #And correct details should be displayed to driver for "SMS FOR CANCEL INCASE OF EMERGENCEY"
    #sms number is shown as 12345 in browserstack and native apps cannot be opened so commented above step
    
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
  
  @regression
    #stable
  Scenario: Verify Control Driver Can Cancel Scheduled Duo Bungii From The App In The Enroute State - iOS Case
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | denver   | enroute      | NEXT_POSSIBLE | denver customer | denver driver 1 | denver driver 2 |
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    Then I should be navigated to "EN ROUTE" screen
    
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    Then I should be navigated to "EN ROUTE" screen
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "Driver Home" screen
    
    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen
  
  @regression
  Scenario: Verify Control Driver Can Cancel Scheduled Duo Bungii From The App In The Arrived State
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | denver   | arrived      | NEXT_POSSIBLE | denver customer | denver driver 1 | denver driver 2 |
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    Then I should be navigated to "ARRIVED" screen
    
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    Then I should be navigated to "ARRIVED" screen
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "Driver Home" screen
    
    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen
  
  @regression
  Scenario: Verify Non Control Driver Can Cancel Scheduled Duo Bungii From The App In The Enroute State
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | denver   | enroute      | NEXT_POSSIBLE | denver customer | denver driver 1 | denver driver 2 |
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    Then I should be navigated to "EN ROUTE" screen
    
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    #non control driver
    And I am logged in as "valid denver driver 2" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    Then I should be navigated to "EN ROUTE" screen
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "Driver Home" screen
    
    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen
  
  @regression
  Scenario: Verify Non Control Driver Can Cancel Scheduled Duo Bungii From The App In The Arrived State
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | denver   | arrived      | NEXT_POSSIBLE | denver customer | denver driver 1 | denver driver 2 |
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    Then I should be navigated to "ARRIVED" screen
    
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    #non control driver
    And I am logged in as "valid denver driver 2" driver
    Then I should be navigated to "ARRIVED" screen
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "Driver Home" screen
    
    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen
  
    
  
  @regression
    #stable
  Scenario:  Verify Customer Can Schedule Duo Bungii Only 5 Days Ahead Including Current Date
    Given I login as "valid denver" customer and on Home page
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | duo    | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time            | PickUpImage |
      | 30       |           |              | Today+1 1:00 PM | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    And I Switch to "customer" application on "same" devices
    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | duo    | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time            | PickUpImage |
      | 30       |           |              | Today+2 1:00 PM | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    And I Switch to "customer" application on "same" devices
    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | duo    | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time            | PickUpImage |
      | 30       |           |              | Today+3 1:00 PM | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    And I Switch to "customer" application on "same" devices
    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | duo    | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time            | PickUpImage |
      | 30       |           |              | Today+4 1:00 PM | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    And I Switch to "customer" application on "same" devices
    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | duo    | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I try to schedule bungii for "today+5"
    Then user is alerted for "SCHEDULED ONLY 5 DAYS"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |