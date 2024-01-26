@ios
@scheduled
@bungii
    # this will run in denver
Feature: Solo Scheduled Bungii Part A
  I want to use request Scheduling Bungii with Solo type

  Background:
    #When I clear all notification
    When I Switch to "customer" application on "same" devices

  @ready
  @sanity
  Scenario: Verify Solo Schedule Bungii Till Completion
    
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Accepted     | NEXT_POSSIBLE |
    
    Given I login as "valid denver" customer and on Home page
    When I Switch to "driver" application on "same" devices
    And I login as "valid denver" driver on "same" device and make driver status as "Online"
    
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    When I wait for Minimum duration for Bungii Start Time
    
    And I start selected Bungii
    #Then I should be navigated to "EN ROUTE" trip status screen
    Then I should be navigated to "EN ROUTE TO PICKUP" trip status screen on driver
    And Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
    
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "EN ROUTE" trip status screen
    And Trip Information should be correctly displayed on "EN ROUTE" status screen for customer
    
    When I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE TO PICKUP" Screen

    #Then I should be navigated to "ARRIVED" trip status screen
    Then I should be navigated to "ARRIVED AT PICKUP" trip status screen on driver
    And Trip Information should be correctly displayed on "ARRIVED" status screen for driver
    
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "ARRIVED" trip status screen
    And Trip Information should be correctly displayed on "ARRIVED" status screen for customer
    
    When I Switch to "driver" application on "same" devices
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
    #Then I should be navigated to "LOADING ITEMS" trip status screen
    Then I should be navigated to "LOADING ITEMS AT PICKUP" trip status screen on driver
    And Trip Information should be correctly displayed on "LOADING ITEMS" status screen for driver
    
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "LOADING ITEMS" trip status screen
    And Trip Information should be correctly displayed on "LOADING ITEMS" status screen for customer
    
    When I Switch to "driver" application on "same" devices
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    #Then I should be navigated to "DRIVING TO DROP-OFF" trip status screen
    Then I should be navigated to "DRIVING TO DROP-OFF" trip status screen on driver
    And Trip Information should be correctly displayed on "DRIVING TO DROP-OFF" status screen for driver
    
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "DRIVING TO DROP-OFF" trip status screen
    And Trip Information should be correctly displayed on "DRIVING TO DROP-OFF" status screen for customer
    
    When I Switch to "driver" application on "same" devices
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    #Then I should be navigated to "UNLOADING ITEMS" trip status screen
    Then I should be navigated to "UNLOADING ITEMS AT DROP-OFF" trip status screen on driver
    And Trip Information should be correctly displayed on "UNLOADING ITEMS" status screen for driver
    
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "UNLOADING ITEMS" trip status screen
    And Trip Information should be correctly displayed on "UNLOADING ITEMS" status screen for customer
    
    When I Switch to "driver" application on "same" devices
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    And I click "On To The Next One" button on "Bungii completed" screen
    
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct details" on Bungii completed page
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

    
    #When I Switch to "driver" application on "same" devices
    #Then Bungii driver should see "correct details" on Bungii completed page


  @ready
  Scenario: Verify Details Of Solo Schedule Bungii

    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Accepted     | NEXT_POSSIBLE |
    
    Given I login as "valid denver" customer and on Home page
    When I Switch to "driver" application on "same" devices
    And I login as "valid denver" driver on "same" device and make driver status as "Online"

    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    When I wait for Minimum duration for Bungii Start Time

    And I start selected Bungii
    #Then I should be navigated to "EN ROUTE" trip status screen
    Then I should be navigated to "EN ROUTE TO PICKUP" trip status screen on driver
    And Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
    And correct details should be displayed to driver on "SMS" app
    And correct details should be displayed to driver on "Call" app
    And correct details should be displayed to driver for "SMS FOR SUPPORT"
    And correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "EN ROUTE" trip status screen
    And Trip Information should be correctly displayed on "EN ROUTE" status screen for customer
    And correct details should be displayed to customer on "SMS" app
    And correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE TO PICKUP" Screen
 #   When I verify and slide update button on "EN ROUTE" Screen

    #Then I should be navigated to "ARRIVED" trip status screen
    Then I should be navigated to "ARRIVED AT PICKUP" trip status screen on driver
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
    Then I should be navigated to "LOADING ITEMS AT PICKUP" trip status screen
    And Trip Information should be correctly displayed on "LOADING ITEMS" status screen for driver
    And correct details should be displayed to driver on "SMS" app
    And correct details should be displayed to driver on "Call" app
    And correct details should be displayed to driver for "SMS FOR SUPPORT"
    And correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "LOADING ITEMS" trip status screen
    And Trip Information should be correctly displayed on "LOADING ITEMS" status screen for customer
    And correct details should be displayed to customer on "SMS" app
    And correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    #Then I should be navigated to "DRIVING TO DROP-OFF" trip status screen
    Then I should be navigated to "DRIVING TO DROP-OFF" trip status screen on driver
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    #Then I should be navigated to "UNLOADING ITEMS" trip status screen
    Then I should be navigated to "UNLOADING ITEMS AT DROP-OFF" trip status screen on driver
    And Trip Information should be correctly displayed on "UNLOADING ITEMS" status screen for driver
    And correct details should be displayed to driver on "SMS" app
    And correct details should be displayed to driver on "Call" app
    And correct details should be displayed to driver for "SMS FOR SUPPORT"
    And correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "UNLOADING ITEMS" trip status screen
    And Trip Information should be correctly displayed on "UNLOADING ITEMS" status screen for customer
    And correct details should be displayed to customer on "SMS" app
    And correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    #Then I should be navigated to "Bungii Completed" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct details" on Bungii completed page
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "driver" application on "same" devices
    And I click "Skip This Step" button on "Rate customer" screen
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii completed" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @ready
  Scenario: Verify Customer Can Create And Complete Schedule Solo Bungii
    Given I login as "valid denver" customer and on Home page
    When I Switch to "driver" application on "same" devices
    And I login as "valid denver" driver on "same" device and make driver status as "Online"
    And I Switch to "customer" application on "ORIGINAL" devices
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |

    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage |
      | 30       |           |              | 30_MIN_AHEAD | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    Then I Select "Home" from Customer App menu
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    Then I should be navigated to "BUNGII DETAILS" screen
    And Driver Bungii Information should be correctly displayed on BUNGII DETAILS screen

    When I accept selected Bungii
    #CORE-4581: Confirmation of acceptance of delivery in driver's app
    Then I should see "Delivery Accepted" popup displayed
    And I click "OK" button on alert message
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    When I wait for Minimum duration for Bungii Start Time
    And I start selected Bungii
    #Then I should be navigated to "EN ROUTE" trip status screen
    Then I should be navigated to "EN ROUTE TO PICKUP" trip status screen on driver
    When I slide update button on "EN ROUTE TO PICKUP" Screen
    #Then I should be navigated to "ARRIVED" trip status screen
    Then I should be navigated to "ARRIVED AT PICKUP" trip status screen on driver
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "ARRIVED" trip status screen
    When I Switch to "driver" application on "same" devices
    When I slide update button on "ARRIVED AT PICKUP" Screen
    And Driver adds photos to the Bungii
    When I slide update button on "ARRIVED AT PICKUP" Screen
    #Then I should be navigated to "LOADING ITEM" trip status screen
    Then I should be navigated to "LOADING ITEMS AT PICKUP" trip status screen on driver
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "LOADING ITEMS" trip status screen
    When I Switch to "driver" application on "same" devices
    When I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And Driver adds photos to the Bungii
    When I slide update button on "LOADING ITEMS AT PICKUP" Screen
    #Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
    Then I should be navigated to "DRIVING TO DROP-OFF" trip status screen on driver
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "DRIVING TO DROP-OFF" trip status screen
    When I Switch to "driver" application on "same" devices
    When I slide update button on "DRIVING TO DROP-OFF" Screen
    #Then I should be navigated to "UNLOADING ITEM" trip status screen
    Then I should be navigated to "UNLOADING ITEMS AT DROP-OFF" trip status screen on driver
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "UNLOADING ITEMS" trip status screen
    When I Switch to "driver" application on "same" devices
    When I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    When I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    And I click "On To The Next One" button on "Bungii completed" screen
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    When I rate Bungii Driver  with following details and Press "OK" Button
      | Ratting | Tip |
      | 5       | 5   |
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @ready
    #stable
  Scenario: Verify Customer Cannot Schedule Bungii At Same Time As That Of Already Scheduled Bungii
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Scheduled    | NEXT_POSSIBLE |

    Given I login as "valid denver" customer and on Home page
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I try to confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time              | PickUpImage | Save Trip Info |
      | 30       |           |              | <OLD BUNGII TIME> | Default     | No             |
    Then user is alerted for "already scheduled bungii"
    When I click "Cancel" button on "Estimate" screen
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    Then I Cancel selected Bungii
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @regression
    #Added case of CORE-3685 to exsting script
    #stable
  Scenario: Verify Customer Can Cancel Solo Scheduled Bungii on Bungii Details screen
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver1   | Scheduled    | NEXT_POSSIBLE |
    Given I login as "valid denver1" customer and on Home page
    When I Select "ACCOUNT > ACCOUNT INFO" from Customer App menu
    Then I should be navigated to "ACCOUNT INFO" screen
    And I click "Delete account" button on "ACCOUNT INFO" screen
    And I enter "valid" password and click on delete button
    Then I should see "Account can't be deleted due to pending deliveries" message
    And I click "Cancel" button on "Delete Account" screen
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    Then Trip Information should be correctly displayed on BUNGII DETAILS screen
    When I Cancel selected Bungii
    And Bungii must be removed from "SCHEDULED BUNGIIS" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @regression
  Scenario: Verify When Admin Cancels Bungii Then Trip Is Removed From The Scheduled Trip In App
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver17   | Scheduled    | NEXT_POSSIBLE |
    #When I am on the "LOG IN" page
    #And I logged in Customer application using  "valid denver" user
    Given I login as "valid denver" customer and on Home page
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii

    And I open Admin portal and navigate to "Scheduled Deliveries" page
    And I Cancel Bungii with following details
      | Charge | Comments |
      | 0      | TEST     |
    And Bungii must be removed from the List
    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I Select "MY BUNGIIS" from Customer App menu
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @regression
  #stable
  Scenario: Verify Status Of Scheduled Solo Bungii Trip In Drivers Scheduled Bungiis Menu Screen When Required Number Of Drivers Have Not Accepted It
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver2   | Scheduled    | NEXT_POSSIBLE |
    #When I am on the "LOG IN" page
    #And I logged in Customer application using  "valid denver" user
    Given I login as "valid denver2" customer and on Home page
    And I Select "MY BUNGIIS" from Customer App menu
    Then trips status should be "Contacting Drivers"
    And I select already scheduled bungii
    Then trips status on bungii details should be "driver 1 - contacting drivers"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @regression
  #Stable
  Scenario:  Verify Customer Can Schedule Solo Bungii Only 5 Days Ahead Including Current Date
    Given I login as "valid denver" customer and on Home page
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
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
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
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
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
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
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
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
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I try to schedule bungii for "today+5"
    Then user is alerted for "SCHEDULED ONLY 5 DAYS"
    #Cancellation not needed
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
#stable
  Scenario: Verify Alert Message Is Displayed When Customer Tries To Contact Driver Who Has Bungii In Progress
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time     |
      | denver16   | Accepted     | 1 hour ahead |
     And I wait for 2 minutes
    And I Switch to "driver" application on "same" devices
    And I login as "valid denver" driver on "same" device and make driver status as "Online"
     And I wait for 2 minutes
    When I request "Solo Ondemand" Bungii as a customer in "denver" geofence with minimum possible distance
      | Bungii Time | Customer Phone | Customer Password | Customer Name                      | Customer label |
      | now         | 8888889917      | Cci12345          | Testcustomertywd_appleZTDafc Stark | 2              |
    And I view and accept virtual notification for "Driver" for "on demand trip"
    And I wait for 2 minutes
    And I Switch to "customer" application on "same" devices
    Given I login as "valid denver" customer and on Home page
    And I Select "MY BUNGIIS" from Customer App menu
    And I select 1st trip from scheduled bungii
    When I wait for 1 hour for Bungii Schedule Time
    When I try to contact driver using "call driver1"
    Then user is alerted for "driver finishing current bungii"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8888889917      |

  @ready
  
  Scenario: Verify If Customer Receives Notification Once Required Number Of Drivers Accepts Solo Scheduled Bungii
    When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    When I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
          #above step to register token for push notification
    And I Switch to "driver" application on "same" devices
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And I click on notification for "Customer" for "SCHEDULED PICKUP ACCEPTED"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

#   Core-3098 Verify online/Offline pop up is shown when Driver has schedule trip accepted for future days
  @regression
  Scenario:  Verify online/Offline pop up is shown when Driver has schedule trip accepted for future days
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver

    When I Switch to "customer" application on "same" devices
    And I login as "valid denver" customer and on Home page
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time            | PickUpImage |
      | 30       |           |              | Today+1 1:00 PM | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen

    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      And I click "ACCEPT" button on "Bungii Request" screen

      When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
        | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
        | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |

      And As a driver "valid denver" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state |
        |Accepted |
        | Enroute  |
        | Arrived |
        | Loading Item |
        | Driving To Dropoff |
        | Unloading Item |

      When I Switch to "driver" application on "same" devices
      And I am logged in as "valid denver" driver
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
      And Driver adds photos to the Bungii
      And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
      And I click "Skip This Step" button on "Rate customer" screen
      Then I should be navigated to "Bungii Completed" screen
      When I click "On To The Next One" button on "Bungii completed" screen
      Then I check online or offline pop up is displayed
      Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8888889917      |

#CORE-2753 : To verify that driver can successfully accept incoming Scheduled trip request during ongoing trip
#Sprint-58==CORE-3396 changes incorporated
  @ready
  Scenario:To verify that driver can successfully accept incoming Scheduled trip request during ongoing trip
    When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_mattB Stark_dvOnEB" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And I wait for 1 minutes
    And As a driver "Testdrivertywd_appledv_b_mattB Stark_dvOnEB" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Enroute       |
    And I get TELET time of of the current trip
    And I Switch to "driver" application on "same" devices
    And I am logged in as "valid denver driver 3" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Switch to "customer" application on "same" devices
    And I logged in Customer application using  "valid denver5" user
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time                 | PickUpImage |
      | 30       |           |              | <1 HOUR AFTER TELET>   | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    And I get pickupref for "valid denver5" customer
    And I Switch to "driver" application on "same" devices
    And I wait for 2 minutes
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    Then I should be navigated to "ARRIVED AT PICKUP" trip status screen on driver
    And I view and accept virtual notification for "Driver" for "SCHEDULED PICKUP2 AVAILABLE"
    And I click "Scheduled Bungiis" button on "update" screen
    And I should select the "valid denver5" customer on driver app
    Then Start button should not be shown
    And I click on "BACK" button
    And I click on "BACK" button
    Then I should be navigated to "ARRIVED AT PICKUP" trip status screen on driver
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    When I click "On To The Next One" button on "Bungii completed" screen
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    Then The trip should be present in schedule delivery


 #CORE-2342 :To verify whether new pickup instructions are displayed to driver when he receive the Bungii request notification for Distribution center
 @ready
  Scenario Outline:To verify whether new pickup instructions are displayed to driver when he receive the Bungii request notification for Distribution center
    When I Switch to "driver" application on "same" devices
    And I am logged in as "valid baltimore driver 6" driver
    And I set the pickup address for "<Delivery Center>"
    When I request Partner Portal "SOLO" Trip for "BestBuy2 service level" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |baltimore| NEXT_POSSIBLE | <Customer Phone> | <Customer Name>|
    And I wait for 1 minutes
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    Then I should see service level information displayed for "<Delivery Center>" address
    When I accept selected Bungii
    #CORE-4581: Confirmation of acceptance of delivery in driver's app
    Then I should see "Delivery Accepted" popup displayed
    And I click "OK" button on alert message
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then The service level information should be displayed
    And I click on start Bungii for service based delivery
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    And I wait for 2 minutes
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I open the trip for "<Customer Name>" the customer
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then The delivery details on "Live" deliveries should have proper pickup "<Delivery Center>" location and service level instructions displayed

   Examples:
     |        Customer Name                    |    Customer Phone  |   Delivery Center     |
     |  Testcustomertywd_BppleMarkCI LutherCI  |     8877661086     |     Store             |
     |  Testcustomertywd_BppleMarkCJ LutherCJ  |     8877661087     |   Warehouse           |


 #CORE-3381 :To verify that admin is unable to revive trips canceled by customer from app
  @ready
  Scenario:To verify that admin is unable to revive trips canceled by customer from app
    When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8877661054     |  Testcustomertywd_BppleMarkBC LutherBC| Cci12345          |
    Given I login as "valid denver6" customer and on Home page
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    Then I Cancel selected Bungii
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I wait for "2" mins
    And I Select "trips" from admin sidebar
    When  I search the delivery using "Pickup Reference"
    Then The revive button should not be displayed

 #CORE-3381 :To verify that admin/partner canceled revived deliveries are not displayed to driver on app
  @ready
  Scenario:To verify that admin/partner canceled revived deliveries are not displayed to driver on app
    When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8877661055     |  Testcustomertywd_appleMarkBD LutherBD | Cci12345        |
    And I wait for "2" mins
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkBD LutherBD" the customer
    And I Select "Cancel Trip" option
    And I enter cancellation fee and Comments
    And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
    And I click on "Cancel Bungii" button
    Then The "Pick up has been successfully canceled." message should be displayed
    And I wait for "2" mins
    And I Select "trips" from admin sidebar
    When  I search the delivery using "Pickup Reference"
    And Revive button should be displayed beside the trip
    When I click on "Revive" button
    Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
    When I click on "Confirm" button on Revival Popup
    And I wait for 2 minutes
    When I switch to "ORIGINAL" instance
   And I Switch to "driver" application on "same" devices
    And I login as "valid denver driver 6" driver on "same" device and make driver status as "Online"
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    Then The trip should not be present in available bungiis
    And I Switch to "customer" application on "same" devices
    And I login as "valid denver7" customer and on Home page
    And I Select "MY BUNGIIS" from Customer App menu
    Then The trip should be present in my bungiis

  #CORE-3417:Verify driver instructions at pickup and drop off is in Markdown format
  @ready
  Scenario:Verify driver instructions at pickup and drop off is in Markdown format
    When I request Partner Portal "SOLO" Trip for "Floor and Decor" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |washingtondc| NEXT_POSSIBLE | 8877661114 | Testcustomertywd_appleMarkDK LutherDK|
    And I wait for 2 minutes
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And  I search the delivery using "Pickup Reference"
    When I click on the "Edit" button from the dropdown
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appledc_a_drvY WashingtonY" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed
    When I click on "CLOSE" button

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appledc_a_drvY WashingtonY" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    And I Select "SCHEDULED BUNGIIS" from driver App menu
    #CORE-2718:Driver was assigned to delivery and could not see it in app issue
    Then I should able to see "one" scheduled trip
    And I Select Trip from scheduled trip
    And I click on start Bungii for service based delivery
    And I swipe to check trip details
    #CORE-3585:To verify Call and Text options in Stops sections for Pickup and Drop-off point on Bungii delivery details page for driver app
    Then The "Delivery Details" "Header" should be displayed
    Then The "Call" "Icon" should be displayed
    Then The "Stops" "Text" should be displayed
    Then The "Pickup" "Icon" should be displayed
    Then The "Dropoff" "Icon" should be displayed
    And I click on "Close" button
    Then The "Delivery Instructions" "Icon" should be displayed
    Then The "Item Details" "Icon" should be displayed
    Then The "Bungii Support" "Icon" should be displayed
    Then The "More Options" "Icon" should be displayed
   #CORE-3585:To verify that driver is able to successfully swipe through all in progress Bungii states
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    And I slide update button on "ARRIVED AT PICKUP" Screen
    Then I should see "Pickup Instructions" popup displayed
    Then The driver "Pickup" instructions should be in markdown format
    And I click on "GOT IT" button
    Then The "Delivery Instructions" "Icon" should be displayed
    Then The "Item Details" "Icon" should be displayed
    Then The "Bungii Support" "Icon" should be displayed
    Then The "More Options" "Icon" should be displayed
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And  Bungii driver should see "Photo Verification"
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
    Then The "Delivery Instructions" "Icon" should be displayed
    Then The "Item Details" "Icon" should be displayed
    Then The "Bungii Support" "Icon" should be displayed
    Then The "More Options" "Icon" should be displayed
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And Bungii driver should see "Photo Verification"
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    Then The "Delivery Instructions" "Icon" should be displayed
    Then The "Item Details" "Icon" should be displayed
    Then The "Bungii Support" "Icon" should be displayed
    Then The "More Options" "Icon" should be displayed
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    Then I should see "Drop-Off Instructions" popup displayed
    Then The driver "Dropoff" instructions should be in markdown format
    And I click on "GOT IT" button
    Then The "Delivery Instructions" "Icon" should be displayed
    Then The "Item Details" "Icon" should be displayed
    Then The "Bungii Support" "Icon" should be displayed
    Then The "More Options" "Icon" should be displayed
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Bungii driver should see "Photo Verification"
    And Driver adds photos to the Bungii
    Then The "Delivery Instructions" "Icon" should be displayed
    Then The "Item Details" "Icon" should be displayed
    Then The "Bungii Support" "Icon" should be displayed
    Then The "More Options" "Icon" should be displayed
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I select "4" customer rating
    And I add comment on rate customer page
    When I click "On To The Next One" button on "Bungii completed" screen
    And I should be navigated to "Bungii Completed" screen

#   Core-3412: Verify Photo Verification screens are shown on driver app for Customer trip when driver has accepted stacked trip
  @ready
  Scenario: Verify Photo Verification screens are shown on driver app for Customer trip when driver has accepted stacked trip
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | goa      | enroute      | 0.5 hour ahead |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |

    When I Switch to "driver" application on "same" devices
    And I view and accept virtual notification for "Driver" for "stack trip"
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And I check if "Save" button is "disabled"
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And I wait for "2" mins
#   Core-3412: Verify uploaded photos are shown on admin portal
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I wait for "2" mins
    And I Select "live trips" from admin sidebar
    And I select the live trip for "Vishal Bagi" customer for delivery details
    Then The "Photos" "on admin portal" should be displayed