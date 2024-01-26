@ios
@scheduled
@bungii
    # this will run in denver
Feature: Solo Scheduled Bungii Part C
  I want to use request Scheduling Bungii with Solo type

  Background:
    #When I clear all notification
    When I Switch to "customer" application on "same" devices

  @regression
 #stable
  Scenario: Verify Re-searched Trip Request Doesnt Show Urgent Notification Text If Is More Than One Hour From The Scheduled Trip Time in iOS
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | denver14   | Accepted     | 2 hour ahead |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appledv_b_mattV DenverV" driver
    Then I wait for "1" mins
    And I open Admin portal and navigate to "Scheduled Deliveries" page
    And I remove current driver and researches Bungii

    When I switch to "ORIGINAL" instance
    When I Switch to "customer" application on "same" devices
    And I should not get notification for "driver" for "URGENT SCHEDULED PICKUP AVAILABLE"
    And I should get virtual notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
  
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
    #stable
  Scenario: Verify Validation Message Shown If Driver Tries To Start A Bungii More Than 60 Mins Before The Scheduled Time
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | denver15   | Accepted     | 2 hour ahead |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appledv_b_mattW DenverW" driver
    
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii
    Then user is alerted for "60 MINS BEFORE SCHEDULE TRIP TIME"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: Verify Driver Is Not Allowed To Start Bungii Within 60 Mins Of Scheduled Time If Required Number Of Drivers Have Not Accepted The Trip
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_seni Stark_dvThree" and "Testdrivertywd_appledv_b_matt Stark_dvOnE" perform below action with respective "DUO SCHEDULED" trip
    #And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      |               |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9955112208 and  Password :Cci12345
    #And I enter phoneNumber :9999998086 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii
    Then user is alerted for "REQUIRED DRIVER NOT ACCEPTED"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
    #stable
  Scenario: Verify Driver Is Not Allowed To Start Bungii If The Customer Is Currently In An Ongoing Solo Scheduled Trip
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time     |
      | denver11   | Accepted     | 1 hour ahead |
    Given that ondemand bungii is in progress for the minimum distance chosen
      | geofence | Bungii State | Driver label | Trip Label |
      | denver11   | Enroute      | driver 2     | 2          |
    And I Switch to "driver" application on "same" devices
    And I wait for 2 minutes
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appledv_b_mattT DenverT" driver
    And I wait for 2 minutes
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii
    Then user is alerted for "CUSTOMER HAS ONGOING BUNGII"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @ready
    #stable
  Scenario:  Verify If Control Driver Is Allowed To Complete The Trip And Proper Summary Detail Is Shown
    Given that duo schedule bungii is in progress
      | geofence | Bungii State    | Bungii Time   | Customer        | Driver1         | Driver2         |
      | denver   | unloading items | NEXT_POSSIBLE | denver customer | denver driver 1 | denver driver 2 |
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct details for duo trip" on Bungii completed page
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "driver" application on "same" devices
    And I click "Skip This Step" button on "Rate customer" screen
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii completed" screen

  @regression
  Scenario: Verify If Non Control Driver Completes Trip Before Control Driver Then He Is Shown Waiting Screen Till The Control Driver Completes And The Correct Summary Is Shown Thereafter
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state  | driver2 state  |
      | Unloading Items | Unloading Items |
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9955112208 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I select "4" Ratting star for solo Driver 1
    And I click "Submit" button on "Rate duo teammate" screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then non control driver should see "waiting for other driver" screen
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "UNLOADING ITEMS" screen

    #control driver complete bungii
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" perform below action with respective "Duo Scheduled" trip
      | driver1 state    |
      | Bungii Completed |

    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct details for duo trip" on Bungii completed page
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "driver" application on "same" devices
    #And I click "Skip This Step" button on "Rate customer" screen
    #Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii completed" screen

  @regression
  #stable
  Scenario: Verify If Re-searched Driver Can Cancel Trip After Starting Solo Scheduled Bungii
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | denver16   | Accepted     | 0.5 hour ahead |
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I wait for 2 minutes
    And I Select "Scheduled Trip" from admin sidebar

    And I remove current driver and researches Bungii
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" perform below action with respective "Solo Scheduled" trip
      | driver1 state |
      | Accepted      |
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9049840394 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "SCHEDULED BUNGII" screen

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario:Verify Driver Cannot Cancel Scheduled Bungii From App When Bungii Is Not Started And He Should Send SMS To Cancel Solo Scheduled Bungii
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver17   | Accepted     | NEXT_POSSIBLE |
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appledv_b_mattY DenverY" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I try to cancel selected Bungii
    Then user is alerted for "FOR EMERGENCY CONTACT SUPPORT LINE"
    And correct details should be displayed to driver for "SMS FOR CANCEL INCASE OF EMERGENCEY"

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @regression
    #stable
  Scenario: Verify Customer Can Request Cancel Solo Scheduled Bungii Through SMS To Admin If No Driver Accepts And Processing Gets Over
    Given that solo schedule bungii is in progress
      | geofence  | Bungii State | Bungii Time   |
      | denver9   | Scheduled    | NEXT_POSSIBLE |

    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver9" user
    When I Switch to "customer" application on "same" devices
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
    When I Switch to "customer" application on "same" devices
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    When I Cancel selected Bungii
    Then correct support details should be displayed to customer on "ADMIN-SMS" app
    And I open Admin portal and navigate to "Scheduled Deliveries" page

    And I Cancel Bungii with following details
      | Charge | Comments |
      | 0      | TEST     |
    And Bungii must be removed from the List
    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I Select "MY BUNGIIS" from Customer App menu
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen

  @regression
    #stable
  Scenario: Verify Customer Can Request Cancel Scheduled Trip Via Admin SMS After 2 Hour (15 mins in QA Auto) Processing Is Over
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8877661120     | Testcustomertywd_appleMarkDQ LutherDQ | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_mattM DenverM" and "Testdrivertywd_appledv_b_mattL DenverL" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    When I enter Username :8877661120 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "MY BUNGIIS" from Customer App menu
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
    Then I wait for "2" mins
    And I select already scheduled bungii
    When I Cancel selected Bungii
    Then correct support details should be displayed to customer on "ADMIN-SMS" app

    And I open Admin portal and navigate to "Scheduled Deliveries" page

    And I Cancel Bungii with following details
      | Charge | Comments |
      | 0      | TEST     |
    And Bungii must be removed from the List
    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I Select "MY BUNGIIS" from Customer App menu
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen